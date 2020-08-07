package com.anyu.tiangou.oauthserve.service.impl;

import com.anyu.tiangou.oauthserve.config.MyPasswordEncoder;
import com.anyu.tiangou.oauthserve.tuils.UserJwt;
import com.anyu.tiangou.user.mdoel.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 14:25
 */
@Component
@Slf4j
public class SmsDetailsServiceImpl implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String phop) throws UsernameNotFoundException {

        log.info("###################认证开始##############################");
        System.out.println("------"+phop);
        //取出身份，如果身份为空说明没有认证
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
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
        if (StringUtils.isEmpty(phop)) {
            return null;
        }
//        User changgou = userServiceFeign.findByName(userName);
//        System.out.println(changgou);

        //根据用户名查询用户信息
        String pwd = new MyPasswordEncoder().encode("123456");
        //创建User对象
        String permissions = "goods_list,seckill_list";
        UserJwt userDetails = new UserJwt(phop,"ok", AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        System.out.println(userDetails);
        log.info("###################认证结束##############################");
        return userDetails;
    }
}
