package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.UserAuthList;
import com.ford.qiye.service.UserService;
import com.ford.qiye.web.controller.model.UserAuthListVo;
import com.ford.qiye.web.util.ExcelUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;

/**
 * 用户权限列表
 * Created by wanglijun on 16/12/4.
 */
@Controller
@RequestMapping("/auth")
public class UserAuthListController {

    /**首页*/
    private static final String INDEX_RETURN="auth/index";


    @Autowired
    private UserService service;


    @RequestMapping(value = "/index")
    public String index(UserAuthListVo vo, Model model){

        PageGrid pageGrid=vo;


        Pagination<UserAuthList> list=service.findUserAuthPage (pageGrid,vo.getUserName (),vo.getRoleName (),vo.getFunctionName ());

        model.addAttribute ("page",list);
        model.addAttribute ("vo",vo);

        return INDEX_RETURN;
    }



    @RequestMapping("/export")
    public void export(UserAuthListVo vo, HttpServletResponse response){

        PageGrid  pageGrid=vo;

        pageGrid.setPageSize (20000);

        Pagination<UserAuthList> page= service.findUserAuthPage (pageGrid,vo.getUserName (),vo.getRoleName (),vo.getFunctionName ());

        ExcelUtils.writerUserAuthList(page,response);
    }
}
