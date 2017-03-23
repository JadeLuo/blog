package com.example.data.base;

import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/22.
 */
@NoRepositoryBean
public interface Base<T, PK extends Serializable> {
}
