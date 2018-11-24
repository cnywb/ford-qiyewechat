package com.ford.qiye.model;

import java.util.Date;

public class DtAnswer {

    private Long Id;

    private Long questionId;

    private String  answerContent;

    private String userId;

    private Date   answerTime;

    /**用户名*/
    private String userName;
    /**真实姓名*/
    private String realName;
    /**部门名称*/
    private String departName;


    public DtAnswer() {
    }

    /***
     *
     * @param questionId
     * @param userId
     * @param answerContent
     */
    public DtAnswer(Long questionId, String userId, String answerContent) {
        this.questionId = questionId;
        this.userId = userId;
        this.answerContent = answerContent;
    }

    public String getAnswerContent() {
        return answerContent;
    }

    public void setAnswerContent(String answerContent) {
        this.answerContent = answerContent;
    }

    public Date getAnswerTime() {
        return answerTime;
    }

    public void setAnswerTime(Date answerTime) {
        this.answerTime = answerTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}