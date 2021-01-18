package com.xandone.yblog.service;

import com.xandone.yblog.pojo.ApkBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/1/18 15:33
 * description：
 */
public interface ApkService {
    void addApk(ApkBean apkBean) throws Exception;

    List<ApkBean> getApkList() throws Exception;

    ApkBean getApkLatest() throws Exception;
}
