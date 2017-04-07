package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.dao.ICommentDao;
import com.example.data.entity.Comment;
import com.example.data.service.ICommentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements ICommentService {

    @Resource
    private ICommentDao commentDao;

    public List<Comment> ListByArticleId (String articleid) {

        return commentDao.findByArticleId (articleid);
    }
}
