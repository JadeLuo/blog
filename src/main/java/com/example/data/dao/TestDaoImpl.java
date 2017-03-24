package com.example.data.dao;

import com.example.data.base.impl.PageBaseDaoImpl;
import com.example.data.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository("test")
public class TestDaoImpl extends PageBaseDaoImpl<User, String> implements TestDao {
}
