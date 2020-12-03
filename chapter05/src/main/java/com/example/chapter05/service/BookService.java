package com.example.chapter05.service;

import com.example.chapter05.dao.BookDao;
import com.example.chapter05.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao){
        this.bookDao = bookDao;
    }

    public int addBook(Book book){
        return bookDao.addBook(book);
    }

    public Book getBookById(int id){
        return bookDao.getBookById(id);
    }

    public int updateBook(Book book){
        return bookDao.updateBook(book);
    }

    public int deleteBookById(Integer id){
        return bookDao.deleteBookById(id);
    }

    public List<Book> getAllBooks(){
        return bookDao.getAllBooks();
    }

    public int getMaxBookId(){
        return bookDao.getMaxBookId();
    }
}
