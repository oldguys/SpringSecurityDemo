package com.example.demo.sys.controllers;


import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author huangrenhao
 * @date 2018/6/6
 */
@Controller
public class TestController {


    @RequestMapping("/")
    public String index(Model model) {

        model.addAttribute("username", "张三，李四，刘五");

        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        System.out.println(userDetails);
        StringBuilder builder = new StringBuilder();
        for (GrantedAuthority authority: userDetails.getAuthorities()) {
            System.out.println("auth:"+authority.getAuthority());
            builder.append(authority.getAuthority()).append(" - ");
        }
        model.addAttribute("perms", builder.toString());

        return "index";
    }

    @ResponseBody
    @RequestMapping("config/admin")
    public String configAdmin() {
        System.out.println("config/admin");
        return "config/admin";
    }

    /**
     * @PreAuthorize 必须具有所有的角色
     * @return
     */
    @PreAuthorize("hasRole('admin')")
    @ResponseBody
    @RequestMapping("test/PreAuthorize")
    public String testPreAuthorize() {
        System.out.println("test/PreAuthorize");
        return "test/PreAuthorize";
    }

    @PreAuthorize("hasRole('normal')")
    @ResponseBody
    @RequestMapping("test/testNormal")
    public String testNormal() {
        System.out.println("test/testNormal");
        return "test/testNormal";
    }

    /**
     *  配置 @PreAuthorize 配置
     * @return
     */
    @PreAuthorize("hasRole('test_normal')")
    @ResponseBody
    @RequestMapping("test/test_normal")
    public String test_normal() {
        System.out.println("test/test_normal");
        return "test/test_normal";
    }

    /**
     *  测试 @Secured 注解配置
     * @return
     */
    @Secured({"ROLE_admin"})
    @ResponseBody
    @RequestMapping("test/Secured")
    public String testSecured() {
        System.out.println("test/Secured");
        return "test/Secured";
    }

    /**
     *  测试自定义方法
     * @return
     */
    @Secured({"IS_AUTHENTICATED_ANONYMOUSLY"})
    @ResponseBody
    @RequestMapping("test/auth")
    public String testAuth() {
        System.out.println("test/Secured");
        return "test/Secured";
    }

    /**
     *  测试默认登录
     * @return
     */
    @ResponseBody
    @RequestMapping("user")
    public String show() {
        System.out.println("user");
        return "User";
    }


}
