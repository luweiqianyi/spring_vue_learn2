package com.example.chapter05.controller;

import com.example.chapter05.entity.Book;
import com.example.chapter05.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller // 这里不能用RestController,Rest的意思是RestBody,所有的return都会在页面上以字符串的方式进行返回
public class BookController {
    BookService bookService;

    @Autowired
    public BookController(BookService bookService){
        this.bookService = bookService;
    }
    @GetMapping("/addBook")
    public String addBook(){
        Book book = new Book();
        int nId = bookService.getMaxBookId();
        if(nId <= 0){ // 数据库中没有数据时返回-1,保证插入时nId从1开始
            nId = 1;
        }else{
            nId = nId+1;
        }

        book.setId(nId);
        book.setName(new StringBuilder("book_id_").append(nId).toString());
        book.setAuthor(new StringBuilder("author_name_").append(nId).toString());
        bookService.addBook(book);

        // return "redirect:/books"也可
        // 从一个controller到另外一个controller必须使用redirect来指定
        return "redirect:books";
    }

    @GetMapping("/addBook2")
    public String addBook2(@RequestParam("name")String name,@RequestParam("author")String author){
        Book book = new Book();
        int nId = bookService.getMaxBookId();
        if(nId <= 0){ // 数据库中没有数据时返回-1,保证插入时nId从1开始
            nId = 1;
        }else{
            nId = nId+1;
        }
        book.setId(nId);
        book.setName(name);
        book.setAuthor(author);
        bookService.addBook(book);
        return "redirect:/books2";
    }

    // 前面将@RestController改成了@Controller
    // 当queryBookById返回时,如果没有正确配置页面路径&&没有为该函数指定@ResponseBody注解
    // 就会返回错误500: Servlet.service() for servlet [dispatcherServlet] in context with path []
    // threw exception [Request processing failed; nested exception is org.thymeleaf.exceptions.TemplateInputException:
    // Error resolving template [Book{id=3, name='book_id_3', author='author_name_3'}],
    // template might not exist or might not be accessible by any of the configured Template Resolvers]
    @ResponseBody
    @GetMapping("/queryBook")
    public String queryBookById(@RequestParam("id") int id){
        return bookService.getBookById(id).toString();
    }

    @ResponseBody
    @GetMapping("/updateBook")
    public void updateBook(@RequestParam("name") String name,@RequestParam("author") String author){
        // 如果ID在数据库中不存在 执行update语句失效
        int newId = bookService.getMaxBookId()+1;
        Book book = new Book();
        book.setId(newId);
        book.setName(name);
        book.setAuthor(author);
        bookService.updateBook(book);
    }

    @GetMapping("/insertBook")
    public String insertBook(@RequestParam("name") String name,@RequestParam("author") String author){
        int newId = bookService.getMaxBookId()+1;
        Book book = new Book();
        book.setId(newId);
        book.setName(name);
        book.setAuthor(author);
        bookService.insertBook(book);
        return "redirect:/books";
    }

    @GetMapping("/deleteBook")
    public String deleteBookById(@RequestParam("id") int id){
        List<Book> books = bookService.getAllBooks();
//        System.out.println("Before Delete Action: ");
//        for (Book book:books) {
//            System.out.println(book.toString());
//        }

        bookService.deleteBookById(id);

        List<Book> books2 = bookService.getAllBooks();
//        System.out.println("After Delete Action: ");
//        for (Book book:books2) {
//            System.out.println(book.toString());
//        }
        return "redirect:/books";
    }

    @GetMapping("/books")
    public ModelAndView books(){
        List<Book> books = bookService.getAllBooks();
        ModelAndView model = new ModelAndView();
        model.addObject("books",books);
        model.setViewName("books");
        return model;
    }

    @ResponseBody
    @GetMapping("/books2")
    public String books2(){
        List<Book> books= bookService.getAllBooks();
        //String retStr = new String();
        StringBuilder stringBuilder = new StringBuilder();
        for (Book book : books){
            stringBuilder.append(book.toString());
            stringBuilder.append("<br>");//换行
        }
        return stringBuilder.toString();
    }
}
