package com.xandone.yblog.utils;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.xandone.yblog.config.Constant;

import java.io.IOException;

public class SimpleUtils {
    private final static ObjectMapper mapper = new ObjectMapper();

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


    public static <T> T json2Pojo(String jsonStr, Class<T> cls) {
        T obj = null;
        try {
            obj = mapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return obj;
    }
}
