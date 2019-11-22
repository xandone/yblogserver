package com.xandone.yblog.service;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.pojo.ArticleBean;

import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:03
 * description：
 */
public interface ArticleService {
    ArticleBean addArticle(Map<String, Object> map) throws Exception;

    BaseListResult getArticleList(Integer page, Integer row,Integer type) throws Exception;
}