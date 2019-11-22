package com.xandone.yblog.common;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:18
 * description：
 */
public class ReturnCode {
    /**
     * 接口调用成功
     */
    public static final int SUCCESS = 200;

    //失败
    public static final int ERROR_CODE = -1;
    //禁言状态
    public static final int ERROR_BANNED_CODE = 201;



    public static final String MES_REQUEST_SUCCESS = "数据请求成功";
    public static final String MES_SERVER_ERROR = "系统出现异常，请稍后再试..";
    public static final String MES_REQUEST_BANNED = "用户处于禁言状态";
}
