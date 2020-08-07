package com.anyu.tiangou.oauth.granter;

import com.anyu.tiangou.oauth.feign.UserServiceFeign;
import com.anyu.tiangou.oauth.tuils.MyPasswordEncoder;
import com.anyu.tiangou.oauth.tuils.UserJwt;
import com.anyu.tiangou.user.mdoel.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.xml.ws.Action;

/**
 * @author shkstart Administrator
 * @create 2020-08-04 11:19
 */
@Component
@Slf4j
public class SMSDetailsServiceImpl implements UserDetailsService {


    @Autowired
    private UserServiceFeign userServiceFeign;
    @Override
    public UserDetails loadUserByUsername(String phone) throws UsernameNotFoundException {
        log.info("###################phone认证开始##############################");
        System.out.println("------"+phone);

        if (StringUtils.isEmpty(phone)) {
            return null;
        }
        User byPhone = userServiceFeign.findByPhone(phone);
        if(byPhone == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        if(byPhone.getStatus().equals("0")){
            throw  new LockedException("账号已锁定");
        }
//        User changgou = userServiceFeign.findByName(userName);
//        System.out.println(changgou);

        //根据用户名查询用户信息
        //创建User对象
        String permissions = "goods_list,seckill_list";
        UserJwt userDetails = new UserJwt(phone,"OK", AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        System.out.println(userDetails);
        log.info("###################phone认证结束##############################");
        return userDetails;
    }
}
