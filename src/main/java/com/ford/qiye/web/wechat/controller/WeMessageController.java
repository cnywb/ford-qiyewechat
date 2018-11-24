package com.ford.qiye.web.wechat.controller;

import com.ford.qiye.web.wechat.controller.vo.ValidateVo;
import com.fute.wechat.service.message.MessageService;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by wanglijun on 16/8/17.
 */
@Controller
@RequestMapping("/wechat/message")
public class WeMessageController {
    /***
     * 日志
     */
    private static final Logger logger= LoggerFactory.getLogger (WeMessageController.class);

    @Resource
    private MessageService messageService;


    @RequestMapping(value = "/send")
    public void sendQyMessage(String json,HttpServletResponse response){
        String  result=messageService.sendQyMessage(json);
        this.writer (response,result);
    }

    /***
     *  GET请求证
     */
    @RequestMapping(value = "/validate",method = RequestMethod.GET)
    public void validateGet(ValidateVo vo,HttpServletResponse response){
        logger.info("get,vo:{}",vo.toString ());
        String result=messageService.checkQySignature(vo.getMsg_signature (),vo.getTimestamp (),vo.getNonce (),vo.getEchostr ());
        logger.info ("result:{}",result);
        this.writer (response,result);
    }

    /**
     * POST请求
     * */
    @RequestMapping(value = "/validate",method = RequestMethod.POST)
    public void validatePost(ValidateVo vo, HttpServletRequest request, HttpServletResponse response){
        logger.info("post,vo:{}",vo.toString ());
        String result=messageService.idoProcessQyRequest(vo.getMsg_signature (),vo.getTimestamp (),vo.getNonce (),request);
        this.writer (response,result);
    }

    /***
     * 输出结果
     * @param response
     * @param result
     */
    private void writer(HttpServletResponse response,String result){
        response.setCharacterEncoding ("UTF-8");
        response.setContentType ("text/html;charset=UTF-8");
        PrintWriter writer=null;
        try {
             writer=response.getWriter ();
             writer.print (result);
        } catch (IOException e) {
            logger.error (e.getMessage (),e);
        }finally{
            IOUtils.closeQuietly (writer);
        }
    }
}
