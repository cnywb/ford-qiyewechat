package com.ford.qiye.web.controller;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.AgencyType;
import com.ford.qiye.lisenter.LogUtil;
import com.ford.qiye.model.AreaTree;
import com.ford.qiye.model.DtMark;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.model.OperationType;
import com.ford.qiye.service.AreaService;
import com.ford.qiye.service.MarkService;
import com.ford.qiye.service.UserService;
import com.ford.qiye.web.controller.model.DealerVo;
import com.ford.qiye.web.controller.model.UserMarkVo;
import com.ford.qiye.web.controller.model.UserVo;
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

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Controller
@RequestMapping("/dealer")
public class DealerController extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (DealerController.class);


    /**首页*/
    private static final String INDEX_RETURN="dealer/index";

    @Autowired
    private AreaService areaService;

    @Autowired
    private MarkService markService;

    @Autowired
    private UserService userService;

    @RequestMapping("/index")
    public String index(DealerVo vo,Model model){

        model.addAttribute ("vo",vo);
        //查询大区区域信息
        List<AreaTree>  areas=areaService.queryByParentId (0L);
        model.addAttribute ("areas",areas);

        //标签列表
        List<DtMark> marks=markService.queryAll ();
        model.addAttribute ("marks",marks);

        PageGrid pageGrid=vo;

        Pagination<DtUser> page= userService.queryPage (pageGrid,vo.getRealName (),vo.getUserName (),
                vo.getPosition (),vo.getEmail (),vo.getPhone (),
                vo.getDepartId (), AgencyType.DEALER,
                vo.getServeCode (),vo.getSellCode (),DtUser.ADMIN_ID);

        model.addAttribute ("page",page);

        return INDEX_RETURN;
    }

    //更新删除
    @RequestMapping("/handler")
    public String handler(UserVo userVo, Errors Errors){

        if(StringUtils.isEmpty (userVo.getId ())){
            DtUser dtUser=new DtUser ();

            BeanUtils.copyProperties (userVo,dtUser);
            dtUser.setIsAgency (AgencyType.DEALER);
            dtUser.setStatus (1);
            userService.insertDealer (dtUser);
        }else{
            DtUser dtUser=userService.findById (userVo.getId ());
            BeanUtils.copyProperties (userVo,dtUser);
            dtUser.setIsAgency (AgencyType.DEALER);
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


    //删除
    @RequestMapping("/delete")
    public String delete(String id){
        if(StringUtils.isNotEmpty (id)){

            DtUser user=userService.findById(id);
            if (user!=null){
                LogUtil.writer (OperationType.USER_REMOVE,user.getUserName());
                userService.delete (id);
            }
        }
        return  this.redirect ("index.do");
    }
}
