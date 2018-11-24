package com.fute.util;

import javax.servlet.http.HttpServletRequest;

public class UrlUtil {

	 public static String getCurUri(HttpServletRequest request) {
		    String qStr = request.getQueryString();
		    String rtn = request.getRequestURI();

		    if (qStr != null) {
		      rtn = rtn + "?" + qStr;
		    }
		    String ur = getCurUr(request);
		    if ((qStr != null) && (!qStr.equals(""))) {
		      if (qStr.indexOf("&") != -1)
		        rtn = ur.substring(ur.indexOf("/"), ur.indexOf("&")).toString();
		      else
		        rtn = ur;
		    }
		    return rtn.toString();
     }
	 public static String getCurUr(HttpServletRequest request) {
		    String qStr = request.getQueryString();
		    StringBuffer rtn = new StringBuffer();
		    rtn.append(request.getRequestURI());
		    if (qStr != null) {
		      rtn.append("?" + qStr);
		    }
		    return rtn.toString();
	 }
	      
}
