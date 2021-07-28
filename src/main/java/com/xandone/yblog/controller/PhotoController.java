package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author ：xandone
 * created on  ：2021/1/18 9:57
 * description：
 */
@Controller
@RequestMapping("/photo")
public class PhotoController {
    @Autowired
    private PhotoService photoService;

    @RequestMapping(value = "/photoCovers")
    @ResponseBody
    public BaseListResult getPhotoCoverList(@RequestParam(value = "page") Integer page,
                                            @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = photoService.getPhotoCoverList(page, row);
            if (result != null) {
                result.setCode(IReturnCode.SUCCESS);
                result.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(IReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/photos")
    @ResponseBody
    public BaseListResult getPhotoList(@RequestParam(value = "albumId") Integer albumId,
                                       @RequestParam(value = "page") Integer page,
                                       @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = photoService.getPhotoListById(albumId, page, row);
            if (result != null) {
                result.setCode(IReturnCode.SUCCESS);
                result.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(IReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }
}
