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
        return jdbcTemplate.update("insert into book(id,name,author) values(?,?,?)",
                book.getId(),
                book.getName(),
                book.getAuthor());
    }

    public Book getBookById(int id){
        return jdbcTemplate.queryForObject("select * from book where id = ?",
                new BeanPropertyRowMapper<>(Book.class),
                id);
    }

    public int updateBook(Book book){
        // MySQL会正确执行id在数据库中不存在的update SQL，但是不会向表中插入数据
        return jdbcTemplate.update("update book set name=?,author=? where id=?"
                ,book.getName()
                ,book.getAuthor()
                ,book.getId());
    }

    public int insertBook(Book book){
        return jdbcTemplate.update("INSERT INTO book (id, name, author)" +
                        "VALUES (?, ?, ?)",
                book.getId(),
                book.getName(),
                book.getAuthor());
    }

    public int deleteBookById(Integer id){
        return jdbcTemplate.update("delete from book where id=?",id);
    }

    public List<Book> getAllBooks(){
        return jdbcTemplate.query("select * from book",
                new BeanPropertyRowMapper<>(Book.class));
    }

    public int getMaxBookId(){
        return jdbcTemplate.queryForObject("select max(id) from book",Integer.class);
    }
}
