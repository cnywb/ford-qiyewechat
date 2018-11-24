package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtMessage;

import java.util.Date;

/**
 * 消息发送
 * Created by wanglijun on 16/8/10.
 */
public interface BiMessageService {
    /***
     *
     * @param page
     * @param id
     * @param realName
     * @param status
     * @param startDate
     * @param endDate
     * @return
     */
      Pagination<DtMessage> queryPage(PageGrid page, Long id,String realName, Integer status, Date startDate,Date endDate);

    /***
     *
     * @param page
     * @param realName
     * @param status
     * @return
     */
      Pagination<DtMessage> queryPage(PageGrid page,String realName, Integer status);
      /**保存消息*/
      void insert(DtMessage message);

    /***
     * 删除
     * @param id
     */
      void delete(Long id);
}
