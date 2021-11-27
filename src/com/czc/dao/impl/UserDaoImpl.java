package com.czc.dao.impl;

import com.czc.dao.UserDao;
import com.czc.pojo.User;

/**
 * @program: book
 * @description: UserDao接口的实现类
 * @author: Mr.Cai
 * @create: 2021-11-15 14:08
 **/
public class UserDaoImpl extends BaseDao implements UserDao {
    @Override
    public User queryUserByUserName(String username) {
        String sql = "select id,username,password,email from t_user where username=?";
        return queryForOne(User.class, sql, username);
    }

    @Override
    public User queryUserByUserNameAndPassword(String username, String password) {
        String sql = "select id,username,password,email from t_user where username = ? and password = ?";
        return queryForOne(User.class, sql, username, password);
    }

    @Override
    public int saveUser(User user) {
        String sql="insert into t_user(username,password,email)value(?,?,?)";
        return updata(sql,user.getUsername(),user.getPassword(),user.getEmail());
    }
}
