package com.czc.web;

import com.czc.pojo.Book;
import com.czc.pojo.Cart;
import com.czc.pojo.CartItem;
import com.czc.service.BookService;
import com.czc.service.impl.BookServiceImpl;
import com.czc.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: Book1
 * @description: 购物车
 * @author: Mr.Cai
 * @create: 2021-11-24 01:04
 **/
public class CartServlet extends BaseServlet {

    private BookService bookService = new BookServiceImpl();

    /**
     * 加入购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void addItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        //获取请求的参数 商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);

        //调用bookService.queryBookById(id):Book得到图书信息
        Book book = bookService.queryBookById(id);

        //把图书信息转换达成CartItem商品项
        CartItem cartItem = new CartItem(book.getId(), book.getName(), 1, book.getPrice(), book.getPrice());

        //调用Cart.add(CartItem)添加商品项
        //从Session中获取的购物车  一次会话内 有效
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart == null) {
            cart = new Cart();
            req.getSession().setAttribute("cart", cart);
        }
        cart.addItem(cartItem);
        System.out.println(cart);

        //将最后加入的书名存到Session域中
        req.getSession().setAttribute("lastName", cartItem.getName());

        //重定向回原来的商品列表页面
        resp.sendRedirect(req.getHeader("Referer"));

    }

    /**
     * 删除商品项
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void deleteItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        System.out.println(id + "-------------------------------------");
        System.out.println(cart.getItems().get(id));
        if (cart != null) {
            //删除购物车里的商品项
            cart.deleteItem(id);
            // 重定向回原来购物车展示面
            resp.sendRedirect(req.getHeader("Referer"));
        }
        //

    }


    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void clearItem(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //删除购物车里的商品项
            cart.clear();
            // 重定向回原来购物车展示面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }

    /**
     * 清空购物车
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    protected void updataCount(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //获取商品编号 商品数量
        int id = WebUtils.parseInt(req.getParameter("id"), 0);
        int count = WebUtils.parseInt(req.getParameter("count"), 1);
        System.out.println(count);
        //获取购物车对象
        Cart cart = (Cart) req.getSession().getAttribute("cart");
        if (cart != null) {
            //修改商品数量
            cart.updateCount(id, count);
            // 重定向回原来购物车展示面
            resp.sendRedirect(req.getHeader("Referer"));
        }
    }
}
