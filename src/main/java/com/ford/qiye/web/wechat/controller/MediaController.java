package com.ford.qiye.web.wechat.controller;

import com.alibaba.fastjson.JSONArray;
import com.ford.qiye.common.FileUploadUtil;
import com.ford.qiye.web.controller.AbstractController;
import com.ford.qiye.web.wechat.controller.vo.MediaVo;
import com.fute.util.DateUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

/**
 * Created by wanglijun on 16/8/17.
 */
@Controller
@RequestMapping("/wechat/media")
public class MediaController extends AbstractController {

    /***
     * 日志
     */
    private static final Logger logger= LoggerFactory.getLogger (MediaController.class);

    /**成功*/
    public static final Integer S_ERROR=0;
    /**失败*/
    public static final Integer F_ERROR=1;

    @RequestMapping("/upload")
    @ResponseBody
    public void uploadQyMedia(MediaVo vo,HttpServletRequest request, HttpServletResponse response){
        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles ("files");

        //请选择文件
        if(!FileUploadUtil.isMultipartContent (request)){
            this.writer (response,F_ERROR,"请选择要上传文件");
            return;
        }

        //定义允许上传的文件扩展名
        HashMap<String, String> extMap = new HashMap<String, String>();
        extMap.put("image", "gif,jpg,jpeg,png,bmp");
        extMap.put("voice","amr");
        extMap.put("video","mp4");
        extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");
        //保存框架
        String savePath =FileUploadUtil.savePath (request);
        //文件保存目录URL
        String saveUrl  =savePath;
        String tempSavePath=savePath.replace (FileUploadUtil.editorProperties (FileUploadUtil.ATTACHED_SAVE_PATH),StringUtils.EMPTY);

        File uploadDir = new File(savePath);
        if(!uploadDir.isDirectory()){
            this.writer (response,F_ERROR,"上传目录不存在");
            return;
        }
        //检查目录写权限
        if(!uploadDir.canWrite()){
            this.writer (response,F_ERROR,"上传目录没有写权限");
            return;
        }
        if (vo.getDir () == null) {
             vo.setDir ("image");
        }
        if(!extMap.containsKey(vo.getDir ())){
            this.writer (response,F_ERROR,"目录名不正确");
            return;
        }
        //创建文件夹
        savePath += vo.getDir () +"/"+this.getUserId ()+"/";
        saveUrl += vo.getDir () +"/"+this.getUserId ()+"/";
        File saveDirFile = new File(savePath);
        if (!saveDirFile.exists()) {
            saveDirFile.mkdirs();
        }
        String ymd = DateUtils.format(new Date (),DateUtils.FORMAT_DATE_YYYYMMDD);
        savePath += ymd + "/";
        saveUrl += ymd + "/";
        File dirFile = new File(savePath);
        if (!dirFile.exists()) {
            dirFile.mkdirs();
        }


        for(MultipartFile file:files){
                String myFileName = file.getOriginalFilename();
                if(!checkFileType(myFileName, extMap.get(vo.getDir ()))){
                   this.writer (response,F_ERROR,"不允许上传该文件类型");
                    return;
                }
                String fileFullName =DateUtils.format(new Date(), DateUtils.FORMAT_DATE_TIME_YYYYMMDDHHMMSS)+"_"+ new Random ().nextInt(1000)+"_";

                String fileType=myFileName.substring(myFileName.lastIndexOf(".") + 1,myFileName.length()).toLowerCase();
                String newFileName=fileFullName+"."+fileType;
            try {
                file.transferTo (new File (savePath, newFileName));
                String newFilePath=savePath+newFileName;
                this.writer (response,S_ERROR,newFilePath,"/"+newFilePath.replace (tempSavePath,StringUtils.EMPTY));
            } catch (IOException e) {
                logger.info (e.getMessage (),e);
                this.writer (response,F_ERROR,"上传出错");
            }
        }

    }

    private boolean checkFileType(String fileName,String authFileType){
        String fileType=fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
        if(authFileType.indexOf(fileType)==-1){
            return false;
        }
        return true;
    }

    private  void writer(HttpServletResponse response,Integer error,String message){
        this.writer (response,error,message, StringUtils.EMPTY);
    }

    /***
     * 输出结果
     * @param response
     * @param message
     */
    private void  writer(HttpServletResponse response,Integer error, String message,String filePath){
        response.setCharacterEncoding ("UTF-8");
        response.setContentType ("text/html;charset=UTF-8");
        PrintWriter writer=null;
        Map<String,Object> params=new HashMap<> ();
        params.put ("error",error);
        params.put ("message",message);
        params.put ("filePath",filePath);
        try {
            writer=response.getWriter ();
            writer.print (JSONArray.toJSONString (params));
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
        }finally{
            IOUtils.closeQuietly (writer);
        }
    }

}
