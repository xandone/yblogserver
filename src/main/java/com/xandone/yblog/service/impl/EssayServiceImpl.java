package com.xandone.yblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.config.Constant;
import com.xandone.yblog.exception.NoImageException;
import com.xandone.yblog.mapper.BannerMapper;
import com.xandone.yblog.mapper.CommentMapper;
import com.xandone.yblog.mapper.EssayMapper;
import com.xandone.yblog.pojo.TypeBean;
import com.xandone.yblog.pojo.BannerBean;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.pojo.EssayBean;
import com.xandone.yblog.service.EssayService;
import com.xandone.yblog.utils.IDUtils;
import com.xandone.yblog.utils.SimpleUtils;
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
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public EssayBean addEssay(Map<String, Object> map) throws Exception {
        EssayBean essayBean = new EssayBean();

        essayBean.setEssayId(IDUtils.RandomId());
        essayBean.setEssayUserId(Constant.ADMIN_ID);
        essayBean.setTitle((String) map.get("title"));
        essayBean.setContent((String) map.get("content"));
        essayBean.setContentHtml((String) map.get("contentHtml"));
        essayBean.setPostTime(new Date());
        essayBean.setEssayCommentCount(0);
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

        for (EssayBean essayBean : list) {
            dealComment(essayBean);
        }

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public EssayBean getEssayById(String essayId) throws Exception {
        EssayBean essayBean = essayMapper.getEssayById(essayId);

        return essayBean;
    }

    @Override
    public void upDateArtBrowse(EssayBean essayBean) {
        essayMapper.upDateArtBrowse(essayBean);
    }

    @Override
    public EssayBean editEssay(Map<String, Object> map) throws Exception {
        String essayId = (String) map.get("essayId");
        EssayBean essayBean = getEssayById(essayId);

        essayBean.setTitle((String) map.get("title"));
        essayBean.setContent((String) map.get("content"));
        essayBean.setContentHtml((String) map.get("contentHtml"));
        essayBean.setCoverImg((String) map.get("coverImg"));
        essayMapper.editEssay(essayBean);

        return essayBean;
    }

    @Override
    public void setEssayAsBanner(EssayBean essayBean) throws Exception {
        if (essayBean.getIsTopping() == 0) {
            essayMapper.editEssay(essayBean);
            bannerMapper.deleteBannerById(essayBean.getEssayId());
            return;
        }
        EssayBean temp = getEssayById(essayBean.getEssayId());
        String imgJson = temp.getCoverImg();
        String[] imgs = SimpleUtils.json2Pojo(imgJson, String[].class);
        if (imgs == null || imgs.length == 0) {
            throw new NoImageException("该文章无图片");
        }
        BannerBean bannerBean = new BannerBean(temp.getEssayUserId(),
                temp.getEssayId(),
                temp.getTitle(),
                imgs[0],
                temp.getEssayId(),
                temp.getEssayBrowseCount(),
                temp.getPostTime()
        );
        bannerMapper.addBanner(bannerBean);
        essayMapper.editEssay(essayBean);
    }

    @Override
    public TypeBean getAllEssayCount() throws Exception {
        return new TypeBean("杂文", essayMapper.getAllEssayCount());
    }

    private EssayBean dealComment(EssayBean bean) throws Exception {
        List<CommentBean> commentBeans = commentMapper.getAllArtCommentById(bean.getEssayId());
        if (commentBeans != null) {
            bean.setEssayCommentCount(commentBeans.size());
        }
        return bean;
    }
}
