package com.example.chapter04.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

// 启用这个配置,那么application.properties中的关于这部分的配置就失效了
// @Configuration
//public class UploadConfig {
//    //@Bean(name = "multipartResolver")
//    public MultipartResolver multipartResolver(){
//        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
//        resolver.setDefaultEncoding("UTF-8");
//        // 推迟文件解析，以便于在FileUploadController中捕获文件异常
//        resolver.setResolveLazily(true);
//        resolver.setMaxInMemorySize(100*1024);
//        resolver.setMaxUploadSize(100*1024*1024);
//        return resolver;
//    }
//}
