package com.ford.qiye.dao;

import com.ford.qiye.model.DtRoleFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/11.
 */
public interface  DtRoleFunctionMapper {
    /****
     * 根据roleId删除角色授权信息;
     * @param roleId
     */
    void deleteByRoleId(@Param ("roleId")Long roleId);

    /***
     * 新增角色授权信息;
     * @param roles
     */
    void insert(@Param("roles")List<DtRoleFunction> roles);
}
