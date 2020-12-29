package com.example.chaptermybatis.service;

import com.example.chaptermybatis.entity.Book;
import com.example.chaptermybatis.mapper.BookMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    BookMapper bookMapper;

    @Autowired
    BookService(BookMapper mapper){
        this.bookMapper = mapper;
    }

    int addBook(Book book) {
        return bookMapper.addBook(book);
    }

    int deteBookById(Integer id){
        return bookMapper.deteBookById(id);
    }

    int updateBookById(Book book){
        return bookMapper.updateBookById(book);
    }

    Book getBookById(){
        return bookMapper.getBookById();
    }

    List<Book> getAllBooks(){
        return bookMapper.getAllBooks();
    }
}
