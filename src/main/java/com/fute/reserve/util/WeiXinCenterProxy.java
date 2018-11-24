package com.fute.reserve.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fute.reserve.api.HttpRequest;
/**
 * <b>微信中控代理</b><br>
 * 全局缓存access_tocken和jsapi_ticket
 * 
 * @ClassName:WeiXinCenterProxy.java
 * @ClassPath:com.cdmedia.reserve.util
 * @Author: robin
 * @Date: 2015-4-25 下午03:59:20
 * @Version:2.0
 */
public class WeiXinCenterProxy {

	private static Logger log = LoggerFactory.getLogger(WeiXinCenterProxy.class);
	
	/**
	 * 获取access_token,失效后会重新请求，保证拿到的access_token是有效的。
	 * @datetime:2015-4-25 下午04:22:06
	 * @Author: robin
	 * @param: @param appid
	 * @param: @param appsecret
	 * @return String
	 */
	public static String getAccessToken() {
		// 验证当前acessToken是否过期
		boolean flag = AccessToken.isTimeout();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (flag) {
			String corpid = Constants.CORPID;
			String corpsecret = Constants.CORPSECRET ;
			// 当前accessToken已失效，需要重新请求
			String access_token_url = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=CORPID&corpsecret=CORPSECRECT";
			String requestUrl = access_token_url.replace("CORPID", corpid).replace("CORPSECRECT", corpsecret);
			JSONObject jsonObject = HttpRequest.httpRequest(requestUrl, "GET", null);
			
			if (null != jsonObject && jsonObject.containsKey("access_token")) {
				
				// 已重新获取到accessToken
				log.info(format.format(new Date())+ "====accessToken失效，重新获取的accessToken信息=" + jsonObject);
				AccessToken.starttime = new Date();
				AccessToken.accessToken = jsonObject.getString("access_token").toString();
				AccessToken.expiresIn = jsonObject.getLong("expires_in");
				
			} else {
				log.info(format.format(new Date()) + "====accessToken获取失败，错误信息="+ jsonObject);
				// accessToken获取失败
				AccessToken.starttime = null ;
				AccessToken.accessToken = "";
				AccessToken.expiresIn = 0;

				// 重新获取ticket
				return getAccessToken();
			}
		}else {
			log.info(format.format(new Date())+ "----accessToken=" + AccessToken.accessToken+"-----");
		}
		// 当前ticket未失效，直接获取
		return AccessToken.accessToken;
	}
	
	
	

	/**
	 * 获取jsapi_ticket,失效后会重新请求，保证拿到的jsapi_ticket是有效的。
	 * @datetime:2015-4-26 下午02:32:42
	 * @Author: robin
	 * @param: @return
	 * @return String
	 */
	public static String getJSApiTicket() {
		// 验证当前ticket是否过期
		boolean flag = JsApiTicket.isTimeout();
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		if (flag) {
			// 当前ticket已失效，需要重新请求
			String token = getAccessToken() ;
			String requestUrl = "https://qyapi.weixin.qq.com/cgi-bin/get_jsapi_ticket?access_token=" + token;
			JSONObject jsonObject = HttpRequest.httpRequest(requestUrl,
					HttpRequest.GET, null);
			if (null != jsonObject && jsonObject.containsKey("ticket")) {
				// 已重新获取到ticket
				log.info(format.format(new Date())
						+ "====jsapi_ticket失效，重新获取的jsapi_ticket信息=" + jsonObject);
				JsApiTicket.starttime = new Date();
				JsApiTicket.errcode = jsonObject.getString("errcode")
						.toString();
				JsApiTicket.errmsg = jsonObject.getString("errmsg").toString();
				JsApiTicket.ticket = jsonObject.getString("ticket").toString();
				JsApiTicket.expiresIn = jsonObject.getLong("expires_in");
			} else {
				log.info(format.format(new Date()) + "====jsapi_ticket获取失败，错误信息="
						+ jsonObject);
				// ticket获取失败
				JsApiTicket.starttime = null;
				JsApiTicket.errcode = "";
				JsApiTicket.errmsg = "";
				JsApiTicket.ticket = "";
				JsApiTicket.expiresIn = 0;

				// 重新获取ticket
				return getJSApiTicket();
			}
		}else {
			log.info(format.format(new Date())+ "-----jsapi_ticket=" + JsApiTicket.ticket+"------");
		}

		// 当前ticket未失效，直接获取
		return JsApiTicket.ticket;
	}

	public static void main(String[] args) {
		for (int i = 0; i < 10; i++) {
			String token = WeiXinCenterProxy.getAccessToken() ;
			System.out.println(token);
			String ticket = WeiXinCenterProxy.getJSApiTicket() ;
			System.out.println(ticket);
		}
	}

}