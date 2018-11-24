package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtRoleMapper;
import com.ford.qiye.model.DtRole;
import com.ford.qiye.service.RoleService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
@Service
public class RoleServiceImpl  implements RoleService{


    @Resource
    private DtRoleMapper dtRoleMapper;


    /***
     * 保存用户角色信息
     *
     * @param userId
     * @param roleIds
     */
    @Override
    public void insertUserRole(String userId, List<Long> roleIds) {
        //删除用户角色
        dtRoleMapper.deleteByUserId (userId);

        if(CollectionUtils.isEmpty (roleIds)){
            return;
        }

        for(Long roleId:roleIds){
            dtRoleMapper.insertUserRole(userId,roleId);
        }
    }

    /**
     * 查询所有角色信息
     */
    @Override
    public List<DtRole> queryAll() {
        return dtRoleMapper.queryAll ();
    }


    /***
     * 根据用户ID查询该用户所有角色;
     *
     * @param userId
     * @return List<DtRole>
     */
    @Override
    public List<DtRole> queryByUserId(String userId) {
        return dtRoleMapper.queryByUserId (userId);
    }


    /***
     * 根据ID查询数据
     *
     * @param id
     * @return DtRole
     */
    @Override
    public DtRole findById(Long id) {
        return this.dtRoleMapper.findById (id);
    }


    /***
     * 更新
     *
     * @param role
     */
    @Override
    public void update(DtRole role) {
        this.dtRoleMapper.update (role);
    }
}
