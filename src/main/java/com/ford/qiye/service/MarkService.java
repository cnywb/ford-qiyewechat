package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtMark;

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
public interface MarkService {

    /***
     * 设置用户标签
     * @param userId
     * @param markIds
     */
    void insertUserMark(String userId,List<Long> markIds);

    /***
     * 根据用户查询标签
     * @param userId
     * @return
     */
    List<DtMark> findByUserId(String userId);

    /**
     * @param page
     * @param id
     * @param name
     * @return
     */
    Pagination<DtMark> queryPage(PageGrid page,Long id,String name);

    /**保存mark*/
    Long insert(DtMark mark);
    /**更新Mark*/
    void update(DtMark mark);

    /***
     * 根据ID查询
     * @param id
     * @return DtMark
     */
    DtMark findById(Long id);

    /***
     * 根据ID删除
     * @param id
     */
    void  deleteById(Long id);

    /***
     * 查询所有标签
     * @return
     */
    List<DtMark> queryAll();
}
