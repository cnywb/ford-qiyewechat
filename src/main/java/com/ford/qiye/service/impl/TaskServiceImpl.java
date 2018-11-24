package com.ford.qiye.service.impl;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtTaskMapper;
import com.ford.qiye.model.DtTask;
import com.ford.qiye.service.TaskService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/9/23.
 */
@Service
public class TaskServiceImpl  implements TaskService{

    @Autowired
    private DtTaskMapper dtTaskMapper;


    /***
     * 查询列表
     *
     * @param page
     * @param status
     * @param dataType
     * @param batchNo
     * @return
     */
    @Override
    public Pagination<DtTask> queryPage(PageGrid page, Long status, String dataType, String batchNo) {
        Page<DtTask> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);
        List<DtTask> list= dtTaskMapper.queryList (status,dataType,batchNo);
        return new Pagination<DtTask> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * 新增
     *
     * @param task
     * @return
     */
    @Override
    public Long insert(DtTask task) {
        return dtTaskMapper.insert (task);
    }

    /***
     * @param id
     * @param status
     * @param batchNo
     * @param updatedDate
     */
    @Override
    public void updateById(Long id, Long status, String batchNo, Date updatedDate) {
          dtTaskMapper.updateById (id,status,batchNo,updatedDate);
    }


    /***
     * 查询状态
     *
     * @param status
     * @param dataType
     * @return
     */
    @Override
    public List<DtTask> queryByStatus(Long status, String dataType) {

         return dtTaskMapper.queryList (status,dataType,null);

    }
}
