package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

public class BannerBean {
    private String userId;
    private String articelId;
    private String title;
    private String imgUrl;
    private String articleUrl;
    private int pageViews;
    private Date upTime;

    public BannerBean() {
    }

    public BannerBean(String userId, String articelId, String title, String imgUrl, String articleUrl, int pageViews, Date upTime) {
        this.userId = userId;
        this.articelId = articelId;
        this.title = title;
        this.imgUrl = imgUrl;
        this.articleUrl = articleUrl;
        this.pageViews = pageViews;
        this.upTime = upTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getArticelId() {
        return articelId;
    }

    public void setArticelId(String articelId) {
        this.articelId = articelId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getArticleUrl() {
        return articleUrl;
    }

    public void setArticleUrl(String articleUrl) {
        this.articleUrl = articleUrl;
    }

    public int getPageViews() {
        return pageViews;
    }

    public void setPageViews(int pageViews) {
        this.pageViews = pageViews;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getUpTime() {
        return upTime;
    }

    public void setUpTime(Date upTime) {
        this.upTime = upTime;
    }
}
