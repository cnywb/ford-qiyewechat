package com.ford.qiye.web.controller;

import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.lisenter.LoginUtils;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.LoginService;
import com.ford.qiye.web.controller.model.LoginVo;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 *
 * Created by wanglijun on 16/8/5.
 */
@Controller
@RequestMapping("/")
public class LoginController extends  AbstractController{
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (LoginController.class);

    /**用户登录页面x*/
    private static final String LOGIN="login";
    /**用户登录页面x*/
    private static final String  MAIN_INDEX="main/index.do";

    @Autowired
    private LoginService loginService;


    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public String  login(LoginVo loginVo){
        //判断用户是否已经登录
        //TODO
        return LOGIN;
    }

    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String  login(@Valid LoginVo loginVo, Errors errors,HttpServletRequest request){
        //判断是否已经登录;
        HttpSession session = request.getSession(true);
        //验证失败则跳转到登录页面;
        if(errors.hasErrors()) {
            return  LOGIN;
        }
        //判断是否判断
        if(StringUtils.isEmpty (loginVo.getUserName ())||StringUtils.isEmpty (loginVo.getPassword ())){
            errors.reject ("","用户名和密码不能为空");
            return LOGIN;
        }

        UsernamePasswordToken token = new UsernamePasswordToken(loginVo.getUserName(), loginVo.getPassword());
        //获取用户（不一定是登录的用户）
        Subject currentUser = SecurityUtils.getSubject();
        try{
            //校验当前用户是否登录成功，如果登录不成功该方法会抛出一些指定的异常
            //这里会调用AuthorizingRealm类当中的doGetAuthenticationInfo(AuthenticationToken token)方法
            currentUser.login(token);
            //获取用户额外的属性,便于页面上获取

        }catch (AuthenticationException e){
            logger.error (e.getMessage (),e);
            errors.reject ("","用户名和密码不能为空");

        }
        //判断是否有错误消息;
        if(errors.hasErrors()) {
            return  LOGIN;
        }

        //登录成功页面
        logger.info ("loginVo:{}登录成功!",loginVo.getUserName ());

        return this.redirect (MAIN_INDEX);
    }

   @RequestMapping(value = "/logout.do",method = RequestMethod.GET)
    public String logout(LoginVo loginVo,HttpServletRequest request){
        //获取用户（不一定是登录的用户）
        Subject currentUser = SecurityUtils.getSubject();
        //写入日志
        LogUtil.writer (OperationType.USER_LOGOUT, LoginUtils.getUserName ());
        currentUser.logout();
        return LOGIN;
    }
}
