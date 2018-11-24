package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/8/10.
 */
public class DtMark {
    /***
     *有效的
     */
    public static final Integer VALID=0;
    /**无效的*/
    public static final Integer  INVALID=1;

    private Long id;

    private String name;

    private Integer ifActive;

    public DtMark() {

    }

    public DtMark(String name,Integer ifActive) {
        this.name = name;
        this.ifActive=ifActive;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getIfActive() {
        return ifActive;
    }

    public void setIfActive(Integer ifActive) {
        this.ifActive = ifActive;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
