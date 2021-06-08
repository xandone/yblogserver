package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/11/29 10:03
 * description：带ip
 */
public class CommentBeanHide {
    private String commentId;
    private String artId;
    private String commentUserId;
    private String commentDetails;
    private String nickname;
    private String email;
    private String visitorUrl;
    private String commentUserVer;
    private Date commentDate;
    private String ip;

    private String commentNick;
    private String commentIcon;

    public String getCommentId() {
        return commentId;
    }

    public void setCommentId(String commentId) {
        this.commentId = commentId;
    }

    public String getArtId() {
        return artId;
    }

    public void setArtId(String artId) {
        this.artId = artId;
    }

    public String getCommentUserId() {
        return commentUserId;
    }

    public void setCommentUserId(String commentUserId) {
        this.commentUserId = commentUserId;
    }

    public String getCommentDetails() {
        return commentDetails;
    }

    public void setCommentDetails(String commentDetails) {
        this.commentDetails = commentDetails;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public String getCommentNick() {
        return commentNick;
    }

    public void setCommentNick(String commentNick) {
        this.commentNick = commentNick;
    }

    public String getCommentIcon() {
        return commentIcon;
    }

    public void setCommentIcon(String commentIcon) {
        this.commentIcon = commentIcon;
    }

    public String getCommentUserVer() {
        return commentUserVer;
    }

    public void setCommentUserVer(String commentUserVer) {
        this.commentUserVer = commentUserVer;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getVisitorUrl() {
        return visitorUrl;
    }

    public void setVisitorUrl(String visitorUrl) {
        this.visitorUrl = visitorUrl;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }
}