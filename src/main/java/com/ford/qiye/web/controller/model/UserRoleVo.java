package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/15.
 */
public class UserRoleVo {
    /**用户ID*/
    private String userId;
    /**标签ID*/
    private List<Long> roleIds;


    public List<Long> getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(List<Long> roleIds) {
        this.roleIds = roleIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
