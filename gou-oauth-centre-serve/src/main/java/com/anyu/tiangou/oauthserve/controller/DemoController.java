package com.anyu.tiangou.oauthserve.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 15:13
 */
@RestController
public class DemoController {


    @GetMapping("/index")
    public Map index(){
        Map<String,Object> map =new HashMap<>();
        map.put("code",200);
        map.put("msg","登录成功");
        map.put("user","{userName:zs,password:123,sex:男}");

        return map;
    }
}
