package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/8/17.
 */
public class DtApp {

    private Long id;

    private String appImg;

    private String linkUrl;

    private String appName;

    private Long departId;

    private Long qiyeAppId;


    public String getAppImg() {
        return appImg;
    }

    public void setAppImg(String appImg) {
        this.appImg = appImg;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public Long getDepartId() {
        return departId;
    }

    public void setDepartId(Long departId) {
        this.departId = departId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }

    public Long getQiyeAppId() {
        return qiyeAppId;
    }

    public void setQiyeAppId(Long qiyeAppId) {
        this.qiyeAppId = qiyeAppId;
    }
}
