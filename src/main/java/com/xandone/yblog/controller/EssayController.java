package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.EssayBean;
import com.xandone.yblog.service.EssayService;
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
            if (map.get("essayId") == null || TextUtils.isEmpty((String) map.get("essayId"))) {
                essayBean = essayService.addEssay(map);
            } else {
                essayBean = essayService.editEssay(map);
            }
            List<EssayBean> list = new ArrayList<>();
            list.add(essayBean);
            baseResult.setData(list);
            baseResult.setCode(ReturnCode.SUCCESS);

        } catch (
                Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
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
                result.setCode(ReturnCode.SUCCESS);
                result.setMsg(ReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(ReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            baseResult.setMsg(ReturnCode.MES_SERVER_ERROR);
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
            baseResult.setCode(ReturnCode.SUCCESS);
            baseResult.setMsg(ReturnCode.MES_REQUEST_SUCCESS);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            baseResult.setMsg(ReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }

}
