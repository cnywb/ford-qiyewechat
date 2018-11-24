package com.ford.qiye.model;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/5.
 */
public class DtRoleFunction {

    private Long roleId;

    private Long functionId;

    private Date createTime;

    public DtRoleFunction() {
    }
    public DtRoleFunction(Long roleId,Long functionId) {
        this.roleId = roleId;
        this.functionId = functionId;
    }



    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getFunctionId() {
        return functionId;
    }

    public void setFunctionId(Long functionId) {
        this.functionId = functionId;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }
}
