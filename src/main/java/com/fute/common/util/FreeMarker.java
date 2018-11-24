package com.fute.common.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import com.fute.reserve.util.Constants;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class FreeMarker {
	private static String root_path_html = Constants.File_Path;
	private Configuration cfg = new Configuration();

	private Template template;

	/**
	 * 切换模板文件
	 * 
	 * @param template
	 */
	public void changeTemplate(String template) {
		try {
			this.template = this.cfg.getTemplate(template);
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("freemarker 实例生成错误");
		}
	}

	/**
	 * 创建文件
	 */
	public String createHTML(String filePath, String fileName,
			Map<String, Object> data) {
		String Path = root_path_html + filePath;
		this.creatDirs(Path);
		Path = Path.endsWith(File.separator) ? Path : Path + File.separator;
		File wmlFile = new File(Path + fileName);
		String fullpath = Path + fileName;
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(wmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			fullpath = "";
			e.printStackTrace();
			throw new RuntimeException("生成文件失败： " + filePath + fileName);
		}
		return fullpath;
	}

	public String createMOHTML(String filePath, String fileName,
			Map<String, Object> data) {
		String Path = filePath;
		this.creatDirs(Path);
		Path = Path.endsWith(File.separator) ? Path : Path + File.separator;
		File wmlFile = new File(Path + fileName);
		String fullpath = Path + fileName;
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(wmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			fullpath = "";
			e.printStackTrace();
			throw new RuntimeException("生成文件失败： " + filePath + fileName);
		}
		return fullpath;
	}

	// JAVA 入口页面的刷新
	public String writeFileIndexList(Map<String, Object> map) {
		boolean isExit = false;
		String file_path = map.get("file_path").toString();
		String file = root_path_html + file_path + map.get("filename");
		creatDirs(root_path_html + file_path);
		File wmlFile = new File(file);
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(wmlFile), "UTF-8"));
			template.process(map, out);
			out.flush();
			isExit = true;
		} catch (Exception e) {
			file = "";
			e.printStackTrace();
		}
		return file;
	}

	/**
	 * 创建文件目录
	 * 
	 * @param path
	 * @return
	 */
	private boolean creatDirs(String path) {
		File aFile = new File(path);
		if (!aFile.exists()) {
			return aFile.mkdirs();
		} else {
			return true;
		}
	}

	public String writeFile(String filePath, String fileName,
			Map<String, Object> data) {
		String Path = root_path_html + filePath;
		this.creatDirs(Path);
		Path = Path.endsWith(File.separator) ? Path : Path + File.separator;
		File wmlFile = new File(Path + fileName);
		String fullpath = Path + fileName;
		Writer out;
		try {
			out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(wmlFile), "UTF-8"));
			template.process(data, out);
			out.flush();
			out.close();
		} catch (Exception e) {
			fullpath = "";
			e.printStackTrace();
			throw new RuntimeException("生成文件失败： " + filePath + fileName);
		}
		return fullpath;
	}

}
