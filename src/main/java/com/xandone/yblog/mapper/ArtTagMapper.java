package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.ArtTypeBean;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2020/9/10 17:55
 * description：
 */
public interface ArtTagMapper {
    void addType(ArtTypeBean bean);

    List<ArtTypeBean> getTagList();
}
