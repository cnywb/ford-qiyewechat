package com.ford.qiye.web.controller;

import com.ford.qiye.common.MessageTemplate;
import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.AgencyType;
import com.ford.qiye.model.DtAnswer;
import com.ford.qiye.model.DtDepartment;
import com.ford.qiye.model.DtQuestion;
import com.ford.qiye.model.DtRole;
import com.ford.qiye.service.AnswerService;
import com.ford.qiye.service.DepartmentService;
import com.ford.qiye.service.QuestionService;
import com.ford.qiye.service.RoleService;
import com.ford.qiye.web.controller.model.QuestionVo;
import com.ford.qiye.web.controller.model.ShareVo;
import com.ford.qiye.web.util.ExcelUtils;
import com.fute.backer.model.Question;
import com.fute.common.util.DateUtils;
import com.fute.wechat.service.message.MessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglijun on 16/8/7.
 */
@Controller
@RequestMapping("/question")
public class  QuestionController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (MainController.class);


    /**首页*/
    private  static final String QUESTION_INDEX="question/index";

    /**操作成功*/
    public static final String SUCCESS="1";
    /**操作失败*/
    public static final String FAIL="0";


    @Autowired
    private QuestionService questionService;


    @Autowired
    private AnswerService answerService;

    @Autowired
    private MessageService messageService;


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private RoleService roleService;


    @RequestMapping("/index")
    public String index(QuestionVo questionVo, Model model){

        PageGrid  pageGrid=questionVo;

        Date startDate= DateUtils.parser (questionVo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (questionVo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        if(super.isAdmin (this.getUserRoles ())) {
            logger.info ("w-----------------");
            Pagination<DtQuestion> page = questionService.findByPage (questionVo, null,null, questionVo.getContent (), startDate, endDate, questionVo.getStatus (), questionVo.getAreaName (), questionVo.getSmallName (), AgencyType.DEALER);

            model.addAttribute ("page", page);

            List<DtDepartment> dictDataList = departmentService.queryDepartmentAll ();

            model.addAttribute ("dictDataList", dictDataList);

            //判断是否是管理员
            model.addAttribute ("isAdmin", super.isAdmin (this.getUserRoles ()));
        }else{
            Pagination<DtQuestion> page = questionService.findByPage (questionVo, null,super.departId (), questionVo.getContent (), startDate, endDate, questionVo.getStatus (), questionVo.getAreaName (), questionVo.getSmallName (),AgencyType.DEALER);

            model.addAttribute ("page", page);

            List<DtDepartment> dictDataList = departmentService.queryDepartmentAll ();

            model.addAttribute ("dictDataList", dictDataList);

            //判断是否是管理员
            model.addAttribute ("isAdmin", super.isAdmin (this.getUserRoles ()));
        }

        return QUESTION_INDEX;
    }


    /***
     * 根据用户Id获取用户角色
     * @return
     */
    private Map<String,String> getUserRoles(){
        List<DtRole> roles=this.roleService.queryByUserId (this.getUserId ());
        Map<String,String> params=new HashMap<> ();
        for(DtRole role:roles){
            params.put (role.getName (),role.getName ());
        }
        return params;
    }

    /**回复*/
    @RequestMapping("/answer")
    public String answer(QuestionVo vo, Model model, HttpServletRequest request){

        String context= MessageTemplate.format (MessageTemplate.MSG_QUESTION,this.getUser ().getRealName (),this.getBasePath (request));

        DtAnswer  answer=new DtAnswer (vo.getId (),this.getUserId (),vo.getAnswerContent ());

        if(QuestionVo.CONTENT_TYPE_TEXT.equals (vo.getContentType ())){
            //保存Answer
            answerService.saveAnswer (answer);
        }else {

            if (StringUtils.isNotEmpty (vo.getImageSrc ())) {
                String content = "<img src=\"" + this.getBasePath (request) + vo.getImageSrc () + "\" />";
                answer.setAnswerContent (vo.getAnswerContent ());
                answerService.saveAnswer (answer);
            }
        }
        //更新问题状态
        questionService.updateQuestionStatus (vo.getId (),Question.STATUS_ANSWERED);
        DtQuestion question=questionService.findById (vo.getId ());
        //发布消息
        try{
             messageService.sendQyMessage("text",question.getUserId (), "0", context);}
        catch(Exception e){
            logger.error (e.getMessage (),e);
        }

        //部门迁移
        if(vo.getDepartId ()!=null){
            questionService.updateQuestionStatus (vo.getId (), vo.getStatus (), vo.getDepartId ());
        }
        return super.redirect("/question/index.do");
    }

    /**转移部门*/
    @RequestMapping("/transform")
    public String transform(QuestionVo questionVo,Model model){
        if(questionVo.getDepartId ()!=null){
            questionService.updateQuestionStatus (questionVo.getId (),questionVo.getStatus (),questionVo.getDepartId ());
        }
        return super.redirect("/question/index.do");
    }


    /**删除*/
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long questionId){
        questionService.deleteById (questionId);
        return SUCCESS;
    }


    /**分享*/
    @RequestMapping("/share")
    @ResponseBody
    public String share(ShareVo shareVo){

        DtQuestion question=questionService.findById (shareVo.getQuestionId ());
        if(question==null){
            return FAIL;
        }
        for(Long departId:shareVo.getDepartIds ()){

            if(this.questionService.queryByDepartId (shareVo.getQuestionId (),departId)==0){
                    question.setId (null);
                    question.setDepartId (departId);
                    //保存question
                    Long newQuestionId= this.questionService.insert (question);
                    List<DtAnswer> answers=answerService.queryByQuestionId (shareVo.getQuestionId ());
                    for(DtAnswer answer:answers){
                        answer.setId (null);
                        answer.setQuestionId (newQuestionId);
                        answerService.saveAnswer (answer);
                    }
            }
        }

        return SUCCESS;
    }


    @RequestMapping("/export")
    public void export(QuestionVo questionVo, HttpServletResponse response){
        PageGrid  pageGrid=questionVo;
        pageGrid.setPageSize (20000);

        Date startDate= DateUtils.parser (questionVo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (questionVo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<DtQuestion> page= questionService.findByPage (questionVo,this.getUserId (),super.departId (),questionVo.getContent (),startDate,endDate,questionVo.getStatus (),questionVo.getAreaName (),questionVo.getSmallName (),AgencyType.DEALER);

        ExcelUtils.writer(page,response);
    }

    /**关闭*/
    @RequestMapping("/close")
    @ResponseBody
    public String close(Long questionId){
        //将状态设置为关闭
        questionService.updateQuestionStatus (questionId,Question.STATUS_CLOSED);
        return SUCCESS;
    }


}
