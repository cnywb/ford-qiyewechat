package com.ford.qiye.model;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/8.
 */
public class DtDepartment {

    /**ID*/
    private Long id;
    /**部门ID*/
    private Long departId;
    /**部门名称*/
    private String departName;
    /**是否激活*/
    private Integer ifActive;
    /**上级部门ID*/
    private Long parentId;
    /**上级部门ID*/
    private Long departParentId;
    /**创建日期*/
    private Date createTime;



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIfActive() {
        return ifActive;
    }

    public void setIfActive(Integer ifActive) {
        this.ifActive = ifActive;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    public Long getDepartId() {
        return this.id;
    }

    public void setDepartId(Long departId) {
        this.departId=this.id;
    }

    public Long getDepartParentId() {
        return  this.parentId;
    }

    public void setDepartParentId(Long departParentId) {
        this.departParentId = this.parentId;
    }


}
