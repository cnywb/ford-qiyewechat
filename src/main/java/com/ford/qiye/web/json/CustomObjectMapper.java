package com.ford.qiye.web.json;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Date;

/**
 * Created by wanglijun on 16/8/5.
 */
public class CustomObjectMapper extends ObjectMapper {

    /**
     * 序列化
     */
    private static final long serialVersionUID = -680693716098834498L;

    public CustomObjectMapper() {
        SimpleModule module = new SimpleModule ();
        module.addSerializer (Date.class, new DateTimeSerializer ());
        //设置null值不参与序列化(字段不被显示)
        this.setSerializationInclusion (JsonInclude.Include.NON_NULL);
        //当找不到对应的序列化器时 忽略此字段
        this.configure (SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
        this.registerModule (module);
    }
}
