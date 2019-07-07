package com.sdut.onlinestore.controller;

import com.sdut.onlinestore.configuration.ServerConfig;
import com.sdut.onlinestore.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.mail.internet.InternetAddress;
import javax.servlet.Servlet;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.InetAddress;
import java.util.UUID;

@RestController
@RequestMapping("/imageUpload")
@CrossOrigin
public class FileController {
	@Autowired
    private ServerConfig serverConfig;

    @PostMapping(value = "/upload")
    @ResponseBody
    public Result uploadFile(HttpServletRequest req, @RequestParam("file") MultipartFile file) throws  Exception {
    	Result result = new Result();
    	String url_suffix ="http://" + InetAddress.getLocalHost().getHostAddress() +"/image/";
        System.err.println(url_suffix);
        String fileName = file.getOriginalFilename();
        String suffix = fileName.substring(fileName.lastIndexOf('.'));
        String newFileName = UUID.randomUUID().toString().replace("-", "") + suffix;
        String path = "D:\\onlinestorepic\\";
//        String path = System.getProperty("user.dir")+"/src/main/resources/static/pic/";
//        File dir = new File(path);
//        if(!dir.exists()) {
//            dir.mkdir();
//        }
        System.err.println(path+"------------------------");

        File newFile = new File(path + newFileName);
        try {
            file.transferTo(newFile);
            result.setSuccess(true);
            result.setData(newFileName);
            return result;
        }
        catch (Exception e){
            e.printStackTrace();
            result.setSuccess(false);
            return result;
        }
    }
}
