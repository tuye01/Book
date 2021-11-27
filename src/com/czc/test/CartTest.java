package com.czc.test;

import com.czc.pojo.Cart;
import com.czc.pojo.CartItem;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Map;

import static org.junit.Assert.*;

public class CartTest {

    @Test
    public void addItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(2,"rose",1,new BigDecimal(88),new BigDecimal(88)));
        System.out.println(cart);
    }

    @Test
    public void deleteItem() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(2,"rose",1,new BigDecimal(88),new BigDecimal(88)));
        cart.deleteItem(1);
        System.out.println(cart);
    }

    @Test
    public void clear() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(2,"rose",1,new BigDecimal(88),new BigDecimal(88)));
        cart.clear();
        System.out.println(cart);
    }

    @Test
    public void updateCount() {
        Cart cart = new Cart();
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(1,"jack",1,new BigDecimal(77),new BigDecimal(77)));
        cart.addItem(new CartItem(2,"rose",1,new BigDecimal(88),new BigDecimal(88)));
        cart.updateCount(1,3);
        System.out.println(cart);
    }
}