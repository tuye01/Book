package com.czc.test;

import com.czc.dao.impl.UserDaoImpl;
import com.czc.pojo.User;
import org.junit.Test;

import static org.junit.Assert.*;

//在接口按 Ctrl+shift+T创建测试类
public class UserDaoTest {

    @Test
    public void queryUserByUserName() {
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.queryUserByUserName("adin")==null){
            System.out.println("用户名可用");
        }else {
            System.out.println("用户名被占用！");
        }
    }

    @Test
    public void queryUserByUserNameAndPassword() {
        UserDaoImpl userDao = new UserDaoImpl();
//        System.out.println(userDao.queryUserByUserNameAndPassword("admin","admin")==null);
        if (userDao.queryUserByUserNameAndPassword("admin","admin")==null){
            System.out.println("登录失败！");
        }else {
            System.out.println("登录成功");
        }
    }

    @Test
    public void saveUser() {
        UserDaoImpl userDao = new UserDaoImpl();
        if (userDao.saveUser(new User(null,"rose","rose","1693560928@qq.com"))>0){
            System.out.println("创建成功");
        }else {
            System.out.println("创建失败！");
        }
    }
}