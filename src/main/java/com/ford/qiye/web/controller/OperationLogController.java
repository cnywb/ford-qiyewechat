package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.OperationLogService;
import com.ford.qiye.web.controller.model.OperationLogVo;
import com.ford.qiye.web.util.ExcelUtils;
import com.fute.common.util.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by wanglijun on 16/12/3.
 */
@Controller
@RequestMapping("/log")
public class OperationLogController {
    /**首页*/
    private static final String INDEX_RETURN="log/index";


    @Autowired
    private OperationLogService service;


    @RequestMapping(value = "/index")
    public String index(OperationLogVo vo, Model model){

        PageGrid pageGrid=vo;

        OperationType operationType=null;

        if(StringUtils.isNotEmpty (vo.getOperationTypeName ())) {
            operationType=OperationType.valueOf (vo.getOperationTypeName ());
        }

        Date startDate= DateUtils.parser (vo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (vo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<OperationLog> list=service.findByPage (pageGrid,vo.getUserName (),operationType,startDate,endDate);

        model.addAttribute ("page",list);

        model.addAttribute ("vo",vo);

        model.addAttribute ("types", OperationType.map ());

        return INDEX_RETURN;
    }

    @RequestMapping("/export")
    public void export(OperationLogVo vo,HttpServletResponse response){

        PageGrid  pageGrid=vo;

        pageGrid.setPageSize (20000);

        OperationType operationType=null;

        if(StringUtils.isNotEmpty (vo.getOperationTypeName ())) {
            operationType=OperationType.valueOf (vo.getOperationTypeName ());
        }

        Date startDate= DateUtils.parser (vo.getStartDate ()+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Date endDate= DateUtils.parser (vo.getEndDate ()+" 23:59:59",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS);

        Pagination<OperationLog> list=service.findByPage (pageGrid,vo.getUserName (),operationType,startDate,endDate);

        ExcelUtils.writerLog(list,response);
    }
}
