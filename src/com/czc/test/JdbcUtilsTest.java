package com.czc.test;

import com.czc.utils.JdbcUtils;

/**
 * @program: book
 * @description: 数据库连接测试
 * @author: Mr.Cai
 * @create: 2021-11-15 01:52
 **/
public class JdbcUtilsTest {

    public  void testJdbcUtils() {
        System.out.println(JdbcUtils.getConnection());
    }
}
