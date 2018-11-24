package com.ford.qiye.web.wechat.controller;

import com.ford.qiye.model.*;
import com.ford.qiye.service.*;
import com.ford.qiye.web.controller.AbstractController;
import com.ford.qiye.web.wechat.controller.vo.QuestionIssueVo;
import com.fute.wechat.model.auth.QyAuthUserInfo;
import com.fute.wechat.service.qy.txl.WechatQyUserService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.text.MessageFormat;
import java.util.List;

/**
 * Created by wanglijun on 16/8/18.
 */
@Controller
@RequestMapping("/we")
public class WeUserController extends AbstractController{
    /***
     * 日志
     */
    private static final Logger logger= LoggerFactory.getLogger (WeUserController.class);


    public static final String WE_URL="https://open.weixin.qq.com/connect/oauth2/authorize?appid={0}&redirect_uri={1}&response_type=code&scope=snsapi_base&state={2}#wechat_redirect";

    public static final String USER_ID="userId";

    public static final String USER="user";

    @Resource
    private WechatQyUserService wechatQyUserService;

    @Autowired
    private UserService userService;

    @Autowired
    private MaterialService materialService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private DepartmentService departmentService;


    @Autowired
    private ParameterService parameterService;

    /**物料主页*/
    private static  final String  UNOPEN_USER_RETURN="wechat/unopen";

    /**物料主页*/
    private static  final String  INDEX_RETURN="wechat/material";

    /**物料我的物料*/
    private static  final String  MY_MATERIAL_RETURN="wechat/mymaterial";

    /**问题列表*/
    private static  final String  QUESTION_RETURN="wechat/question";

    /**问题列表*/
    private static  final String  QUESTION_ISSUE_RETURN="wechat/questionissue";



    @RequestMapping("/questionsave")
    @ResponseBody
    public QuestionIssueVo questionSave(QuestionIssueVo vo,HttpServletRequest request, Model model){
        String userId=this.getUserId (request);
        //内空不能为空
        if(StringUtils.isEmpty (vo.getContent ())){
            vo.setStatus (QuestionIssueVo.FAILURE);
            vo.setMessage ("内容不能为空!");
            return vo;
        }
        //用户是否空
        DtUser dtUser=this.getUser (request);
        logger.info ("dtUser:{}",dtUser.toString ());
        if(dtUser==null){
            vo.setStatus (QuestionIssueVo.FAILURE);
            vo.setMessage ("用户未注册");
            return vo;
        }
        //用户是否能提问题
        if(!"1".equals (dtUser.getIsAgency ())){
            vo.setStatus (QuestionIssueVo.FAILURE);
            vo.setMessage ("内容员工不能提问题");
            return vo;
        }
        //同一个用户不能重复提问
        Long count=this.questionService.findByUserId(userId,vo.getContent ());
        if(count>0){
            vo.setStatus (QuestionIssueVo.FAILURE);
            vo.setMessage ("不能重新提交问题");
            return vo;
        }
        //保存问题
        DtQuestion question=new DtQuestion ();
        question.setDepartId (vo.getDepartId ());
        question.setContent (vo.getContent ());
        question.setUserId (userId);
        question.setStatus (DtQuestion.STATUS_UNANSWERED);
        this.questionService.insert (question);
        vo.setStatus (QuestionIssueVo.SUCCESS);
        vo.setMessage ("发布问题成功!");
        return vo;
    }

    @RequestMapping("/questionissue")
    public String questionIssue(HttpServletRequest request, Model model){
        String userId=this.getUserId (request);
        List<DtDepartment> departments=this.departmentService.queryDepartmentAll ();
        model.addAttribute ("departments",departments);
        return QUESTION_ISSUE_RETURN;
    }


    /**问题列表*/
    @RequestMapping("/question")
    public String question(HttpServletRequest request, Model model){
        String userId=this.getUserId (request);

        if(StringUtils.isNotEmpty (userId)) {
            List<DtQuestion> questions = this.questionService.findByUserId (userId);
            model.addAttribute ("questions", questions);
        }
        return QUESTION_RETURN;
    }


    //物料
    @RequestMapping("/mymaterial")
    public String myMaterial(String userId,HttpServletRequest request,Model model){
        userId=this.getUserId (request);
        List<DtMaterial>  materials=this.materialService.findByUserId (userId,0);
        model.addAttribute ("dataList",materials);
        return MY_MATERIAL_RETURN;
    }


    @RequestMapping("/questionAll")
    public String questionAll(HttpServletRequest request){
        return this.redirectWeChat (request,"456");
    }

    @RequestMapping("/userId")
    public String userId(HttpServletRequest request){
        return this.redirectWeChat (request,"123");
    }

    private String  redirectWeChat(HttpServletRequest request,String state){
        String url=StringUtils.EMPTY;
        String corpId= parameterService.getValue (DtParameter.WECHAT_QY_CORP_ID);
        try {
            url= URLEncoder.encode (this.getBasePath (request)+"/we/index.do","UTF-8");
            logger.info ("url:{}",this.getBasePath (request)+"/we/index.do");
            logger.info ("url:{}",url);

        } catch (UnsupportedEncodingException e) {
            logger.error (e.getMessage (),e);
        }

        logger.info("url:{},state:{}",corpId,state);

        String redirectUrl=MessageFormat.format (WE_URL,corpId,url,state);

       logger.info ("redirectUrl:{}",redirectUrl);
        return this.redirect (redirectUrl);
    }

    /***
     * 用户认证
     * @param code
     * @param state
     * @param request
     */
    @RequestMapping("/index")
    public String  material(String code, String state, HttpServletRequest request, Model model){
        logger.info ("code:{},state:{}",code,state);
        if(StringUtils.isEmpty (code)){
           return  this.redirectWeChat (request,state);
        }
        //查询
        if(StringUtils.isNotEmpty (code)){
            QyAuthUserInfo userInfo=wechatQyUserService.getAuthUserInfo (code);
             String wxId= userInfo.getUserId ();

             logger.info ("wxId:{}",wxId);
             DtUser user=userService.findById (wxId);
             if(user==null){
                 return UNOPEN_USER_RETURN;
             }
             model.addAttribute ("userId",user.getId ());
             this.put (user,request);
        }

        if(state.equals ("456")){
            return  this.redirect ("question.do");
        }
        return INDEX_RETURN;
    }

    /***
     * 将SessionId保存到会话中
     * @param user
     * @param request
     */
    private void put(DtUser user,HttpServletRequest request){
        HttpSession  session=request.getSession ();
        session.setAttribute (USER,user);
        session.setAttribute (USER_ID,user.getId ());
    }

    /***
     * 从Session获取userId
     * @param request
     * @return
     */
    private String getUserId(HttpServletRequest request){
        HttpSession  session=request.getSession (false);
        return (String) session.getAttribute (USER_ID);
    }

    /***
     *
     * @param request
     * @return
     */
    private DtUser getUser(HttpServletRequest request){
        HttpSession  session=request.getSession (false);
        return (DtUser) session.getAttribute (USER);
    }

}
