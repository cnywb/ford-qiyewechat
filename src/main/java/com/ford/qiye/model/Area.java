package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/8/7.
 */
public class Area {

    protected Long id;

    protected Long parentId;

    protected String name;

    protected String code;

    protected String remark;

    protected Integer ifActive;

    protected Integer priority;


    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getPriority() {
        return priority;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

}
