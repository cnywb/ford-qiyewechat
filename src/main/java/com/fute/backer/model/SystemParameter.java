package com.fute.backer.model;

import java.io.Serializable;








/**
 * 系统配置参数
 * @author Administrator
 *
 */

public class SystemParameter implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = -3340831184070776835L;

		private long id;//

	
	private String name;
	
	
	private String paramKey;
	
	
	private String paramValue;
	
	
	private String category;
	
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SystemParameter other = (SystemParameter) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	

	

}
