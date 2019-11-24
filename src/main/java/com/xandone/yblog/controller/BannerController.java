package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.Config;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.BannerBean;
import com.xandone.yblog.service.BannerService;
import com.xandone.yblog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/24 16:30
 * description：
 */
@Controller
@RequestMapping("/banner")
public class BannerController {
    @Autowired
    BannerService bannerService;

    @RequestMapping(value = "/list")
    @ResponseBody
    public BaseResult getBannerData() {
        BaseResult baseResult = new BaseResult();
        try {
            BaseResult temp = bannerService.getBannerData();
            if (temp != null) {
                temp.setCode(ReturnCode.SUCCESS);
                return temp;
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            return baseResult;
        }
        baseResult.setCode(ReturnCode.ERROR_CODE);
        return baseResult;
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addBanner(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            String userId = map.get("userId");
            String title = map.get("title");
            String imgUrl = map.get("imgUrl");
            String articleUrl = map.get("articleUrl");
            BannerBean temp = new BannerBean();
            temp.setTitle(title);
            temp.setUserId(userId);
            temp.setImgUrl(imgUrl);
            temp.setArticleUrl(articleUrl);
            temp.setArticelId(IDUtils.RandomId());
            temp.setUpTime(new Date());
            BannerBean bannerBean = bannerService.addBanner(temp);
            List<BannerBean> list = new ArrayList<>();
            list.add(bannerBean);
            baseResult.setData(list);
            baseResult.setCode(ReturnCode.SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            return baseResult;
        }

        return baseResult;
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult deleteBannerById(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        try {
            String articelId = map.get("articelId");
            String adminId = map.get("adminId");
            if (!Config.ADMIN_ID.equals(adminId)) {
                baseResult.setCode(ReturnCode.ERROR_CODE);
                baseResult.setMsg("没有权限");
                return baseResult;
            }
            bannerService.deleteBannerById(articelId);
            baseResult.setCode(ReturnCode.SUCCESS);
            baseResult.setMsg("删除成功");
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            baseResult.setMsg("删除失败");
        }
        return baseResult;
    }
}
