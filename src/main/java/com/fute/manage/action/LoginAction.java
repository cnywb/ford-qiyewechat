package com.fute.manage.action;

import com.fute.backer.service.BatteryService;
import com.fute.backer.service.SystemService;
import com.fute.common.action.BaseActionImpl;
import com.fute.common.annotation.Log;
import com.fute.common.constant.WebConstant;
import com.fute.common.util.FileDeal;
import com.fute.common.util.Md5Util;
import com.fute.common.util.URLConstant;
import com.opensymphony.xwork2.ActionContext;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;
public class LoginAction extends BaseActionImpl {

	private static final long serialVersionUID = 1L;

	private BatteryService batteryService;
	private List<Map<String, Object>> menuList;
	private SystemService systemService;
	private Map<String, Object> user;
	private String errorinfo;

	public Map<String, Object> getUser() {
		return user;
	}

	public void setUser(Map<String, Object> user) {
		this.user = user;
	}
	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public BatteryService getBatteryService() {
		return batteryService;
	}

	public void setBatteryService(BatteryService batteryService) {
		this.batteryService = batteryService;
	}

	public String getErrorinfo() {
		return errorinfo;
	}

	public void setErrorinfo(String errorinfo) {
		this.errorinfo = errorinfo;
	}

	public List<Map<String, Object>> getMenuList() {
		return menuList;
	}

	public void setMenuList(List<Map<String, Object>> menuList) {
		this.menuList = menuList;
	}

	List<Map<String, Object>> newsList;

	public List<Map<String, Object>> getNewsList() {
		return newsList;
	}

	public void setNewsList(List<Map<String, Object>> newsList) {
		this.newsList = newsList;
	}

	public String right() {
		try {
			return "right";
		} catch (Exception ex) {
			return URLConstant.ERROR;
		}
	}

	public String left() {
		try {
			return "left";
		} catch (Exception ex) {
			return URLConstant.ERROR;
		}
	}

	public String top() {
		try {
			return "top";
		} catch (Exception ex) {
			return URLConstant.ERROR;
		}
	}

	public String login() {
		try {
			return logOut();
		} catch (Exception ex) {
			return URLConstant.ERROR;
		}
	}

	private String menuJson;

	public String getMenuJson() {
		return menuJson;
	}

	public void setMenuJson(String menuJson) {
		this.menuJson = menuJson;
	}


	public String logOut() {
		invalidateSession(getRequest());
		return "logOut";
	}

	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public static void invalidateSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.invalidate();
	}

	public static void removeUserSession(HttpServletRequest req) {
		HttpSession session = req.getSession();
		session.removeAttribute(WebConstant.USER_SESSION_KEY);
		session.removeAttribute(WebConstant.PROJECT_SESSION_KEY);
	}

	@Log("登陆系统")
	public String checkuser() {
		FileDeal.parseSimpleForm(user);
		String forward =null;
		Map<String, Object> mapSession = ActionContext.getContext().getSession();
		if (mapSession.keySet().size() == 0) {
			if (null != user && user.get("password") != null) {
				user.put("password",Md5Util.getMD5(user.get("password")+""));
				Map<String, Object> userinfo=this.systemService.getUserInfo(user);
				removeUserSession(getRequest());
				if (userinfo!=null){
					ActionContext.getContext().getSession().put(WebConstant.USER_SESSION_KEY,userinfo);
					menuList=systemService.getMenuList(userinfo);
					ActionContext actionContext = ActionContext.getContext();
					Map<String, Object> session = actionContext.getSession();
					getRequest().getSession().putValue("username", userinfo.get("username"));
					getRequest().getSession().putValue("menuList", menuList);
					forward = "valid";
				} else {
					forward = URLConstant.LOGIN;
					errorinfo = "用户名和密码无效,请重新输入!";
				}
			} else {
				forward = URLConstant.LOGIN;
			}

		} else {
			forward = "valid";
		}

		return forward;
	}
}