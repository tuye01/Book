package com.czc.test;

import com.czc.dao.OrderDao;
import com.czc.dao.impl.OrderDaoImpl;
import com.czc.pojo.Order;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.*;

public class OrderDaoTest {

    @Test
    public void saveOrder() {
        OrderDao orderDao = new OrderDaoImpl();
        Order order = new Order("2356", new Date(), new BigDecimal(100), 0, 2);
        System.out.println(order.getOrderId()+" "+ order.getCreateTime()+" "+ order.getPrice()+" "+order.getStatus()+" "+order.getUserId());
        orderDao.saveOrder(order);
    }
}