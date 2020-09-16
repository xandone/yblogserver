package com.xandone.yblog.pojo;

/**
 * @author ：xandone
 * created on  ：2020/9/16 14:39
 * description：
 */
public class TypeBean {
    private String name;
    private int count;

    public TypeBean(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
