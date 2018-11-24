package com.ford.qiye.service;

import com.ford.qiye.model.DtRoleFunction;

import java.util.List;

/**
 * Created by wanglijun on 16/8/11.
 */
public interface RoleFunctionService {

    /***
     *
     * @param roleId
     */
     void deleteByRoleId(Long roleId);

    /***
     *  权限列表
     * @param  roleId
     * @param roleFunctions
     */
     void insert(Long roleId,List<DtRoleFunction> roleFunctions);
}
