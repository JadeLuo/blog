package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.ArticleType;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/27.
 */
@Repository("articleTypeDao")
public interface IArticleTypeDao extends BaseRepository<ArticleType, Long> {
}
