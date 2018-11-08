package com.example.data.base.controller;

import com.example.data.common.ImageUploadUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2017/4/6.
 */
@Controller
public class Upload {

    @Value("${image.host}")
    private String host;
    @Value("${image.port}")
    private String port;

    @RequestMapping(value = "/upload")
    public void uploadImage (HttpServletRequest request,HttpServletResponse response) {

        try {
            ImageUploadUtil.ckeditor (request,response,"uploadfile/",host,port);
        } catch (IOException e) {
            e.printStackTrace ();
        }
    }

}
