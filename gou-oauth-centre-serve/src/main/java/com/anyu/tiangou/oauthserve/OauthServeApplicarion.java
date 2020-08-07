package com.anyu.tiangou.oauthserve;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 14:22
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
@EnableEurekaClient
@EnableConfigurationProperties
@ConfigurationPropertiesScan
@EnableFeignClients
public class OauthServeApplicarion {
    public static void main(String[] args) {
        SpringApplication.run(OauthServeApplicarion.class,args);
    }
}
