package com.anyu.tiangou.oauth.granter;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 增加QQ登录TokenGranter
 * @author shkstart Administrator
 * @create 2020-08-04 13:28
 */
public class WeChatCodeTokenGranter extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "we_chat_openId";

//    protected QQCodeTokenGranter(AuthorizationServerTokenServices tokenServices, ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
//        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);
//    }
    public WeChatCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                                  ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);

    }

    @Override
    protected OAuth2Authentication getOAuth2Authentication(ClientDetails client, TokenRequest tokenRequest) {

        //获取所有数据
        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String openId = parameters.get("weixin_openId");

        String permissions = "goods_list,seckill_list";
        Authentication userAuth = new UsernamePasswordAuthenticationToken(openId, null, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);
        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        //设置类型
        Map<String, Serializable> extensions = storedOAuth2Request.getExtensions();
        extensions.put("weixin_openId",openId);
        extensions.put("type","WeChat");
        return new OAuth2Authentication(storedOAuth2Request, userAuth);
    }


}
