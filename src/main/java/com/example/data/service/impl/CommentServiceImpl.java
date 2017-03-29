package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.entity.Comment;
import com.example.data.service.ICommentService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service("commentService")
public class CommentServiceImpl extends BaseServiceImpl<Comment, String> implements ICommentService {
}
