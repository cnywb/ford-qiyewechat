package com.fute.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

public class PageHelper {
	private String output;
	private int pageCount = 0;

	private int currentPage = 1;

	private int totalCount = 0;

	private int rowPage = 10;

	private HttpServletRequest request = null;

	private int begin = 0;
	private int end = 0;
	Map<String, Object> params = new HashMap<String, Object>();
	PagerVO page = new PagerVO();
	private String form = "pageForm";
	String linkUrl = "";
	String param = "";

	public PageHelper() {
	}

	public PageHelper(HttpServletRequest request) {
		setRequest(request);
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
		this.linkUrl = request.getRequestURI();
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
		init();
	}

	private void init() {
		this.linkUrl = UrlUtil.getCurUri(this.request);

		if (this.linkUrl.indexOf("?") == -1) {
			this.linkUrl += "?currentPage=";
			this.param = "1";
		} else {
			int pos = this.linkUrl.indexOf("currentPage");
			if (pos == -1) {
				this.linkUrl = this.linkUrl.substring(0, this.linkUrl
						.indexOf("?"));
				this.linkUrl += "?currentPage=";
				this.param = "1";
			} else {
				this.linkUrl = this.linkUrl.substring(0, pos + 12);
				this.param = this.request.getParameter("currentPage");
			}

		}

		this.pageCount = (this.totalCount % this.rowPage > 0 ? this.totalCount
				/ this.rowPage + 1 : this.totalCount / this.rowPage);

		if ((this.request.getParameter("currentPage") != null)
				&& (!"".equals(this.request.getParameter("currentPage")))
				&& (!"null".equals(this.request.getParameter("currentPage")))) {
			try {
				this.currentPage = Integer.parseInt(this.request
						.getParameter("currentPage"));
			} catch (Exception e) {
				this.currentPage = this.pageCount;
			}

		}

		if (this.currentPage > this.pageCount) {
			this.currentPage = this.pageCount;
		}

		if (this.currentPage <= 0) {
			this.currentPage = 1;
		}

		if (this.currentPage > 1) {
			this.begin = (this.rowPage * (this.currentPage - 1));
		}

		StringBuffer paramBuf = new StringBuffer();
		Enumeration enumeration = this.request.getParameterNames();
		paramBuf.append("<form name='" + this.form + "' action='"
				+ this.linkUrl + this.param + "' method='post'>");
		while (enumeration.hasMoreElements()) {
			String name = (String) enumeration.nextElement();
			String value = ParamUtil.getParameter(this.request, name);

			this.params.put(name, value);
			if (!name.equals("currentPage")) {
				paramBuf.append("<input id='" + name + "' type='hidden' name='"
						+ name + "' value='" + value + "'>\n");
			}
		}
		paramBuf.append("</form>");
		this.output = paramBuf.toString();
		this.page.setFormStr(this.output);
	}

	public PagerVO paginate() {
		StringBuffer rt = new StringBuffer();

		rt.append("&nbsp;&nbsp;<SPAN class='current'>共有&nbsp;"
				+ this.totalCount + "&nbsp;条记录");
		if (this.pageCount > 0) {
			rt.append(",&nbsp;当前第&nbsp;" + this.currentPage + "/"
					+ this.pageCount + "&nbsp;页&nbsp;</span>");
			rt.append("&nbsp;");
			if ((this.currentPage > 1) && (this.pageCount > 1)) {
				rt.append("<a class='list_link' href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + "1';document."
						+ this.form + ".submit();return false;\">");
			}
			rt.append("&lt;&lt;首页");
			if ((this.currentPage > 1) && (this.pageCount > 1)) {
				rt.append("</a>");
			}
			rt.append("&nbsp;");
			if ((this.currentPage > 1) && (this.pageCount > 1)) {
				rt.append("<a class='list_link' href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + (this.currentPage - 1)
						+ "';document." + this.form
						+ ".submit();return false;\">");
			}
			rt.append("&lt;上一页");
			if ((this.currentPage > 1) && (this.pageCount > 1)) {
				rt.append("</a>");
			}
			rt.append("&nbsp;");
			if (this.currentPage < this.pageCount) {
				rt.append("<a class='list_link' href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + (this.currentPage + 1)
						+ "';document." + this.form
						+ ".submit();return false;\">");
			}
			rt.append("下一页&gt;");
			if (this.currentPage < this.pageCount) {
				rt.append("</a>");
			}
			rt.append("&nbsp;");
			if (this.currentPage < this.pageCount) {
				rt.append("<a class='list_link' href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + this.pageCount
						+ "';document." + this.form
						+ ".submit();return false;\">");
			}
			rt.append("尾页&gt;&gt;");
			if (this.currentPage < this.pageCount) {
				rt.append("</a>");
			}

			rt.append("&nbsp;");
			rt
					.append("转到<input id='pagerSelect' name='pagerSelect'  style=\"width:5;\" class=\"intext\" size=\"1\">页");
			rt.append("<input type='button' value='go'  style=\"width:30;\" ");
			rt.append("\" onclick=\"pagego();\"");
			rt.append(">");
			rt.append("<script type=\"text/javascript\">");
			rt.append("function pagego(){document." + this.form + ".action='"
					+ this.linkUrl
					+ "'+document.getElementById(\"pagerSelect\").value;"
					+ "document." + this.form + ".submit();" + "}");
			rt.append("</script>");
		}
		this.page.setPageStr(rt.toString());

		return this.page;
	}

	public PagerVO paginate1() {
		StringBuffer rt = new StringBuffer();

		rt.append("<div class=\"row area-table-bar\"><div class=\"area-pager\">");

		rt.append("&nbsp;&nbsp;<SPAN class='current'>共有&nbsp;"
				+ this.totalCount + "&nbsp;条记录");
			rt.append(",&nbsp;当前第&nbsp;" + this.currentPage + "/"
					+ this.pageCount + "&nbsp;页&nbsp;</span>");

		if (this.pageCount > 0) {
			rt.append(" <ul class=\"pagination pagination-sm\">");

			// 上一页
			if ((this.currentPage > 1) && (this.pageCount > 1)) {
				rt.append("<li><a href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + (this.currentPage - 1)
						+ "';document." + this.form
						+ ".submit();return false;\">");
			} else {
				rt.append("<li><a  class=\"disabled\">");
			}

			rt.append("上一页");
			if ((this.currentPage > 1) && (this.pageCount > 1))
				rt.append("</a></li>");
			else {
				rt.append("</a></li>");
			}
			// 当前页
			rt.append(" <li class=\"active\"><a href=\"\">" + this.currentPage
					+ "</a></li>");

			// 下一页
			if (this.currentPage < this.pageCount) {
				rt.append("<li><a href=\"");
				rt.append("#");
				rt.append("\" onclick=\"javascript:document." + this.form
						+ ".action='" + this.linkUrl + (this.currentPage + 1)
						+ "';document." + this.form
						+ ".submit();return false;\">");
			} else {
				rt.append("<li><a  class=\"disabled\">");
			}
			rt.append("下一页");
			if (this.currentPage < this.pageCount)
				rt.append("</a></li>");
			else {
				rt.append("</a></li>");
			}
			rt.append(" </ul>");
			rt.append("<div class=\"pagination-jump\">");
			// 跳转页面
			rt
					.append("<input id='pagerSelect' name='pagerSelect'  class=\"form-control\"/>");
			rt.append("<input type='button' value='跳转' class=\"btn\"");
			rt.append("\" onclick=\"pagego();\"");
			rt.append("/>");
			rt.append("<script type=\"text/javascript\">");
			rt.append("function pagego(){document." + this.form + ".action='"
					+ this.linkUrl
					+ "'+document.getElementById(\"pagerSelect\").value;"
					+ "document." + this.form + ".submit();" + "}");
			rt.append("</script>");

			rt.append("</div>");
			rt.append("</div>");
		}
		this.page.setPageStr(rt.toString());

		return this.page;
	}

	public void setPages(int rowPage, HttpServletRequest request, String form) {
		this.rowPage = rowPage;
		this.request = request;
		this.form = form;
	}

	public int getBegin() {
		return this.begin;
	}

	public int getRowPage() {
		return this.rowPage;
	}

	public void setRowPage(int rowPage) {
		this.rowPage = rowPage;
	}

	public void setBegin(int begin) {
		this.begin = begin;
	}

	public String getParameter(String par) {
		return this.request.getParameter(par);
	}

	public HttpSession getSession() {
		return this.request.getSession();
	}

	public int getTotalCount() {
		return this.totalCount;
	}

	public int getCurrentPage() {
		return this.currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getIndexSer() {
		return (getCurrentPage() - 1) * this.rowPage + 1;
	}

	public int getEnd() {
		this.end = (getRowPage() + getBegin());
		this.end = (this.end > getTotalCount() ? getTotalCount() : this.end);
		return this.end;
	}

	public void setEnd(int end) {
		this.end = end;
	}

	public HttpServletRequest getRequest() {
		return this.request;
	}

}
