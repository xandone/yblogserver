package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/22 15:16
 * description：
 */
@Controller
@RequestMapping("/art")
public class ArticleController {
    @Autowired
    ArticleService articleService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addArt(@RequestBody Map<String, Object> map) {
        BaseResult baseResult = new BaseResult();
        try {
            ArticleBean articleBean = articleService.addArticle(map);
            List<ArticleBean> list = new ArrayList<>();
            list.add(articleBean);
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


    @RequestMapping(value = "/artlist")
    @ResponseBody
    public BaseListResult getAllArt(@RequestParam(value = "page") Integer page,
                                     @RequestParam(value = "row") Integer row,
                                     Integer tag) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = articleService.getArticleList(page, row, tag);
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


    @RequestMapping(value = "/artDetails")
    @ResponseBody
    public BaseResult getArtDetailsById(@RequestParam(value = "artId") String artId) {
        BaseResult baseResult = new BaseResult();
        try {
            ArticleBean articleBean = articleService.getArtById(artId);
            articleBean.setArtBrowseCount(articleBean.getArtBrowseCount() + 1);
            articleService.upDateArtBrowse(articleBean);

            List<ArticleBean> list = new ArrayList<>();
            list.add(articleBean);
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
