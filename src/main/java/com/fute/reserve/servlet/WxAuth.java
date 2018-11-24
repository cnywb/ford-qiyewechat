package com.fute.reserve.servlet;

import com.fute.backer.action.BatteryAction;
import com.fute.reserve.util.WeixinUtil;
import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

/**
 * Servlet implementation class WxAuth
 */
public class WxAuth extends HttpServlet {
	private static Logger log = Logger.getLogger(BatteryAction.class);
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WxAuth() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		System.out.println("openId=" + request.getParameter("openid"));
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		Enumeration e = request.getParameterNames();
		
		String str = "";
		String code = request.getParameter("code");
		log.info("code===" + code);
		String result = new WeixinUtil().getInfo(code);
		log.info("resutl===" + result);
		PrintWriter out = response.getWriter();
		out.print(str);
	}

}
