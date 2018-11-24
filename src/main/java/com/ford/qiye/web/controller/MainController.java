package com.ford.qiye.web.controller;

import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.model.DtFunction;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.FunctionService;
import com.ford.qiye.service.UserService;
import com.ford.qiye.web.controller.model.PasswordVo;
import com.fute.common.util.DateUtils;
import com.fute.common.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/5.
 */
@Controller
@RequestMapping("/main")
public class MainController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (MainController.class);

    /**首页*/
    private static final String INDEX_RETURN="main";
    /**
     * */
    private static final String PASSWORD_RETURN="user/password";

    @Autowired
    private FunctionService functionService;

    @Autowired
    private UserService  userService;


    @RequestMapping("/index")
    public ModelAndView index(Model model){
        logger.info ("主页:");
        List<DtFunction> functions=functionService.queryMenuByUserId (super.getUserId ());
        model.addAttribute ("menus",functions);
        ModelAndView mv = new ModelAndView(INDEX_RETURN);
        mv.addObject ("menus",functions);
        logger.info ("主页:{}",functions.size ());
        return mv;
    }


    @RequestMapping(value = "/password",method = RequestMethod.GET)
    public String password(Model model){
        model.addAttribute ("user",this.getUser ());
        return PASSWORD_RETURN;
    }

    @RequestMapping(value = "/password",method = RequestMethod.POST)
    public String password(@Valid PasswordVo vo, Errors errors, ModelAndView  modelAndView,Model model){
        //提示跳转到登录页面;
        model.addAttribute ("user",this.getUser ());
        //是否有错误
        if(errors.hasErrors ()){
            return PASSWORD_RETURN;
        }

        //判断两次密码是否相等
        if(!vo.getPassword ().equals (vo.getConfirmPassword ())){
            errors.rejectValue ("password","","输入的两次不相同,请重新输入");
            return PASSWORD_RETURN;
        }
        //密码加密
        String password= Md5Util.getMD5 (vo.getPassword ());
        //更新密码
        Date credentialExpiredDate  =DateUtils.getNextDateByMonth(new Date (), 3);
        userService.updatePassword (this.getUserId (),password,credentialExpiredDate);
        //添加日志
        LogUtil.writer (OperationType.USER_PASSWORD_MODIFY,this.getUser ().getUserName (),LogUtil.SENSITIVE_WORDS);

        model.addAttribute ("login","1");
        return PASSWORD_RETURN;
    }
}
