package com.example.data.entity;

import com.example.data.entity.baseEntity.BaseModel;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.sql.Clob;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/10.
 */
@Entity
@Table(name = "t_article")
public class Article extends BaseModel {
    /**
     * 主键
     */
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    /**
     * 内容
     */
    @Column(length = 16777216)
    private String articleText;
    /**
     * 摘要
     */
    private String summary;
    /**
     * 作者
     */
    private String userId;
    /**
     * 标题
     */
    @NotEmpty(message = "标题不能为空")
    private String title;

    private Date articleDate;
    /**
     * 分类
     */
    private String type;
    /**
     * 标签
     */
    private String tag;
    /**
     * 评论
     */
    @Column(length = 16777216)
    private Clob comment;
    /**
     * 喜欢
     */
    private int enjoy;
    /**
     * 不喜欢
     */
    private int Hate;
    /**
     * 状态（公开，不公开）
     */
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getArticleText() {
        return articleText;
    }

    public void setArticleText(String articleText) {
        this.articleText = articleText;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public Clob getComment() {
        return comment;
    }

    public void setComment(Clob comment) {
        this.comment = comment;
    }

    public int getEnjoy() {
        return enjoy;
    }

    public void setEnjoy(int enjoy) {
        this.enjoy = enjoy;
    }

    public int getHate() {
        return Hate;
    }

    public void setHate(int hate) {
        Hate = hate;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }
}
