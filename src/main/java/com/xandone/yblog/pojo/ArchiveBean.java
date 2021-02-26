package com.xandone.yblog.pojo;

import com.xandone.yblog.utils.DateUtils;

/**
 * @author ：xandone
 * created on  ：2021/2/26 9:56
 * description：
 */
public class ArchiveBean {
    private String artId;
    //0：IT文章，1：杂文
    private int artType;
    private String title;
    private String postTime;

    public ArchiveBean() {
    }

    public ArchiveBean(String artId, int artType, String title, String postTime) {
        this.artId = artId;
        this.artType = artType;
        this.title = title;
        this.postTime = postTime;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public int getArtType() {
        return artType;
    }

    public void setArtType(int artType) {
        this.artType = artType;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPostTime() {
        return postTime;
    }

    public void setPostTime(String postTime) {
        this.postTime = postTime;
    }

    public static ArchiveBean createArchvieByArt(ArticleBean articleBean) {
        return new ArchiveBean(articleBean.getArtId(), 0, articleBean.getTitle(),
                DateUtils.date2String(articleBean.getPostTime()));
    }

    public static ArchiveBean createArchvieByEssay(EssayBean essayBean) {
        return new ArchiveBean(essayBean.getEssayId(), 1, essayBean.getTitle(),
                DateUtils.date2String(essayBean.getPostTime()));
    }
}
