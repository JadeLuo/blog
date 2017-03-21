package com.example.data.base;

import com.example.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
public interface IBaseDao<T,PK> {

    /**
     *
     */

    List<T> find(T t, String sql ,String where ,Object... para);

    Page<T> find(Pageable pageable, T t, Sort sort, Object... para);

    T findById(PK id);

    int count(Pageable pageable, T t, Sort sort, Object... para);



}
