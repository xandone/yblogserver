package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.xandone.yblog.config.Constant;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2021/1/18 15:22
 * description：
 */
public class ApkBean {
    private int id;
    private String versionCode;
    private String versionName;
    private String versionTip;
    private Date postTime;
    private boolean isForce;
    private String apkUrl = Constant.DOANLOAD_APK_URL;

    public ApkBean() {
    }

    public ApkBean(String versionCode, String versionName, String versionTip, boolean isForce, Date postTime) {
        this.versionCode = versionCode;
        this.versionName = versionName;
        this.versionTip = versionTip;
        this.postTime = postTime;
        this.isForce = isForce;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVersionCode() {
        return versionCode;
    }

    public void setVersionCode(String versionCode) {
        this.versionCode = versionCode;
    }

    public String getVersionName() {
        return versionName;
    }

    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    public String getVersionTip() {
        return versionTip;
    }

    public void setVersionTip(String versionTip) {
        this.versionTip = versionTip;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public boolean isForce() {
        return isForce;
    }

    public void setForce(boolean force) {
        isForce = force;
    }

    public String getApkUrl() {
        return apkUrl;
    }

    public void setApkUrl(String apkUrl) {
        this.apkUrl = apkUrl;
    }
}
