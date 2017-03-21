package com.example.data.base.impl;

import com.example.data.User;
import com.example.data.base.IBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.persistence.criteria.Root;
import java.util.List;

/**
 * Created by Administrator on 2017/3/21.
 */
public class BaseDaoImpl<T,PK> implements IBaseDao<T,PK> {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<T> find(T t,String sql, String where, Object... para) {
        RowMapper<T> rowMapper = new BeanPropertyRowMapper<T>();
        return jdbcTemplate.query(sql+where, rowMapper,para);
    }

    public Page<T> find(Pageable pageable, T t, Sort sort, Object... para) {
        return null;
    }

    public T findById(PK id) {
        return null;
    }

    public int count(Pageable pageable, T t, Sort sort, Object... para) {
        return 0;
    }
}
