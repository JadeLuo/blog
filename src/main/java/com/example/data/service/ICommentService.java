package com.example.data.service;


import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.Comment;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface ICommentService extends IBaseService<Comment, String> {

    List<Comment> ListByArticleId (String articleid);
}
