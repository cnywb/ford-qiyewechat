package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/8/10.
 */
public class DealerVo extends PageGrid{

    /**真实姓名*/
    private String realName;
    /**手机号*/
    private String phone;
    /**部门ID*/
    private Long  departId;
    /**经销商服务代码*/
    private String serveCode;
    /**经销商销售代码*/
    private String sellCode;
    /**用户名*/
    private String userName;
    /**邮箱*/
    private String email;
    /**职位*/
    private String position;
    /**类型*/
    private String isAgency;

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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
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

    public String getSellCode() {
        return sellCode;
    }

    public void setSellCode(String sellCode) {
        this.sellCode = sellCode;
    }

    public String getServeCode() {
        return serveCode;
    }

    public void setServeCode(String serveCode) {
        this.serveCode = serveCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    public String getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(String isAgency) {
        this.isAgency = isAgency;
    }
}
