package com.ford.qiye.dao;

import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.OperationType;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/12/3.
 */
public interface OperationLogMapper {
    /***
     *
     * @param log
     * @return
     */
    Long insert(@Param("log") OperationLog  log);

    /***
     *
     * @param userName
     * @param operationType
     * @param startDate
     * @param endDate
     * @return
     */
    List<OperationLog> findByPage(@Param ("userName") String userName,
                                  @Param ("operationType")OperationType operationType,
                                  @Param ("startDate")Date startDate,
                                  @Param ("endDate")Date endDate);
}
