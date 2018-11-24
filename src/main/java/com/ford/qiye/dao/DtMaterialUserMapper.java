package com.ford.qiye.dao;

import com.ford.qiye.model.DtMaterialUser;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanglijun on 16/8/9.
 */
public interface DtMaterialUserMapper {

    void insert(DtMaterialUser materialUser);

    /***
     * 查询是否已经过了
     * @param materialId
     * @param userId
     * @return
     */
    DtMaterialUser findByUserId(@Param ("materialId") Long materialId, @Param ("userId")String userId);
}
