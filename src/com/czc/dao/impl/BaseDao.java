package com.czc.dao.impl;

import com.czc.utils.JdbcUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * @program: book
 * @description: 工具
 * @author: Mr.Cai
 * @create: 2021-11-15 02:13
 **/
public abstract class BaseDao {
    //使用Dbutils操作数据库
    private QueryRunner queryRunner = new QueryRunner();

    /**
     * updata方法执行：Insert\Updata\Delete语句
     * @param sql sql语句
     * @param args 传sql语句中的 占位符 ？
     * @return 返回-1表示失败 其他表示影响的行数
     */
    public int updata(String sql, Object... args) {
        Connection connection = JdbcUtils.getConnection();
        try {
            return queryRunner.update(connection, sql, args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                JdbcUtils.close(connection);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return -1;
    }

    /**
     *  queryForOne查询一个javaBean的sql语句
     * @param type  返回对象类型
     * @param sql 执行的sql语句
     * @param args  sql对相应的参数值
     * @param <T>  返回的类型的泛型
     * @return  返回null表明用户名或密码错误
     */
    public <T> T queryForOne(Class<T> type,String sql, Object... args) {
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new BeanHandler<T>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                //关闭连接
                JdbcUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     *  queryForList查询多个javaBean的sql语句
     * @param type  返回对象类型
     * @param sql 执行的sql语句
     * @param args  sql对相应的参数值
     * @param <T>  返回的类型的泛型
     * @return
     */
    public <T>List<T> queryForList(Class<T> type, String sql, Object... args) {
        Connection con = JdbcUtils.getConnection();
        try {
            return queryRunner.query(con, sql, new BeanListHandler<>(type),args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(con);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * 执行返回一行一列的sql语句
     * @param
     * @param sql 执行的sql语句
     * @return 返回对象类型
     */
    public Object queryForSingleValue(String sql,Object...args){
        Connection conn = JdbcUtils.getConnection();
        try {
            return queryRunner.query(conn, sql, new ScalarHandler(), args);
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            try {
                JdbcUtils.close(conn);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
