package com.fute.backer.dao;

import java.util.Map;

import com.fute.backer.model.SystemParameter;





public interface SystemParameterMapper {
	
	public SystemParameter getByKey(String key);
	
	public void updateValueByKey(Map<String, Object> data);

}
