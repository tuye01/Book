package com.czc.service.impl;

import com.czc.dao.BookDao;
import com.czc.dao.impl.BookDaoImpl;
import com.czc.pojo.Book;
import com.czc.pojo.Page;
import com.czc.service.BookService;

import java.util.List;

/**
 * @program: book
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-18 21:24
 **/
public class BookServiceImpl implements BookService {
    //与dao持久层联系
    private BookDao bookDao = new BookDaoImpl();

    @Override
    public void addBook(Book book) {
        bookDao.addBook(book);
    }

    @Override
    public void deleteBookById(Integer id) {
        bookDao.deleteBookById(id);
    }

    @Override
    public void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    @Override
    public Book queryBookById(Integer id) {
        return bookDao.queryBookById(id);
    }

    @Override
    public List<Book> queryBooks() {
        return bookDao.queryBooks();
    }

    @Override
    public Page<Book> page(int pageNo, int pageSize) {
        Page<Book> page = new Page<>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页码的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCount();
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0) {
            ++pageTotal;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //防注入 在浏览器地址输入
        //数据边界的有效检查
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo >= pageTotal) {
            pageNo = pageTotal;
        }
        //将值再次写入
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItems(begin, pageSize);
        //设置当前页数据
        page.setItems(items);

        return page;
    }

    @Override
    public Page<Book> pageByPrice(int pageNo, int pageSize, int min, int max) {
        Page<Book> page = new Page<>();

        //设置当前页码
        page.setPageNo(pageNo);
        //设置当前页码的数量
        page.setPageSize(pageSize);
        //求总记录数
        Integer pageTotalCount = bookDao.queryForPageTotalCountByPrice(min,max);
        //设置总记录数
        page.setPageTotalCount(pageTotalCount);
        //求总页码
        Integer pageTotal = pageTotalCount / pageSize;
        if (pageTotalCount % pageSize != 0) {
            ++pageTotal;
        }
        //设置总页码
        page.setPageTotal(pageTotal);

        //防注入 在浏览器地址输入
        //数据边界的有效检查
        if (pageNo < 1) {
            pageNo = 1;
        }
        if (pageNo >= pageTotal) {
            pageNo = pageTotal;
        }
        //将值再次写入
        page.setPageNo(pageNo);
        //求当前页数据的开始索引
        int begin = (page.getPageNo() - 1) * pageSize;
        //求当前页数据
        List<Book> items = bookDao.queryForPageItemsByPrice(begin, pageSize,min,max);
        //设置当前页数据
        page.setItems(items);

        return page;
    }
}
