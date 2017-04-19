package com.example.data.service;

import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface IArticleService extends IBaseService<Article, String> {
    Page<Article> serchByKey(Pageable pageable, String key);

    Page<Article> PageByUser (Pageable pageable,String userID);

}
