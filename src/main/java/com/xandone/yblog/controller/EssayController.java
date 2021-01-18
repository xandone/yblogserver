package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.config.Constant;
import com.xandone.yblog.exception.NoImageException;
import com.xandone.yblog.pojo.EssayBean;
import com.xandone.yblog.service.EssayService;
import com.xandone.yblog.utils.SimpleUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/27 21:16
 * description：
 */
@Controller
@RequestMapping("/essay")
public class EssayController {
    @Autowired
    EssayService essayService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addEssay(@RequestBody Map<String, Object> map) {
        BaseResult baseResult = new BaseResult();
        EssayBean essayBean;
        try {
            if (!Constant.ADMIN_ID.equals(map.get("adminId"))) {
                baseResult.setCode(IReturnCode.ERROR_NO_ADMIN_CODE);
                baseResult.setMsg(IReturnCode.MES_NO_ADMIN);
                return baseResult;
            }
            if (map.get("essayId") == null || TextUtils.isEmpty((String) map.get("essayId"))) {
                essayBean = essayService.addEssay(map);
            } else {
                essayBean = essayService.editEssay(map);
            }
            List<EssayBean> list = new ArrayList<>();
            list.add(essayBean);
            baseResult.setData(list);
            baseResult.setCode(IReturnCode.SUCCESS);

        } catch (
                Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/essaylist")
    @ResponseBody
    public BaseListResult getAllJoke(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row,
                                     Integer tag) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = essayService.getEssayList(page, row, tag);
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


    @RequestMapping(value = "/essayDetails")
    @ResponseBody
    public BaseResult getEssayDetailsById(@RequestParam(value = "essayId") String essayId) {
        BaseResult baseResult = new BaseResult();
        try {
            EssayBean essayBean = essayService.getEssayById(essayId);
            essayBean.setEssayBrowseCount(essayBean.getEssayBrowseCount() + 1);
            essayService.upDateArtBrowse(essayBean);

            List<EssayBean> list = new ArrayList<>();
            list.add(essayBean);
            baseResult.setData(list);
            baseResult.setCode(IReturnCode.SUCCESS);
            baseResult.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/setBanner")
    @ResponseBody
    public BaseResult setEssayAsBanner(@RequestParam(value = "adminId") String adminId,
                                       @RequestParam(value = "jsonEssay") String jsonEssay) {
        BaseResult baseResult = new BaseResult();
        try {
            if (!Constant.ADMIN_ID.equals(adminId)) {
                baseResult.setCode(IReturnCode.ERROR_NO_ADMIN_CODE);
                baseResult.setMsg(IReturnCode.MES_NO_ADMIN);
                return baseResult;
            }
            EssayBean essayBean = SimpleUtils.json2Pojo(jsonEssay, EssayBean.class);
            essayService.setEssayAsBanner(essayBean);
            baseResult.setCode(IReturnCode.SUCCESS);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            if (e instanceof NoImageException) {
                baseResult.setCode(IReturnCode.ERROR_NO_IMAGE_CODE);
                baseResult.setMsg(IReturnCode.MES_NO_IMAGE);
            } else {
                baseResult.setCode(IReturnCode.ERROR_CODE);
                baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
            }
        }
        return baseResult;
    }

}
