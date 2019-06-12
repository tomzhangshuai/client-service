package com.wufanbao.api.oldclientservice.entity;

import java.util.Date;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:17:20
 */
public class Article {
    private long articleId;//articleId
    private String title;//标题
    private String summary;//总结
    private String content;//内容
    private Date createTime;//创建时间
    private Integer likes;//点赞数
    private String picture;//图片
    private String author;//作者
    private String copied;//原文链接
    private String from;//来自其他网站名字

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getCopied() {
        return copied;
    }

    public void setCopied(String copied) {
        this.copied = copied;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
