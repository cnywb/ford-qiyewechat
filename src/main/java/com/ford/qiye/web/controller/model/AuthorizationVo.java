package com.ford.qiye.web.controller.model;

import java.util.List;

/**
 * Created by wanglijun on 16/8/11.
 */
public class AuthorizationVo {

    private Long id;

    private List<Long>  ids;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<Long> getIds() {
        return ids;
    }

    public void setIds(List<Long> ids) {
        this.ids = ids;
    }


    @Override
    public String toString() {
        return "AuthorizationVo{" +
                "id=" + id +
                ", ids=" + ids +
                '}';
    }
}
