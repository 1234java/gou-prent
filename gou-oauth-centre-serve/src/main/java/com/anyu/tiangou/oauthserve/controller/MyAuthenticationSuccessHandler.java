package com.anyu.tiangou.oauthserve.controller;

import com.alibaba.fastjson.JSONObject;
import com.anyu.tiangou.oauthserve.tuils.CommonConstants;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.mybatis.logging.Logger;
import org.mybatis.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.UnapprovedClientAuthenticationException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import org.thymeleaf.util.MapUtils;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Base64;
import java.util.HashMap;
import java.util.Set;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 14:40
 */
@Component
public class MyAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ClientDetailsService clientDetailsService;
    @Resource
    private AuthorizationServerTokenServices authorizationServerTokenServices;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) {
        String header = request.getHeader("Authorization");

//        if (header == null || !header.startsWith("Basic ")) {
//            throw new UnapprovedClientAuthenticationException("请求头中client信息为空");
//        }

        try {
//            String[] tokens = extractAndDecodeHeader(header);
//            assert tokens.length == 2;
//            String clientId = tokens[0];
//            String clientSecret = tokens[1];
//
//            JSONObject params = new JSONObject();
//            params.put("clientId", clientId);
//            params.put("clientSecret", clientSecret);
//            params.put("authentication", authentication);

            ClientDetails clientDetails = clientDetailsService.loadClientByClientId("myapp");
            System.out.println(clientDetails);
            TokenRequest tokenRequest = new TokenRequest(new HashMap<>(), "lxapp", clientDetails.getScope(), "password");
            OAuth2Request oAuth2Request = tokenRequest.createOAuth2Request(clientDetails);

            OAuth2Authentication oAuth2Authentication = new OAuth2Authentication(oAuth2Request, authentication);
            OAuth2AccessToken oAuth2AccessToken = authorizationServerTokenServices.createAccessToken(oAuth2Authentication);
//            logger.info("获取token 成功：{}", oAuth2AccessToken.getValue());

            response.setCharacterEncoding(CommonConstants.UTF8);
            response.setContentType(CommonConstants.CONTENT_TYPE);
            PrintWriter printWriter = response.getWriter();
            printWriter.append(objectMapper.writeValueAsString(oAuth2AccessToken));
        } catch (IOException e) {
            throw new BadCredentialsException(
                    "Failed to decode basic authentication token");
        }
    }


}