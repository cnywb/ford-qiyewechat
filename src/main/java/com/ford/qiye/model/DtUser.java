package com.ford.qiye.model;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/5.
 */
public class DtUser {

    /**管理员*/
    public static final String ADMIN_ID="1";

    private String id;

    private String userName;

    private String password;

    private String realName;

    private String email;

    private String qq;

    private String phone;

    private String telephone;
    /**创建时间*/
    private Date createTime;
    /**状态*/
    private Integer status;
    /**省份*/
    private Long  provinceId;
    /**头像*/
    private String headImage;
    /**性别*/
    private Integer sex;
    /**生日*/
    private Date birthday;
    /**职位*/
    private String position;
    /**部门编号*/
    private Long  departId;
    /**部门名称*/
    private String departName;
    /**微信编号*/
    private String wxNum;
    /***用户类型 1:经销商员工    0：福特员工**/
    private String isAgency;
    /**大区*/
    private String areaName;
    /**小区*/
    private String smallName;
    /**大区ID*/
    private String areaId;
    /**小区ID*/
    private String smallAreaId;
    /**经销商服务代码*/
    private String serveCode;
    /**经销商销售代码*/
    private String sellCode;
    /**角色ID*/
    private Long roleId;
    /**解决代码*/
    /**密码有效期*/
    private Date credentialExpiredDate;
    /**密码到期天数*/
    private Long expiredDay;


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public String getDepartName() {
        return departName;
    }

    public void setDepartName(String departName) {
        this.departName = departName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getHeadImage() {
        return headImage;
    }

    public void setHeadImage(String headImage) {
        this.headImage = headImage;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIsAgency() {
        return isAgency;
    }

    public void setIsAgency(String isAgency) {
        this.isAgency = isAgency;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
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

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getSmallName() {
        return smallName;
    }

    public void setSmallName(String smallName) {
        this.smallName = smallName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getWxNum() {
        return wxNum;
    }

    public void setWxNum(String wxNum) {
        this.wxNum = wxNum;
    }


    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }



    public String getSmallAreaId() {
        return smallAreaId;
    }

    public void setSmallAreaId(String smallAreaId) {
        this.smallAreaId = smallAreaId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public static String getAdminId() {
        return ADMIN_ID;
    }

    public Date getCredentialExpiredDate() {
        return credentialExpiredDate;
    }

    public void setCredentialExpiredDate(Date credentialExpiredDate) {
        this.credentialExpiredDate = credentialExpiredDate;
    }

    public Long getExpiredDay() {
        return expiredDay;
    }

    public void setExpiredDay(Long expiredDay) {
        this.expiredDay = expiredDay;
    }

    @Override
    public String toString() {
        return "DtUser{" +
                "areaId='" + areaId + '\'' +
                ", id='" + id + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", realName='" + realName + '\'' +
                ", email='" + email + '\'' +
                ", qq='" + qq + '\'' +
                ", phone='" + phone + '\'' +
                ", telephone='" + telephone + '\'' +
                ", createTime=" + createTime +
                ", status=" + status +
                ", provinceId=" + provinceId +
                ", headImage='" + headImage + '\'' +
                ", sex=" + sex +
                ", birthday=" + birthday +
                ", position='" + position + '\'' +
                ", departId=" + departId +
                ", departName='" + departName + '\'' +
                ", wxNum='" + wxNum + '\'' +
                ", isAgency='" + isAgency + '\'' +
                ", areaName='" + areaName + '\'' +
                ", smallName='" + smallName + '\'' +
                ", smallAreaId='" + smallAreaId + '\'' +
                ", serveCode='" + serveCode + '\'' +
                ", sellCode='" + sellCode + '\'' +
                ", roleId=" + roleId +
                '}';
    }
}
