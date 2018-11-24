package com.fute.common.util;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

public class OAuthAPI {

	public static final String APP_ID = "ABC";
	public static final String APP_SECRET = "CDE";
	public static final String DOMAIN = "WWW.ABC.COM";

	public static void OAuthIfNesscary(HttpServletRequest request,
			HttpServletResponse response) throws IOException {
		String code = request.getParameter("code");
		HttpSession session = request.getSession();
		boolean isValidCode = true;
		String serviceUrl = URLEncoder.encode("http://" + DOMAIN
				+ request.getRequestURI(), "utf-8");
		// 检查是否已验证或者验证是否通过
		if (code == null || code.equals("authdeny")) {
			isValidCode = false;
		}
		// 如果session未空或者取消授权，重定向到授权页面
		if ((!isValidCode) && session.getAttribute("user") == null) {
			StringBuilder oauth_url = new StringBuilder();
			oauth_url
					.append("https://open.weixin.qq.com/connect/oauth2/authorize?");
			oauth_url.append("appid=").append(APP_ID);
			oauth_url.append("&redirect_uri=").append(serviceUrl);
			oauth_url.append("&response_type=code");
			oauth_url.append("&scope=snsapi_userinfo");
			oauth_url.append("&state=1#wechat_redirect");
			response.sendRedirect(oauth_url.toString());
			return;
		}
		// 如果用户同意授权并且，用户session不存在，通过OAUTH接口调用获取用户信息
		if (isValidCode && session.getAttribute("user") == null) {
			Map<String, Object> member = new HashMap<String, Object>();
			JSONObject obj = OAuthAPI.getAccessToken(OAuthAPI.APP_ID,
					OAuthAPI.APP_SECRET, code);
			String token = obj.getString("access_token");
			String openid = obj.getString("openid");

		}
	}

	/**
	 * 获取授权令牌
	 * */
	public static JSONObject getAccessToken(String appid, String secret,
			String code) {
		StringBuilder url = new StringBuilder();
		url.append("https://api.weixin.qq.com/sns/oauth2/access_token?");
		url.append("appid=" + appid);
		url.append("&secret=").append(secret);
		url.append("&code=").append(code);
		url.append("&grant_type=authorization_code");
		// HttpClientUtils.getJson(url.toString())
		return new JSONObject();
	}

}
