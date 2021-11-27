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
import java.util.List;

/**
 * @program: book
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-18 23:03
 **/
public class BookServlet extends BaseServlet {
    //连接服务层
    private BookService bookService = new BookServiceImpl();

    protected void add(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //现在所在页数
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 0);
        pageNo += 1;
        //获取请求的参数封装成一个Book对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        //保存图书
        bookService.addBook(book);
        //跳转到图书列表页面
//        req.getRequestDispatcher("manager/bookServlet?action=list").forward(req,resp);//请求转发 按下F5浏览器会发起最后一次请求
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo=" + pageNo);//请求重定向
    }

    protected void delete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameter("id")为字符串 转为int
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //执行删除操作
        bookService.deleteBookById(id);
        //转发重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void update(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //生成对象
        Book book = WebUtils.copyParamToBean(req.getParameterMap(), new Book());
        System.out.println(book);
        System.out.println(book.getId() + "=========================");
        //执行更改操作
        bookService.updateBook(book);
        //转发重定向
        resp.sendRedirect(req.getContextPath() + "/manager/bookServlet?action=page&pageNo="+req.getParameter("pageNo"));
    }

    protected void getBook(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //req.getParameter("id")为字符串 转为int
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //执行删除操作
        Book book = bookService.queryBookById(id);
        //.把图书保存到request域中
        req.setAttribute("book", book);
        //请求转发
        req.getRequestDispatcher("/pages/manager/book_edit.jsp").forward(req, resp);
    }

    protected void list(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1.通过bookService查询所有图书
        List<Book> books = bookService.queryBooks();
        //2.把全部图书保存到request域中
        req.setAttribute("books", books);
        //3.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }

    protected void page(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //1．获取请求的参数　pageNo和pageSize
        int pageNo = WebUtils.parseInt(req.getParameter("pageNo"), 1);
        int pageSize = WebUtils.parseInt(req.getParameter("pageSize"), Page.PAGE_SIZE);
        //2.调用BookService.page(pageNo,pageSize):Page对象
        Page<Book> page = bookService.page(pageNo, pageSize);
        //设置后端地址
        page.setUrl("manager/bookServlet?action=page");
        //3.保存page对象到Request域中
        req.setAttribute("page", page);//美女:xzh1849364592
        //4.请求转发到/pages/manager/book_manager.jsp页面
        req.getRequestDispatcher("/pages/manager/book_manager.jsp").forward(req, resp);
    }
}
