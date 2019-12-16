package com.xandone.yblog.utils;


import com.xandone.yblog.config.Constant;

public class SimpleUtils {
    /**
     * 根据type获取具体分类类型
     *
     * @param type
     * @return
     */
    public static String getArtType(int type) {
        if (type < Constant.ART_TYPE.length) {
            return Constant.ART_TYPE[type];
        } else {
            return Constant.ART_TYPE[0];
        }
    }
}
