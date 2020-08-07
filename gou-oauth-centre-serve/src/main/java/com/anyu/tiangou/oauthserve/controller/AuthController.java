package com.anyu.tiangou.oauthserve.controller;
import com.alibaba.fastjson.JSONObject;
import com.anyu.gou.core.utils.Result;
import com.anyu.gou.core.utils.StatusCode;
import com.anyu.tiangou.oauthserve.config.UserInfo;
import com.anyu.tiangou.oauthserve.services.AuthService;
import com.anyu.tiangou.oauthserve.tuils.AuthToken;
import com.anyu.tiangou.oauthserve.tuils.CookieUtil;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping(value = "/user")
@CrossOrigin
public class AuthController {

    //客户端ID
    @Value("${auth.clientId}")
    private String clientId;

    //秘钥
    @Value("${auth.clientSecret}")
    private String clientSecret;

    //Cookie存储的域名
    @Value("${auth.cookieDomain}")
    private String cookieDomain;

    //Cookie生命周期
    @Value("${auth.cookieMaxAge}")
    private int cookieMaxAge;

    @Autowired
    AuthService authService;

    @PostMapping("/login")
    public Result login(String username, String password) {
        if(StringUtils.isEmpty(username)){
            throw new RuntimeException("用户名不允许为空");
        }
        if(StringUtils.isEmpty(password)){
            throw new RuntimeException("密码不允许为空");
        }
        //申请令牌
        AuthToken authToken =  authService.login(username,password,"myapp","lxapp");
        System.out.println(authToken.toString());
        //用户身份令牌
        String access_token = authToken.getAccessToken();
        //将令牌存储到cookie
        saveCookie(access_token);

        UserInfo userInfo = JSONObject.parseObject(authToken.getInfo(), UserInfo.class);
        Map<String,Object> map =new HashMap<>();
        map.put("userInfo",userInfo);
        map.put("Authorization",access_token);
        return new Result(true, StatusCode.OK,"登录成功！",map);
    }

    /***
     * 将令牌存储到cookie
     * @param token
     */
    private void saveCookie(String token){
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        CookieUtil.addCookie(response,cookieDomain,"/","Authorization",token,cookieMaxAge,false);
    }
}
