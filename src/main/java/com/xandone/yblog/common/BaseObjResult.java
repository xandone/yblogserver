package com.xandone.yblog.common;

/**
 * @author ：xandone
 * created on  ：2020/9/16 14:57
 * description：
 */
public class BaseObjResult<T> {
    private T data;
    private int code;
    private String msg;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
