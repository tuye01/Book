package com.czc.test;

import com.czc.pojo.Cart;
import com.czc.pojo.CartItem;
import com.czc.pojo.OrderItem;
import com.czc.service.impl.OrderServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class OrderServiceImplTest {

    @Test
    public void createOrder() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        OrderServiceImpl orderService = new OrderServiceImpl();
        orderService.createOrder(cart,1);
    }
}