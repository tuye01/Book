package com.czc.dao.impl;

import com.czc.dao.OrderDao;
import com.czc.pojo.Order;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @program: Book1
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-25 00:42
 **/
public class OrderDaoImpl extends BaseDao implements OrderDao {

    @Override
    public int saveOrder(Order order) {
        String sql = "insert into t_order(order_id ,create_time,price,status,user_id  ) values (?,?,?,?,?)";
        return updata(sql, order.getOrderId(), order.getCreateTime(), order.getPrice(), order.getStatus(), order.getUserId());
    }
}
