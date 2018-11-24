package com.ford.qiye.service.impl;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.OperationLogMapper;
import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.OperationLogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/12/3.
 */
@Service
public class OperationLogSerivceImpl implements OperationLogService {

    @Autowired
    private OperationLogMapper mapper;

    /***
     * 查询分页
     *
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Pagination<OperationLog> findByPage(PageGrid page, String userName, OperationType operationType, Date startDate, Date endDate) {

        Page<OperationLog> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<OperationLog> list=  mapper.findByPage (userName, operationType, startDate, endDate);

        return new Pagination<> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }

    /***
     * 保存
     *
     * @param operationLog
     * @return
     */
    @Override
    public Long insert(OperationLog operationLog) {
        return mapper.insert (operationLog);
    }
}
