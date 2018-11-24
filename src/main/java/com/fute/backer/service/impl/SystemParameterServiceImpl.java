package com.fute.backer.service.impl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fute.backer.dao.SystemParameterMapper;
import com.fute.backer.model.SystemParameter;
import com.fute.backer.service.SystemParameterService;


@Service("systemParameterService")
public class SystemParameterServiceImpl implements SystemParameterService {
	
	
	@Resource
	private SystemParameterMapper systemParameterMapper;
	
	public  SystemParameter getByKey(String key){
		return systemParameterMapper.getByKey(key);
	}
	public void updateValueByKey(String key,String value){
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("key", key);
		data.put("value", value);
		systemParameterMapper.updateValueByKey(data);
	}
	
	public void updateWechatWelcomeWords(String welcomeWords){
		updateValueByKey("WECHAT_QY_WELCOME_WORDS",welcomeWords);
	}
	@Override
	public String getValueByKey(String key) {
		SystemParameter t=getByKey(key);
		if(t!=null){
			return t.getParamValue();
		}
		return null;
	}
	

}
