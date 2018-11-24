package com.fute.common.servlet;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

public class CharacterEncodingFilter implements Filter {
	private FilterConfig config;
	private String encoding;// 字符编码

	public void init(FilterConfig config) throws ServletException {
		this.config = config;
		encoding = config.getInitParameter("encoding");
		if (encoding == null) {
			String message = "can not found init parameter\"encoding\" in filter declaration";
			throw new ServletException(message);
		}
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		if (encoding != null && !"".equals(encoding)) {
			// log.debug("config CharacterEncodingFilter encoding is "+encoding);
			request.setCharacterEncoding(encoding);
		} else {
			request.setCharacterEncoding("UTF-8");
		}
		chain.doFilter(request, response);
	}

	public void destroy() {
		this.config = null;
		this.encoding = null;
	}
}
