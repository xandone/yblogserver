package com.xandone.yblog.utils;

import com.qiniu.util.Auth;
import com.xandone.yblog.common.Config;

/**
 * @author ：xandone
 * created on  ：2019/11/27 9:52
 * description：
 */
public class UploadQiNiu {
    /**
     * ACCESS_KEY
     */
    private static String ACCESS_KEY = Config.CONST_ACCESS_KEY;
    /**
     * SECRET_KEY
     */
    private static String SECRET_KEY = Config.CONST_SECRET_KEY;
    /**
     * 要上传的空间
     */
    private static String bucketName = Config.CONST_BUCKET_NAME;

    /**
     * 密钥配置
     */
    private static Auth auth = Auth.create(ACCESS_KEY, SECRET_KEY);

    /**
     * 简单上传，使用默认策略，只需要设置上传的空间名就可以了
     *
     * @return
     */
    public static String getUpToken() {
        return auth.uploadToken(bucketName);
    }
}
