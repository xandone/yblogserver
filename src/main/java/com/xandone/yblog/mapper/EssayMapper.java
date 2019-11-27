package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.EssayBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/27 21:55
 * description：
 */
public interface EssayMapper {
    List<EssayBean> getEssayList();

    void addEssay(EssayBean essayBean);

    EssayBean getEssayById(String essayId);
}
