package com.ford.qiye.service;

import com.ford.qiye.model.DtRole;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public interface RoleService {

    /**查询所有角色信息*/
    List<DtRole> queryAll();

    /***
     * 根据用户ID查询该用户所有角色;
     * @param userId
     * @return List<DtRole>
     */
    List<DtRole> queryByUserId(String userId);

    /***
     * 根据ID查询数据
     * @param id
     * @return DtRole
     */
    DtRole findById(Long id);

    /***
     * 更新
     * @param role
     */
    void update(DtRole role);

    /***
     * 保存用户角色信息
     * @param userId
     * @param roleIds
     */
    void insertUserRole(String userId,List<Long> roleIds);
}
