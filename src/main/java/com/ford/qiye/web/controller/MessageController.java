package com.ford.qiye.web.controller;

import com.ford.qiye.common.MessageTemplate;
import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.*;
import com.ford.qiye.service.*;
import com.ford.qiye.web.controller.model.MessageHandlerVo;
import com.ford.qiye.web.controller.model.MessageVo;
import com.fute.wechat.service.message.MessageService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
@Controller
@RequestMapping("/message")
public class MessageController  extends AbstractController{

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (MessageController.class);

    /**首页*/
    private static final String INDEX_RETURN="message/index";

    @Autowired
    private AreaService areaService;

    @Autowired
    private MarkService markService;


    @Autowired
    private  BiMessageService biMessageService;


    @Autowired
    private MessageService messageService;

    @Autowired
    private UserService userService;


    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private AppService appService;


    @RequestMapping("/index")
    public String index(MessageVo vo, Model model){
        model.addAttribute ("vo",vo);
        //查询大区区域信息
        List<AreaTree> areas=areaService.queryByParentId (0L);
        model.addAttribute ("areas",areas);

        //标签列表
        List<DtMark> marks=markService.queryAll ();
        model.addAttribute ("marks",marks);

        //部门信息
        List<DtDepartment> departments=this.departmentService.queryDepartmentAll ();
        model.addAttribute ("departments",departments);
        //查询所有员工记录信息
        //List<DtUser> users=userService.queryPage ();
        //model.addAttribute ("users",users);

        PageGrid pageGrid=vo;

        Pagination<DtMessage> page=biMessageService.queryPage (pageGrid,vo.getRealName (),vo.getStatus ());
        model.addAttribute ("page",page);

        return INDEX_RETURN;
    }


    @RequestMapping("/delete")
    @ResponseBody
    public String delete(Long id, Model model) {

        if(id!=null){
            this.biMessageService.delete(id);
            return "1";
        }
        return "0";
    }


    @RequestMapping("/send")
    @ResponseBody
    public String send(MessageHandlerVo vo){

        //内容类型:1 文本 为空返回0
        if(DtMessage.CONTENT_TYPE_TEXT.equals (vo.getContentType ())&& StringUtils.isEmpty (vo.getContent ())){
            return "0";
        }
        //内容类型:2 图片 为空返回0
        if(DtMessage.CONTENT_TYPE_IMAGE.equals (vo.getContentType ())&& StringUtils.isEmpty (vo.getImageSrc ())){
            return "0";
        }


        //发送给单个用户
        if(StringUtils.isNotEmpty (vo.getUserId ())){
            this.sendToUser(vo,vo.getUserId ());
            return "1";
        }

       // DtApp app=this.appService.findByDepartId (this.departId ());



        //发送给部门
        if(StringUtils.isEmpty (vo.getAreaName ())&& CollectionUtils.isEmpty (vo.getSmallNames ())){
            List<DtUser> dtUsers=this.userService.findUserByDepartId (vo.getDepartId (),vo.getPosition (),null,null,vo.getServeCode (),vo.getSellCode (),vo.getMarkId ());
            this.sendBatchMessage(vo,dtUsers);
            return "1";
        }
        //发送区域
        if(StringUtils.isNotEmpty (vo.getAreaName ())){

            //发送给大区
            if(CollectionUtils.isEmpty (vo.getSmallNames ())){
                List<DtUser>   dtUsers=this.userService.findUserByDepartId (vo.getDepartId (),vo.getPosition (),vo.getAreaName (),null,vo.getServeCode (),vo.getSellCode (),vo.getMarkId ());
                this.sendBatchMessage(vo,dtUsers);
            }
            //发送给大小区
            if(!CollectionUtils.isEmpty (vo.getSmallNames ())){
                for(String smallName:vo.getSmallNames ()) {
                    List<DtUser> dtUsers = this.userService.findUserByDepartId (vo.getDepartId (), vo.getPosition (), vo.getAreaName (), smallName, vo.getServeCode (), vo.getSellCode (), vo.getMarkId ());
                    this.sendBatchMessage (vo, dtUsers);
                }
            }
            return "1";
        }

        return "0";
    }

    private void sendBatchMessage(MessageHandlerVo vo, List<DtUser> dtUsers) {
        for(DtUser user:dtUsers){
            this.sendToUser (vo,user.getId ());
        }
    }


    private void sendToUser(MessageHandlerVo vo,String userId) {

        DtMessage message=new DtMessage ();
        //发送文本
        if(DtMessage.CONTENT_TYPE_TEXT.equals (vo.getContentType ())){
            this.sendContent (userId,vo.getContent ());
            message.setContentType (DtMessage.CONTENT_TYPE_TEXT);
            message.setContent (vo.getContent ());
            message.setStatus (1);
            message.setUserId (userId);
            message.setImageId (1L);
        }
        //发送图片
        if(DtMessage.CONTENT_TYPE_IMAGE.equals (vo.getContentType ())){
            String content= MessageTemplate.format (MessageTemplate.MSG_IMAGE,vo.getImageSrc ());
            this.sendContent (userId,content);
            message.setContentType (DtMessage.CONTENT_TYPE_IMAGE);
            message.setContent (content);
            message.setImgContent (vo.getImageSrc ());
            message.setStatus (1);
            message.setUserId (userId);
            message.setImageId (1L);
        }
        //保存发送消息
        this.biMessageService.insert(message);
    }

    private void sendContent(String userId,String content){
        try{
            messageService.sendQyMessage("text",userId, "0", content);
        }catch (Exception e){
            logger.error (e.getMessage (),e);
        }
    }

}
