package com.ford.qiye.web.wechat.controller.vo;

/**
 * Created by wanglijun on 16/8/22.
 */
public class QuestionIssueVo {
    /**成功*/
    public static final Integer SUCCESS=1;
    /**失败*/
    public static final Integer FAILURE=2;


    /**内容管理*/
    private String content;
    /**部门*/
    public  Long departId;
    /**状态 1 发布成功,2,发布失败*/
    public Integer status;
    /**发布消息*/
    public String message;

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

    public static Integer getFAILURE() {
        return FAILURE;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static Integer getSUCCESS() {
        return SUCCESS;
    }
}
