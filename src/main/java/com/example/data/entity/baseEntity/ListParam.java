package com.example.data.entity.baseEntity;

import java.io.Serializable;

/**
 * Created by Administrator on 2017/3/3.
 * 列表页面的一些基础参数
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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public void setBaseurl(String baseurl) {
        this.baseurl = baseurl;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
