package com.xandone.yblog.service.impl;

import com.xandone.yblog.mapper.AdminMapper;
import com.xandone.yblog.pojo.AdminBean;
import com.xandone.yblog.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        adminBean.setTotalArts(adminMapper.getArtCount() + adminMapper.getEssayCount());
        return adminBean;
    }
}
