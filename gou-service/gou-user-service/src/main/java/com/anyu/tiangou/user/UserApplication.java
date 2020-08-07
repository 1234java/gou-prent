package com.anyu.tiangou.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 11:32
 */
@SpringBootApplication
@EnableEurekaClient
@MapperScan("com.anyu.tiangou.user.mapper")
public class UserApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class,args);
    }
}
