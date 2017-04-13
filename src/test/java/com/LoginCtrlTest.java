package com;

import com.example.data.DemoApplication;
import com.example.data.contrlor.LoginCtrl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
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
public class LoginCtrlTest {
    @Resource
    private LoginCtrl loginCtrl;
    @Test
    public void sendSMS() throws Exception {
        String result= loginCtrl.sendSMS();
        System.out.println("--------------------------"+result);
    }

}