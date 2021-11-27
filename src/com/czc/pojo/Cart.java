package com.czc.pojo;

import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @program: Book1
 * @description: 购物车对象
 * @author: Mr.Cai
 * @create: 2021-11-23 21:01
 **/
public class Cart {
    //    private Integer totalCount;
//    private BigDecimal totalPrices;
    private Map<Integer, CartItem> items = new LinkedHashMap<>();


    /**
     * 添加商品项
     *
     * @param cartItem
     */
    public void addItem(CartItem cartItem) {
        //查看购物车有没有此本书 若有数量加1 ，如没有直接放放到集合中
        CartItem item = items.get(cartItem.getId());

        if (item == null) {
            //之前没有添加过此书
            items.put(cartItem.getId(), cartItem);
        } else {
            //已经添加过 只需数量加1
            item.setCount(item.getCount() + 1);
            //更新总价
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(item.getCount())));
        }
    }

    /**
     * 删除商品项
     *
     * @param id
     */
    public void deleteItem(Integer id) {
        items.remove(id);
    }

    /**
     * 清空购物车
     */
    public void clear() {
        items.clear();
    }

    /**
     * 修改商品数量
     *
     * @param id
     */
    public void updateCount(Integer id, Integer count) {
        //查看购物车有没有此本书 若有数量加1 ，如没有直接放放到集合中
        CartItem item = items.get(id);

        if (item != null) {
            //已经添加过 只需数量加1
            item.setCount(count);
            //更新总价
            item.setTotalPrice(item.getPrice().multiply(new BigDecimal(count)));
        }
    }

    public Integer getTotalCount() {
        Integer totalCount = 0;
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalCount += entry.getValue().getCount();
        }
        return totalCount;
    }


    public BigDecimal getTotalPrices() {
        BigDecimal totalPrice = new BigDecimal(0);
        for (Map.Entry<Integer, CartItem> entry : items.entrySet()) {
            totalPrice = totalPrice.add(entry.getValue().getTotalPrice());
        }
        return totalPrice;
    }


    public Map<Integer, CartItem> getItems() {
        return items;
    }

    public void setItems(Map<Integer, CartItem> items) {
        this.items = items;
    }


    @Override
    public String toString() {
        return "Cart{" +
                "totalCount=" + getTotalCount() +
                ", totalPrices=" + getTotalPrices() +
                ", items=" + items +
                '}';
    }
}
