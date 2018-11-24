package com.fute.wechat.action;

import com.fute.wechat.service.message.MessageService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/wechat/message")
public class MessageAction {
	
	@Resource
	private MessageService messageService;

	private String json;
	
	private String  msg_signature;

	private String timestamp;

	private String nonce;


	private String echostr;
	
    HttpServletResponse response = ServletActionContext.getResponse();

	HttpServletRequest request=ServletActionContext.getRequest();
	
	public String getJson() {
		return json;
	}

	public void setJson(String json) {
		this.json = json;
	}

	@Action(value = "sendQyMessage")
	public void sendQyMessage(){
	    try {
	    	
	    	String responseString=messageService.sendQyMessage(json);
	    	response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(responseString);
		
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 企业号验证接口
	 */
	@Action(value = "qyReceive")
	public void qyReceive(){
		try{
			response.setContentType("text/html;charset=utf-8");
			String method=request.getMethod();
			if("GET".equals(method)){
				response.getWriter().write(messageService.checkQySignature(msg_signature,timestamp,nonce,echostr));
			}else if("POST".equals(method)){
				response.getWriter().write(messageService.idoProcessQyRequest(msg_signature,timestamp,nonce, request));
			}
			}catch(Exception e){
			e.printStackTrace();
			
		}
	}

	public String getMsg_signature() {
		return msg_signature;
	}

	public void setMsg_signature(String msg_signature) {
		this.msg_signature = msg_signature;
	}

	public String getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(String timestamp) {
		this.timestamp = timestamp;
	}

	public String getNonce() {
		return nonce;
	}

	public void setNonce(String nonce) {
		this.nonce = nonce;
	}

	public String getEchostr() {
		return echostr;
	}

	public void setEchostr(String echostr) {
		this.echostr = echostr;
	}

}
