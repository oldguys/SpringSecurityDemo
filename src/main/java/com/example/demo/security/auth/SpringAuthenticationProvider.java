package com.example.demo.security.auth;


import com.example.demo.sys.dao.entities.MyUser;
import com.example.demo.sys.dao.jpas.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

/**
 * 自定义登录授权认证器
 *
 * @author huangrenhao
 * @date 2018/6/7
 */
public class SpringAuthenticationProvider implements AuthenticationProvider {


    @Qualifier("myUserRepository")
    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = (String) authentication.getPrincipal();
        System.out.println("Principal:" + username);

        MyUser myUser = myUserRepository.findByUsername(username);

        if(null == myUser){
            return null;
        }

        UsernamePasswordAuthenticationToken token;
        token = new UsernamePasswordAuthenticationToken(myUser.getUsername(), myUser.getPassword());

        return token;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}

