package com.anyu.tiangou.oauthserve;

import com.anyu.tiangou.oauthserve.config.Pool;
import com.anyu.tiangou.oauthserve.tuils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author shkstart Administrator
 * @create 2020-07-30 14:48
 */
@SpringBootTest
@EnableConfigurationProperties(Pool.class)
class OauthServeApplicarionTest {

    @Autowired
    private  Pool pool;

    @Autowired
    RedisUtil redisUtil;

    @Test
    void test1(){
        System.out.println(pool.toString());
    System.out.println(pool.getHost());
    }


    @Test
    void test2(){
        redisUtil.sSet("ks",new Pool());
    }

}