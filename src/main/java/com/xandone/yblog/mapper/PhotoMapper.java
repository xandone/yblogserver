package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.PhotoBean;
import com.xandone.yblog.pojo.PhotoCoverBean;

import java.util.List;

/**
 * addPhotoById
 *
 * @author ：xandone
 * created on  ：2021/1/18 15:21
 * description：
 */
public interface PhotoMapper {

    void addPhotoCover(PhotoCoverBean photoBean);

    List<PhotoCoverBean> getPhotoCoverList();

    void addPhotoById(PhotoBean photoBean);

    List<PhotoBean> getPhotoListById(int id);

    int getPhotoCountById(int id);

}
