package com.ford.qiye.web.controller.model;

import com.ford.qiye.common.PageGrid;

/**
 * Created by wanglijun on 16/8/10.
 */
public class MarkVo extends PageGrid{

    private Long id;

    private String name;

    private Integer ifActive;


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


    public Integer getIfActive() {
        return ifActive;
    }

    public void setIfActive(Integer ifActive) {
        this.ifActive = ifActive;
    }
}
