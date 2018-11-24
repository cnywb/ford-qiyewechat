package com.ford.qiye.service.impl;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtMarkMapper;
import com.ford.qiye.model.DtMark;
import com.ford.qiye.service.MarkService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Service
public class MarkServiceImpl  implements MarkService{


    @Autowired
    private DtMarkMapper dtMarkMapper;


    /***
     * 设置用户标签
     *
     * @param userId
     * @param markIds
     */
    @Override
    public void insertUserMark(String userId, List<Long> markIds) {

        //先删除用户的标签
        dtMarkMapper.deleteByUserId (userId);

        if(CollectionUtils.isEmpty (markIds)){
            return;
        }
        for(Long markId:markIds) {
            dtMarkMapper.insertUserMark (userId, markId);
        }
    }

    /***
     * 根据用户查询标签
     *
     * @param userId
     * @return
     */
    @Override
    public List<DtMark> findByUserId(String userId) {
        return dtMarkMapper.findByUserId (userId);
    }

    /***
     * 查询所有标签
     *
     * @return
     */
    @Override
    public List<DtMark> queryAll() {
        return this.dtMarkMapper.queryAll ();
    }

    /**
     * @param page
     * @param id
     * @param name
     * @return
     */
    @Override
    public Pagination<DtMark> queryPage(PageGrid page, Long id, String name) {
        Page<DtMark> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);
        List<DtMark> list= dtMarkMapper.queryList (id,name);
        return new Pagination<DtMark> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * 根据ID查询
     *
     * @param id
     * @return DtMark
     */
    @Override
    public DtMark findById(Long id) {
        return this.dtMarkMapper.findById (id);
    }

    /***
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        this.dtMarkMapper.deleteById (id);
    }

    /**
     * 保存mark
     *
     * @param mark
     */
    @Override
    public Long insert(DtMark mark) {
        return this.dtMarkMapper.insert (mark);
    }

    /**
     * 更新Mark
     *
     * @param mark
     */
    @Override
    public void update(DtMark mark) {
        this.dtMarkMapper.update (mark);
    }
}
