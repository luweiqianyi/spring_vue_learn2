package com.example.chapter04;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.MultipartAutoConfiguration;

// 排除掉原有的MultipartAutoConfiguration配置
@SpringBootApplication//(exclude = {MultipartAutoConfiguration.class})
public class Chapter04Application {
    public static void main(String[] args) {
        SpringApplication.run(Chapter04Application.class, args);
    }

}
