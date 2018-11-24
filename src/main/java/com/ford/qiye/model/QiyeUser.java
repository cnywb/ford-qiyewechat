package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/9/26.
 */
public class QiyeUser {

    private String userid;

    private String name;

    private Long[] department;

    private String position;

    private String mobile;

    private String gender;

    private String email;

    private String wexinid;

    private String enable;

    private String avatar_mediaid;

    private String extattr;


    public String getAvatar_mediaid() {
        return avatar_mediaid;
    }

    public void setAvatar_mediaid(String avatar_mediaid) {
        this.avatar_mediaid = avatar_mediaid;
    }

    public Long[] getDepartment() {
        return department;
    }

    public void setDepartment(Long[] department) {
        this.department = department;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEnable() {
        return enable;
    }

    public void setEnable(String enable) {
        this.enable = enable;
    }

    public String getExtattr() {
        return extattr;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getWexinid() {
        return wexinid;
    }

    public void setWexinid(String wexinid) {
        this.wexinid = wexinid;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
