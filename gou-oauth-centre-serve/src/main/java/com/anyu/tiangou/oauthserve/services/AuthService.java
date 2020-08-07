package com.anyu.tiangou.oauthserve.services;

import com.anyu.tiangou.oauthserve.tuils.AuthToken;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 11:06
 */
public interface AuthService {

    /***
     * 授权认证方法
     */
    AuthToken login(String username, String password, String clientId, String clientSecret);
}
