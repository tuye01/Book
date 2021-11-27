package com.czc.service.impl;

import com.czc.dao.UserDao;
import com.czc.dao.impl.UserDaoImpl;
import com.czc.pojo.User;
import com.czc.service.UserService;

/**
 * @program: book
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-15 15:56
 **/
public class UserServiceImpl implements UserService {

    //与dao持久层联系
    private UserDao userDao = new UserDaoImpl();

    @Override
    public void registUser(User user) {
        userDao.saveUser(user);
    }

    @Override
    public User login(User user) {
        return userDao.queryUserByUserNameAndPassword(user.getUsername(), user.getPassword());
    }

    @Override
    public boolean existsUsername(String username) {
        if (userDao.queryUserByUserName(username)==null){
            //等于null，说明没有查到
            return false;
        }
        return true;
    }
}
