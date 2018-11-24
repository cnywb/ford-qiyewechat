package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/16.
 */
public class MaterialHandlerVo {

    private String content;

    private String fileName;

    private String filePath;

    private String serveCode;

    private String sellCode;

    private String position;

    private String areaName;

    private List<String> smallAreaIds;


    private String realName;


    private String basePath;


    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getSellCode() {
        return sellCode;
    }

    public void setSellCode(String sellCode) {
        this.sellCode = sellCode;
    }

    public String getServeCode() {
        return serveCode;
    }

    public void setServeCode(String serveCode) {
        this.serveCode = serveCode;
    }

    public List<String> getSmallAreaIds() {
        return smallAreaIds;
    }

    public void setSmallAreaIds(List<String> smallAreaIds) {
        this.smallAreaIds = smallAreaIds;
    }


    public String getBasePath() {
        return basePath;
    }

    public void setBasePath(String basePath) {
        this.basePath = basePath;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }
}
