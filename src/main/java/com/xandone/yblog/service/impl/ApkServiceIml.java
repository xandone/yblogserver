package com.xandone.yblog.service.impl;

import com.xandone.yblog.mapper.ApkMapper;
import com.xandone.yblog.pojo.ApkBean;
import com.xandone.yblog.service.ApkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/1/18 15:34
 * description：
 */

@Service
public class ApkServiceIml implements ApkService {
    @Autowired
    ApkMapper apkMapper;

    @Override
    public void addApk(ApkBean apkBean) throws Exception {
        apkMapper.addApk(apkBean);
    }

    @Override
    public List<ApkBean> getApkList() throws Exception {
        return apkMapper.getApkList();
    }

    @Override
    public ApkBean getApkLatest() throws Exception {
        return apkMapper.getApkLatest();
    }
}
