package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.ApkBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/1/18 15:21
 * description：
 */
public interface ApkMapper {
    void addApk(ApkBean apkBean);

    List<ApkBean> getApkList();

    ApkBean getApkLatest();
}
