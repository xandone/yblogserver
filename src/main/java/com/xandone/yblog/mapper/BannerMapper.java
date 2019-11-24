package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.BannerBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/24 16:33
 * description：
 */
public interface BannerMapper {
    List<BannerBean> getBannerData();

    void addBanner(BannerBean bean);

    void deleteBannerById(String articelId);
}
