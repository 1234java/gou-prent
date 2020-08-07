package com.anyu.tiangou.oauth.granter;

import com.anyu.tiangou.oauth.tuils.MyPasswordEncoder;
import com.anyu.tiangou.oauth.tuils.UserJwt;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.List;

/*******
        * 自定义授权认证类
        */
@Component
@Slf4j
public class UserDetailsServiceImpl implements UserDetailsService {

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        log.info("###################认证开始##############################");
        System.out.println("------"+userName);

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

}
