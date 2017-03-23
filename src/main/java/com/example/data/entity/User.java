package com.example.data.entity;


import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by wanghuiwen on 17-1-5.
 * 用户实体
 */
@Entity
@Table(name = "t_user")
public class User {
    public static final int USER_LOCK = 2;
    public static final int USER_NORMAL = 2;
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    @NotEmpty(message = "姓名不能为空")
    private String userName;
    @NotEmpty(message = "密码不能为空")
    @Length(min = 6, message = "密码长度不能小于6位")
    private String passWord;
    /**
     * 账户状态
     */
    private int state;
    private String realName;
    @NotEmpty(message = "邮箱不能为空")
    private String eMail;
    @Length(max = 11, min = 11, message = "请输入真确的格式")
    private String phone;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


}
