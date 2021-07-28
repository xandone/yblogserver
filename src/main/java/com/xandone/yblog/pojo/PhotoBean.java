package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2021/7/27 17:03
 * description：
 */
public class PhotoBean {
    private int albumId;
    private int id;
    private String title;
    private String url;
    private int count;
    private Date postTime;

    public PhotoBean() {
    }

    public PhotoBean(int albumId, String title, String url, Date postTime) {
        this.albumId = albumId;
        this.title = title;
        this.url = url;
        this.postTime = postTime;
    }

    public int getAlbumId() {
        return albumId;
    }

    public void setAlbumId(int albumId) {
        this.albumId = albumId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getPostTime() {
        return postTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }
}
