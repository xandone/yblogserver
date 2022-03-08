package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.config.Constant;
import com.xandone.yblog.pojo.ArchiveBean;
import com.xandone.yblog.pojo.ArtTypeBean;
import com.xandone.yblog.pojo.ArticleBean;
import com.xandone.yblog.pojo.EssayBean;
import com.xandone.yblog.service.ArticleService;
import com.xandone.yblog.service.EssayService;
import com.xandone.yblog.utils.ComparatorDate;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Collections;
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
    @Autowired
    EssayService essayService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addArt(@RequestBody Map<String, Object> map) {
        BaseResult baseResult = new BaseResult();
        ArticleBean articleBean;
        try {
            if (!Constant.ADMIN_ID.equals(map.get("adminId"))) {
                baseResult.setCode(IReturnCode.ERROR_NO_ADMIN_CODE);
                baseResult.setMsg(IReturnCode.MES_NO_ADMIN);
                return baseResult;
            }
            if (map.get("artId") == null || TextUtils.isEmpty((String) map.get("artId"))) {
                articleBean = articleService.addArticle(map);
            } else {
                articleBean = articleService.editArticle(map);
            }
            List<ArticleBean> list = new ArrayList<>();
            list.add(articleBean);
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


    @RequestMapping(value = "/artlist")
    @ResponseBody
    public BaseListResult getArticleList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         Integer type) {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = articleService.getArticleList(page, row, type);
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

    @RequestMapping(value = "/artTypeList")
    @ResponseBody
    public BaseResult getArtTypeList() {
        BaseResult baseResult = new BaseResult();
        try {
            List<ArtTypeBean> list = articleService.getArtCountAllType();
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


    @RequestMapping(value = "/archivelist")
    @ResponseBody
    public BaseListResult getArchiveList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row) {
        BaseListResult baseResult = new BaseListResult();
        try {
            List<ArticleBean> allArts = articleService.getAllArts();
            List<EssayBean> essays = essayService.getAllEssays();
            List<ArchiveBean> list = new ArrayList<>();
            for (ArticleBean articleBean : allArts) {
                list.add(ArchiveBean.createArchvieByArt(articleBean));
            }
            for (EssayBean essayBean : essays) {
                list.add(ArchiveBean.createArchvieByEssay(essayBean));
            }
            Collections.sort(list, new ComparatorDate());
            int total = list.size();
            int start = (page - 1) * row;
            int end = page * row;
            if (start > total - 1) {
                baseResult.setData(new ArrayList<>());
                baseResult.setTotal(0);
            } else {
                baseResult.setData(list.subList(start, end > total ? total : end));
                baseResult.setTotal(total);
            }
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

    @RequestMapping(value = "/artlist/search")
    @ResponseBody
    public BaseListResult searchJokeList(@RequestParam(value = "page") Integer page,
                                         @RequestParam(value = "row") Integer row,
                                         String key) {
        BaseListResult baseResult = new BaseListResult();
        try {
            System.out.println("key=" + URLDecoder.decode(key, "UTF-8"));
            ArticleBean articleBean = new ArticleBean(URLDecoder.decode(key, "UTF-8"));
            BaseListResult result = articleService.searchArtList(page, row, articleBean);
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
