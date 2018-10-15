package com.example.demo.security.configs;


import com.example.demo.security.auth.SpringAuthenticationProvider;
import com.example.demo.security.auth.SpringDataUserDetailsService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.intercept.aopalliance.MethodSecurityInterceptor;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Collection;

import static org.apache.coyote.http11.Constants.a;

/**
 *
 * @author huangrenhao
 * @date 2018/6/7
 */
//@EnableWebSecurity
//@EnableGlobalMethodSecurity(prePostEnabled = false,securedEnabled = false)
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/config/admin").hasRole("admin1")
                .anyRequest().authenticated()
                .and().formLogin();
        http.csrf().disable();
    }


    /**
     * 自定义授权认证器
     *
     * @return
     */
//    @Bean
    public SpringAuthenticationProvider springAuthenticationProvider() {
        return new SpringAuthenticationProvider();
    }

    @Bean
    public SpringDataUserDetailsService springDataUserDetailsService() {
        return new SpringDataUserDetailsService();
    }

    /**
     *  系统内置
     * @return
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

//    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        BCryptPasswordEncoder bean = new BCryptPasswordEncoder();
        return bean;
    }

}
