package com.fute.reserve.api;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;

/**
 * @ClassName:WeiXinMessageAPI.java
 * @ClassPath:com.hk.reserve.api
 * @Desciption:微信消息api
 * @Author: robin
 * @Date: 2015-1-19 下午01:47:00
 * 
 */
public class WeiXinMessageAPI extends WeiXinBase{
	
	
	/**
	 * @function:生成需要进行信息群发的json数据
	 * @datetime:2015-1-21 下午12:29:03
	 * @Author: robin
	 * @param: @param sendMap
	 * @return JSONObject
	 */
	private static JSONObject getSendJson(Map<String, Object> sendMap,String msgtypekey){
		if(null == sendMap || sendMap.isEmpty()){
			return null ;
		}
		JSONObject sendJson = new JSONObject();
		//发送群体
		//固特异官方的分组
//		boolean is_to_all = true ;
		JSONObject filterJson = new JSONObject();
		//全部发送
		filterJson.put("is_to_all", true) ;
		/*if(sendMap.containsKey("roletype") && Constants.ROLE_TYPE_ADMIN.equals(sendMap.get("roletype").toString())){
			String groupid = FileUtil.getMapValue(sendMap, "groupid") ;
			if(null == groupid || "".equals(groupid)){
				//分组为空，表示全部发送
				is_to_all = true ;
			}else {
				filterJson.put("group_id", groupid);
			}
			filterJson.put("is_to_all", is_to_all) ;
		}
		sendJson.put("filter", filterJson);*/
		
		JSONObject sendTypeJson = new JSONObject();
		String msgtype = "" ;
		if(null != msgtypekey && "txt".equals(msgtypekey.toString())){
			//文本
			sendTypeJson.put("content", MapUtils.getString(sendMap, "content"));
			msgtype = "text" ;
		}else if ("img".equals(msgtypekey.toString())) {
			//图片
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
			msgtype = "image" ;
		}else if ("article".equals(msgtypekey.toString())) {
			//图文
			msgtype = "mpnews" ;
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
		}else {
			//TODO 语音
			msgtype = "voice" ;
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
		}
		sendJson.put(msgtype, sendTypeJson);
		sendJson.put("msgtype", msgtype);
		return sendJson ;
	}
	
	private static JSONObject getOpenidSendJson(Map<String, Object> sendMap,String msgtypekey,List<Map<String, Object>> openidList){
		if(null == sendMap || sendMap.isEmpty()){
			return null ;
		}
		JSONObject sendJson = new JSONObject();
		//发送群体
		if(null == openidList || openidList.isEmpty() || openidList.size()<=0){
			return null ;
		}
		
		String[] openidArray = new String[openidList.size()] ;
		int index = 0 ;
		for (int i = 0; i < openidList.size(); i++) {
			Map<String, Object> map = openidList.get(i) ;
			if(null != map && !map.isEmpty() && map.containsKey("openid")){
				openidArray[index] = MapUtils.getString(map, "openid") ;
				index ++ ;
			}
		}
		System.out.println(openidArray);
		sendJson.put("touser", openidArray) ;
		
		JSONObject sendTypeJson = new JSONObject();
		String msgtype = "" ;
		if(null != msgtypekey && "txt".equals(msgtypekey.toString())){
			//文本
			sendTypeJson.put("content", MapUtils.getString(sendMap, "content"));
			msgtype = "text" ;
		}else if ("img".equals(msgtypekey.toString())) {
			//图片
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
			msgtype = "image" ;
		}else if ("article".equals(msgtypekey.toString())) {
			//图文
			msgtype = "mpnews" ;
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
		}else {
			//TODO 语音
			msgtype = "voice" ;
			sendTypeJson.put("media_id", MapUtils.getString(sendMap, "mediaid"));
		}
		sendJson.put(msgtype, sendTypeJson);
		sendJson.put("msgtype", msgtype);
		return sendJson ;
	}
	
	public static Map<String, Object> sendMessage(Map<String, Object> sendMap,String msgtypekey,List<Map<String, Object>> openidList){
		if(null == sendMap || sendMap.isEmpty()){
			return null ;
		}
		//判断信息的提交者，从而判断使用哪种方式进行调用
		if(null == openidList){
			//全部发送
			return sendAllByGroup(sendMap,msgtypekey);
		}
		//需要按openid进行群发
		return sendAllByOpendid(sendMap,msgtypekey,openidList);
	}
	
	/**
	 * @function: 群发消息(通过组进行群发)
	 * @datetime:2015-1-21 上午11:26:59
	 * @Author: robin
	 * @param: @param graphicList
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> sendAllByGroup(Map<String, Object> sendMap,String msgtypekey){
		//获取群发的json数据
		JSONObject sendJson = getSendJson(sendMap,msgtypekey) ; 
		
		String tokenid = getTokenID();
		StringBuffer temp = new StringBuffer("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=");
		temp.append(tokenid);
		JSONObject resultJson = doPost(temp.toString(), sendJson.toString());
		
		String result = resultJson.toString(); //MenuManager.get(url);
		System.out.println(result);
		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		//{"errcode":0,"errmsg":"send job submission success","msg_id":2350051593}
		if (jsonobj.has("msg_id")) {
			String msgid = jsonobj.getString("msg_id");// 获取字符串。
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msgid", msgid) ;
//			map.put("createat", FileDeal.formatTimeStamp(jsonobj.getLong("created_at")));
			return map ;
		}else {
			new Exception(result);
		}
		return null ;
	}
	
	/**
	 * @function: 信息群发（通过openid进行群发）
	 * @datetime:2015-1-21 下午02:06:59
	 * @Author: robin
	 * @param: @param sendMap
	 * @return Map<String,Object>
	 */
	public static Map<String, Object> sendAllByOpendid(Map<String, Object> sendMap,String msgtypekey,List<Map<String, Object>> openidList){
		//获取群发的json数据
		JSONObject sendJson = getOpenidSendJson(sendMap,msgtypekey,openidList) ; 
		
		String tokenid = getTokenID();
		StringBuffer temp = new StringBuffer("https://api.weixin.qq.com/cgi-bin/message/mass/sendall?access_token=");
		temp.append(tokenid);
		JSONObject resultJson = doPost(temp.toString(), sendJson.toString());
		
		String result = resultJson.toString(); //MenuManager.get(url);
		System.out.println(result);
		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		if (jsonobj.has("msg_id")) {
			String msgid = jsonobj.getString("msg_id");// 获取字符串。
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("msgid", msgid) ;
			return map ;
		}else {
			new Exception(result);
		}
		return null ;
	}

	
	
	
}
