package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/8/13.
 */
public class AreaTree extends Area{

    private Long pId;//ztree的父ID

    private Boolean isParent;// ztree的是否父节点

    public Long getpId() {
        return parentId;
    }

    public void setpId(Long pId) {
        this.pId = pId;
    }

    public Boolean getIsParent() {
        return parentId==0;
    }

    public void setIsParent(Boolean isParent) {
        this.isParent = isParent;
    }
}
