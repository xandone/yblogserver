package com.xandone.yblog.pojo;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2020/9/16 14:59
 * description：
 */
public class ArtStatistical {
    private List<TypeBean> typeBeans;
    private List<ArtTypeBean> artTypeBeans;
    private int commentCounts;

    public ArtStatistical(List<TypeBean> typeBeans, List<ArtTypeBean> artTypeBeans) {
        this.typeBeans = typeBeans;
        this.artTypeBeans = artTypeBeans;
    }

    public List<TypeBean> getTypeBeans() {
        return typeBeans;
    }

    public void setTypeBeans(List<TypeBean> typeBeans) {
        this.typeBeans = typeBeans;
    }

    public List<ArtTypeBean> getArtTypeBeans() {
        return artTypeBeans;
    }

    public void setArtTypeBeans(List<ArtTypeBean> artTypeBeans) {
        this.artTypeBeans = artTypeBeans;
    }

    public int getCommentCounts() {
        return commentCounts;
    }

    public void setCommentCounts(int commentCounts) {
        this.commentCounts = commentCounts;
    }
}
