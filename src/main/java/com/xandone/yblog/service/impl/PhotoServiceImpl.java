package com.xandone.yblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.mapper.PhotoMapper;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.pojo.PhotoBean;
import com.xandone.yblog.pojo.PhotoCoverBean;
import com.xandone.yblog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/7/27 17:31
 * description：
 */
@Service
public class PhotoServiceImpl implements PhotoService {
    @Autowired
    PhotoMapper photoMapper;

    @Override
    public BaseListResult getPhotoCoverList(Integer page, Integer row) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<PhotoCoverBean> list;
        list = photoMapper.getPhotoCoverList();
        for (PhotoCoverBean bean : list) {
            bean.setCount(dealCount(bean.getId()));
        }
        int total = (int) new PageInfo<>(list).getTotal();

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public BaseListResult getPhotoListById(Integer id, Integer page, Integer row) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<PhotoBean> list;
        list = photoMapper.getPhotoListById(id);
        int total = (int) new PageInfo<>(list).getTotal();

        base.setData(list);
        base.setTotal(total);
        return base;
    }

    private int dealCount(int id) throws Exception {
        return photoMapper.getPhotoCountById(id);
    }

}
