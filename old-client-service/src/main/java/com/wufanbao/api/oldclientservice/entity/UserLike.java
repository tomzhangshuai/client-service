package com.wufanbao.api.oldclientservice.entity;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:17:59
 */
public class UserLike {
    private long userId;
    private long articleId;

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getArticleId() {
        return articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }
}
