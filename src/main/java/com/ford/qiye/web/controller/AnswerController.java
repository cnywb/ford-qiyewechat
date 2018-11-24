package com.ford.qiye.web.controller;

import com.ford.qiye.model.DtAnswer;
import com.ford.qiye.service.AnswerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * Created by wanglijun on 16/8/8.
 */
@Controller
@RequestMapping("/answer")
public class AnswerController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (AnswerController.class);


    private static final  String  DETAIL_RETURN="answer/detail";


    @Autowired
    private AnswerService answerService;
    /****
     *
     * @param questionId
     * @param model
     * @return
     */

    @RequestMapping("/detail")
    public String  detail(Long questionId, Model model){
        List<DtAnswer> answers=answerService.queryByQuestionId (questionId);
        model.addAttribute ("answers",answers);
        return DETAIL_RETURN;
    }
}
