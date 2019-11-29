package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.Config;
import com.xandone.yblog.common.ReturnCode;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.service.CommentService;
import com.xandone.yblog.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2019/11/29 10:02
 * description：
 */
@Controller
@RequestMapping("/comment")
public class CommetController {
    @Autowired
    CommentService commentService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addComment(@RequestParam(value = "artId") String artId,
                                 @RequestParam(value = "details") String details) {
        BaseResult baseResult = new BaseResult();
        try {
            List<CommentBean> dataList = new ArrayList<>();

//            if (TextUtils.isEmpty(userId)) {
//                baseResult.setCode(ReturnCode.ERROR_CODE);
//                return baseResult;
//            }
            CommentBean commentBean = new CommentBean();
            commentBean.setCommentId(IDUtils.RandomId());
            commentBean.setArtId(artId);
            commentBean.setCommentUserId(Config.USER_DEFAULT_ID);
            commentBean.setCommentDetails(details);
            commentBean.setCommentDate(new Date());

            commentService.addComment(commentBean);
            baseResult.setCode(ReturnCode.SUCCESS);
            dataList.add(commentBean);
            baseResult.setData(dataList);
            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            return baseResult;
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseListResult getAllJokeCommentById(@RequestParam(value = "page") Integer page,
                                                @RequestParam(value = "row") Integer row,
                                                @RequestParam(value = "jokeId") String jokeId) throws Exception {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = commentService.getAllArtCommentById(page, row, jokeId);
            if (result != null) {
                result.setCode(ReturnCode.SUCCESS);
                result.setMsg(ReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(ReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(ReturnCode.ERROR_CODE);
            baseResult.setMsg(ReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }
}
