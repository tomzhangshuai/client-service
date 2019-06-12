package com.wufanbao.api.oldclientservice.service;

import com.wufanbao.api.oldclientservice.entity.Article;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:17:33
 */
public interface ArticleService {
    /**
     * 文章
     */
    public List<Article> query(Article article);

    /**
     * 更新点赞数
     *
     * @param
     */
    public void updateLikes(Article article);
}
