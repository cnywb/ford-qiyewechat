package com.ford.qiye.web.controller;

import com.ford.qiye.model.DtParameter;
import com.ford.qiye.service.ParameterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by wanglijun on 16/8/6.
 */
@Controller
@RequestMapping("/parameter")
public class ParameterController  extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (ParameterController.class);

    private static final String WELCOME_RETURN="parameter/welcome";

    private static final String WELCOME_RETURN_DO="parameter/welcome.do";

    /**操作成功*/
    public static final String SUCCESS="1";
    /**操作失败*/
    public static final String FAIL="0";


    @Autowired
    private ParameterService parameterService;

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    public String welcome(Model model){
        String welcomeWord=parameterService.getValue (DtParameter.KEY_WELCOME_WORD);
        model.addAttribute ("welcome",welcomeWord);
        return WELCOME_RETURN;
    }

    @RequestMapping(value = "/welcome",method = RequestMethod.POST)
    @ResponseBody
    public String welcome(String welcome,Model model){
        this.parameterService.updateValue (DtParameter.KEY_WELCOME_WORD,welcome);
        return SUCCESS;
    }
}
