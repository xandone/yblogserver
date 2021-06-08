package com.xandone.yblog.mapper;

import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.pojo.CommentBeanHide;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/29 10:11
 * description：
 */
public interface CommentMapper {
    void addComment(CommentBeanHide commentBean);

    List<CommentBean> getAllArtCommentById(String artId);

    int getAllCommentCount();

}
