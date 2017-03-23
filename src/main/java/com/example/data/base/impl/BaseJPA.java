package com.example.data.base.impl;

import com.example.data.base.Base;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/21.
 */
@Repository
public class BaseJPA<T, PK extends Serializable> implements Base<T, PK> {

}
