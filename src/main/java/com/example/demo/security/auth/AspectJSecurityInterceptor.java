package com.example.demo.security.auth;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.stereotype.Component;

/**
 *
 * @author huangrenhao
 * @date 2018/6/8
 */
//@Component
public class AspectJSecurityInterceptor extends AbstractSecurityInterceptor {

    @Override
    public Class<?> getSecureObjectClass() {



        return null;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {



        return null;
    }
}
