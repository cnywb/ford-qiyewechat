package com.ford.qiye.service.impl;


import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtMessageMapper;
import com.ford.qiye.model.DtMessage;
import com.ford.qiye.service.BiMessageService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Service
public class BiMessageServiceImpl implements BiMessageService {


    @Autowired
    private DtMessageMapper messageMapper;

    /***
     * @param page
     * @param id
     * @param realName
     * @param status
     * @param startDate
     * @param endDate
     * @return
     */
    @Override
    public Pagination<DtMessage> queryPage(PageGrid page, Long id, String realName, Integer status, Date startDate, Date endDate) {
        Page<DtMessage> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);
        List<DtMessage> list= messageMapper.queryPage(id, realName, status, startDate, endDate);
        return new Pagination<DtMessage> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * @param page
     * @param realName
     * @param status
     * @return
     */
    @Override
    public Pagination<DtMessage> queryPage(PageGrid page, String realName, Integer status) {
        return this.queryPage (page,null,realName,status,null,null);
    }




    /**
     * 保存消息
     *
     * @param message
     */
    @Override
    public void insert(DtMessage message) {
        this.messageMapper.insert(message);
    }

    /***
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.messageMapper.delete(id);
    }
}
