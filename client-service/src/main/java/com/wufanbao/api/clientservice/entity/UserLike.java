package com.wufanbao.api.clientservice.entity;

import java.util.Date;
import java.math.BigDecimal;


// UserLike,用户点赞记录
public class UserLike {
    //UserId,
    private long userId;
    //ArticleId,
    private long articleId;

    public long getUserId() {
        return this.userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public long getArticleId() {
        return this.articleId;
    }

    public void setArticleId(long articleId) {
        this.articleId = articleId;
    }

}
