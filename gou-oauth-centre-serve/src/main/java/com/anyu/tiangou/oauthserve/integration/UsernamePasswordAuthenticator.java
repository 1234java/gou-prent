package com.anyu.tiangou.oauthserve.integration;

import com.anyu.tiangou.oauthserve.feign.UserServiceFeign;
import com.anyu.tiangou.user.mdoel.User;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;


/**
 * 默认登录处理
 * @author LIQIU
 * @date 2018-3-31
 **/
@Component
@Primary
public class UsernamePasswordAuthenticator extends AbstractPreparableIntegrationAuthenticator {

    @Autowired
    private UserServiceFeign userServiceFeign;

    @Override
    public SysUserAuthentication authenticate(IntegrationAuthentication integrationAuthentication) {
        SysUserAuthentication sysUserAuthentication =new SysUserAuthentication();
        User byName = userServiceFeign.findByName(integrationAuthentication.getUsername());
        BeanUtils.copyProperties(User.class,  sysUserAuthentication);
//        SysUserAuthentication sysUserAuthentication = sysUserClient.findUserByUsername(integrationAuthentication.getUsername());
        return sysUserAuthentication;
    }

    @Override
    public void prepare(IntegrationAuthentication integrationAuthentication) {

    }

    @Override
    public boolean support(IntegrationAuthentication integrationAuthentication) {
        return StringUtils.isEmpty(integrationAuthentication.getAuthType());
    }
}