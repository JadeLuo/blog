package com.example.data.base;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/3/23.
 *
 */
public interface ListBaseDao<T, PK extends Serializable> extends CrudBaseDao<T, PK> {
    List<T> listByWhere(String sql, Object... para);

    List<Map<String, Object>> mapByWhere(String select, String where, Object... para);

    List<T> listAll();

    long count(String where, Object... para);

    long count();

}
