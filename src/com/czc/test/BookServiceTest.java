package com.czc.test;

import com.czc.pojo.Book;
import com.czc.pojo.Page;
import com.czc.service.BookService;
import com.czc.service.impl.BookServiceImpl;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

public class BookServiceTest {
private BookService bookService=new BookServiceImpl();
    @Test
    public void addBook() {
        bookService.addBook(new Book(null,"大好","小菜",new BigDecimal(23477),15000,0,null));
    }

    @Test
    public void deleteBookById() {
        bookService.deleteBookById(22);
    }

    @Test
    public void updateBook() {
        bookService.updateBook(new Book(20,"大好","小菜",new BigDecimal(23477),15000,0,null));
    }

    @Test
    public void queryBookById() {
        System.out.println(bookService.queryBookById(20));
    }

    @Test
    public void queryBooks() {
        for (Book queryBook:bookService.queryBooks()){
            System.out.println(queryBook);
        }
    }
    @Test
    public void page() {
        System.out.println(bookService.page(1,Page.PAGE_SIZE));
    }

    @Test
    public void pageByPrice() {
        System.out.println(bookService.pageByPrice(1,Page.PAGE_SIZE,1,50));
    }


}