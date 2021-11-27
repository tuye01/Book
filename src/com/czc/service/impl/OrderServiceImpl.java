package com.czc.service.impl;

import com.czc.dao.BookDao;
import com.czc.dao.OrderDao;
import com.czc.dao.OrderItemDao;
import com.czc.dao.impl.BookDaoImpl;
import com.czc.dao.impl.OrderDaoImpl;
import com.czc.dao.impl.OrderItemDaoImpl;
import com.czc.pojo.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * @program: Book1
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-25 11:19
 **/
public class OrderServiceImpl implements com.czc.service.OrderService {
    private OrderDao orderDao = new OrderDaoImpl();
    private OrderItemDao orderItemDao = new OrderItemDaoImpl();
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public String createOrder(Cart cart, Integer userId) {
        //订单号的唯一性
        String orderId = System.currentTimeMillis() + "" + userId;
        //创建一个订单对象
        Order order = new Order(orderId, new Date(), cart.getTotalPrices(), 0, userId);
        //保存订单
        orderDao.saveOrder(order);
        //        遍历购物车的每一个商品项
        for (Map.Entry<Integer, CartItem> entry : cart.getItems().entrySet()) {
            //           获得购物车商品想
            CartItem cartItem = entry.getValue();
            //            转化为订单项
            OrderItem orderItem = new OrderItem(null, cartItem.getName(), cartItem.getCount(), cartItem.getPrice(), cartItem.getTotalPrice(), orderId);
            //              保存订单想到数据库
            orderItemDao.saveOrderItem(orderItem);
//            更新库存和销量
            Book book = bookDao.queryBookById(cartItem.getId());
            book.setSales(book.getSales()+cartItem.getCount());
            book.setStock(book.getStock()-cartItem.getCount());
            bookDao.updateBook(book);
        }
//     最后清空购物车
        cart.clear();
        return orderId;
    }
}
