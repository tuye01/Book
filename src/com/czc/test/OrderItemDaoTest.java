package com.czc.test;

import com.czc.dao.impl.OrderItemDaoImpl;
import com.czc.pojo.OrderItem;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderItemDaoTest {

    @Test
    public void saveOrderItem() {
        OrderItemDaoImpl orderItemDao = new OrderItemDaoImpl();
        orderItemDao.saveOrderItem(new OrderItem(null,"jackandose",1,new BigDecimal(100),new BigDecimal(100),"23456"));
        orderItemDao.saveOrderItem(new OrderItem(null,"2jackandose",1,new BigDecimal(100),new BigDecimal(100),"23456"));
    }
}