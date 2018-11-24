package com.ford.qiye.dao;

import com.ford.qiye.model.DtParameter;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public interface DtParameterMapper {
    /***
     * 根据KEY获取Value的值
     * @param key
     * @return
     */
    List<DtParameter> getValueForKey(@Param ("key") String key);

    /***
     * 根据key更新Value
     * @param key
     * @param value
     */
    void updateValueByKey(@Param ("key") String key, @Param ("value") String value);
}
