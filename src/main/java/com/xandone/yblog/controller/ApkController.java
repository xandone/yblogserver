package com.xandone.yblog.controller;

import com.xandone.yblog.common.BaseListResult;
import com.xandone.yblog.common.BaseResult;
import com.xandone.yblog.common.IReturnCode;
import com.xandone.yblog.config.Config;
import com.xandone.yblog.pojo.ApkBean;
import com.xandone.yblog.service.ApkService;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author ：xandone
 * created on  ：2021/1/18 9:57
 * description：
 */
@Controller
@RequestMapping("/apk")
public class ApkController {
    @Autowired
    private ApkService apkService;

    @RequestMapping(value = "/apkupload", method = RequestMethod.POST)
    @ResponseBody
    public BaseResult doUploadFile(HttpServletRequest request, @RequestParam MultipartFile apkfile,
                                   @RequestParam(value = "codeName") String codeName,
                                   @RequestParam(value = "versionName") String versionName,
                                   @RequestParam(value = "versionTip") String versionTip,
                                   @RequestParam(value = "isForce") Integer isForce) {
        BaseResult baseResult = new BaseResult();
        try {
            apkService.addApk(new ApkBean(codeName, versionName, versionTip, isForce, new Date()));
            //设置一下保存的路径
            String pathApk = request.getSession().getServletContext().getRealPath("apkUpload");
            File dir = new File(pathApk);
            if (!dir.isDirectory())
                dir.mkdir();
            //给文件一个新的名字
            String filename = Config.ANDROID_APK_NAME + "_v" + versionName;
            //获取文件的扩展名
            String ext = FilenameUtils.getExtension(apkfile.getOriginalFilename());

            //把文件存到指定的位置
            File saveFile = new File(pathApk + File.separator + filename + "." + ext);
            saveFile.setWritable(true, false);
            apkfile.transferTo(saveFile);
            baseResult.setCode(IReturnCode.SUCCESS);
            baseResult.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return baseResult;
    }

    @RequestMapping("/apkdown")
    public void down(HttpServletRequest request, HttpServletResponse response) throws Exception {
        BufferedOutputStream out = null;
        InputStream bis = null;
        try {
            String filePath = request.getSession().getServletContext().getRealPath("apkUpload");
            String versionName = apkService.getApkLatest().getVersionName();
            String fileName = Config.ANDROID_APK_NAME + "_v" + versionName + ".apk";
            System.out.println("fileName=" + fileName);
            //获取输入流
            bis = new BufferedInputStream(new FileInputStream(new File(filePath, fileName)));
            //设置文件下载头
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            //1.设置文件ContentType类型，这样设置，会自动判断下载文件类型
            response.setContentType("multipart/form-data");
            out = new BufferedOutputStream(response.getOutputStream());
            int len;
            while ((len = bis.read()) != -1) {
                out.write(len);
                out.flush();
            }
            out.close();
        } catch (Exception e) {
            e.printStackTrace();
            if (bis != null) {
                bis.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }

    @RequestMapping(value = "/apklist")
    @ResponseBody
    public BaseListResult getApkList(@RequestParam(value = "adminId") String adminId) {
        BaseListResult baseResult = new BaseListResult();
        try {
            List<ApkBean> list = apkService.getApkList();
            baseResult.setData(list);
            if (list != null) {
                baseResult.setCode(IReturnCode.SUCCESS);
                baseResult.setMsg(IReturnCode.MES_REQUEST_SUCCESS);
                return baseResult;
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
