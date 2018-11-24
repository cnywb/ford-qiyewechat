package com.fute.util;


import net.sf.json.JSONObject;

import com.fute.common.util.HttpClient;
import com.fute.reserve.api.HttpRequest;
import com.fute.reserve.util.WeiXinCenterProxy;


public class WeixinSendMessage {

	public void sendMsg(String msgType,String touser,String agentid,String safe,String msgContent){

		try{
		System.out.println(msgType+"-----------------------------------");
		System.out.println(touser+"------------------------------------");
		System.out.println(msgContent+"---------------------------------");
		String accessToken = WeiXinCenterProxy.getAccessToken();       //获取token
		String requestUrl =  "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token="+accessToken;
		//发送消息url地址
		JSONObject jo= new JSONObject();
		jo.put("touser", touser);
		jo.put("msgtype", msgType);
		jo.put("agentid", agentid);
		JSONObject jo1= new JSONObject();
		jo1.put("content", msgContent);
		jo.put("text", jo1);
		jo.put("safe", safe);
		JSONObject jsonObject = HttpRequest.httpRequest(requestUrl, "GET", jo.toString());
		if (null != jsonObject ) {
			System.out.println(jsonObject);
			System.out.println("isOk-----------------------------------------------------------------");
		}
		}catch(Exception e){
		e.printStackTrace();
		}
	}
	public void receiveMsg(String msg_signature,String timestamp,String nonce){
		try{
		String accessToken = WeiXinCenterProxy.getAccessToken();       //获取token
		String requestUrl = "http://api.3dept.com/?msg_signature=ASDFQWEXZCVAQFASDFASDFSS&timestamp=13500001234&nonce=123412323";
			//"http://api.3dept.com/?msg_signature="+msg_signature+"&timestamp="+timestamp+"&nonce="+nonce;
		//发送消息url地址
		System.out.println("requestUrl:"+requestUrl);
		JSONObject jsonObject = HttpRequest.httpRequest(requestUrl, "GET",null);
		System.out.println("测试的jsonobject："+jsonObject);
		if (null != jsonObject ) {
			System.out.println(jsonObject);
			System.out.println("successs-----------------------------------------------------------------");
		}
		}catch(Exception e){
		e.printStackTrace();
		}
	}
	public static void main(String[] args) {
		String requestUrl = "http://api.3dept.com/?signature=ASDFQWEXZCVAQFASDFASDFSS&timestamp=13500001234&nonce=123412323";

		System.out.println("1");
		
		System.out.println("0"+HttpClient.get(requestUrl));

		System.out.println("2");
		
	}
	
}
