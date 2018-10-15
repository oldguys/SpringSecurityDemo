package com.example.demo.security.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * 密碼加密校驗器
 *
 * @author huangrenhao
 * @date 2018/6/7
 */
public class BCryptPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {

        System.out.println("encode:" + rawPassword);

        String salt = BCrypt.gensalt();

        System.out.println("salt:" + salt);

        String psw = BCrypt.hashpw(rawPassword.toString(), salt);

        System.out.println("psw:" + psw);

        return psw;
    }

    @Override
    public boolean matches(CharSequence charSequence, String s) {

        System.out.println("matches:" + charSequence.toString() + " s:" + s);

        if (StringUtils.equals(charSequence.toString(), s)) {
            return true;
        }
        return false;
    }
}
