package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.User;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository("testDao")
public interface TestDao extends BaseRepository<User, String> {
    User findByUserName(String userName);
}
