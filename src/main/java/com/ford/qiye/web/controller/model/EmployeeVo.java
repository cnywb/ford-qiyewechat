package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/8/10.
 */
public class EmployeeVo extends PageGrid {

    /**真实姓名*/
    private String realName;
    /**用户名*/
    private String userName;
    /**职位*/
    private String position;
    /**邮箱*/
    private String email;
    /**部门ID*/
    private Long departId;
    /**角色ID*/
    private Long roleId;


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
