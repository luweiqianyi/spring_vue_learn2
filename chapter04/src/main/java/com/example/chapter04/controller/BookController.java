package com.example.chapter04.controller;

import com.example.chapter04.entity.Book;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
@RestController
public class BookController {
    @GetMapping("/book")
    //@ResponseBody 频繁使用该注解时可以用@RestController来替代
    public Book book(){
        Book book = new Book();
        book.setAuthor("罗贯中");
        book.setName("三国演义");
        book.setPrice(100.0f);
        book.setPublicationDate(new Date());
        return book;
    }
}
