package com.example.data.base.impl;

import com.example.data.base.ListBaseDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.ColumnMapRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Table;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository
public class ListBaseDaoImpl<T, PK extends Serializable> extends CrudBaseDaoImpl<T, PK> implements ListBaseDao<T, PK> {
    private static final String SELECT_ALL = "SELECT * FROM ";
    private static final String SEELECT_COUNT = "SELECT count(id) as NUM FROM ";

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private RowMapper<T> getRowMapper(Class<T> clazz) {
        return new BeanPropertyRowMapper<T>(clazz);
    }

    public Class<T> getEntity() {
        ParameterizedType pt = (ParameterizedType) this.getClass().getGenericSuperclass();
        return (Class<T>) pt.getActualTypeArguments()[0];
    }

    private String getTableName() {
        return getEntity().getAnnotation(Table.class).name() + " ";
    }

    @Transactional(readOnly = true)
    public List<T> listByWhere(String where, Object... para) {
        if (where == null || where.equals("")) {
            return listAll();
        }
        return jdbcTemplate.query(SELECT_ALL + getTableName() + where, getRowMapper(getEntity()), para);
    }

    public List<Map<String, Object>> mapByWhere(String select, String where, Object... para) {
        ColumnMapRowMapper clomap = new ColumnMapRowMapper();
        return jdbcTemplate.queryForList(select + where, para);
    }

    public List<T> listAll() {
        String name = getTableName();
        RowMapper rowMapper = getRowMapper(getEntity());
        return jdbcTemplate.query(SELECT_ALL + getTableName(), getRowMapper(getEntity()));
    }

    public List<T> listAll(Sort sort) {
        return null;
    }

    @Transactional(readOnly = true)
    public long count(String where, Object... para) {
        if (where != null && !where.equals("")) {
            return (Long) jdbcTemplate.queryForObject(SEELECT_COUNT + getTableName() + where, Long.class, para);
        }
        return count();
    }

    public long count() {
        Object o = jdbcTemplate.queryForObject(SEELECT_COUNT + getTableName(), Long.class);
        return 0;

    }
}
