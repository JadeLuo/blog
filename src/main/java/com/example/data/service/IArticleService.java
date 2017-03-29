package com.example.data.service;

import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * Created by Administrator on 2017/3/10.
 */
public interface IArticleService extends IBaseService<Article, String> {
    Page<Article> serchByKey(Pageable pageable, String key);
}
