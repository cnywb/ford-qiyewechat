package com.ford.qiye.service;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface ParameterService {
    /***
     * 根据KEY,获取Value的值
     * @param key
     * @return
     */
    String getValue(String key);

    /***
     * 根据KEY更新Value
     * @param key
     * @param value
     * @return
     */
    void updateValue(String key,String value);
}
