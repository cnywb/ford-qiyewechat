package com.ford.qiye.common;

import com.fute.util.PropUtils;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by wanglijun on 16/8/17.
 */
public class FileUploadUtil {
    /**上传地址*/
    public static final String UPLOAD_URL="upload.url";
    /**attached_save_path*/
    public static final String ATTACHED_SAVE_PATH="attached_save_path";
    /***
     * 判断是否上传文件内容
     * @param request
     * @return Boolean
     */
    public static Boolean isMultipartContent(HttpServletRequest request){
         return ServletFileUpload.isMultipartContent (request);
    }

    public static String uploadUrl(HttpServletRequest request){
        String uploadUrl=editorProperties (UPLOAD_URL);
        if(StringUtils.isEmpty (uploadUrl)){
            uploadUrl=request.getSession().getServletContext().getRealPath("/");
        }
        return uploadUrl;
    }

    /**
     *
     * @param request
     * @return
     */
    public static String savePath(HttpServletRequest request){
        String uploadUrl=editorProperties (UPLOAD_URL);
        if(StringUtils.isEmpty (uploadUrl)){
            uploadUrl=request.getSession().getServletContext().getRealPath("/");
        }
       return  uploadUrl+editorProperties(ATTACHED_SAVE_PATH)+"/";
    }

    public static String editorProperties(String key){
       return  PropUtils.getPropertyValue("editor.properties",key);
    }
}
