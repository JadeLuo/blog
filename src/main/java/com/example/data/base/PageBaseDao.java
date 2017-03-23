package com.example.data.base;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/23.
 */
public interface PageBaseDao<T, PK extends Serializable> extends ListBaseDao<T, PK> {

    Page<T> PageAll(Pageable pageable);

    Page<T> PageByWhere(Pageable pageable, String where, Object... para);

}
