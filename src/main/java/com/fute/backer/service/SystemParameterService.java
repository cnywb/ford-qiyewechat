package com.fute.backer.service;



import com.fute.backer.model.SystemParameter;

public interface SystemParameterService {

	SystemParameter getByKey(String key);

	void updateValueByKey(String key, String value);

	void updateWechatWelcomeWords(String welcomeWords);

	String getValueByKey(String string);

}
