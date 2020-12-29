package com.example.chaptermybatis.controller;

import com.example.chaptermybatis.entity.Book;
import com.example.chaptermybatis.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {
    BookService bookService;

    @Autowired
    BookController(BookService service){
        this.bookService = service;
    }
    @GetMapping("/books")
    public String getBooks() {
       // List<Book> books =
    }
}
