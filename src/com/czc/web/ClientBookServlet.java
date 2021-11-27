package com.czc.web;

import com.czc.pojo.Book;
import com.czc.pojo.Page;
import com.czc.service.BookService;
import com.czc.service.impl.BookServiceImpl;
import com.czc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: Book1
 * @description: 静态页面查找
 * @author: Mr.Cai
 * @create: 2021-11-21 21:39
 **/
public class ClientBookServlet extends BaseServlet {
    private BookService bookService = new BookServiceImpl();

    //分页方法
    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1．获取请求的参数　pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置前端地址
        page.setUrl("client/clientBookServlet?action=page");
        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

    //查询价格分页方法
    protected void pageByPrice(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //1．获取请求的参数　pageNo和pageSize min max
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        int min = WebUtils.parseInt(req.getParameter("min"), 0);
        int max = WebUtils.parseInt(req.getParameter("max"), Integer.MAX_VALUE);

        //2.调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.pageByPrice(pageNo, pageSize, min, max);

        StringBuilder stringBuilder = new StringBuilder("client/clientBookServlet?action=pageByPrice");
        //再加个之间的书存放到分页中
        if (req.getParameter("min") != null) {
            stringBuilder.append("&max=").append(req.getParameter("min"));
        }
        if (req.getParameter("max") != null) {
            stringBuilder.append("&max=").append(req.getParameter("max"));
        }

        //设置前端地址
        page.setUrl(stringBuilder.toString());
        //3.保存page对象到Request域中
        req.setAttribute("page", page);
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/client/index.jsp").forward(req, resp);
    }

}
