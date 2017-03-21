package com.example.ctrl;

import com.example.data.base.IBaseDao;
import com.example.data.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 *
 */
@Controller
public class Ctrl {
    @Resource
    private IBaseDao dao;

    @RequestMapping(value = "/show")
    @ResponseBody
    public  List<User> ctrl(){
       List<User> u= dao.test("select * from t_user ", "",User.class,null);
        return u;
    }




    }
