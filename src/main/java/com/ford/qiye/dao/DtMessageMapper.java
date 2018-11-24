package com.ford.qiye.dao;

import com.ford.qiye.model.DtMessage;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface DtMessageMapper {
    /***
     *@param id
     * @param realName
     * @param status
     * @param startDate
     * @param endDate
     * @return
     */
   List<DtMessage> queryPage(@Param ("id") Long id,
                             @Param ("realName")String realName,
                             @Param ("status")Integer status,
                             @Param ("startDate")Date startDate,
                             @Param ("endDate")Date endDate);

    /***
     * insert
     * @param message
     */
    void insert(DtMessage message);

    void delete(@Param ("id")Long id);
}
