package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/12/4.
 */
public class UserAuthListVo extends PageGrid{

    /**用户名*/
    private String userName;

    /**角色名称*/
    private String roleName;

    /**资源名称*/
    private String functionName;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}
