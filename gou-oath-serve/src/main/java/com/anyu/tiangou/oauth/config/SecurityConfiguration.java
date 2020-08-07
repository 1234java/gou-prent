package com.anyu.tiangou.oauth.config;

import com.anyu.tiangou.oauth.granter.UserDetailsServiceImpl;
import com.anyu.tiangou.oauth.tuils.MyPasswordEncoder;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

/**
 * @author shkstart Administrator
 * @create 2020-08-03 10:11
 */
@Configuration
@EnableWebSecurity
@Order(-1)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Bean
    public PasswordEncoder passwordEncoder() {
        //return new BCryptPasswordEncoder();
        return new MyPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                // 匹配oauth相关，匹配健康，匹配默认登录登出 在httpSecurity处理，，其他到ResourceServerConfigurerAdapter OAuth2处理  1
                .requestMatchers().antMatchers( "/actuator/health", "/client/login","/client/getCode")
                .and()
                // 匹配的全部无条件通过 permitAll 2
                .authorizeRequests().antMatchers( "/actuator/health", "/client/login","/client/getCode").permitAll()
                // 匹配条件1的 并且不再条件2通过范围内的其他url全部需要验证登录
                .and().authorizeRequests().anyRequest().authenticated()
                // 启用登录验证
                .and().formLogin().permitAll();

        // 不启用 跨站请求伪造 默认为启用， 需要启用的话得在form表单中生成一个_csrf
        http.csrf().disable();



    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsServiceImpl).passwordEncoder(passwordEncoder());
    }

    /**
     * 不定义没有password grant_type
     *
     * @return
     * @throws Exception
     */
    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    /***
     * 忽略安全拦截的URL
     * @param web
     * @throws Exception
     */
    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring().antMatchers(
                "sms/**");
    }
}
