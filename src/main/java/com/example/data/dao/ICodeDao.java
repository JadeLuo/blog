package com.example.data.dao;

import com.example.data.base.BaseRepository;
import com.example.data.entity.Code;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2017/4/17.
 */
@Repository("codeDao")
public interface ICodeDao extends BaseRepository<Code,String> {}
