package com.ford.qiye.web.controller;

import com.alibaba.fastjson.JSONArray;
import com.ford.qiye.common.FileUploadUtil;
import com.ford.qiye.common.UploadUtil;
import com.fute.common.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;

/**
 * Created by wanglijun on 16/8/14.
 */
@Controller
@RequestMapping("/image")
public class ImageController {
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (ImageController.class);

    private static final String    path = "/upload/images/";


    private static final int UPLOAD_EXERCISE_WIDTH = 300;

    private static final int UPLOAD_EXERCISE_HEIGHT = 500;



    @Value("#{'upload.path'}")
    private String name;


    @RequestMapping(value = "/uploadFile",method = RequestMethod.POST)
    public void  uploadFile(HttpServletRequest request, HttpServletResponse response) {

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        List<MultipartFile> files = multipartRequest.getFiles ("files");
        if(CollectionUtils.isEmpty (files)){
            logger.info ("文件为空");
            return;
        }
        MultipartFile file=files.get (0);
        if(file!=null){
            String originalFilename=file.getOriginalFilename();
            String ext = originalFilename.substring(originalFilename.lastIndexOf("."),originalFilename.length());
            String fileName=this.getFileName (ext);
            String filePath = FileUploadUtil.uploadUrl (request)+"/upload/"+fileName;
            try {
                file.transferTo (new File (filePath));
                //输出文件名
                this.writeResponse (response,filePath,fileName);
            } catch (IOException e) {
                logger.error (e.getMessage (),e);
            }
        }
    }

    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public void  upload(HttpServletRequest request, HttpServletResponse response){

        MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
        //取得上传文件
        List<MultipartFile> files = multipartRequest.getFiles ("files");

        if(CollectionUtils.isEmpty (files)){
            logger.info ("文件为空");
            return;
        }

        logger.info ("name:{}",name);

        MultipartFile file=files.get (0);

        String realPath = FileUploadUtil.uploadUrl (request);

        if(file!=null) {
            String myFileName = file.getOriginalFilename();
            if(StringUtils.isEmpty (myFileName)){
                return;
            }
            String ext = myFileName.substring(myFileName.lastIndexOf("."),myFileName.length());

            try {
                InputStream  inputStream = file.getInputStream ();
                String targetPath = UploadUtil.copyResolutionFile (inputStream,ext,realPath, path, UPLOAD_EXERCISE_WIDTH, UPLOAD_EXERCISE_HEIGHT);

                this.writeResponse (response,targetPath,null);
            } catch (IOException e) {
               logger.error (e.getMessage (),e);
            }

        }
    }


    private void writeResponse(HttpServletResponse response,String filePath,String fileName) throws IOException {
        response.setCharacterEncoding ("UTF-8");
        response.setContentType("application/json");
        Map<String,String> params=new HashMap<> ();
        params.put ("filePath",filePath);
        params.put ("fileName",fileName);
        response.getWriter().write(JSONArray.toJSONString (params));
    }

    private String getFileName(String ext){
         return DateUtils.formatDate (new Date (),DateUtils.FORMAT_DATETIME_YYYYMMDDHHMMSSSSS)+Math.abs(new Random ().nextLong()) + ext;
    }
}
