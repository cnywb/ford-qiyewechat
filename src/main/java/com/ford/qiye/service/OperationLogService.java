package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.OperationType;

import java.util.Date;

/**
 * Created by wanglijun on 16/12/3.
 */
public interface OperationLogService {
    /***
     * 保存
     * @param operationLog
     * @return
     */
    Long insert(OperationLog  operationLog);

    /***
     * 查询分页
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDate
     * @return
     */
    Pagination<OperationLog> findByPage (PageGrid page, String userName, OperationType operationType, Date startDate, Date endDate);
}
