package com.anyu.tiangou.oauth.controller;

import com.anyu.tiangou.oauth.consd.SmsConfig;
import com.anyu.tiangou.oauth.tuils.RedisUtil;
import com.anyu.tiangou.oauth.tuils.SmsSampleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart Administrator
 * @create 2020-08-04 9:43
 */
@RestController
@EnableConfigurationProperties(SmsConfig.class)
@RequestMapping("/sms")
public class DemoController {
    @Autowired
    private  SmsConfig smsConfig;


    private  String SMS_SEND="sms_";
    @Resource
    private RedisUtil redisUtil;
    @GetMapping("/send")
   public Map<String,Object> sendNote(@RequestParam("phone") String phone){
        Map<String,Object> map =new HashMap<>();

    if(StringUtils.isEmpty(phone)){
        map.put("code","500");
        map.put("msg","手机号不能为空");
        return map;
    }
        String yzm = yzm();
        System.out.println(yzm);

        String replace = smsConfig.getContenthead().replace("{}", yzm);
        String sms_phone = SMS_SEND + phone;
        boolean set = redisUtil.set(sms_phone, yzm, 300);

        String s = SmsSampleUtil.httpArgs(smsConfig.getUsername(), smsConfig.getPassword(), phone, replace);
//        System.out.println(s);

        String request = SmsSampleUtil.request(smsConfig.getHttpurl(), s);
        if(request.equals("0")){
            map.put("code","200");
            map.put("msg","发送成功");
            return map;
        }
        map.put("code","500");
        map.put("msg","系统错误");
       return  map;
   }


        private   String  yzm(){
            String[] codees={"0","1","2","3","4","5","6","7","8","9"};
                String code="";
                for(int i=0;i<6;i++){
                    int j=(int)(Math.random()*10);
                    code+=codees[j];
                }
            return  code;
            }

}
