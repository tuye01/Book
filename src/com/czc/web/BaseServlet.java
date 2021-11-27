package com.czc.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

/**
 * @program: book
 * @description: 通过反射 action获取方法
 * @author: Mr.Cai
 * @create: 2021-11-18 18:06
 **/
public abstract class BaseServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 解决post请求中文乱码问题
        // 一定要在获取请求参数之前调用才有效
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");

        String action = req.getParameter("action");
        Method declaredMethod = null;
        try {
//            通过反射获取action业务鉴别字符串 获取相应的业务方法反射对象  this不同类使用这个模块
            declaredMethod = this.getClass().getDeclaredMethod(action, HttpServletRequest.class, HttpServletResponse.class);
//            调用目标业务和方法
            declaredMethod.invoke(this, req, resp);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //book_mannger中a标签是doGet请求
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
    doPost(req, resp);
    }
}
