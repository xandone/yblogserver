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
    //token失效
    int ERROR_TOKEN_CODE = 203;
    //非管理员操作
    int ERROR_NO_ADMIN_CODE = 204;


    String MES_REQUEST_SUCCESS = "数据请求成功";
    String MES_SERVER_ERROR = "系统出现异常，请稍后再试..";
    String MES_REQUEST_BANNED = "用户处于禁言状态";
    String MES_NO_IMAGE = "该文章没有图片";
    String MES_ERROR_TOKEN = "token无效";
    String MES_HANDLE_SUCCESS = "操作成功";
    String MES_NO_ADMIN = "你不是超级管理员，无法操作";
}
