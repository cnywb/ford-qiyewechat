package com.ford.qiye.service;

import com.ford.qiye.model.DtAnswer;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface AnswerService {

    Long saveAnswer(DtAnswer answer);

    /***
     *
     * @param questionId
     * @return
     */
    List<DtAnswer>  queryByQuestionId(Long questionId);
}
