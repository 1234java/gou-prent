package com.anyu.tiangou.oauthserve.provider;

import com.anyu.tiangou.oauthserve.tuils.SmsCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Collection;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 14:15
 */
public class SmsCodeAuthenticationProvider  implements AuthenticationProvider {

    private UserDetailsService userDetailsService;

    public UserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    public void setUserDetailsService(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    /**
     * 进行身份认证的逻辑
     * @param authentication    就是我们传入的Token
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        //利用UserDetailsService获取用户信息，拿到用户信息后重新组装一个已认证的Authentication

        SmsCodeAuthenticationToken authenticationToken = (SmsCodeAuthenticationToken)authentication;
        UserDetails user = userDetailsService.loadUserByUsername((String) authenticationToken.getPrincipal());  //根据手机号码拿到用户信息
        if(user == null){
            throw new InternalAuthenticationServiceException("无法获取用户信息");
        }

        System.out.println(user);
        System.out.println(user.getAuthorities());
//        //优先匹配密码
//        if (passwordEncoder.matches(password, userDetails.getPassword())) {
//            Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
//            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
//        } else {
//            //这里将password尝试作为手机验证码，然后和已发送的验证码检验，需要注意验证码有效期，是否已验证码等判断
//            return new UsernamePasswordAuthenticationToken(userDetails, password, authorities);
//

            SmsCodeAuthenticationToken authenticationResult = new SmsCodeAuthenticationToken(user,user.getAuthorities());
        authenticationResult.setDetails(authenticationToken.getDetails());
        return authenticationResult;
    }

    /**
     * AuthenticationManager挑选一个AuthenticationProvider
     * 来处理传入进来的Token就是根据supports方法来判断的
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return SmsCodeAuthenticationToken.class.isAssignableFrom(aClass);
    }
}
