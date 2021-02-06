package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/11/22 14:51
 * description：
 */
public class EssayBean {
    private String essayId;
    private String essayUserId;
    private String title;
    private int essayCommentCount;
    private int essayBrowseCount;
    private int type;
    private String coverImg;
    private Date postTime;
    private String content;
    private String contentHtml;
    private boolean isTopping;

    public String getEssayId() {
        return essayId;
    }

    public void setEssayId(String essayId) {
        this.essayId = essayId;
    }

    public String getEssayUserId() {
        return essayUserId;
    }

    public void setEssayUserId(String essayUserId) {
        this.essayUserId = essayUserId;
    }

    public int getEssayCommentCount() {
        return essayCommentCount;
    }

    public void setEssayCommentCount(int essayCommentCount) {
        this.essayCommentCount = essayCommentCount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getCoverImg() {
        return coverImg;
    }

    public void setCoverImg(String coverImg) {
        this.coverImg = coverImg;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getPostTime() {
        return postTime;
    }

    public void setPostTime(Date postTime) {
        this.postTime = postTime;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getContentHtml() {
        return contentHtml;
    }

    public void setContentHtml(String contentHtml) {
        this.contentHtml = contentHtml;
    }

    public int getEssayBrowseCount() {
        return essayBrowseCount;
    }

    public void setEssayBrowseCount(int essayBrowseCount) {
        this.essayBrowseCount = essayBrowseCount;
    }

    public boolean isTopping() {
        return isTopping;
    }

    public void setTopping(boolean topping) {
        isTopping = topping;
    }
}
