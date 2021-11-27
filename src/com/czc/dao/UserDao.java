package com.czc.dao;

import com.czc.pojo.User;

public interface    UserDao {

    /**
     * 根据用户名查询信息
     * @param username 用户名
     * @return 返回null表示没有这个用户
     */
    public User queryUserByUserName(String  username);

    /**
     *用户登录验证
     * @param username
     * @param password
     * @return 返回null表示用户名或密码错误
     */
    public User queryUserByUserNameAndPassword(String  username,String password);

    /**
     * 保存用户信息
     * @param user
     * @return
     */
    public int saveUser(User user);


}
