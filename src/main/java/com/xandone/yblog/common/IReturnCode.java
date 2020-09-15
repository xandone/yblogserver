package com.xandone.yblog.common;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:18
 * description：
 */
public interface IReturnCode {
    /**
     * 接口调用成功
     */
    int SUCCESS = 200;

    //失败
    int ERROR_CODE = -1;
    //禁言状态
    int ERROR_BANNED_CODE = 201;
    //没有图片
    int ERROR_NO_IMAGE_CODE = 202;


    String MES_REQUEST_SUCCESS = "数据请求成功";
    String MES_SERVER_ERROR = "系统出现异常，请稍后再试..";
    String MES_REQUEST_BANNED = "用户处于禁言状态";
    String MES_NO_IMAGE = "该文章没有图片";
}
