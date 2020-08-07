package com.anyu.tiangou.oauthserve.service.impl;

import com.anyu.tiangou.oauthserve.config.MyPasswordEncoder;
import com.anyu.tiangou.oauthserve.feign.UserServiceFeign;
import com.anyu.tiangou.oauthserve.integration.IntegrationAuthentication;
import com.anyu.tiangou.oauthserve.integration.IntegrationAuthenticationContext;
import com.anyu.tiangou.oauthserve.integration.SysUserAuthentication;
import com.anyu.tiangou.oauthserve.integration.authenticator.IntegrationAuthenticator;
import com.anyu.tiangou.oauthserve.tuils.UserJwt;
import com.anyu.tiangou.user.mdoel.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.provider.ClientDetails;
import org.springframework.security.oauth2.provider.ClientDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.thymeleaf.spring5.context.SpringContextUtils;

import javax.annotation.Resource;
import java.util.List;

/*******
        * 自定义授权认证类
        */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

//    @Resource
//   private ClientDetailsService clientDetailsService;

    @Autowired
    private UserServiceFeign userServiceFeign;
//
//    @Resource
//    private
    private List<IntegrationAuthenticator> authenticators;

        @Autowired(required = false)
        public void setIntegrationAuthenticators(List<IntegrationAuthenticator> authenticators) {
            this.authenticators = authenticators;
        }
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("###################认证开始##############################");
        System.out.println("------"+userName);

        IntegrationAuthentication integrationAuthentication = IntegrationAuthenticationContext.get();
        //判断是否是集成登录
        if (integrationAuthentication == null) {
            integrationAuthentication = new IntegrationAuthentication();
        }
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        integrationAuthentication.setUsername(userName);
        SysUserAuthentication sysUserAuthentication = this.authenticate(integrationAuthentication);

        if(sysUserAuthentication == null){
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        //没有认证统一采用httpbasic认证，httpbasic中存储了client_id和client_secret，开始认证client_id和client_secret
//        if(authentication==null){
//            ClientDetails clientDetails = clientDetailsService.loadClientByClientId(userName);
//            if(clientDetails!=null){
//                //秘钥
//                String clientSecret = clientDetails.getClientSecret();
//                //静态方式
//                //return new User(username,new BCryptPasswordEncoder().encode(clientSecret), AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//                //数据库查找方式
//                return new User(userName,clientSecret, AuthorityUtils.commaSeparatedStringToAuthorityList(""));
//            }
//        }
        if (StringUtils.isEmpty(userName)) {
            return null;
        }
//        User changgou = userServiceFeign.findByName(userName);
//        System.out.println(changgou);

        //根据用户名查询用户信息
        String pwd = new MyPasswordEncoder().encode("123456");
        //创建User对象
        String permissions = "goods_list,seckill_list";
        UserJwt userDetails = new UserJwt(userName,pwd,AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        System.out.println(userDetails);
        log.info("###################认证结束##############################");
        return userDetails;
    }
    private SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        if (this.authenticators != null) {
            for (IntegrationAuthenticator authenticator : authenticators) {
                if (authenticator.support(integrationAuthentication)) {
                    return authenticator.authenticate(integrationAuthentication);
                }
            }
        }
        return null;
    }
}
