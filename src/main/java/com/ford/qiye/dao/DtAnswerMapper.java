package com.ford.qiye.dao;

import com.ford.qiye.model.DtAnswer;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface DtAnswerMapper {

    /***
     * @param answer
     * @return
     */
    Long saveAnswer(DtAnswer answer);

    List<DtAnswer> queryByQuestionId(@Param ("questionId") Long questionId);
}
