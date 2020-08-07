package com.anyu.tiangou.oauthserve.config;

import com.anyu.tiangou.oauthserve.exception.WebResponseExceptionTranslator;
import com.anyu.tiangou.oauthserve.integration.IntegrationAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.token.*;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.*;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 10:37
 */
@Configuration
@EnableAuthorizationServer
public class AuthServerConfig extends AuthorizationServerConfigurerAdapter  {

    // 资源ID
    private static final String SOURCE_ID = "order";
    private static final int ACCESS_TOKEN_TIMER = 60 * 60 * 24;
    private static final int REFRESH_TOKEN_TIMER = 60 * 60 * 24 * 30;


    @Autowired
    private IntegrationAuthenticationFilter integrationAuthenticationFilter;

    //异常转换器
    @Autowired
    private WebResponseExceptionTranslator webResponseExceptionTranslator;

    //管理器
    @Resource
    AuthenticationManager authenticationManager;
    //redis
    @Autowired
    RedisConnectionFactory redisConnectionFactory;

//    @Resource
//    private JwtAccessTokenConverter jwtAccessTokenConverter;
    //SpringSecurity 用户自定义授权认证类
    @Resource
    UserDetailsService userDetailsService;
    //授权认证管理器
    @Autowired
    public TokenEnhancer jwtTokenEnhancer;
//    @Resource
//    public TuckerJwtTokenEnhancer tuckerJwtTokenEnhancer;

//    @Resource
//    private  CustomUserAuthenticationConverter customUserAuthenticationConverter;

    //设置控制用户
    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory().withClient("myapp").resourceIds(SOURCE_ID).authorizedGrantTypes("password", "refresh_token")
                .scopes("all").authorities("ADMIN").secret("lxapp").accessTokenValiditySeconds(ACCESS_TOKEN_TIMER)
                .refreshTokenValiditySeconds(REFRESH_TOKEN_TIMER);
    }


    /***
     * 授权服务器端点配置
     * @param endpoints
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints
//                .accessTokenConverter(jwtAccessTokenConverter())
                .authenticationManager(authenticationManager)//认证管理器
                .tokenStore(tokenStore())                       //令牌存储
                .exceptionTranslator(webResponseExceptionTranslator)
                .userDetailsService(userDetailsService)
                .allowedTokenEndpointRequestMethods(HttpMethod.OPTIONS, HttpMethod.POST, HttpMethod.GET);     //用户信息service
//        if (jwtAccessTokenConverter() != null && new TuckerJwtTokenEnhancer() != null) {
            TokenEnhancerChain chain = new TokenEnhancerChain();

            List<TokenEnhancer> list = new ArrayList<TokenEnhancer>();
            //TODO 一定要注意顺序,先执行 自定义 enhancer 添加信息 然后尽心加密生成token
            list.add(new TuckerJwtTokenEnhancer());
            list.add(jwtAccessTokenConverter());
            chain.setTokenEnhancers(list);

            endpoints.tokenEnhancer(chain).accessTokenConverter(jwtAccessTokenConverter());
//        }

    }



//    @Override
//    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
//        // 允许表单认证
//        oauthServer.allowFormAuthenticationForClients();
//    }


    /***
     * 授权服务器的安全配置
     * @param oauthServer
     * @throws Exception
     */
    @Override
    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
        //允许表单验证
        oauthServer.allowFormAuthenticationForClients()
                .passwordEncoder(new MyPasswordEncoder())
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
                    .addTokenEndpointAuthenticationFilter(integrationAuthenticationFilter);;
    }

//    @Bean
//    public JwtAccessTokenConverter accessTokenConverterss() {
//        return  new TuckerJwtTokenEnhancer();
//
//    }
    // JWT
//    @Bean
//    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter accessTokenConverter = new JwtAccessTokenConverter() {
//            /***
//             * 重写增强token方法,用于自定义一些token总需要封装的信息
//             */
//            @Override
//            public OAuth2AccessToken enhance(OAuth2AccessToken accessToken, OAuth2Authentication authentication) {
//                String userName = authentication.getUserAuthentication().getName();
//
//
//                // 得到用户名，去处理数据库可以拿到当前用户的信息和角色信息（需要传递到服务中用到的信息）
//                final Map<String, Object> additionalInformation = new HashMap<>();
//                // Map假装用户实体
//                Map<String, String> userinfo = new HashMap<>();
//                userinfo.put("id", "1");
//                userinfo.put("username", "LiaoXiang");
//                userinfo.put("qqnum", "438944209");
//                userinfo.put("userFlag", "1");
//                additionalInformation.put("userinfo", JSON.toJSONString(userinfo));
//                ((DefaultOAuth2AccessToken) accessToken).setAdditionalInformation(additionalInformation);
//                OAuth2AccessToken enhancedToken = super.enhance(accessToken, authentication);
//                return enhancedToken;
//            }
//        };
//        // 测试用,资源服务使用相同的字符达到一个对称加密的效果,生产时候使用RSA非对称加密方式
//        accessTokenConverter.setSigningKey("SigningKey");
//        return accessTokenConverter;
//    }


    /****
     * JWT令牌转换器
     * 加密
     * @param
     * @return
     */
    @Primary
    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //JWT签名
        converter.setSigningKey("SigningKey");
        return converter;
    }

    /**
     * 存入到redis中
     * @return
     */
    @Bean
    public TokenStore tokenStore() {
        RedisTokenStore tokenStore = new RedisTokenStore(redisConnectionFactory);
        return tokenStore;
    }

}
