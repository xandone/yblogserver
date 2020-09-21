package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.AdminBean;
import org.apache.ibatis.annotations.Param;

public interface AdminMapper {
    void addAdmin(AdminBean user);

    AdminBean getAdminByName(String name);

    void updateAdmin(AdminBean userBean);

    AdminBean getAdminById(String id);

    int getArtCount();

    int getEssayCount();

    int getArtYearCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    int getEssayYearCount(@Param("startTime") String startTime, @Param("endTime") String endTime);
}
