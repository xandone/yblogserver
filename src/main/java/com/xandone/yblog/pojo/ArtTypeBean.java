package com.xandone.yblog.pojo;

import java.io.Serializable;

/**
 * @author ：xandone
 * created on  ：2019/12/20 10:03
 * description：
 */
public class ArtTypeBean implements Serializable {
    private int count;
    private String typeName;
    private int type;

    public ArtTypeBean(){}

    public ArtTypeBean(int count, String typeName, int type) {
        this.count = count;
        this.typeName = typeName;
        this.type = type;
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
