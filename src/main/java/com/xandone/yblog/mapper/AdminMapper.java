package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.AdminBean;

public interface AdminMapper {
    void addAdmin(AdminBean user);

    AdminBean getAdminByName(String name);

    void updateAdmin(AdminBean userBean);

    AdminBean getAdminById(String id);

    int getArtCount();

    int getEssayCount();
}
