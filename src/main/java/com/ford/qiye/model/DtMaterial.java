package com.ford.qiye.model;

        import java.util.Date;

/**
 * Created by wanglijun on 16/8/9.
 */
public class DtMaterial {
    /**启用中**/
    public static final Integer STATUS_INUSE=0;

    public  static final Integer STATUS_STOPED=1;

    private Long id;
    /**物料名称*/
    private  String name;
    /**物料内容简介*/
    private  String content;
    /**物料路径*/
    private String saveUrl;
    /**下载次数*/
    private Integer download;
    /**0启动 1禁用*/
    private Integer status;
    /**下发人*/
    private String userId;
    /**大区*/
    private String areaName;
    /**小区*/
    private String smallName;
    /**服务代码*/
    private String serveCode;
    /**销售代码*/
    private String sellCode;
    /**创建时间*/
    private Date createTime;
    /***发送者的部门ID*/
    private Long departId;

    private String timestamp;
    /**用户真实姓名*/
    private String realName;


    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
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

    public Integer getDownload() {
        return download;
    }

    public void setDownload(Integer download) {
        this.download = download;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSaveUrl() {
        return saveUrl;
    }

    public void setSaveUrl(String saveUrl) {
        this.saveUrl = saveUrl;
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

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }


}
