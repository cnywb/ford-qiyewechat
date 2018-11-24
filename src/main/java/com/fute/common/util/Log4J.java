package com.fute.common.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.PropertyConfigurator;

public class Log4J extends HttpServlet {
	public Log4J() {
	}

	public void init(ServletConfig config) throws ServletException {
		String prefix = config.getServletContext().getRealPath("/");
		String file = config.getInitParameter("log4j");
		String filePath = prefix + file;
		Properties props = new Properties();
		try {
			FileInputStream istream = new FileInputStream(filePath);
			props.load(istream);
			istream.close();
			String logFile = prefix
					+ props.getProperty("log4j.appender.logfile.File");// 设置路径
			props.setProperty("log4j.appender.logfile.File", logFile);
			PropertyConfigurator.configure(props);// 装入log4j配置信息
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
