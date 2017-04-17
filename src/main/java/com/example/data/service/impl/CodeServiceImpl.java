package com.example.data.service.impl;

import com.example.data.base.baseservice.BaseServiceImpl;
import com.example.data.entity.Code;
import com.example.data.service.ICodeService;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2017/4/17.
 */
@Service("codeService")
public class CodeServiceImpl extends BaseServiceImpl<Code,String> implements ICodeService {}
