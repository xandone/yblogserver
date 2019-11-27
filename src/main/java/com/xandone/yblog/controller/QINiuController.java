package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.utils.UploadQiNiu;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ：xandone
 * created on  ：2019/11/27 9:50
 * description：
 */
@Controller
@RequestMapping("/qiniu")
public class QINiuController {
    @RequestMapping(value = "/getToken")
    @ResponseBody
    public BaseResult addJoke() {
        BaseResult baseResult = new BaseResult();
        try {
            String token = UploadQiNiu.getUpToken();
            baseResult.setMsg(token);
        } catch (Exception e) {

        }

        return baseResult;
    }
}
