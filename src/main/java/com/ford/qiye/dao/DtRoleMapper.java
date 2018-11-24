package com.ford.qiye.dao;

import com.ford.qiye.model.DtRole;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public interface DtRoleMapper {

    /**查询所有角色信息*/
    List<DtRole> queryAll();

    /**
     * 根据用户ID查询该用户所有的角色
     * @param userId
     * @return List<DtRole>
     */
    List<DtRole> queryByUserId(@Param ("userId") String userId);

    /***
     * 根据ID查询数据
     * @param id
     * @return DtRole
     */
    DtRole findById(@Param ("id")Long id);

    /***
     * 更新
     * @param role
     */
    void update(@Param ("role")DtRole role);

    /***
     * 更新用户角色
     * @param userId
     * @param roleId
     */
    void insertUserRole(@Param("userId")String userId,@Param("roleId")Long roleId);


    /***
     * 删除用户角色
     * @param userId
     */
    void deleteByUserId(@Param ("userId") String userId);
}
