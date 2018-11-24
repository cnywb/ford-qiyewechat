package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtRoleFunctionMapper;
import com.ford.qiye.model.DtRoleFunction;
import com.ford.qiye.service.RoleFunctionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglijun on 16/8/11.
 */
@Service
public class RoleFunctionServiceImpl implements RoleFunctionService{


    @Autowired
    private DtRoleFunctionMapper roleFunctionMapper;

    /***
     * @param roleId
     */
    @Override
    public void deleteByRoleId(Long roleId) {
        roleFunctionMapper.deleteByRoleId (roleId);
    }


    /***
     * 权限列表
     *
     * @param roleId
     * @param roleFunctions
     */
    @Override
    public void insert(Long roleId, List<DtRoleFunction> roleFunctions) {
        //删除权限列表
         roleFunctionMapper.deleteByRoleId (roleId);
        //保存权限
        roleFunctionMapper.insert (roleFunctions);
    }
}
