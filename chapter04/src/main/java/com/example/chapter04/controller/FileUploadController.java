package com.example.chapter04.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

@RestController
public class FileUploadController {
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy/MM/dd");

    @PostMapping("/upload")
    public String upload(MultipartFile multipartFile, HttpServletRequest httpServletRequest){
        // 将上传的文件的保存路径设置为项目运行目录下的uploadFile文件夹下
        String realPath = httpServletRequest.getSession().getServletContext().getRealPath("/uploadFile/");
        System.out.println("realPath: "+realPath);
        String format = simpleDateFormat.format(new Date());
        File folder = new File(realPath+format);
        System.out.println("folder: "+folder.toString());
        if(!folder.isDirectory()) {
            folder.mkdirs();
        }
        // 加上UUID文件重名
        String oldName = multipartFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString()+
                oldName.substring(oldName.lastIndexOf("."),oldName.length());
        System.out.println("oldName: "+oldName);
        System.out.println("newName: "+newName);

        try{
            // 保存文件
            multipartFile.transferTo(new File(folder,newName));

            // 生成上传文件的访问路径并返回给客户端
            String filePath = httpServletRequest.getScheme()+"://"+
                    httpServletRequest.getServerName()+":"+
                    httpServletRequest.getServerPort()+"/uploadFile/"+
                    format+newName;
            System.out.println("filePath: "+filePath);
            return filePath;
        }catch (IOException e){
            e.printStackTrace();
        }
        return "上传失败";
    }
}
