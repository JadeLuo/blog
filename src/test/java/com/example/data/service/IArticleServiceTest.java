package com.example.data.service;

import com.alibaba.fastjson.JSON;
import com.example.data.DemoApplication;
import com.example.data.dao.IArticleDao;
import com.example.data.service.impl.ArticleServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2017/3/29.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = DemoApplication.class)
public class IArticleServiceTest {
    @Resource
    IArticleDao articleDao;
    @Resource
    ArticleServiceImpl articleService;

    @Test
    public void serchByKey() throws Exception {
        Pageable pageable = new PageRequest(0, 10);

        System.out.print(JSON.toJSONString(articleDao.listByWhere(" where summary = ?", "asdf")));
    }

    @Test
    public void map() throws Exception {
        Pageable pageable = new PageRequest(0, 10);

        System.out.print(JSON.toJSONString(articleDao.mapByWhere("select id,title from t_article", "")));
    }


    @Test
    public void count() {
        System.out.print(JSON.toJSONString(articleDao.count("where summary = ? ", "asdf")));
    }

}