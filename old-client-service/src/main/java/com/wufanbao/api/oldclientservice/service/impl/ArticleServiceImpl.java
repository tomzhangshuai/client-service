package com.wufanbao.api.oldclientservice.service.impl;

import com.wufanbao.api.oldclientservice.dao.ArticleDao;
import com.wufanbao.api.oldclientservice.entity.Article;
import com.wufanbao.api.oldclientservice.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-09
 * Time:17:34
 */
@Transactional
@Service
public class ArticleServiceImpl implements ArticleService {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> query(Article article) {
        return articleDao.query(article);
    }

    @Override
    public void updateLikes(Article article) {
        articleDao.updateLikes(article);
    }
}
