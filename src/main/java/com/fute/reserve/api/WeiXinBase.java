package com.fute.reserve.api;

import net.sf.json.JSONObject;

import com.fute.reserve.util.AccessToken;
import com.fute.reserve.util.Constants;
import com.fute.reserve.util.WeiXinCenterProxy;
import com.fute.reserve.util.WeixinUtil;

public class WeiXinBase {
	
	protected static JSONObject doPost(String url,String postParam){
		JSONObject jsonObject = HttpRequest.httpRequest(url, HttpRequest.POST, postParam);
		return jsonObject ;
	}
	
	protected static JSONObject doGet(String url){
		JSONObject jsonObject = HttpRequest.httpRequest(url, HttpRequest.GET, null);
		return jsonObject ;
	}
	
	protected static String getTokenID(){
		// 第三方用户唯一凭证
		String appId = Constants.CORPID;
		// 第三方用户唯一凭证密钥
		String appSecret = Constants.CORPSECRET;

		// 调用接口获取access_token
		String at = WeiXinCenterProxy.getAccessToken();
		String tokenid = at;
		System.out.println(tokenid);
		return tokenid;
	}

}
