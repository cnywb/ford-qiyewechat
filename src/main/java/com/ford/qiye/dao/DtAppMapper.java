package com.ford.qiye.dao;

import com.ford.qiye.model.DtApp;
import com.sun.tools.javac.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * Created by wanglijun on 16/8/17.
 */
public interface DtAppMapper {

    /***
     * 查询DtApp
     * @param departId
     * @return
     */
   List<DtApp> findByDepartId(@Param ("departId") Long departId);

}
