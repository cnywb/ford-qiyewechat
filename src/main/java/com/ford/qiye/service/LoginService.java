package com.ford.qiye.service;

import com.ford.qiye.model.DtUser;

/**
 * Created by wanglijun on 16/8/5.
 */
public interface LoginService {


    /***
     * 根据用户名查询用户信息
     * @param userName
     * @return
     */
    DtUser doLogin(String userName);

}
