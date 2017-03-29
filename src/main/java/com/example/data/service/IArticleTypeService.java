package com.example.data.service;


import com.example.data.base.baseservice.IBaseService;
import com.example.data.entity.ArticleType;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */

public interface IArticleTypeService extends IBaseService<ArticleType, Long> {
    String WHERE_BY_USER = " WHERE user_id = ?";

    List<ArticleType> listByUser(String userId);

}
