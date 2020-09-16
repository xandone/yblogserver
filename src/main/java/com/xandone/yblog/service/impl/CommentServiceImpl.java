package com.xandone.yblog.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.mapper.CommentMapper;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/29 10:10
 * description：
 */
@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    CommentMapper commentMapper;

    @Override
    public void addComment(CommentBean commentBean) throws Exception {
        commentMapper.addComment(commentBean);
    }

    @Override
    public BaseListResult getAllArtCommentById(Integer page, Integer row, String artId) throws Exception {
        BaseListResult base = new BaseListResult();
        PageHelper.startPage(page, row);
        List<CommentBean> list = commentMapper.getAllArtCommentById(artId);
        int total = (int) new PageInfo<>(list).getTotal();
        base.setData(list);
        base.setTotal(total);
        return base;
    }

    @Override
    public int getAllCommentCount() throws Exception{
        return commentMapper.getAllCommentCount();
    }


}
