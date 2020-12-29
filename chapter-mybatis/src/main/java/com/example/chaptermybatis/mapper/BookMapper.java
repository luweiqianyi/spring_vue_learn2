package com.example.chaptermybatis.mapper;

import com.example.chaptermybatis.entity.Book;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface BookMapper {
    int addBook(Book book);
    int deteBookById(Integer id);
    int updateBookById(Book book);
    Book getBookById();
    List<Book> getAllBooks();
}
