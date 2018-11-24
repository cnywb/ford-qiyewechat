package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtParameterMapper;
import com.ford.qiye.model.DtParameter;
import com.ford.qiye.service.ParameterService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
@Service
public class ParameterServiceImpl implements ParameterService {


    @Autowired
    private DtParameterMapper parameterMapper;


    /***
     * 根据KEY,获取Value的值
     *
     * @param key
     * @return
     */
    @Override
    public String getValue(String key) {
        List<DtParameter> list=parameterMapper.getValueForKey (key);
        if(CollectionUtils.isEmpty (list)){
           return  StringUtils.EMPTY;
        }
        return  list.get (0).getValue ();
    }


    /***
     * 根据KEY更新Value
     *
     * @param key
     * @param value
     * @return
     */
    @Override
    public void updateValue(String key, String value) {
        parameterMapper.updateValueByKey (key,value);
    }
}
