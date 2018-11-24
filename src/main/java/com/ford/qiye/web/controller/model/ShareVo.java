package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/9.
 */
public class ShareVo {
    /**问题ID*/
    private Long questionId;

    /**部门ID*/
    private List<Long> departIds;


    public List<Long> getDepartIds() {
        return departIds;
    }

    public void setDepartIds(List<Long> departIds) {
        this.departIds = departIds;
    }

    public Long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(Long questionId) {
        this.questionId = questionId;
    }
}
