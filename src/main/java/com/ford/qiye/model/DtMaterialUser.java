package com.ford.qiye.model;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/16.
 */
public class DtMaterialUser {
    /**是否 是下载记录 1： 是 */
    public static final Integer DOWNLOAD_YES=1;
    /**是否 是下载记录 2：否*/
    public static final Integer DOWNLOAD_NO=2;

    private Long id;

    private Long materialId;

    private String userId;

    private Date createTime;

    private Integer isDownload;


    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public Integer getIsDownload() {
        return isDownload;
    }

    public void setIsDownload(Integer isDownload) {
        this.isDownload = isDownload;
    }
}
