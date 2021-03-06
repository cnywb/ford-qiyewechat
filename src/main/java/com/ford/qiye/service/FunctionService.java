package com.ford.qiye.service;

import com.ford.qiye.model.DtFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public interface FunctionService {

    /***
     * 根据用户ID查询菜单
     * @param userId
     * @return
     */
    List<DtFunction> queryMenuByUserId(@Param("userId") String userId);

    /**查询所有权限列表*/
    List<DtFunction> queryAll();
}
