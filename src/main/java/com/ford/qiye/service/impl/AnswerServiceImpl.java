package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtAnswerMapper;
import com.ford.qiye.model.DtAnswer;
import com.ford.qiye.service.AnswerService;
import com.fute.backer.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
@Service
public class AnswerServiceImpl implements AnswerService{


    @Autowired
    private DtAnswerMapper answerMapper;

    @Override
    public Long saveAnswer(DtAnswer answer) {
        return answerMapper.saveAnswer (answer);
    }

    /***
     * @param questionId
     * @return
     */
    @Override
    public List<DtAnswer> queryByQuestionId(Long questionId) {
        return answerMapper.queryByQuestionId (questionId);
    }
}
