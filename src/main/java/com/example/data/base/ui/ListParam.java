package com.example.data.base.ui;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/4/10.
 */
public class ListParam implements Serializable {

    /**
     * 顶部标题
     */
    private String title;

    /**
     * url 的开头部分 /user/list 中的/user
     */
    private String baseurl;

    /**
     * action 字符串
     */
    private String action;

    public String getTitle () {

        return title;
    }

    public void setTitle (String title) {

        this.title = title;
    }

    public String getBaseurl () {

        return baseurl;
    }

    public void setBaseurl (String baseurl) {

        this.baseurl = baseurl;
    }

    public String getAction () {

        return action;
    }

    public void setAction (String action) {

        this.action = action;
    }
}
