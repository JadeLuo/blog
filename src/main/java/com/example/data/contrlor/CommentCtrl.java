package com.example.data.contrlor;

import com.alibaba.fastjson.JSON;
import com.example.data.base.controller.BaseControllerImpl;
import com.example.data.entity.Comment;
import com.example.data.service.ICommentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by wanghuiwen on 17-3-11.
 */
@Controller
@RequestMapping(value = "/comment")
public class CommentCtrl extends BaseControllerImpl<Comment, String> {

    @Resource
    private ICommentService commentService;

    @Override
    protected String save (@Valid Comment comment,BindingResult result,Model model,HttpServletRequest request) {

        comment = JSON.parseObject (request.getParameter ("comment"),Comment.class);
        comment.setCommentDate (new Date ());
        if (getSessionUser () == null) return "登陆后才可以评论";
        comment.setUserId (getSessionUser ().getId ());
        return super.save (comment,result,model,request);
    }

    @RequestMapping(value = "/list")
    public String ListByArticleId (@RequestParam(defaultValue = "") String id,Model model) {

        List<Comment> comments = commentService.ListByArticleId (id);

        model.addAttribute ("comments",comments);

        return "/blog/comment/comment";
    }
}
