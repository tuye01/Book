package com.czc.dao.impl;

import com.czc.dao.OrderDao;
import com.czc.dao.OrderItemDao;
import com.czc.pojo.Order;
import com.czc.pojo.OrderItem;

/**
 * @program: Book1
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-25 00:42
 **/
public class OrderItemDaoImpl extends BaseDao implements OrderItemDao {

    @Override
    public int saveOrderItem(OrderItem orderItem) {
        String sql = "insert into t_order_item(name,count,price,total_price ,order_id)values(?,?,?,?,?)";
        return updata(sql, orderItem.getName(), orderItem.getCount(), orderItem.getPrice(), orderItem.getTotalPrice(), orderItem.getOrderId());

    }
}
