package com.ford.qiye.dao;

import com.ford.qiye.model.DtFunction;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
public interface DtFunctionMapper {

    /***
     * 根据用户ID查询菜单
     * @param userId
     * @return
     */
    List<DtFunction> queryMenuByUserId(@Param ("userId") String userId);

    /***
     * 查询所有权限列表
     * @return
     */
    List<DtFunction> queryAll();
    
}
