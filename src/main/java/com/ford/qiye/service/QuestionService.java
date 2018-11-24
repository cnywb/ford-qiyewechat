package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtQuestion;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface QuestionService {


    List<DtQuestion> findByUserId(String userId);



    /***
     *  更新状态信息
     * @param id
     * @param status
     */
    void updateQuestionStatus(Long id,Integer status);
    /***
     *  更新状态信息
     * @param id
     * @param status
     */
    void updateQuestionStatus(Long id,Integer status,Long departId);

    /***
     * 根据ID删除
     * @param id
     */
    void deleteById(Long id);

    /***
     * 保存问题
     * @param dtQuestion
     */
    Long insert(DtQuestion dtQuestion);


    /***
     *
     * @param id
     * @return
     */
    DtQuestion findById( Long id);

    /**
     *
     * @param id
     * @param departId
     * @return
     */
    Integer queryByDepartId(Long id,Long departId);


    /***
     *
     * @param page
     * @param userId
     * @param departId
     * @param content
     * @param startDate
     * @param endDate
     * @param status
     * @param areaName
     * @param isAgency
     * @return
     */
    Pagination<DtQuestion> findByPage(PageGrid page,String userId,Long departId, String content, Date startDate, Date endDate,Integer status,String areaName,String smallName,String isAgency);

    /***
     * 统计内容是否有重复
     * @param userId
     * @param content
     * @return
     */
    Long findByUserId(String userId, String content);
}
