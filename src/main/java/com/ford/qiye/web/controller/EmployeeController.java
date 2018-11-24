package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.AgencyType;
import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.model.*;
import com.ford.qiye.service.*;
import com.ford.qiye.web.controller.model.EmployeeVo;
import com.ford.qiye.web.controller.model.UserMarkVo;
import com.ford.qiye.web.controller.model.UserRoleVo;
import com.ford.qiye.web.controller.model.UserVo;
import com.ford.qiye.web.util.WeChatMemberUtils;
import com.fute.common.util.DateUtils;
import com.fute.common.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Controller
@RequestMapping("/employee")
public class EmployeeController extends AbstractController{
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (EmployeeController.class);

    /**首页*/
    private static final String INDEX_RETURN="employee/index";

    @Autowired
    private DepartmentService departmentService;

        @Autowired
        private MarkService markService;

        @Autowired
        private RoleService roleService;


        @Autowired
        private UserService userService;


        @Autowired
        private AccessTokenService accessTokenService;



        @RequestMapping("/index")
        public String index(EmployeeVo vo, Model model){


            model.addAttribute ("vo",vo);

        //查询我的部门
        List<DtDepartment> departments=this.departmentService.queryDepartmentAll ();
        model.addAttribute ("departments",departments);

        //标签列表
        List<DtMark> marks=markService.queryAll ();
        model.addAttribute ("marks",marks);


        //角色列表信息
        List<DtRole> roles=roleService.queryAll ();
        model.addAttribute ("roles",roles);


        PageGrid pageGrid=vo;


        Pagination<DtUser> page= userService.queryPage (pageGrid,vo.getRealName (),vo.getUserName (),
                vo.getPosition (),vo.getEmail (),
                vo.getDepartId (), AgencyType.EMPLOYEE);

        model.addAttribute ("page",page);


        return INDEX_RETURN;
    }


    //更新删除
    @RequestMapping("/handler")
    public String handler(UserVo vo, Errors Errors){

        if(StringUtils.isEmpty (vo.getId ())){

            DtUser dtUser=new DtUser ();
            BeanUtils.copyProperties (vo,dtUser);
            dtUser.setIsAgency (AgencyType.EMPLOYEE);
            if(StringUtils.isNotEmpty (vo.getPassword ())) {
                String newPassword = Md5Util.getMD5 (vo.getPassword ());
                dtUser.setPassword (newPassword);
            }

            if(vo.getDepartId ()!=null) {
                DtDepartment department = departmentService.findById (vo.getDepartId ());
                if (department != null) {
                    dtUser.setDepartName (department.getDepartName ());
                }
            }
            dtUser.setStatus (1);
            //设置密码有效期
            dtUser.setCredentialExpiredDate (DateUtils.getNextDateByMonth (new Date (),3));
            userService.insertEmployee (dtUser);
            LogUtil.writer (OperationType.USER_CREATE,dtUser.getUserName ());

        }else{
            DtUser dtUser=userService.findById (vo.getId ());
            String password=dtUser.getPassword ();
            //日志
            LogUtil.writer (OperationType.USER_MODIFY,dtUser.getUserName (),vo.getUserName ());
            BeanUtils.copyProperties (vo,dtUser);
            dtUser.setIsAgency (AgencyType.EMPLOYEE);
            if(StringUtils.isNotEmpty (vo.getPassword ())){
                String newPassword=Md5Util.getMD5 (vo.getPassword ());
                if(newPassword.equals (password)){
                    dtUser.setPassword (password);
                }else{
                    dtUser.setPassword (newPassword);
                    //将密码设置为三个月更新
                    dtUser.setCredentialExpiredDate (DateUtils.getNextDateByMonth (new Date (),3));
                    LogUtil.writer (OperationType.USER_PASSWORD_RESET,dtUser.getUserName (),LogUtil.SENSITIVE_WORDS);
                }
            }
            if(vo.getDepartId ()!=null) {
                DtDepartment department = departmentService.findById (vo.getDepartId ());
                if (department != null) {
                    dtUser.setDepartName (department.getDepartName ());
                }
            }else{
                dtUser.setDepartId (null);
            }

            userService.updateDealer (dtUser);
        }
        return  this.redirect ("index.do");
    }


    //设置标签
    @RequestMapping("/mark")
    @ResponseBody
    public String mark(UserMarkVo vo){
        if(StringUtils.isNotEmpty (vo.getUserId ())){
            markService.insertUserMark (vo.getUserId (),vo.getMarkIds ());
        }
        return "1";
    }

    //设置角色
    @RequestMapping("/role")
    @ResponseBody
    public String role(UserRoleVo vo){
        if(StringUtils.isNotEmpty (vo.getUserId ())){
            roleService.insertUserRole (vo.getUserId (),vo.getRoleIds ());
            LogUtil.writer (OperationType.AUTH_ROLE_USER_ADD,vo.getUserId (),vo.getRoleIds ().toString ());
        }
        return "1";
    }

    //删除
    @RequestMapping("/delete")
    public String delete(String id){
        DtUser user=userService.findById (id);
        if(StringUtils.isNotEmpty (id)&&user!=null){
            userService.delete (id);
            //日志
            LogUtil.writer (OperationType.USER_REMOVE,user.getUserName ());
        }

        return  this.redirect ("index.do");
    }


    @RequestMapping("/openId")
    @ResponseBody
    public String openId(){
       return WeChatMemberUtils.convertToOpenId ("scwanglijun1",accessTokenService.getAccessToken ());
    }

    @RequestMapping("/export")
    public void exportToExcel(HttpServletResponse response, String realName,String userName,String position,String email,String departId ){
        try {
            response.setHeader("Content-Disposition","attachment;filename=employees.xls");
            response.setContentType("application/vnd.ms-excel");
            Long departIdLong=null;
            if(departId!=null&&!"".equals(departId)){
                departIdLong=Long.parseLong(departId);
            }
            userService.createHSSFWorkbook( realName, userName, position, email, departIdLong).write(response.getOutputStream());
            response.getOutputStream().flush();
            response.getOutputStream().close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
