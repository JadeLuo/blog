package com.example.data.base;

import com.alibaba.fastjson.JSON;
import com.example.data.dao.TestDao;
import com.example.data.entity.User;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PageBaseDaoTest {

    @Resource
    private TestDao userDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void pageAll() throws Exception {
        Pageable pageable = new PageRequest(0, 5);
        Page<User> list = userDao.PageAll(pageable);
        list.getSize();
        System.out.println(JSON.toJSONString(list) + "-------------------------------------------------------");
    }

    @Test
    public void pageByWhere() throws Exception {
        Pageable pageable = new PageRequest(3, 10);
        Page<User> list = userDao.PageByWhere(pageable, "where user_name like ? or real_name like ?", "%1%", "%1%");
        System.out.print(JSON.toJSONString(list));
    }

    @Test
    public void pageMapByWhere() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Map<String, Object>> page = userDao.PageMapByWhere(pageable, "select user_name from t_user ", " where user_name=?", "success");
        Assert.assertNotNull(page);
        System.out.print(JSON.toJSONString(page));
    }

}