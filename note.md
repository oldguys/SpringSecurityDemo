

>  添加一个注解到一个方法（或者一个类或者接机）会限制对相应方法的访问。
>  Spring Security的原生注解支持定义了一套用于该方法的属性。
>  这些将被传递AccessDecisionManager到来做实际的决定： 
    
    @EnableGlobalMethodSecurity( securedEnabled = true)

>  使用如下代码启用JSR-250注解的支持
>  jsr250Enabled = true,
    
    @EnableGlobalMethodSecurity( jsr250Enabled = true)

>  启动权限拦截

    @EnableGlobalMethodSecurity( prePostEnabled = true)


   
   
   
    
      
      
    
      
## Spring-Security使用
+ 方式一:默认使用，引入依赖之后，直接在属性配置文件配置用户信息及角色信息
    + step1:仅仅开启登录拦截
        
            security:
                user:
                    name: user  -- 默认 user
                    password: 123 -- 不配置会生成默认随机密码，并打印在控制台。
                    roles: "admin"

    + step2:添加注解 
    开启注解式权限拦截，开启之后，可以使用注解拦截方法。
    
            @EnableGlobalMethodSecurity(prePostEnabled = true,securedEnabled = true) 
    
    


+ 方式二:
继承 org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
配置config方法

        public class SecurityConfig extends WebSecurityConfigurerAdapter {
            @Override
            protected void configure(HttpSecurity http) throws Exception {
                http.authorizeRequests()
                        .antMatchers("/config/admin").hasRole("admin1")
                        .anyRequest().authenticated()
                        .and().formLogin();
        
                // 关闭csrf
                http.csrf().disable();
            }
        }

+ 使用自定义登录方式:
    + 方式一:
    
            org.springframework.security.authentication.AuthenticationProvider
            参考: com.spring.security.auth.SpringAuthenticationProvider
    + 方式二:
        
            org.springframework.security.core.userdetails.UserDetailsService
            必须使用 org.springframework.security.crypto.password.PasswordEncoder 否则会报异常


## 1. 接口实现类：权限认证
    org.springframework.security.core.userdetails.UserDetailsService
    参考: com.spring.security.auth.SpringDataUserDetailsService
注意：
1. 权限的前缀必须是 ROLE_ 如：ROLE_admin
1. 使用 @Secured 进行权限校验的时候，应该使用全称  @Secured({"ROLE_admin"})
1. 使用 @PreAuthorize("hasRole('admin')") ，应该注入简称

