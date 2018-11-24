package com.ford.qiye.web.controller;

import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.model.DtRole;
import com.ford.qiye.model.DtRoleFunction;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.FunctionService;
import com.ford.qiye.service.RoleFunctionService;
import com.ford.qiye.service.RoleService;
import com.ford.qiye.web.controller.model.AuthorizationVo;
import com.ford.qiye.web.controller.model.RoleVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wanglijun on 16/8/6.
 */
@Controller
@RequestMapping("/role")
public class RoleController  extends AbstractController{



    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (RoleController.class);

    /**首页*/
    private static final String INDEX_RETURN="role/index";


    /**首页*/
    private static final String INDEX_RETURN_DO="index.do";

    @Autowired
    private RoleService roleService;

    @Autowired
    private FunctionService functionService;

    @Autowired
    private RoleFunctionService roleFunctionService;


    @RequestMapping(value = "/index",method = RequestMethod.GET)
    public String index(Model model){
        //角色功能列表
        model.addAttribute ("roles",roleService.queryAll ());

        //权限列表
        model.addAttribute ("functions",functionService.queryAll ());
        return INDEX_RETURN;
    }


    @RequestMapping("/edit")
    public String edit(RoleVo roleVo,Model model){
        if(roleVo.getId ()!=null){
            //查询再更新;
            DtRole role=this.roleService.findById (roleVo.getId ());
            //添加日志
            LogUtil.writer (OperationType.ROLE_MODIFY,role.getName (),roleVo.getName (),String.valueOf (role.getStatus ()),String.valueOf (role.getStatus ()));
            role.setName (roleVo.getName ());
            role.setStatus (roleVo.getStatus ());
            this.roleService.update (role);
        }

        return this.redirect (INDEX_RETURN_DO);
    }


    @RequestMapping("/user")
    @ResponseBody
    public List<DtRole> user(String userId){
        return this.roleService.queryByUserId (userId);
    }


    @RequestMapping("/authorization")
    public String authorization(AuthorizationVo vo, Model model){
        if(vo.getId ()!=null){
            List<String> params=new ArrayList<> ();
            List<DtRoleFunction> roleFunctions=new ArrayList<> ();
            for(Long functionId:vo.getIds ()){
                    if(functionId!=null) {
                        DtRoleFunction roleFunction = new DtRoleFunction (vo.getId (), functionId);
                        roleFunctions.add (roleFunction);
                        params.add (String.valueOf (roleFunction.getFunctionId ()));
                    }
            }
            //
            LogUtil.writer (OperationType.AUTH_ROLE_RESOURCE,String.valueOf (vo.getId ()),params.toString ());
            //更新权限
            roleFunctionService.insert (vo.getId (),roleFunctions);
        }
        return this.redirect (INDEX_RETURN_DO);
    }

}
