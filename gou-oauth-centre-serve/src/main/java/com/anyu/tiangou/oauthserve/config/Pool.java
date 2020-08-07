package com.anyu.tiangou.oauthserve.config;

import lombok.Data;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 14:36
 */

@Component
@Data
@ToString
@ConfigurationProperties("spring.redis")
public class Pool  implements Serializable {
    private String timeout;
    private   String host;
    private  String password;
    private  Integer port;
    @Value(("${spring.redis.lettuce.pool.max-wait}"))
    private Long maxWait;
    @Value(("${spring.redis.lettuce.pool.max-idle}"))
    private Long maxIdle;
    @Value(("${spring.redis.lettuce.pool.max-active}"))
    private Long maxActive;
    @Value(("${spring.redis.lettuce.pool.min-idle}"))
    private Long minIdle;



}
