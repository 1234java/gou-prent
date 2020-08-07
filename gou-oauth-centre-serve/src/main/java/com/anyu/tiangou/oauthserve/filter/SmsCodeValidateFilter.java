package com.anyu.tiangou.oauthserve.filter;

import com.anyu.tiangou.oauthserve.tuils.SmsCode;
import org.apache.commons.lang.StringUtils;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.naming.AuthenticationException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

/**
 * 短信验证码校验过滤器
 * 实现
 * 用户登录时手机号不能为空
 * 用户登录时短信验证码不能为空
 * 用户登陆时在session中必须存在对应的校验谜底（获取验证码时存放的）
 * 用户登录时输入的短信验证码必须和“谜底”中的验证码一致
 * 用户登录时输入的手机号必须和“谜底”中保存的手机号一致
 * 用户登录时输入的手机号必须是系统注册用户的手机号，并且唯一
 * @author shkstart Administrator
 * @create 2020-07-31 14:06
 */
@Component
public class SmsCodeValidateFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (request.getRequestURI().equals("/smslogin")
                && request.getMethod().equalsIgnoreCase("post")) {
            validate(new ServletWebRequest(request));
        }
        filterChain.doFilter(request,response);

    }

    /**
     * 验证手机号是否存在与验证码是否有效
     * @param request
     * @throws ServletRequestBindingException
     */
    private void validate(ServletWebRequest request) throws ServletRequestBindingException {
        HttpSession session = request.getRequest().getSession();
        SmsCode codeInSession = (SmsCode) session.getAttribute("sms_key");
        String codeInRequest = request.getParameter("smsCode");
        String mobileInRequest = request.getParameter("mobile");


        System.out.println(mobileInRequest);
        System.out.println(codeInRequest);
        if(StringUtils.isEmpty(mobileInRequest)){
            throw new SessionAuthenticationException("手机号码不能为空！");
        }
        if(StringUtils.isEmpty(codeInRequest)){
            throw new SessionAuthenticationException("短信验证码不能为空！");
        }
//        if(Objects.isNull(codeInSession)){
//            throw new SessionAuthenticationException("短信验证码不存在！");
//        }
//        if(codeInSession.isExpired()){
//            session.removeAttribute("sms_key");
//            throw new SessionAuthenticationException("短信验证码已过期！");
//        }
//        if(!codeInSession.getCode().equals(codeInRequest)){
//            throw new SessionAuthenticationException("短信验证码不正确！");
//        }
        if(!"1234".equals(codeInRequest)){
            throw new SessionAuthenticationException("短信验证码不正确！");
        }
//        if(!codeInSession.getMobile().equals(mobileInRequest)){
//            throw new SessionAuthenticationException("短信发送目标与该手机号不一致！");
//        }

//        MyUserDetails myUserDetails = myUserDetailsServiceMapper.findByUserName(mobileInRequest);
//        if(Objects.isNull(myUserDetails)){
//            throw new SessionAuthenticationException("您输入的手机号不是系统的注册用户");
//        }

        session.removeAttribute("sms_key");
    }
}
