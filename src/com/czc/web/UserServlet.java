package com.czc.web;

import com.czc.pojo.User;
import com.czc.service.UserService;
import com.czc.service.impl.UserServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY;


/**
 * @program: book
 * @description: 用户功能
 * @author: Mr.Cai
 * @create: 2021-11-18 03:24
 **/
public class UserServlet extends BaseServlet {
    private UserService userService = new UserServiceImpl();

    //登录方发
    protected void login(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.获取登录界面的用户名 密码
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        //2.与数据库作比较
        User loginUser = userService.login(new User(null, username, password, null));
        if (loginUser == null) {
            System.out.println("登陆失败！");
//将错误信息和 回显的表单信息，保存到request域中
            req.setAttribute("msg", "用户名或密码错误！");
            req.setAttribute("username", username);
            req.setAttribute("password", password);

            //跳回登陆页面
            req.getRequestDispatcher("/pages/user/login.jsp").forward(req, resp);

        } else {
            //登陆成功
            System.out.println("登陆成功");
            //保存用户登录信息到Session域中
            req.getSession().setAttribute("user", loginUser);

            //保存用户登录信息到request域中
            req.getRequestDispatcher("/pages/user/login_success.jsp").forward(req, resp);
        }
    }

    //登录方法
    protected void regist(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取session验证码
        String  token = (String) req.getSession().getAttribute(KAPTCHA_SESSION_KEY);
        //删除验证码
        req.getSession().removeAttribute(KAPTCHA_SESSION_KEY);


        //1.获取请求的参数
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        //获取表单中的验证码
        String code = req.getParameter("code");

        //2.检查验证码是否正确
        if (token!=null&&token.equalsIgnoreCase(code)) {//验证码正确
            if (userService.existsUsername(username)) {
                //用户名存在
                System.out.println("【" + username + "】已存在！");
                req.setAttribute("code", code);
                req.setAttribute("username", username);
                req.setAttribute("msg", "用户名已存在！");
                req.setAttribute("password", password);
                req.setAttribute("email", email);
            } else {
                //用户名不存在
                //写入数据库
                userService.registUser(new User(null, username, password, email));
                System.out.println("注册成功");
                //跳回注册页面
                req.getRequestDispatcher("/pages/user/regist_success.jsp").forward(req, resp);

            }
        } else {//不正确
            System.out.println("【" + code + "】验证码错误！");
            //将错误信息和 回显的表单信息，保存到request域中
            req.setAttribute("code", code);
            req.setAttribute("msg", "验证码错误！");
            req.setAttribute("username", username);
            req.setAttribute("password", password);
            req.setAttribute("email", email);
            //跳回注册页面
            req.getRequestDispatcher("/pages/user/regist.jsp").forward(req, resp);
        }
    }

    //注销方法
    protected void logout(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //销毁Session中用户登陆的信息
        req.getSession().invalidate();
        //重定向到首页
        resp.sendRedirect(req.getContextPath());
    }
}
