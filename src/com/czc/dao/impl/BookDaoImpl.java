package com.czc.dao.impl;

import com.czc.dao.BookDao;
import com.czc.pojo.Book;

import java.util.List;

/**
 * @program: book
 * @description:
 * @author: Mr.Cai
 * @create: 2021-11-18 19:55
 **/
public class BookDaoImpl extends BaseDao implements BookDao {

    @Override
    public int addBook(Book book) {
        String sql = "insert into t_book(id , name , author , price , sales , stock , img_path) values(?,?,?,?,?,?,?)";
        return updata(sql, book.getId(), book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath());
    }

    @Override
    public int deleteBookById(Integer id) {
        String sql = "delete from t_book where id=?";
        return updata(sql, id);
    }

    @Override
    public int updateBook(Book book) {
        String sql = "update  t_book set name=? , author=? , price=? , sales=? , stock=? , img_path=? where id=?";
        return updata(sql, book.getName(), book.getAuthor(), book.getPrice(), book.getSales(), book.getStock(), book.getImgPath(), book.getId());
    }


    @Override
    public Book queryBookById(Integer id) {
        String sql = "select id , name , author , price , sales , stock , img_path imgPath from t_book where id=?";
        return queryForOne(Book.class, sql, id);
    }

    @Override
    public List<Book> queryBooks() {
        String sql = "select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`imgPath from t_book";
        return queryForList(Book.class, sql);
    }

    @Override
    public Integer queryForPageTotalCount() {
        String sql = "select count(*) from t_book";
        //所有进本类型的父类
        Number count = (Number) queryForSingleValue(sql);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItems(int begin, int pageSize) {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`imgPath from t_book limit ?,?";
        return queryForList(Book.class,sql,begin,pageSize);
    }

    @Override
    public Integer queryForPageTotalCountByPrice(int min, int max) {
        String sql = "select count(*) from t_book where price between ? and ?";
        //所有进本类型的父类
        Number count = (Number) queryForSingleValue(sql,min,max);
        return count.intValue();
    }

    @Override
    public List<Book> queryForPageItemsByPrice(int begin, int pageSize, int min, int max) {
        String sql="select `id` , `name` , `author` , `price` , `sales` , `stock` , `img_path`imgPath from t_book where price between ? and ? order by price limit ?,?";
        return queryForList(Book.class,sql,min,max,begin,pageSize);
    }

}
