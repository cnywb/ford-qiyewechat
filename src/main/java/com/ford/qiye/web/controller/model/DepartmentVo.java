package com.ford.qiye.web.controller.model;

/**
 * Created by wanglijun on 16/8/15.
 */
public class DepartmentVo {
    /**ID*/
    private Long id;
    /**部门名称*/
    private String departName;
    /**上次*/
    private Long parentId;


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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }


    @Override
    public String toString() {
        return "DepartmentVo{" +
                "departName='" + departName + '\'' +
                ", id=" + id +
                ", parentId=" + parentId +
                '}';
    }
}
