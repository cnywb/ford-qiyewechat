package com.fute.wechat.service.qy.txl;

import com.fute.wechat.model.auth.QyAuthUserInfo;

public interface WechatQyUserService {
	 QyAuthUserInfo getAuthUserInfo(String code);


	String create(String json);

}
