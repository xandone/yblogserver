package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.config.Constant;
import com.xandone.yblog.pojo.CommentBean;
import com.xandone.yblog.service.CommentService;
import com.xandone.yblog.utils.IDUtils;
import com.xandone.yblog.utils.SimpleUtils;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    HttpServletRequest req;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult addComment(@RequestParam(value = "artId") String artId,
                                 @RequestParam(value = "details") String details,
                                 @RequestParam(value = "nickname") String nickname,
                                 @RequestParam(value = "email") String email,
                                 String visitorUrl,
                                 String commentUserVer) {
        BaseResult baseResult = new BaseResult();
        try {
            String remoteAddr = SimpleUtils.getIPAddress(req);
            System.out.println(remoteAddr);

            List<CommentBean> dataList = new ArrayList<>();

//            if (TextUtils.isEmpty(userId)) {
//                baseResult.setCode(IReturnCode.ERROR_CODE);
//                return baseResult;
//            }
            CommentBean commentBean = new CommentBean();
            commentBean.setCommentId(IDUtils.RandomId());
            commentBean.setArtId(artId);
            commentBean.setCommentUserId(Constant.USER_DEFAULT_ID);
            commentBean.setCommentDetails(details);
            commentBean.setNickname(nickname);
            commentBean.setEmail(email);
            commentBean.setVisitorUrl(visitorUrl);
            commentBean.setCommentUserVer(TextUtils.isEmpty(commentUserVer) ? "未知" : commentUserVer);
            commentBean.setCommentDate(new Date());
            commentBean.setIp(remoteAddr);

            commentService.addComment(commentBean);
            baseResult.setCode(IReturnCode.SUCCESS);
            dataList.add(commentBean);
            baseResult.setData(dataList);


            return baseResult;
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            return baseResult;
        }
    }

    @RequestMapping("/list")
    @ResponseBody
    public BaseListResult getAllArtCommentById(@RequestParam(value = "page") Integer page,
                                               @RequestParam(value = "row") Integer row,
                                               @RequestParam(value = "artId") String artId) throws Exception {
        BaseListResult baseResult = new BaseListResult();
        try {
            BaseListResult result = commentService.getAllArtCommentById(page, row, artId);
            if (result != null) {
                result.setCode(IReturnCode.SUCCESS);
                result.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
                return result;
            }
            baseResult.setCode(IReturnCode.ERROR_CODE);
        } catch (Exception e) {
            e.printStackTrace();
            baseResult.setCode(IReturnCode.ERROR_CODE);
            baseResult.setMsg(IReturnCode.MES_SERVER_ERROR);
        }
        return baseResult;
    }
}
