package com.ford.qiye.dao;

import com.ford.qiye.model.DtQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface   DtQuestionMapper {


    /***
     * 问题列表
     * @param userId
     * @return
     */
    List<DtQuestion> findByUserId(@Param ("userId")String userId);

    /***
     * 保存的问题;
     * @param question
     * @return
     */
     Long insert(DtQuestion question);

    /***
     *
     * @param userId
     * @param departId
     * @param content
     * @param startDate
     * @param endDate
     * @param status
     * @param areaName
     * @param smallName
     * @return
     */
    List<DtQuestion> findByPage(@Param ("userId") String userId,@Param ("departId") Long departId, @Param ("content") String content,
                              @Param ("startDate")Date startDate, @Param ("endDate")Date endDate, @Param ("status")Integer status,
                              @Param ("areaName")String areaName,@Param ("smallName") String smallName,@Param ("isAgency")String isAgency);




    /***
     *
     * @param id
     * @param status
     * @param departId
     */
    void updateQuestionStatus(@Param("id") Long id, @Param("status")Integer  status,@Param ("departId") Long departId);

    /***
     * 删除ID
     * @param id
     */
    void deleteById(@Param ("id") Long id);

    /***
     *
     * @param id
     * @return
     */
    List<DtQuestion> findById(@Param ("id") Long id);

    /**
     *
     * @param id
     * @param departId
     * @return
     */
    Integer countByDepartId(@Param ("id") Long id,@Param ("departId") Long departId);

    /***
     *
     * @param userId
     * @param content
     * @return
     */
    Long findByContent(@Param("userId") String userId,@Param("content") String content);
}
