package com.fute.backer.action;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.fute.backer.model.Area;
import com.fute.backer.model.SystemParameter;
import com.fute.backer.service.AreaService;
import com.fute.backer.service.SystemParameterService;






@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/backer/systemparameter")
public class SystemParameterAction {

	

    HttpServletResponse response = ServletActionContext.getResponse();  
	HttpServletRequest request=ServletActionContext.getRequest();
	
	private SystemParameter t;
	
	
	

	public SystemParameter getT() {
		return t;
	}



	public void setT(SystemParameter t) {
		this.t = t;
	}

	@Resource
	private SystemParameterService systemParameterService;
	
	
	@Action(value = "wechatWelcomeWordsEdit",results={@Result(name="SUCCESS",location="/management/wechat_welcome_words_edit.jsp")})
	public String  wechatWelcomeWordsEdit(){
     	 t=systemParameterService.getByKey("WECHAT_QY_WELCOME_WORDS");
	   	return "SUCCESS";
	}
	
	
	
	@Action(value = "updateWechatWelcomeWords")
	public void updateWechatWelcomeWords(){
	    try {
	    	response.setContentType("text/html;charset=utf-8");
	    	systemParameterService.updateWechatWelcomeWords(t.getParamValue());
	        response.getWriter().write("1");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
}
