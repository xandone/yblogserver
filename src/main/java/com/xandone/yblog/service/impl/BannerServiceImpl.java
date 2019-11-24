package com.xandone.yblog.service.impl;

import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.mapper.BannerMapper;
import com.xandone.yblog.pojo.BannerBean;
import com.xandone.yblog.service.BannerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/24 16:32
 * description：
 */
@Service
public class BannerServiceImpl implements BannerService {
    @Autowired
    BannerMapper bannerMapper;

    @Override
    public BaseResult getBannerData() {
        BaseResult result = new BaseResult();
        List<BannerBean> list = bannerMapper.getBannerData();
        result.setData(list);
        return result;
    }

    @Override
    public BannerBean addBanner(BannerBean bean) throws Exception {
        bannerMapper.addBanner(bean);
        return bean;
    }

    @Override
    public void deleteBannerById(String articelId) throws Exception {
        bannerMapper.deleteBannerById(articelId);
    }
}
