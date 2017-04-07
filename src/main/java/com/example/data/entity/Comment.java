package com.example.data.entity;

import com.example.data.entity.baseEntity.BaseModel;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * Created by Administrator on 2017/3/10.
 */
@Entity
@Table(name = "t_comment")
public class Comment extends BaseModel {
    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    private String id;
    private String pid;

    private String articleId;
    private String cotent;
    private Date commentDate;

    private String userId;

    public String getUserId () {

        return userId;
    }

    public void setUserId (String userId) {

        this.userId = userId;
    }

    public String getArticleId () {

        return articleId;
    }

    public void setArticleId (String articleId) {

        this.articleId = articleId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getCotent() {
        return cotent;
    }

    public void setCotent(String cotent) {
        this.cotent = cotent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }
}
