package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.entity.ArticleType;
import com.example.data.service.IArticleTypeService;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/3/27.
 */
@Service("articleTypeService")
public class ArticleTypeServiceImpl extends BaseServiceImpl<ArticleType, Long> implements IArticleTypeService {

    public List<ArticleType> listByUser(String userId) {
        return baseDao.listByWhere(WHERE_BY_USER, userId);
    }
}
