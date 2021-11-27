package com.czc.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @program: book
 * @description: 获取数据库连接池中的连接
 * @author: Mr.Cai
 * @create: 2021-11-15 00:40
 **/
public class JdbcUtils {
    private static DruidDataSource dataSource;

    static{
        try {
            //读取配置文件 获取流
            Properties properties=new Properties();
            InputStream inputStream = JdbcUtils.class.getClassLoader().getResourceAsStream("jdbc.properties");
            properties.load(inputStream);
            //创建了数据库连接池
            dataSource= (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    //获取数据库连接
    public static Connection getConnection() {
       Connection conn=null;
        try {
            conn = dataSource.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    //释放数据路连接
    public static void close(Connection conn) throws SQLException {
        if (conn!=null){
            conn.close();
        }
    }
}
