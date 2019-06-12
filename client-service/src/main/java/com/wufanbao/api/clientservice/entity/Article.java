package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// Article,文章资讯
public class Article {
    //ArticleId,
    private long articleId;
    //类别,
    private CompanyType companyType;
    //标题,
    private String title;
    //作者,
    private String author;
    //摘要,
    private String summary;
    //内容,
    private String content;
    //创建时间,
    private Date createTime;
    //点赞数,
    private int likes;
    //主图片,
    private String picture;
    //原文链接,
    private String copied;
    //来自其他网站名称,
    private String from;

    public long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

    public CompanyType getCompanyType() {
        return this.companyType;
    }

    public void setCompanyType(CompanyType companyType) {
        this.companyType = companyType;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getauthor() {
        return this.author;
    }

    public void setauthor(String author) {
        this.author = author;
    }

    public String getSummary() {
        return this.summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public int getLikes() {
        return this.likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getPicture() {
        return this.picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getCopied() {
        return this.copied;
    }

    public void setCopied(String copied) {
        this.copied = copied;
    }

    public String getFrom() {
        return this.from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

}
