package com.example.data.contrlor;

import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.entity.Comment;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by wanghuiwen on 17-3-11.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentCtrl extends BaseControllerImpl<Comment, String> {
}
