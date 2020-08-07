package com.anyu.tiangou.oauth.config;

import com.alibaba.fastjson.JSON;
import com.anyu.tiangou.oauth.feign.UserServiceFeign;
import com.anyu.tiangou.user.mdoel.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 11:25
 */
@Component
public class TuckerJwtTokenEnhancer implements TokenEnhancer {


    @Resource
    private UserServiceFeign userServiceFeign;

    TuckerJwtTokenEnhancer(UserServiceFeign userServiceFeign){
        this.userServiceFeign=userServiceFeign;
    }
    TuckerJwtTokenEnhancer(){
    }

    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {



        Serializable type = authentication.getOAuth2Request().getExtensions().get("type");
        final Map<String, Object> additionalInformation = new HashMap<>();

        //判断是否为null 如何为空则查询默认（默认为用户名与密码登录）
        if(StringUtils.isEmpty(type)){
            String userName = authentication.getUserAuthentication().getName();
            User byName = this.userServiceFeign.findByName(userName);
            System.out.println(byName);
            System.out.println("---------"+userName);
            Map<String, String> userinfo = new HashMap<>();
            userinfo.put("username", userName);
            additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
            ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
        }else{
            //判断是否是手机短信登录
            if(type.equals("smsPhpne")) {
                Serializable userMobileNo1 = authentication.getOAuth2Request().getExtensions().get("userMobileNo");
                System.out.println(userMobileNo1.toString());
                System.out.println("-------------------");
                // Map假装用户实体
                User byPhone = this.userServiceFeign.findByPhone(userMobileNo1.toString());
                Map<String, String> userinfo = new HashMap<>();
                userinfo.put("id", "145");
                userinfo.put("username", userMobileNo1.toString());
                userinfo.put("qqnum", "438944209");
                userinfo.put("userFlag", "1");
                additionalInformation.put("userinfo", JSON.toJSONString(byPhone));
                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
            }
        }


        return accessToken;
    }
}
