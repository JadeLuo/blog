package com.example.data.base;

import com.alibaba.fastjson.JSON;
import com.example.data.dao.UserDao;
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
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class PageBaseDaoTest {

    @Resource
    private UserDao userDao;

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void pageAll() throws Exception {
        List<User> list = userDao.listAll();
        System.out.println(list.size() + "-------------------------------------------------------");
    }

    @Test
    public void pageByWhere() throws Exception {

    }

    @Test
    public void pageMapByWhere() throws Exception {
        Pageable pageable = new PageRequest(0, 10);
        Page<Map<String, Object>> page = userDao.PageMapByWhere(pageable, "select user_name from t_user ", " where user_name=?", "success");
        Assert.assertNotNull(page);
        System.out.print(JSON.toJSONString(page));
    }

}