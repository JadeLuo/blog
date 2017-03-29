package com.example.data.contrlor;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Administrator on 2017/3/13.
 */
@Controller
@RequestMapping("/blog")
public class Blog {
    public String index() {
        return "/blog/index";
    }
}
