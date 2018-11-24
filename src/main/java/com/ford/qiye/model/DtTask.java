package com.ford.qiye.model;

import java.util.Date;

/**
 * Task 业务类型
 * Created by wanglijun on 16/9/23.
 */
public class DtTask {

    /**待处理*/
    public static final Long STATUS_PENDING=1L;

    /**处理中*/
    public static final Long STATUS_HANDLING=2L;

    /**完成的*/
    public static final Long STATUS_FINISHED=3L;


    private Long id;
    /**
     * 状态 1 待处理 2 处理中 3 已处理
     */
    private Long status;
    /**
     * 参数
     */
    private String params;
    /**
     * 备注
     */
    private String remark;
    /**

    /**
     * 批次号
     */
    private String batchNo;
    /**
     * 数据类型
     */
    private String dataType;

    /**
     * 业务编号
     */
    private Long businessId;

    private Date createdDate;
    /***
     * @Fields updatedById: is update Date
     */
    private Date updatedDate;


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public void setBusinessId(Long businessId) {
        this.businessId = businessId;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }

    public Date getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Date createdDate) {
        this.createdDate = createdDate;
    }

    public Date getUpdatedDate() {
        return updatedDate;
    }

    public void setUpdatedDate(Date updatedDate) {
        this.updatedDate = updatedDate;
    }
}
