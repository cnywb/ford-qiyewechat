package com.fute.manage.pojo;

import java.util.Date;

/**
 * @author KEVIN
 */
public class LogNews {
	private int id;
	private String typ;
	private String title;
	private String filepath;
	private String newstype;
	private Date createdate;
	private String uploadFilePath;

	public String getUploadFilePath() {
		return uploadFilePath;
	}

	public void setUploadFilePath(String uploadFilePath) {
		this.uploadFilePath = uploadFilePath;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTyp() {
		return typ;
	}

	public void setTyp(String typ) {
		this.typ = typ;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}

	public String getNewstype() {
		return newstype;
	}

	public void setNewstype(String newstype) {
		this.newstype = newstype;
	}

}
