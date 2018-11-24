package com.ford.qiye.model;

/**
 * Created by wanglijun on 16/8/6.
 */
public class DtParameter {
    /***
     * 欢迎词
     */
    public static final String KEY_WELCOME_WORD="WECHAT_QY_WELCOME_WORDS";
    /**KEY*/
    public static final String WECHAT_QY_CORP_ID="WECHAT_QY_CORP_ID";

    private Long id;

    private String category;

    private String name;

    private String key;

    private String value;


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
