package com.fute.backer.model;

import com.fute.util.JSONUtil;


public class Area {
	
	private Integer id;
	
	private String name;
	
	private String code;
	
	private Integer parentId;
	
	private String remark;
	
	private Integer ifActive;
	
	private Integer priority;
	
	private Integer pId;//ztree的父ID
	
	private Boolean isParent;// ztree的是否父节点
	
	
	

	public Integer getpId() {
		return parentId;
	}

	public void setpId(Integer pId) {
		this.pId = pId;
	}

	public Boolean getIsParent() {
		return parentId==0;
	}

	public void setIsParent(Boolean isParent) {
		this.isParent = isParent;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getParentId() {
		return parentId;
	}

	public void setParentId(Integer parentId) {
		this.parentId = parentId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIfActive() {
		return ifActive;
	}

	public void setIfActive(Integer ifActive) {
		this.ifActive = ifActive;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}
	
	@Override
	public String toString() {
		return JSONUtil.objectToJson(this);
	}

}
