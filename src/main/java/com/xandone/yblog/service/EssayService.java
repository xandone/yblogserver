package com.xandone.yblog.service;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.pojo.TypeBean;
import com.xandone.yblog.pojo.EssayBean;

import java.util.List;
import java.util.Map;

/**
 * @author ：xandone
 * created on  ：2019/11/22 21:53
 * description：
 */
public interface EssayService {
    EssayBean addEssay(Map<String, Object> map) throws Exception;

    BaseListResult getEssayList(Integer page, Integer row, Integer type) throws Exception;

    EssayBean getEssayById(String essayId) throws Exception;

    void upDateArtBrowse(EssayBean essayBean);

    EssayBean editEssay(Map<String, Object> map) throws Exception;

    void setEssayAsBanner(EssayBean essayBean) throws Exception;

    TypeBean getAllEssayCount() throws Exception;

    List<EssayBean> getAllEssays() throws Exception;
}
