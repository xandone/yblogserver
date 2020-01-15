package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.ArtTypeBean;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.service.ArticleService;
import org.apache.http.util.TextUtils;
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
        ArticleBean articleBean;
        try {
            if (map.get("artId") == null || TextUtils.isEmpty((String) map.get("artId"))) {
                articleBean = articleService.addArticle(map);
            } else {
                articleBean = articleService.editArticle(map);
            }
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
                                    Integer type) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = articleService.getArticleList(page, row, type);
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

    @RequestMapping(value = "/artTypeList")
    @ResponseBody
    public BaseResult getArtTypeList() {
        BaseResult baseResult = new BaseResult();
        try {
            List<ArtTypeBean> list = articleService.getArtCountAllType();
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
