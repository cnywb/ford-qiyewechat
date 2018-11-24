package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtTask;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/9/23.
 */
public interface TaskService {
    /***
     * 查询列表
     * @param pageGrid
     * @param status
     * @param dataType
     * @param batchNo
     * @return
     */
    Pagination<DtTask> queryPage(PageGrid pageGrid, Long status, String dataType, String batchNo);



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
    void updateById(Long id,Long status, String batchNo,Date updatedDate);

    /***
     * 查询状态
     * @param status
     * @param dataType
     * @return
     */
    List<DtTask> queryByStatus(Long status, String dataType);
}
