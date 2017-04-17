package com.example.data.entity;

import com.example.data.entity.baseEntity.BaseModel;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * Created by Administrator on 2017/4/17.
 * 验证码
 */
@Entity
public class Code extends BaseModel<String>{

    @Id
    private String eMail;
    private String code;

    public Code () {
    }

    public Code (String eMail,String code) {

        this.eMail = eMail;
        this.code = code;
    }

    public String geteMail () {

        return eMail;
    }

    public void seteMail (String eMail) {

        this.eMail = eMail;
    }

    public String getCode () {

        return code;
    }

    public void setCode (String code) {

        this.code = code;
    }
}
