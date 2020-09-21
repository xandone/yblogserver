package com.xandone.yblog.service;


import com.xandone.yblog.pojo.AdminBean;
import com.xandone.yblog.pojo.YearArtData;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/12/6 14:57
 * description：
 */
public interface AdminService {

    AdminBean getAdminByName(String name) throws Exception;

    void updateAdmin(AdminBean adminBean) throws Exception;

    AdminBean getAdminById(String userId) throws Exception;

    List<YearArtData> getArtYearData() throws Exception;

}
