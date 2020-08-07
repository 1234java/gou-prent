package com.anyu.tiangou.oauth.granter;

import com.alibaba.fastjson.JSON;
import com.anyu.tiangou.oauth.tuils.RedisUtil;
import com.anyu.tiangou.oauth.tuils.SmsCodeAuthenticationToken;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.common.exceptions.InvalidGrantException;
import org.springframework.security.oauth2.provider.*;
import org.springframework.security.oauth2.provider.token.AbstractTokenGranter;
import org.springframework.security.oauth2.provider.token.AuthorizationServerTokenServices;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * 增加短信验证码的 TokenGranter
 * @author shkstart Administrator
 * @create 2020-08-03 10:12
 */
public class SMSCodeTokenGranter  extends AbstractTokenGranter {
    private static final String GRANT_TYPE = "sms_code";

    private RedisUtil redisUtil;

    private  SMSDetailsServiceImpl smsDetailsService;

    public SMSCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);

    }
    public SMSCodeTokenGranter(AuthenticationManager authenticationManager, AuthorizationServerTokenServices tokenServices,
                               ClientDetailsService clientDetailsService, OAuth2RequestFactory requestFactory,RedisUtil redisUtil,SMSDetailsServiceImpl smsDetailsService) {
        super(tokenServices, clientDetailsService, requestFactory, GRANT_TYPE);

        this.redisUtil=redisUtil;
        this.smsDetailsService=smsDetailsService;

    }
    private  String SMS_SEND="sms_";





    @Override
    public OAuth2Authentication getOAuth2Authentication(ClientDetails client,
                                                           TokenRequest tokenRequest) {

        Map<String, String> parameters = new LinkedHashMap<String, String>(tokenRequest.getRequestParameters());
        String userMobileNo = parameters.get("username");  //客户端提交的用户名
        String smscode = parameters.get("smscode");  //客户端提交的验证码

        //拼接为key   sms_15243698125
        String phpne = SMS_SEND + userMobileNo;
//        查询缓存
        String smsCodeCached =(String)this.redisUtil.get(phpne);
//        System.out.println(smsCodeCached);


//       判断是否手机号是否为null
        if(StringUtils.isEmpty(userMobileNo)) {
            throw new InvalidGrantException("手机号不能为空");
        }

        // 验证验证码
        if(StringUtils.isBlank(smsCodeCached)) {
            throw new InvalidGrantException("用户没有发送验证码");
        }
        if(!smscode.equals(smsCodeCached)) {
            throw new InvalidGrantException("验证码不正确");
        }else {
//            验证通过后从缓存中移除验证码,代码略
            //删除
//            this.redisUtil.del(phpne);
        }
        //查询用户
        UserDetails userDetails = this.smsDetailsService.loadUserByUsername(userMobileNo);
        System.out.println(userDetails);

        System.out.println(userDetails.getAuthorities());
//        String permissions = "goods_list,seckill_list";
//        Authentication userAuth = new UsernamePasswordAuthenticationToken(userMobileNo, null, AuthorityUtils.commaSeparatedStringToAuthorityList(permissions));
//        ((AbstractAuthenticationToken) userAuth).setDetails(parameters);

        Authentication userAuths = new SmsCodeAuthenticationToken(userMobileNo, null, userDetails.getAuthorities());

        ((AbstractAuthenticationToken) userAuths).setDetails(parameters);

        OAuth2Request storedOAuth2Request = getRequestFactory().createOAuth2Request(client, tokenRequest);
        //设置类型
        Map<String, Serializable> extensions = storedOAuth2Request.getExtensions();
        extensions.put("userMobileNo",userMobileNo);
        extensions.put("type","smsPhpne");
        return new OAuth2Authentication(storedOAuth2Request, userAuths);
    }





}
