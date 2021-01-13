package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.AdminBean;
import com.xandone.yblog.pojo.ProjectLogBean;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AdminMapper {
    void addAdmin(AdminBean user);

    AdminBean getAdminByName(String name);

    void updateAdmin(AdminBean userBean);

    AdminBean getAdminById(String id);

    int getArtCount();

    int getEssayCount();

    int getArtYearCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    int getEssayYearCount(@Param("startTime") String startTime, @Param("endTime") String endTime);

    void addLog(ProjectLogBean bean);

    List<ProjectLogBean> getLogs();
}
