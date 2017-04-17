package com;

import com.example.data.DemoApplication;
import com.example.data.contrlor.LoginCtrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.annotation.Resource;

import static org.junit.Assert.*;

/**
 * Created by wanghuiwen on 17-4-13.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = DemoApplication.class)
@WebAppConfiguration
public class  LoginCtrlTest {
    @Resource
    private LoginCtrl loginCtrl;
    @Test
    public void sendSMS() throws Exception {
//        String result= loginCtrl.sendSMS();
//        System.out.println("--------------------------"+result);
    }
    @Autowired
    private JavaMailSender mailSender;

    /**
     * 修改application.properties的用户，才能发送。
     */
    @Test
    public void sendSimpleEmail(){
//        SimpleMailMessage message = new SimpleMailMessage();
//
//        message.setFrom("wanghuiwen312@sina.com");//发送者.
//        message.setTo("1026076331@qq.com");//接收者.
//        message.setSubject("测试邮件（邮件主题）");//邮件主题.
//        message.setText("这是邮件内容");//邮件内容.
//
//        mailSender.send(message);//发送邮件
    }

}