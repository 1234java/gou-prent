package com.anyu.tiangou.oauth.consd;

import com.anyu.tiangou.oauth.tuils.SmsSampleUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author shkstart Administrator
 * @create 2020-08-04 9:54
 */
@SpringBootTest
@EnableConfigurationProperties(SmsConfig.class)
class SmsConfigTest {

    @Autowired
    private  SmsConfig smsConfig;

    @Test
    void  sms(){
        System.out.println(smsConfig.getPassword());
        System.out.println(smsConfig.toString());
        String replace = smsConfig.getContenthead().replace("{}", "123456");
        String s = SmsSampleUtil.httpArgs(smsConfig.getUsername(), smsConfig.getPassword(), "15243698125", replace);
        System.out.println(s);

        String request = SmsSampleUtil.request(smsConfig.getHttpurl(), s);
        System.out.println(request);




    }
}