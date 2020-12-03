package com.example.chapter05.controller;

import com.example.chapter05.entity.Book;
import com.example.chapter05.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/addBook")
    public void addBook(){
        Book book = new Book();
        int nId = bookService.getMaxBookId();
        if(nId == 0)
        {
            nId = 1;
        }
        book.setId(nId);
        book.setName(new StringBuilder("book_id_").append(nId).toString());
        book.setAuthor(new StringBuilder("author_name_").append(nId).toString());
        bookService.addBook(book);
    }

    @GetMapping("/addBook2")
    public void addBook2(@RequestParam("name")String name,@RequestParam("author")String author){
        Book book = new Book();
        int nId = bookService.getMaxBookId();
        if(nId == 0)
        {
            nId = 1;
        }
        book.setId(nId);
        book.setName(name);
        book.setAuthor(author);
        bookService.addBook(book);
    }

    @GetMapping("/queryBook")
    public String queryBookById(@RequestParam("id") int id){
        return bookService.getBookById(id).toString();
    }

    @GetMapping("/updateBook")
    public void updateBook(@RequestParam("name") String name,@RequestParam("author") String author){
        int newId = bookService.getMaxBookId()+1;
        Book book = new Book();
        book.setId(newId);
        book.setName(name);
        book.setAuthor(author);
        bookService.updateBook(book);
    }

    @GetMapping("/deleteBook")
    public void deleteBookById(@RequestParam("id") int id){
        List<Book> books = bookService.getAllBooks();
        System.out.println("Before Delete Action: ");
        for (Book book:books) {
            System.out.println(book.toString());
        }

        bookService.deleteBookById(id);

        List<Book> books2 = bookService.getAllBooks();
        System.out.println("After Delete Action: ");
        for (Book book:books2) {
            System.out.println(book.toString());
        }
    }
}
