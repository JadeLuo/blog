package com.example.data.dao;

import com.example.data.base.impl.PageBaseDaoImpl;
import com.example.data.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository("userDao")
public class UserDaoImpl extends PageBaseDaoImpl<User, String> implements UserDao {
}
