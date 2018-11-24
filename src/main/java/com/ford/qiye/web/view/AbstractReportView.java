package com.ford.qiye.web.view;

import org.apache.commons.codec.CharEncoding;
import org.springframework.web.servlet.view.AbstractView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

/**
 * Created by wanglijun on 16/8/21.
 */
public abstract class AbstractReportView extends AbstractView {
    /****
     * 生成下载报表的文件名,支持中文名称下载，对下载文件名进行编码
     * @param prefixName 前缀名称
     * @param extension  扩展名
     * @param fileName   文件名
     * @return 下载附件文件名
     * @throws UnsupportedEncodingException
     */
    public String getAttachmentFileName(String prefixName,String extension,String fileName) throws UnsupportedEncodingException{
        StringBuilder sb=new StringBuilder(prefixName);
        sb.append(URLEncoder.encode(fileName, CharEncoding.UTF_8));
        sb.append(extension);
        return sb.toString();
    }
}

