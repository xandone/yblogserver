package com.xandone.yblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.mapper.EssayMapper;
import com.xandone.yblog.pojo.EssayBean;
import com.xandone.yblog.service.EssayService;
import com.xandone.yblog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:05
 * description：
 */
@Service
public class EssayServiceImpl implements EssayService {
    @Autowired
    EssayMapper essayMapper;

    @Override
    public EssayBean addEssay(Map<String, Object> map) throws Exception {
        EssayBean essayBean = new EssayBean();

        essayBean.setArtId(IDUtils.RandomId());
        essayBean.setArtUserId((String) map.get("artUserId"));
        essayBean.setTitle((String) map.get("title"));
        essayBean.setContent((String) map.get("content"));
        essayBean.setContentHtml((String) map.get("contentHtml"));
        essayBean.setPostTime(new Date());
        essayBean.setArtCommentCount(0);
        essayBean.setType((Integer) map.get("type"));
        essayBean.setCoverImg((String) map.get("coverImg"));
        essayMapper.addEssay(essayBean);

        return essayBean;
    }

    @Override
    public BaseListResult getEssayList(Integer page, Integer row, Integer type) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<EssayBean> list;
        if (type == null || type == -1) {
            list = essayMapper.getEssayList();
        } else {
            list = essayMapper.getEssayList();
        }
        int total = (int) new PageInfo<>(list).getTotal();

//        for (ArticleBean bean : list) {
//            dealJokeBean(bean);
//        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public EssayBean getEssayById(String essayId) throws Exception {
        EssayBean essayBean = essayMapper.getEssayById(essayId);
//        dealJokeBean(articleBean);
        return essayBean;
    }

//    private JokeBean dealJokeBean(JokeBean bean) throws Exception {
//        UserBean user = userMapper.getUserById(bean.getJokeUserId());
//        List<JokeLikeBean> likeBeans = selectJokeLikeById(bean.getJokeId());
//        List<CommentBean> commentBeans = jokeMapper.getJokeCommentById(bean.getJokeId());
//        if (user != null) {
//            bean.setJokeUserNick(user.getNickname());
//            bean.setJokeUserIcon(user.getUserIcon());
//        }
//        if (likeBeans != null) {
//            bean.setArticleLikeCount(likeBeans.size());
//        }
//        if (commentBeans != null) {
//            bean.setArticleCommentCount(commentBeans.size());
//        }
//        return bean;
//    }

}