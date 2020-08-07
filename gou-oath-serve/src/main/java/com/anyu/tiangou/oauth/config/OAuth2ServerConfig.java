package com.anyu.tiangou.oauth.config;

import com.anyu.tiangou.oauth.feign.UserServiceFeign;
import com.anyu.tiangou.oauth.granter.*;
import com.anyu.tiangou.oauth.tuils.MyPasswordEncoder;
import com.anyu.tiangou.oauth.tuils.RedisUtil;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.CompositeTokenGranter;
import org.springframework.security.oauth2.provider.TokenGranter;
import org.springframework.security.oauth2.provider.client.ClientCredentialsTokenGranter;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeTokenGranter;
import org.springframework.security.oauth2.provider.implicit.ImplicitTokenGranter;
import org.springframework.security.oauth2.provider.refresh.RefreshTokenGranter;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.TokenEnhancer;
import org.springframework.security.oauth2.provider.token.TokenEnhancerChain;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.redis.RedisTokenStore;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * @author shkstart Administrator
 * @create 2020-08-03 14:30
 */
@Configuration
@EnableAuthorizationServer
public class OAuth2ServerConfig  extends AuthorizationServerConfigurerAdapter implements ApplicationContextAware {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private RedisConnectionFactory redisConnectionFactory;

    @Resource
    private UserDetailsServiceImpl userDetailsServiceImpl;

    private  ApplicationContext applicationContext;

    @Autowired
    private RedisUtil redisUtil;

    @Autowired
    private SMSDetailsServiceImpl smsDetailsService;


    @Autowired
    private UserServiceFeign userServiceFeign;

//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new MyPasswordEncoder();
//    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("android")
                .scopes("read","all")
                .secret("android")
                .redirectUris("https://www.baidu.com")
                .authorizedGrantTypes("password", "authorization_code", "refresh_token","sms_code","qq_openId","we_chat_openId")
                .and()
                .withClient("webapp")
                .scopes("read")
                .authorizedGrantTypes("implicit")
                .and()
                .withClient("browser")
                .authorizedGrantTypes("refresh_token", "password")
                .scopes("read");

    }



    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
//        endpoints.tokenStore(tokenStore())
//                .userDetailsService(userDetailsServiceImpl)
//                .authenticationManager(authenticationManager);
//        endpoints.tokenServices(defaultTokenServices());


                Collection<TokenEnhancer> tokenEnhancers = applicationContext.getBeansOfType(TokenEnhancer.class).values();
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(new ArrayList<>(tokenEnhancers));
        endpoints
                .tokenStore(tokenStore())
                .userDetailsService(userDetailsServiceImpl)  //refresh_token 需要配制它,否则会 UserDetailsService is required
                .tokenGranter(tokenGranter(endpoints)) // 配置granter 为最新的
                .tokenEnhancer(tokenEnhancerChain)
                .authenticationManager(authenticationManager)
                .allowedTokenEndpointRequestMethods(HttpMethod.OPTIONS, HttpMethod.POST, HttpMethod.GET);
//        endpoints.tokenServices(defaultTokenServices());




//                .reuseRefreshTokens(false);

        //认证异常翻译
        // endpoints.exceptionTranslator(webResponseExceptionTranslator());
    }

    /**
     * <p>注意，自定义TokenServices的时候，需要设置@Primary，否则报错，</p>
     * @return
     */
//    @Primary
//    @Bean
//    public DefaultTokenServices defaultTokenServices() {
//        DefaultTokenServices tokenServices = new DefaultTokenServices();
//        tokenServices.setTokenStore(tokenStore());
//        tokenServices.setSupportRefreshToken(true);
//        //tokenServices.setClientDetailsService(clientDetails());
//        // token有效期自定义设置，默认12小时
//        tokenServices.setAccessTokenValiditySeconds(60 * 60 * 12);
//        // refresh_token默认30天
//        tokenServices.setRefreshTokenValiditySeconds(60 * 60 * 24 * 7);
//        return tokenServices;
//    }


        @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        //表示支持client_id和client_secret
            security
                    .tokenKeyAccess("permitAll()")
                    .checkTokenAccess("permitAll()")
                    .allowFormAuthenticationForClients();


    }

    @Bean
    public TokenStore tokenStore() {
        return new RedisTokenStore(redisConnectionFactory);
    }



        private TokenGranter tokenGranter(final AuthorizationServerEndpointsConfigurer endpoints) {
        List<TokenGranter> granters = new ArrayList<TokenGranter>(Arrays.asList(endpoints.getTokenGranter()));// 获取默认的granter集合

        for (TokenGranter granter : granters) {
            System.out.println(granter);
        }
        System.out.println(granters);

        granters.add(new AuthorizationCodeTokenGranter(endpoints.getTokenServices(),
                endpoints.getAuthorizationCodeServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        granters.add(new RefreshTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));

        ImplicitTokenGranter implicit = new ImplicitTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory());
        granters.add(implicit);
        granters.add(new ClientCredentialsTokenGranter(endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
//
        granters.add(new SMSCodeTokenGranter(authenticationManager,endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory(),redisUtil,smsDetailsService));
            granters.add(new QQCodeTokenGranter(authenticationManager,endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
            granters.add(new WeChatCodeTokenGranter(authenticationManager,endpoints.getTokenServices(), endpoints.getClientDetailsService(), endpoints.getOAuth2RequestFactory()));
        return new CompositeTokenGranter(granters);
    }


        @Bean
    public TokenEnhancer tokenEnhancer() {
        TokenEnhancerChain tokenEnhancerChain = new TokenEnhancerChain();
        tokenEnhancerChain.setTokenEnhancers(Arrays.asList(new TuckerJwtTokenEnhancer(userServiceFeign), accessTokenConverter()));   // CustomTokenEnhancer 是我自定义一些数据放到token里用的
        return tokenEnhancerChain;
    }

        @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
//        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
//        KeyStoreKeyFactory keyStoreKeyFactory = new KeyStoreKeyFactory(new ClassPathResource("authorizationKey.jks"), "123456".toCharArray());
//        converter.setKeyPair(keyStoreKeyFactory.getKeyPair("klw"));
//        return converter;
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        //JWT签名
        converter.setSigningKey("SigningKey");
        return converter;
    }

//        @Bean
//    @Primary
//    public DefaultTokenServices tokenServices() {
//        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
//        defaultTokenServices.setTokenStore(tokenStore());
//        defaultTokenServices.setSupportRefreshToken(true);
//        defaultTokenServices.setTokenEnhancer(tokenEnhancer());   // 如果没有设置它,JWT就失效了.
//        return defaultTokenServices;
//    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext=applicationContext;
    }
}
