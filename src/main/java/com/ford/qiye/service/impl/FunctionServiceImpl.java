package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtFunctionMapper;
import com.ford.qiye.model.DtFunction;
import com.ford.qiye.service.FunctionService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
@Service("functionService")
public class FunctionServiceImpl implements FunctionService{

    @Autowired
    private DtFunctionMapper dtFunctionMapper;

    /***
     * 根据用户ID查询菜单
     *
     * @param userId
     * @return
     */
    @Override
    public List<DtFunction> queryMenuByUserId(@Param("userId") String userId) {
        return this.dtFunctionMapper.queryMenuByUserId (userId);
    }


    /**
     * 查询所有权限列表
     */
    @Override
    public List<DtFunction> queryAll() {
        return dtFunctionMapper.queryAll ();
    }
}
