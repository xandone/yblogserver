package com.xandone.yblog.service;

import com.xandone.yblog.common.BaseListResult;

/**
 * @author ：xandone
 * created on  ：2021/1/18 15:33
 * description：
 */
public interface PhotoService {
    BaseListResult getPhotoCoverList(Integer page, Integer row) throws Exception;

    BaseListResult getPhotoListById(Integer id, Integer page, Integer row) throws Exception;
}
