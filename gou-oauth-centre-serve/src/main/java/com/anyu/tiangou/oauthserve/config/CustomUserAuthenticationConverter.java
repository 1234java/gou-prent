package com.anyu.tiangou.oauthserve.config;

import com.anyu.tiangou.oauthserve.tuils.UserJwt;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.provider.token.DefaultUserAuthenticationConverter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomUserAuthenticationConverter extends DefaultUserAuthenticationConverter {

    @Resource
    UserDetailsService userDetailsService;

    @Override
    public Map<String, ?> convertUserAuthentication(Authentication authentication) {

        String s = authentication.toString();
        System.out.println("---------------"+s);


        LinkedHashMap response = new LinkedHashMap();
        String name = authentication.getName();
        response.put("username", name);

        Object principal = authentication.getPrincipal();
        UserJwt userJwt = null;
        if(principal instanceof UserJwt){
            userJwt = (UserJwt) principal;
            System.out.println(userJwt);
        }else{
            //refresh_token默认不去调用userdetailService获取用户信息，这里我们手动去调用，得到 UserJwt
            UserDetails userDetails = userDetailsService.loadUserByUsername(name);
            userJwt = (UserJwt) userDetails;
            System.out.println(userJwt);
        }
        response.put("name", userJwt.getName());
        response.put("id", userJwt.getId());
        if (authentication.getAuthorities() != null && !authentication.getAuthorities().isEmpty()) {
            response.put("authorities", AuthorityUtils.authorityListToSet(authentication.getAuthorities()));
        }
        System.out.println(response);
        return response;
    }

}