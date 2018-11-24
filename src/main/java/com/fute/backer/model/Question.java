package com.fute.backer.model;

import java.util.Date;

public class Question {
	/**未答复*/
	public static final Integer  STATUS_NOANSWER=0;
	/**已回复*/
	public static final Integer  STATUS_ANSWERED=1;
	/**关闭*/
	public static final Integer  STATUS_CLOSED=2;

	
	private Integer id;
	
	private Integer departmentId;
	
	private String content;
	
    private String userId;
    
    private Date createTime;
    
    private Integer status;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getDepartmentId() {
		return departmentId;
	}

	public void setDepartmentId(Integer departmentId) {
		this.departmentId = departmentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Question(Integer id, Integer departmentId, String content,
			String userId, Date createTime, Integer status) {
		super();
		this.id = id;
		this.departmentId = departmentId;
		this.content = content;
		this.userId = userId;
		this.createTime = createTime;
		this.status = status;
	}

	public Question() {
		super();
	}
    
    

}
