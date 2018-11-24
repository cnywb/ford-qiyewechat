package com.ford.qiye.model;

import java.util.Date;
import java.util.List;

public class DtQuestion {

    public static final Integer STATUS_UNANSWERED=0;

    public static final Integer STATUS_ANSWERED=1;

    private Long Id;

    private String content;

    private String userId;

    private Long departId;

    private Date questionTime;

    private Integer status;
    /**大区名称*/
    private String areaName;
    /**小区名称*/
    private String smallName;
    /**经销商名称*/
    private String dealerName;
    /**部门名称*/
    private String departName;
    /**电话*/
    private String tel;
    /**用户名*/
    private String userName;
    /**answerContent*/
    private String answerContent;
    /**经销商代码*/
    private String serveCode;
    /**用户姓名*/
    private String realName;

    /**答案列表*/
    private List<DtAnswer> answers;


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }


    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Date getQuestionTime() {
        return questionTime;
    }

    public void setQuestionTime(Date questionTime) {
        this.questionTime = questionTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }


    public String getServeCode() {
        return serveCode;
    }

    public void setServeCode(String serveCode) {
        this.serveCode = serveCode;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public List<DtAnswer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<DtAnswer> answers) {
        this.answers = answers;
    }
}