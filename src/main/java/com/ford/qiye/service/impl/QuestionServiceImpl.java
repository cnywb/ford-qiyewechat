package com.ford.qiye.service.impl;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtAnswerMapper;
import com.ford.qiye.dao.DtQuestionMapper;
import com.ford.qiye.model.DtAnswer;
import com.ford.qiye.model.DtQuestion;
import com.ford.qiye.service.QuestionService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
@Service
public class QuestionServiceImpl  implements QuestionService {

    @Autowired
    private DtQuestionMapper questionMapper;

    @Autowired
    private DtAnswerMapper answerMapper;


    @Override
    public List<DtQuestion> findByUserId(String userId) {
        List<DtQuestion> list=this.questionMapper.findByUserId (userId);
        if(CollectionUtils.isEmpty (list)){
            return null;
        }
        List<DtQuestion> questions=new ArrayList<> ();
        for(DtQuestion question:list){
            List<DtAnswer> answers=this.answerMapper.queryByQuestionId (question.getId ());
            question.setAnswers (answers);
            questions.add (question);
        }

        return questions;
    }


    /***
     * 统计内容是否有重复
     *
     * @param userId
     * @param content
     * @return
     */
    @Override
    public Long findByUserId(String userId, String content) {
        return this.questionMapper.findByContent(userId,content);
    }

    /***
     * @param page
     * @param content
     * @param startDate
     * @param endDate
     * @param status
     * @param areaName
     * @return
     */
    @Override
    public Pagination<DtQuestion> findByPage(PageGrid page,String userId,Long departId, String content, Date startDate, Date endDate,Integer status,String areaName,String smallName,String isAgency) {
        Page<DtQuestion> pageInfo=PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);
        List<DtQuestion>   list= questionMapper.findByPage (userId,departId,content,startDate,endDate,status,areaName,smallName,isAgency);
        return new Pagination<DtQuestion> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * 保存问题
     *
     * @param dtQuestion
     */
    @Override
    public Long insert(DtQuestion dtQuestion) {
        this.questionMapper.insert (dtQuestion);
        return dtQuestion.getId ();
    }

    /***
     *  更新状态信息
     *
     * @param id
     * @param status
     */
    @Override
    public void updateQuestionStatus(Long id, Integer status) {
        questionMapper.updateQuestionStatus (id,status,null);
    }


    /***
     *  更新状态信息
     *
     * @param id
     * @param status
     * @param departId
     */
    @Override
    public void updateQuestionStatus(Long id, Integer status, Long departId) {
        questionMapper.updateQuestionStatus (id,status,departId);
    }


    /***
     * 根据ID删除
     *
     * @param id
     */
    @Override
    public void deleteById(Long id) {
        this.questionMapper.deleteById (id);
    }


    /**
     * @param id
     * @param departId
     * @return
     */
    @Override
    public Integer queryByDepartId(@Param("id") Long id, @Param("departId") Long departId) {
        return this.questionMapper.countByDepartId (id,departId);
    }

    /***
     * @param id
     * @return
     */
    @Override
    public DtQuestion findById(Long id) {
      List<DtQuestion> list=this.questionMapper.findById (id);
      if(!CollectionUtils.isEmpty (list)){
          return list.get (0);
      }
      return null;
    }
}
