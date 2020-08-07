package com.anyu.tiangou.oauth.tuils;

import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @author shkstart Administrator
 * @create 2020-07-31 10:22
 */
public class MyPasswordEncoder implements PasswordEncoder {
    @Override
    public String encode(CharSequence charSequence) {
        return charSequence.toString();
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {
        return s.equals(charSequence.toString());


    }
}