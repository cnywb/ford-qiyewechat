package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtTask;
import com.ford.qiye.service.TaskService;
import com.ford.qiye.web.controller.model.TaskVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * TASK任务列表
 * Created by wanglijun on 16/9/23.
 */
@Controller
@RequestMapping("/task")
public class TaskController {


    /**首页*/
    private static final String INDEX_RETURN="task/index";
    /**首页*/
    private static final String INDEX_RETURN_DO="/task/index.do";


    @Autowired
    private TaskService taskService;


    @RequestMapping(value ="/index.do")
    public String index(TaskVo vo, Model model){

        PageGrid pageGrid=vo;

        Pagination<DtTask> list=taskService.queryPage (pageGrid,vo.getStatus (),vo.getDataType (),vo.getBatchNo ());

        model.addAttribute ("page",list);

        return INDEX_RETURN;
    }

}
