package com.xandone.yblog.service;


import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.pojo.BannerBean;

/**
 * @author ：xandone
 * created on  ：2019/11/24 16:32
 * description：
 */
public interface BannerService {
    BaseResult getBannerData();

    BannerBean addBanner(BannerBean bean) throws Exception;

    void deleteBannerById(String articelId) throws Exception;
}
