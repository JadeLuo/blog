package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Repository("commentDao")
public interface ICommentDao extends BaseRepository<Comment, String> {

    List<Comment> findByArticleId (String articleid);
}
