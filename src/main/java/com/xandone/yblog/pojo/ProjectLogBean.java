package com.xandone.yblog.pojo;

/**
 * @author ：xandone
 * created on  ：2021/1/13 10:11
 * description：项目更新日志
 */
public class ProjectLogBean {
    private String createTime;
    private String data;

    public ProjectLogBean() {
    }

    public ProjectLogBean(String createTime, String data) {
        this.createTime = createTime;
        this.data = data;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }
}
