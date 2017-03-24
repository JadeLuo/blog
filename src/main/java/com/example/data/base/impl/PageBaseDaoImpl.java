package com.example.data.base.impl;

import com.example.data.base.PageBaseDao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 */
@Repository
public class PageBaseDaoImpl<T, PK extends Serializable> extends ListBaseDaoImpl<T, PK> implements PageBaseDao<T, PK> {

    public Page<T> PageAll(Pageable pageable) {
        return new PageImpl<T>(listAll(), pageable, count());
    }

    public Page<T> PageByWhere(Pageable pageable, String where, Object... para) {
        where = BulidSql(pageable, where);
        return new PageImpl<T>(listByWhere(where, para), pageable, count(where, para));
    }

    public Page<Map<String, Object>> PageMapByWhere(Pageable pageable, String select, String where, Object... para) {
        where = BulidSql(pageable, where);
        return new PageImpl<Map<String, Object>>(mapByWhere(select, where, para), pageable, count());
    }

    private String BulidSql(Pageable pageable, String where) {
        StringBuilder sql = new StringBuilder(where);
        sql.append(" limit ").append(pageable.getOffset()).append(" , ").append(pageable.getPageSize());
        return sql.toString();
    }
}
