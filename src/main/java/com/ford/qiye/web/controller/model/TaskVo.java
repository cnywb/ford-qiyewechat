package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * 任务类型
 * Created by wanglijun on 16/9/23.
 */
public class TaskVo extends PageGrid {

    private Long status;

    private String dataType;

    private String batchNo;

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getDataType() {
        return dataType;
    }

    public void setDataType(String dataType) {
        this.dataType = dataType;
    }

    public Long getStatus() {
        return status;
    }

    public void setStatus(Long status) {
        this.status = status;
    }
}
