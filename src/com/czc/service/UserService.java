package com.czc.service;

import com.czc.pojo.User;

/**
 * @program: book
 * @description: 处理业务逻辑
 * @author: Mr.Cai
 * @create: 2021-11-15 15:48
 **/
public interface UserService {
    /**
     * 注册用户
     * @param user
     */
    public void registUser(User user);

    /**
     * 用户登录
     * @param user
     * @return
     */
    public User login(User user);

    /**
     * 检查用户名是否可用
     * @param username
     * @return 返回true表示用户名已经存在 返回false表示用户名可用
     */
    public boolean existsUsername(String username);




}
