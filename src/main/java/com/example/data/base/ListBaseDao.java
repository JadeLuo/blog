package com.example.data.base;

import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface ListBaseDao<T, PK extends Serializable> extends CrudBaseDao<T, PK> {
    List<T> listByWhere(String sql, Object... para);

    List<T> listAll();

    List<T> listAll(Sort sort);

    long count(String where, Object... para);

    long count();

}
