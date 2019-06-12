package com.wufanbao.api.oldclientservice.controller;

import com.wufanbao.api.oldclientservice.Tool.LoginRequired;
import com.wufanbao.api.oldclientservice.entity.Article;
import com.wufanbao.api.oldclientservice.entity.UserLike;
import com.wufanbao.api.oldclientservice.service.ArticleService;
import com.wufanbao.api.oldclientservice.service.UserLikeService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User:Wangshihao
 * Date:2017-08-10
 * Time:9:17
 */
@Controller
@RequestMapping("userlike")
public class UserLikeController {

    @Autowired
    private UserLikeService userLikeService;

    @Autowired
    private ArticleService articleService;
    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(UserLikeController.class);

    @RequestMapping(value = "thumbUp", method = RequestMethod.POST)
    @ResponseBody
    @LoginRequired
    public Object thumbUp(HttpServletRequest request) {
        long userId = Long.parseLong(request.getParameter("userId"));
        long articleId = Long.parseLong(request.getParameter("articleId"));
        Integer state = Integer.parseInt(request.getParameter("state"));
        Map map = new HashMap();
        if (state == 0) {
            try {
                UserLike userLike = new UserLike();
                userLike.setUserId(userId);
                userLike.setArticleId(articleId);
                userLikeService.addlike(userLike);
                List<UserLike> userLikeList = userLikeService.query(articleId);
                Integer dianzanshu = userLikeList.size();
                System.out.println(dianzanshu);
                Article article = new Article();
                article.setArticleId(articleId);
                article.setLikes(dianzanshu);
                System.out.println(article.getLikes());
                articleService.updateLikes(article);
                map.put("return", 0);
            } catch (Exception e) {
                map.put("return", 2);
                logger.info(e.toString());
            }
        }
        map.put("return", 1);
        return map;
    }
}