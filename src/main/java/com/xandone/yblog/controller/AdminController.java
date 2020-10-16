package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseObjResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.Config;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.pojo.*;
import com.xandone.yblog.service.AdminService;
import com.xandone.yblog.service.ArticleService;
import com.xandone.yblog.service.CommentService;
import com.xandone.yblog.service.EssayService;
import com.xandone.yblog.utils.TokenUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * @author ：xandone
 * created on  ：2019/12/6 14:57
 * description：
 */
@Controller
@RequestMapping(value = "admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private EssayService essayService;
    @Autowired
    private CommentService commentService;
    @Autowired
    private HttpServletRequest request;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestParam(value = "name") String name,
                            @RequestParam(value = "psw") String psw) {
        BaseResult baseResult = new BaseResult();
        List<AdminBean> list = new ArrayList<>();
        AdminBean adminBean;
        try {
            adminBean = adminService.getAdminByName(name);
            if (adminBean == null) {
                baseResult.setMsg("不存在该用户");
                baseResult.setCode(IReturnCode.ERROR_CODE);
                return baseResult;
            } else if (!adminBean.getPassword().equals(psw)) {
                baseResult.setMsg("密码错误");
                baseResult.setCode(IReturnCode.ERROR_CODE);
                return baseResult;
            } else {
                adminBean.setToken(TokenUtils.getToken(adminBean.getAdminId()));
                list.add(adminBean);
                baseResult.setData(list);
                baseResult.setCode(IReturnCode.SUCCESS);
                baseResult.setMsg("登录成功");

                adminBean.setLastLoginTime(new Date());
                adminService.updateAdmin(adminBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
            baseResult.setCode(IReturnCode.ERROR_CODE);
        }
        return baseResult;
    }


    @RequestMapping(value = "/userInfo")
    @ResponseBody
    public BaseResult getUserInfo() {
        BaseResult baseResult = new BaseResult();
        List<AdminBean> list = new ArrayList<>();
        try {
            AdminBean userBean = adminService.getAdminById(Config.ADMIN_ID);
            list.add(userBean);
            baseResult.setData(list);
            baseResult.setCode(IReturnCode.SUCCESS);
            baseResult.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }

    @RequestMapping(value = "/artInfo")
    @ResponseBody
    public BaseObjResult getArtTypeList(@RequestParam(value = "adminId") String adminId) {
        BaseObjResult<ArtStatistical> baseResult = new BaseObjResult<>();
        try {
            String token = request.getHeader("token");
            if (!TokenUtils.verifyToken(token)) {
                baseResult.setCode(IReturnCode.ERROR_TOKEN_CODE);
                baseResult.setMsg(IReturnCode.MES_ERROR_TOKEN);
                return baseResult;
            }
            List<ArtTypeBean> artTypeBeans = articleService.getArtCountAllType();
            List<YearArtData> yearArtData = adminService.getArtYearData();
            List<TypeBean> typeBeans = new ArrayList<>();
            TypeBean art = articleService.getAllArtCount();
            TypeBean essay = essayService.getAllEssayCount();
            typeBeans.add(art);
            typeBeans.add(essay);

            ArtStatistical statistical = new ArtStatistical(typeBeans, artTypeBeans);
            statistical.setCommentCounts(commentService.getAllCommentCount());
            statistical.setYearArtData(yearArtData);

            baseResult.setData(statistical);
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


}
