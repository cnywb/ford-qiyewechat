package com.fute.wechat.service.impl.qy.txl;

import com.alibaba.fastjson.JSONObject;
import com.fute.backer.service.SystemParameterService;
import com.fute.wechat.model.auth.QyAuthUserInfo;
import com.fute.wechat.service.qy.txl.WechatQyUserService;
import com.fute.wechat.util.WechatQyUserUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;






@Service("wechatQyUserService")
public class WechatQyUserServiceImpl implements WechatQyUserService{

	private static final Logger logger= LoggerFactory.getLogger (WechatQyUserServiceImpl.class);

	
	@Resource
	private SystemParameterService systemParameterService;

	
	public String create(String json){
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
	return WechatQyUserUtil.create(corpId, corpSecret, json);
}

	public String update(String json){
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
		return WechatQyUserUtil.update(corpId, corpSecret, json);
	}

	public String delete(String id){
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
		return WechatQyUserUtil.delete(corpId, corpSecret, id);
	}


	public String queryById(String id){
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
		return WechatQyUserUtil.getById(corpId, corpSecret, id);
	}
	public String getAuthUserInfoJsonStr(String code){
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
		return WechatQyUserUtil.getAuthUserInfo(corpId,corpSecret,code) ;
	}
	
    /**
     * 得到授权用户信息
     * @param code
     * @return
     */
	public QyAuthUserInfo getAuthUserInfo(String code){
		String json=getAuthUserInfoJsonStr(code);
		logger.info ("json:{}",json);
		QyAuthUserInfo userInfo= JSONObject.parseObject (json,QyAuthUserInfo.class);
		return userInfo;
	}
	
	
}
