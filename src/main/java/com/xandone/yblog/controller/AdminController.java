package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.Config;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.AdminBean;
import com.xandone.yblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


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

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult login(@RequestBody Map<String, String> map) {
        BaseResult baseResult = new BaseResult();
        List<AdminBean> list = new ArrayList<>();
        AdminBean adminBean = null;
        String name = map.get("name");
        String psw = map.get("psw");
        try {
            adminBean = adminService.getAdminByName(name);
            if (adminBean == null) {
                baseResult.setMsg("不存在该用户");
                baseResult.setCode(ReturnCode.ERROR_CODE);
                return baseResult;
            } else if (!adminBean.getPassword().equals(psw)) {
                baseResult.setMsg("密码错误");
                baseResult.setCode(ReturnCode.ERROR_CODE);
                return baseResult;
            } else {
                list.add(adminBean);
                baseResult.setData(list);
                baseResult.setCode(ReturnCode.SUCCESS);
                baseResult.setMsg("登录成功");

                adminBean.setLastLoginTime(new Date());
                adminService.updateAdmin(adminBean);
            }
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setMsg(ReturnCode.MES_SERVER_ERROR);
            baseResult.setCode(ReturnCode.ERROR_CODE);
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
            baseResult.setCode(ReturnCode.SUCCESS);
            baseResult.setMsg(ReturnCode.MES_REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            baseResult.setMsg(ReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }


}
