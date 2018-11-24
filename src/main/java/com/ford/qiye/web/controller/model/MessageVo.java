package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * 消息管理
 * Created by wanglijun on 16/8/10.
 */
public class MessageVo extends PageGrid {
    /**ID*/
    private Long id;
    /**真实姓名*/
    private String realName;
    /**状态*/
    private Integer status;
    

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
