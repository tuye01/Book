package com.czc.service;

import com.czc.pojo.Cart;

public interface OrderService {
    public String createOrder(Cart cart,Integer userId);
}
