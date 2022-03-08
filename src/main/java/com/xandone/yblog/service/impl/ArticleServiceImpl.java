package com.xandone.yblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.config.Constant;
import com.xandone.yblog.mapper.ArticleMapper;
import com.xandone.yblog.mapper.CommentMapper;
import com.xandone.yblog.pojo.TypeBean;
import com.xandone.yblog.pojo.ArtTypeBean;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.service.ArticleService;
import com.xandone.yblog.utils.IDUtils;
import com.xandone.yblog.utils.SimpleUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import static com.xandone.yblog.config.Constant.ART_TYPE;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:05
 * description：
 */
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommentMapper commentMapper;

    @Override
    public ArticleBean addArticle(Map<String, Object> map) throws Exception {
        ArticleBean articleBean = new ArticleBean();

        articleBean.setArtId(IDUtils.RandomId());
        articleBean.setArtUserId(Constant.ADMIN_ID);
        articleBean.setTitle((String) map.get("title"));
        articleBean.setContent((String) map.get("content"));
        articleBean.setContentHtml((String) map.get("contentHtml"));
        articleBean.setPostTime(new Date());
        articleBean.setArtCommentCount(0);
        articleBean.setArtBrowseCount(0);
        int type = (Integer) map.get("type");
        articleBean.setType(type);
        articleBean.setTypeName(SimpleUtils.getArtType(type));
        articleBean.setCoverImg((String) map.get("coverImg"));
        articleMapper.addArticle(articleBean);

        return articleBean;
    }

    @Override
    public BaseListResult getArticleList(Integer page, Integer row, Integer type) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<ArticleBean> list;
        if (type == null || type == -1) {
            list = articleMapper.getArticleList();
        } else {
            list = articleMapper.getArtListByType(type);
        }
        int total = (int) new PageInfo<>(list).getTotal();

        for (ArticleBean bean : list) {
            dealComment(bean);
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public ArticleBean getArtById(String artId) throws Exception {
        ArticleBean articleBean = articleMapper.getArtBeanById(artId);
        return articleBean;
    }

    @Override
    public void upDateArtBrowse(ArticleBean articleBean) {
        articleMapper.upDateArtBrowse(articleBean);
    }

    @Override
    public List<ArtTypeBean> getArtCountAllType() {
        List<ArtTypeBean> list = new ArrayList<>();
        int total = 0;
        for (int i = 0; i < ART_TYPE.length; i++) {
            int count = articleMapper.getArtCountByType(i);
            ArtTypeBean artTypeBean = new ArtTypeBean(count, ART_TYPE[i], i);
            list.add(artTypeBean);
            total += count;
        }
        list.add(0, new ArtTypeBean(total, "全部", -1));
        return list;
    }

    @Override
    public ArticleBean editArticle(Map<String, Object> map) throws Exception {
        String artId = (String) map.get("artId");
        ArticleBean articleBean = getArtById(artId);

        articleBean.setTitle((String) map.get("title"));
        articleBean.setContent((String) map.get("content"));
        articleBean.setContentHtml((String) map.get("contentHtml"));
        int type = (Integer) map.get("type");
        articleBean.setType(type);
        articleBean.setTypeName(SimpleUtils.getArtType(type));
        articleBean.setCoverImg((String) map.get("coverImg"));
        articleMapper.editArticle(articleBean);

        return articleBean;
    }

    @Override
    public TypeBean getAllArtCount() throws Exception {
        return new TypeBean("编程", articleMapper.getAllArtCount());
    }

    @Override
    public List<ArticleBean> getAllArts() throws Exception {
        return articleMapper.getArticleList();
    }

    @Override
    public BaseListResult searchArtList(Integer page, Integer row, ArticleBean articleBean) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<ArticleBean> list = articleMapper.searchArtList(articleBean);
        int total = (int) new PageInfo<>(list).getTotal();
        for (ArticleBean bean : list) {
            dealComment(bean);
        }
        base.setData(list);
        base.setTotal(total);
        return base;
    }

    private ArticleBean dealComment(ArticleBean bean) throws Exception {
        List<CommentBean> commentBeans = commentMapper.getAllArtCommentById(bean.getArtId());
        if (commentBeans != null) {
            bean.setArtCommentCount(commentBeans.size());
        }
        return bean;
    }

}
