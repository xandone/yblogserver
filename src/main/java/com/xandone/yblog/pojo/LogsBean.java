package com.xandone.yblog.pojo;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/1/13 11:45
 * description：
 */
public class LogsBean {
    public LogsBean(String createTime, List<String> data) {
        this.createTime = createTime;
        this.data = data;
    }

    private String createTime;
    private List<String> data;

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public List<String> getData() {
        return data;
    }

    public void setData(List<String> data) {
        this.data = data;
    }
}
