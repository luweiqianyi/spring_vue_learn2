package com.example.chapter05.dao;

import com.example.chapter05.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDao {

    JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDao(JdbcTemplate jdbcTemplate){
        this.jdbcTemplate = jdbcTemplate;
    }

    public int addBook(Book book){
        return jdbcTemplate.update("insert into book(name,author) values(?,?)"
                ,book.getName()
                ,book.getAuthor());
    }

    public Book getBookById(int id){
        return jdbcTemplate.queryForObject("select * from book where id = ?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }

    public int updateBook(Book book){
        return jdbcTemplate.update("update book set name=?,author=? where id=?"
                ,book.getName()
                ,book.getAuthor()
                ,book.getId());
    }

    public int deleteBookById(Integer id){
        return jdbcTemplate.update("delete from book where id=?",id);
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public int getMaxBookId(){
        return jdbcTemplate.getMaxRows();//jdbcTemplate.query("select MAX(?) from book");
    }
}
