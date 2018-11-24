package com.ford.qiye.web.controller;

import com.ford.qiye.model.DtDepartment;
import com.ford.qiye.service.AccessTokenService;
import com.ford.qiye.service.DepartmentService;
import com.ford.qiye.web.controller.model.DepartmentVo;
import com.ford.qiye.web.util.WeChatDepartmentUtils;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglijun on 16/8/11.
 */
@Controller
@RequestMapping("/department")
public class DepartmentController  extends  AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (EmployeeController.class);

    /**首页*/
    private static final String ORG_TREE_RETURN="department/orgtree";

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AccessTokenService accessTokenService;


    @RequestMapping("/orgtree")
    public String orgTree(Model model){
        return ORG_TREE_RETURN;
    }


    @RequestMapping("/tree")
    @ResponseBody
    public  List<DtDepartment>   tree(){
        //查询我的部门
        List<DtDepartment> departments=this.departmentService.queryDepartmentAll ();

        return departments;
    }

    @RequestMapping("/handler")
    @ResponseBody
    public String  handler(DepartmentVo vo){
        logger.info (vo.toString ());
        if(vo.getId ()==null){
            //检查部门名称是否已存在
            if(this.departmentService.findByName (vo.getDepartName (),null)>0){
                return "-1";
            }
            DtDepartment department=new DtDepartment ();
            BeanUtils.copyProperties (vo,department);
            department.setIfActive (1);
            Long id=this.departmentService.insert (department);
            return String.valueOf (id);
        }else{
            //检查部门名称是否已存在
            if(this.departmentService.findByName (vo.getDepartName (),vo.getId ())>0){
                return "-1";
            }
            DtDepartment department=this.departmentService.findById (vo.getId ());
            BeanUtils.copyProperties (vo,department);
            this.departmentService.update (department);

        }
        return "1";
    }

    @ResponseBody
    @RequestMapping("/create")
    public String createDepartment(){
        List<DtDepartment> departments=this.departmentService.queryDepartmentAll ();

        List<String> results=new ArrayList<> ();

        Integer index=1;

        for(DtDepartment department:departments){
            JSONObject result=WeChatDepartmentUtils.insertDepart (department.getDepartName (),"1",String.valueOf (index),String.valueOf (department.getId ()),accessTokenService.getAccessToken ());
            results.add (result.toString ());
            index++;
        }
        return results.toString ();
    }



    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id){
         if(id!=null){
             this.departmentService.delete (id);
             return "1";
         }
        return "0";
    }
}
