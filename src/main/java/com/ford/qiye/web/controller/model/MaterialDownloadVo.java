package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/8/16.
 */
public class MaterialDownloadVo extends PageGrid {

    private Long materialId;

    private String realName;

    private String startDate;

    private String endDate;


    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getMaterialId() {
        return materialId;
    }

    public void setMaterialId(Long materialId) {
        this.materialId = materialId;
    }

    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
}
