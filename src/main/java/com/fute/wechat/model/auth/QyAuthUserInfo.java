package com.fute.wechat.model.auth;


/**
 * 企业号用户信息
 * @author xuwenfeng
 *
 */
public class QyAuthUserInfo {
	

	//报文里属性开头字母是大写，不作此注解会报错
	private String userId;

	private String openId;
	

	private String deviceId;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		this.deviceId = deviceId;
	}

	public String getOpenId() {
		return openId;
	}

	public void setOpenId(String openId) {
		this.openId = openId;
	}



	
	
	
	

}
