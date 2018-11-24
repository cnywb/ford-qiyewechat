package com.fute.backer.action;

import com.fute.backer.model.Area;
import com.fute.backer.service.AreaService;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;






@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/backer/area")
public class AreaAction {

	

    HttpServletResponse response = ServletActionContext.getResponse();  
	HttpServletRequest request=ServletActionContext.getRequest();
	
	private Area t;
	
	
	public Area getT() {
		return t;
	}


	public void setT(Area t) {
		this.t = t;
	}

	@Resource
	private AreaService areaService;
	
	
	@Action(value = "manage",results={@Result(name="SUCCESS",location="/management/area_manage.jsp")})
	public String  manage(){
	   	return "SUCCESS";
	}
	
	
	@Action(value = "getAllZtreeJson")
	public void getAllZtreeJson(){
	    try {
	    	response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(areaService.getAllArea().toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "add")
	public void add(){
	    try {
	    	response.setContentType("text/html;charset=utf-8");
	        response.getWriter().write(areaService.idoAddArea(t)+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "update")
	public void update(){
	    try {
	    	response.setContentType("text/html;charset=utf-8");
	        response.getWriter().write(areaService.idoUpdateArea(t)+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Action(value = "delete")
	public void delete(){
	    try {
	    	response.setContentType("text/html;charset=utf-8");
	        response.getWriter().write(areaService.idoDeleteAreaById(t.getId())+"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
