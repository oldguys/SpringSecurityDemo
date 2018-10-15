package com.example.demo.security.auth;


import com.example.demo.sys.dao.entities.MyUser;
import com.example.demo.sys.dao.jpas.MyUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

/**
 * @author huangrenhao
 * @date 2018/6/7
 */
public class SpringDataUserDetailsService implements UserDetailsService {


    @Qualifier("myUserRepository")
    @Autowired
    private MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println(this.getClass().getName() + ":" + s);

        MyUser myUser = myUserRepository.findByUsername(s);

        if (null == myUser) {
            throw new UsernameNotFoundException("没有找到该用户！请确定用户名是否正确！");
        }

        ArrayList<GrantedAuthority> authorities = new ArrayList<>();
//        authorities.add( new SimpleGrantedAuthority("ROLE_admin"));
        authorities.add( new SimpleGrantedAuthority("ROLE_normal"));

        User user = new User(myUser.getUsername(), myUser.getPassword(), authorities);

        return user;
    }
}
