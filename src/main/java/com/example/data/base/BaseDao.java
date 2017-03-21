package com.example.data.base;

import com.example.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;


/**
 * Created by Administrator on 2017/3/21.
 */
@Repository
public class BaseDao<T,PK> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<User> test(String sql, String where, Class c, Object... para){
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<User>(User.class);
        return jdbcTemplate.query(sql+where, rowMapper,para);

    }


}
