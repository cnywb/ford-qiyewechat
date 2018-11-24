package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/17.
 */
public class MessageHandlerVo {
    /**部门ID*/
    private Long departId;
    /**用户Id*/
    private String userId;
    /**标签ID*/
    private Long markId;
    /**大区*/
    private String areaName;
    /**小区*/
    private List<String> smallNames;
    /**职位*/
    private String position;
    /**服务代码*/
    private String serveCode;
    /**销售代码*/
    private String sellCode;
    /**内容*/
    private String content;
    /**图片*/
    private String imageSrc;
    /**内容类型*/
    private Integer contentType;

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

    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Long getMarkId() {
        return markId;
    }

    public void setMarkId(Long markId) {
        this.markId = markId;
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

    public List<String> getSmallNames() {
        return smallNames;
    }

    public void setSmallNames(List<String> smallNames) {
        this.smallNames = smallNames;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
