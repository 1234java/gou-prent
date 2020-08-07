package com.anyu.tiangou.oauthserve.config;

import com.alibaba.fastjson.JSON;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 11:25
 */
public class TuckerJwtTokenEnhancer implements TokenEnhancer {
    @Override
    public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
        String userName = authentication.getUserAuthentication().getName();

        System.out.println(userName);
        // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
        final Map<String, Object> additionalInformation = new HashMap<>();
        // Map假装用户实体
        Map<String, String> userinfo = new HashMap<>();
        userinfo.put("id", "145");
        userinfo.put("username", userName);
        userinfo.put("qqnum", "438944209");
        userinfo.put("userFlag", "1");
        additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
        ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//        OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
        return accessToken;
    }
}
