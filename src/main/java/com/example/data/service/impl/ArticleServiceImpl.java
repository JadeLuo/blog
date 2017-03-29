package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.dao.IArticleDao;
import com.example.data.entity.Article;
import com.example.data.service.IArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/3/10.
 */
@Service("articleService")
public class ArticleServiceImpl extends BaseServiceImpl<Article, String> implements IArticleService {
    @Resource
    private IArticleDao articleDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Page<Article> serchByKey(Pageable pageable, String key) {
        return articleDao.PageByWhere(pageable, IArticleDao.WHERE_BY_KEYWORD, key, key, key);
    }

    public List getlist() {
        RowMapper rowMapper = new BeanPropertyRowMapper(Article.class);
        return jdbcTemplate.query("select * from t_user ", rowMapper);
    }
}
