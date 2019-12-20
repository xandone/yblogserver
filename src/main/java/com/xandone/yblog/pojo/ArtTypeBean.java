package com.xandone.yblog.pojo;

/**
 * @author ：xandone
 * created on  ：2019/12/20 10:03
 * description：
 */
public class ArtTypeBean {
    private int count;
    private String typeName;

    public ArtTypeBean(int count, String typeName) {
        this.count = count;
        this.typeName = typeName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }
}
