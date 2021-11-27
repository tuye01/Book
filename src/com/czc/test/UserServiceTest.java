package com.czc.test;

import com.czc.pojo.User;
import com.czc.service.UserService;
import com.czc.service.impl.UserServiceImpl;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserServiceTest {

    UserService userService = new UserServiceImpl();

    @Test
    public void registUser() {
        userService.registUser(new User(null, "marry", "marry", "1693560928@qq.com"));
        userService.registUser(new User(null, "linda", "marry", "1693560928@qq.com"));
    }

    @Test
    public void login() {
        if (userService.login(new User(null,"admin","admin",null))!=null){
            System.out.println("登陆成功");
        }else {
            System.out.println("登录失败");
        }
    }

    @Test
    public void existsUsername() {
        if (userService.existsUsername("admin")){
            System.out.println("用户名存在");
        }else {
            System.out.println("用户名不存在！");
        }
    }
}