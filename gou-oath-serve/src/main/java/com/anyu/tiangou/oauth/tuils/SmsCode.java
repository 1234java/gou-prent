package com.anyu.tiangou.oauth.tuils;

import java.time.LocalDateTime;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 14:06
 */
public class SmsCode {
    private String code;  //短信验证码
    private LocalDateTime expireTime; //验证码的过期时间
    private String mobile; //发送手机号

    public SmsCode(String code,int expireAfterSeconds,String mobile){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
        this.mobile = mobile;
    }

    public boolean isExpired(){
        return  LocalDateTime.now().isAfter(expireTime);
    }

    public String getCode() {
        return code;
    }

    public String getMobile() {
        return mobile;
    }

}
