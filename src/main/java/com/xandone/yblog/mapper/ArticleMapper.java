package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.ArticleBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/22 14:55
 * description：
 */
public interface ArticleMapper {
    List<ArticleBean> getArticleList();

    void addArticle(ArticleBean articleBean);

    ArticleBean getArtBeanById(String artId);
}
