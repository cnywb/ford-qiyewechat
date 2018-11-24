package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/8/7.
 */
public class QuestionVo extends PageGrid {
    /**文本*/
    public static final Integer CONTENT_TYPE_TEXT=1;
    /**图片*/
    public static final Integer CONTENT_TYPE_IMAGE=2;
    /**Id问题*/
    private Long id;

    private String content;

    private String startDate;

    private String endDate;

    private Integer status;

    private String areaName;

    private String smallName;

    private Long departId;

    private String imageSrc;

    private String answerContent;

    /**转移的部*/
    private String transformDepartId;
    /**回复类型*/
    private Integer contentType;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }


    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }


    public Integer getContentType() {
        return contentType;
    }

    public void setContentType(Integer contentType) {
        this.contentType = contentType;
    }

    public String getTransformDepartId() {
        return transformDepartId;
    }

    public void setTransformDepartId(String transformDepartId) {
        this.transformDepartId = transformDepartId;
    }
}
