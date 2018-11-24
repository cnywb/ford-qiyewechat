package com.ford.qiye.service;

import com.ford.qiye.model.DtApp;

/**
 * Created by wanglijun on 16/8/17.
 */
public interface AppService {
    /***
     * 查询DtApp
     * @param departId
     * @return
     */
    DtApp findByDepartId(Long departId);
}
