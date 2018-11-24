package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/14.
 */
public class UserMarkVo {
    /**用户ID*/
    private String userId;
    /**标签ID*/
    private List<Long> markIds;

    public List<Long> getMarkIds() {
        return markIds;
    }

    public void setMarkIds(List<Long> markIds) {
        this.markIds = markIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
