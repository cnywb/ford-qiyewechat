package com.fute.backer.action;

import java.util.List;
import java.util.Map;

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

import com.fute.backer.service.SystemService;





@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/backer/consult")
public class AnswerAction {
	

    HttpServletResponse response = ServletActionContext.getResponse();  
	HttpServletRequest request=ServletActionContext.getRequest();
	
	private List<Map<String, Object>> dataList ;
	
	private Integer questionId;
	
	
	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	@Resource
	private SystemService systemService;
	
	@Action(value = "answerDetail",results={@Result(name="SUCCESS",location="/management/answer_detail.jsp")})
	public String  answerDetail(){
		dataList=systemService.getAnswerDetailByQuestionById(questionId);
	   	return "SUCCESS";
	}
	

}
