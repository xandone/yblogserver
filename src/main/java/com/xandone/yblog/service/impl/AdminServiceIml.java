package com.xandone.yblog.service.impl;

import com.xandone.yblog.config.Constant;
import com.xandone.yblog.mapper.AdminMapper;
import com.xandone.yblog.pojo.AdminBean;
import com.xandone.yblog.pojo.YearArtData;
import com.xandone.yblog.service.AdminService;
import com.xandone.yblog.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/12/6 15:00
 * description：
 */
@Service
public class AdminServiceIml implements AdminService {
    @Autowired
    AdminMapper adminMapper;

    @Override
    public AdminBean getAdminByName(String name) throws Exception {
        return adminMapper.getAdminByName(name);
    }

    @Override
    public void updateAdmin(AdminBean adminBean) throws Exception {
        adminMapper.updateAdmin(adminBean);
    }

    @Override
    public AdminBean getAdminById(String userId) throws Exception {
        AdminBean adminBean = adminMapper.getAdminById(userId);
        int arts = adminMapper.getArtCount();
        int essays = adminMapper.getEssayCount();
        adminBean.setArtCounts(arts);
        adminBean.setEssayCounts(essays);
        adminBean.setTotalArts(arts + essays);
        return adminBean;
    }

    @Override
    public List<YearArtData> getArtYearData() throws Exception {
        List<YearArtData> list = new ArrayList<>();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        int year = calendar.getWeekYear();
        String startTime = Constant.STATR_BLOG_DATE;
        for (int i = 2012; i <= year; i++) {
            int count1 = adminMapper.getArtYearCount(startTime, DateUtils.getNextYear(startTime));
            int count2 = adminMapper.getEssayYearCount(startTime, DateUtils.getNextYear(startTime));
            list.add(new YearArtData(String.valueOf(i), count1, count2));
            startTime = DateUtils.getNextYear(startTime);
        }
        return list;
    }
}
