package com.ford.qiye.dao;

import com.ford.qiye.model.DtTask;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 任务列表
 * Created by wanglijun on 16/9/23.
 */
public interface DtTaskMapper {

    /***
     *查询分析
     * @param status
     * @param dataType
     * @param batchNo
     * @return
     */
      List<DtTask> queryList(@Param("status")Long status, @Param("dataType")String dataType, @Param("batchNo")String batchNo);





    /***
     * 新增
     * @param task
     * @return
     */
    Long insert(DtTask task);

    /***
     * @param id
     * @param status
     * @param batchNo
     * @param updatedDate
     */
    void updateById(@Param("id")Long id, @Param("status")Long status, @Param("batchNo")String batchNo, @Param("updatedDate")Date updatedDate);
}
