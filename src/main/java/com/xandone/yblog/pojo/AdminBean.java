package com.xandone.yblog.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

/**
 * @author ：xandone
 * created on  ：2019/12/6 14:50
 * description：
 */
public class AdminBean {
    private String name;
    private String password;
    private String nickname;
    private String adminId;
    private String adminIcon;
    private String permission;
    private int totalArts;
    private int artCounts;
    private int essayCounts;
    private String email;
    private Date registTime;
    private Date lastLoginTime;
    private String token;
    private String identity;

    public AdminBean() {

    }

    public AdminBean(String name, String password, String nickname, String adminId, Date registTime) {
        this.name = name;
        this.password = password;
        this.nickname = nickname;
        this.adminId = adminId;
        this.registTime = registTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAdminId() {
        return adminId;
    }

    public void setAdminId(String adminId) {
        this.adminId = adminId;
    }

    public String getAdminIcon() {
        return adminIcon;
    }

    public void setAdminIcon(String adminIcon) {
        this.adminIcon = adminIcon;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getRegistTime() {
        return registTime;
    }

    public void setRegistTime(Date registTime) {
        this.registTime = registTime;
    }

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    public Date getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(Date lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public int getTotalArts() {
        return totalArts;
    }

    public void setTotalArts(int totalArts) {
        this.totalArts = totalArts;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getArtCounts() {
        return artCounts;
    }

    public void setArtCounts(int artCounts) {
        this.artCounts = artCounts;
    }

    public int getEssayCounts() {
        return essayCounts;
    }

    public void setEssayCounts(int essayCounts) {
        this.essayCounts = essayCounts;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    @Override
    public String toString() {
        return "UserBean{" +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                ", adminId=" + adminId +
                ", adminIcon=" + adminIcon +
                ", token=" + token +
                ", registTime=" + registTime +
                ", lastLoginTime=" + lastLoginTime +
                '}';
    }
}
