package com.anyu.tiangou.oauthserve.config;

import com.anyu.tiangou.oauthserve.service.impl.SmsDetailsServiceImpl;
import com.anyu.tiangou.oauthserve.service.impl.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 10:13
 */
@Configuration
@EnableWebSecurity
@Order(-1)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


@Resource
private SmsCodeSecurityConfig  smsCodeSecurityConfig;



    @Bean
    public RestTemplate restTemplate(){
        return  new RestTemplate();
    }
    /***
            * 忽略安全拦截的URL
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "/oauth/login",
                "/oauth/logout","/user/login");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable() //禁用跨站csrf攻击防御，后面的章节会专门讲解
                .formLogin()
//                .loginPage("/login.html")//用户未登录时，访问任何资源都转跳到该路径，即登录页面
                .loginProcessingUrl("/login")//登录表单form中action的地址，也就是处理认证请求的路径
                .usernameParameter("username")///登录表单form中用户名输入框input的name名，不修改的话默认是username
                .passwordParameter("password")//form中密码输入框input的name名，不修改的话默认是password
                .defaultSuccessUrl("/index")//登录认证成功后默认转跳的路径
                .and()
                .apply(smsCodeSecurityConfig)
                .and()
                .authorizeRequests()
                .antMatchers("/login.html","/login","/smslogin","oauth/**").permitAll()//不需要通过登录验证就可以被访问的资源路径
                .anyRequest().authenticated();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new MyPasswordEncoder();
    }

    @Autowired
    public void globalUserDetails(AuthenticationManagerBuilder auth) throws Exception {
        // 配置用户来源于数据库
        // 配置密码加密方式  BCryptPasswordEncoder，添加用户加密的时候请也用这个加密
        auth.userDetailsService(userDetailsServices()).passwordEncoder(passwordEncoder());
//        auth.userDetailsService(smsDetailsService());
    }

    @Primary
    @Bean
    public  UserDetailsServiceImpl userDetailsServices(){
        return new UserDetailsServiceImpl();
    }

    @Primary
    @Bean
    public  SmsDetailsServiceImpl smsDetailsService(){
        return new SmsDetailsServiceImpl();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {

        // 这里是添加两个用户到内存中去，实际中是从#下面去通过数据库判断用户是否存在
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();

        BCryptPasswordEncoder passwordEncode = new BCryptPasswordEncoder();
        String pwd = passwordEncode.encode("123456");
        manager.createUser(User.withUsername("user_1").password(passwordEncoder().encode("123456")).authorities("USER").build());
        manager.createUser(User.withUsername("user_2").password(passwordEncoder().encode("123456")).authorities("USER").build());
        return manager;

    }


    //    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
//    }
    @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
    @Override
    @Primary
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
