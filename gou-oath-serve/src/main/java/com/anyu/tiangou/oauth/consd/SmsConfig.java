package com.anyu.tiangou.oauth.consd;

import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author shkstart Administrator
 * @create 2020-08-04 9:48
 */
@Component
@Data
@ToString
@ConfigurationProperties("smsbao")
public class SmsConfig  implements Serializable {

    private  String username;
    private  String password;
    private  String phone;
    private  String contenthead;
    private  String httpurl;
    private  String  time;

    public SmsConfig(String username, String password, String phone, String contenthead, String httpurl, String time) {
        this.username = username;
        this.password = password;
        this.phone = phone;
        this.contenthead = contenthead;
        this.httpurl = httpurl;
        this.time = time;
    }


    public SmsConfig() {
    }
}
