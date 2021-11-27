package com.czc.dao;

import com.czc.pojo.Order;
import com.czc.pojo.OrderItem;

public interface OrderItemDao {
    public int saveOrderItem(OrderItem orderItem);
}
