package com.fute.reserve.main;

import com.fute.reserve.pojo.*;
import com.fute.reserve.util.Constants;
import com.fute.reserve.util.WeiXinCenterProxy;
import com.fute.reserve.util.WeixinUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.collections.MapUtils;
import org.apache.http.client.CookieStore;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.log4j.Logger;
import org.jfree.util.Log;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MenuManager {

	private static Logger log = Logger.getLogger(MenuManager.class);

	public void importOpenId() {
		// 第三方用户唯一凭证
		String appId = Constants.CORPID;
		// 第三方用户唯一凭证密钥
		String appSecret = Constants.CORPSECRET;

		// 调用接口获取access_token
		// 调用接口获取access_token
		String tokenid = WeiXinCenterProxy.getAccessToken();
		System.out.println(tokenid);
		String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="
				+ tokenid;
		String result = get(url);
		JSONObject jsonobj = JSONObject.fromObject(result);// 将字符串转化成json对象
		if (jsonobj.has("data")) {
			String data = jsonobj.getString("data");// 获取字符串。
			JSONObject jsonopenid = JSONObject.fromObject(data);
			if (jsonobj.has("data")) {
				JSONArray openidArray = jsonopenid.getJSONArray("openid");
				for (int i = 0; i < openidArray.size(); i++) {
					System.out
							.print("openid:" + openidArray.getString(i) + " ");

				}
			}

		}

	}

	private static CookieStore _cookieStore = null;

	public static String get(String url) {
		DefaultHttpClient httpclient = new DefaultHttpClient();
		HttpGet httpget = new HttpGet(url);
		if (_cookieStore != null) {
			httpclient.setCookieStore(_cookieStore);
		}
		ResponseHandler<String> responseHandler = new BasicResponseHandler();
		String responseBody = null;
		try {
			responseBody = httpclient.execute(httpget, responseHandler);
		} catch (Exception e) {

		}
		_cookieStore = httpclient.getCookieStore();
		httpclient.getConnectionManager().shutdown();
		return responseBody;
	}

	
	
	public boolean createMenu(Menu menu) {
		// 第三方用户唯一凭证
		String appId = Constants.CORPID;
		// 第三方用户唯一凭证密钥
		String appSecret = Constants.CORPSECRET;

		// 调用接口获取access_token
		// 调用接口获取access_token
		String tokenid = WeiXinCenterProxy.getAccessToken();

		if (null != tokenid) {
			// 调用接口创建菜单
			int result = WeixinUtil.createMenu(menu,tokenid);

			// 判断菜单创建结果
			if (0 == result){
				log.info("菜单创建成功！");
				return true ;
			}else{
				log.info("菜单创建失败，错误码：" + result);
			}
		}
		return false ;
	}

	
	
	
	/**
	 * 组装菜单数据
	 * 
	 * @return
	 */
	public static Menu getMenu(List<LinkedHashMap<String, Object>> menuList) {
		/**
		 * 这是公众号xiaoqrobot目前的菜单结构，每个一级菜单都有二级菜单项
		 * 
		 * 
		 * 在某个一级菜单下没有二级菜单的情况，menu该如何定义呢？
		 * 
		 * 比如，第三个一级菜单项不是“更多体验”，而直接是“幽默笑话”，那么menu应该这样定义：
		 * 
		 * menu.setButton(new Button[] { mainBtn1, mainBtn2, btn33 });
		 */
		Menu menu = new Menu();
		Button[] menuButton ;
		
		if(null!=menuList && menuList.size()>0){
			menuButton = new Button[menuList.size()] ;
			
			for (int i=0, len=menuList.size();i<len;i++) {
				Map<String, Object> menuMap = menuList.get(i);
				List<LinkedHashMap<String, Object>> childRen = (List<LinkedHashMap<String, Object>>) menuMap.get("childRen");
				Button[] subButton ;
				ComplexButton bottomButton = new ComplexButton();
				bottomButton.setName(MapUtils.getString(menuMap, "menuname"));
				if(null!=childRen && childRen.size()>0){
					subButton = new Button[childRen.size()] ;
					//生成子菜单
					for (int j=0,len2=childRen.size();j<len2;j++) {
						LinkedHashMap<String, Object> item = childRen.get(j);
						if(null!=item && !item.isEmpty()){
							//这里生成每一个子菜单
							// 如果key = click ，则获取linkurl（存储的是关键字）
							//如果key= view ，则获取linkurl（存储的是连接地址）
							String msgKey = MapUtils.getString(item, "msgKey");
							if(null!=msgKey && Constants.WEIXIN_MENU_CLICK.equals(msgKey)){
								//生成click类型的微信菜单
								CommonButton itemButton = new CommonButton();
								itemButton.setName(MapUtils.getString(item, "menuname"));
								itemButton.setType(msgKey);
								itemButton.setKey(MapUtils.getString(item, "linkurl"));
								subButton[j] = itemButton ;
							}else if (Constants.WEIXIN_MENU_VIEW.equals(msgKey)) {
								//生成view类型的微信菜单
								ViewButton itemButton = new ViewButton();
								itemButton.setName(MapUtils.getString(item, "menuname"));
								itemButton.setType(msgKey);
								itemButton.setUrl(MapUtils.getString(item, "linkurl"));
								subButton[j] = itemButton ;
							}
							//=============
						}
					}
					//subButton : 是生成的子菜单的数组
					//注意格式问题，如果不存在子菜单，是不允许存在的
//					 {	
//				          "type":"click",
//				          "name":"今日歌曲",
//				          "key":"V1001_TODAY_MUSIC"
//				      },
					if(null==subButton || subButton.length==0){
						bottomButton.setSub_button(null);
					}else {
						bottomButton.setSub_button(subButton);
					}
					menuButton[i] = bottomButton ;
				}else {
					//空菜单
					Log.info("空菜单");
					if(null!=menuMap && !menuMap.isEmpty()){
						//这里生成每一个子菜单
						// 如果key = click ，则获取linkurl（存储的是关键字）
						//如果key= view ，则获取linkurl（存储的是连接地址）
						String msgKey = MapUtils.getString(menuMap, "msgKey");
						if(null!=msgKey && Constants.WEIXIN_MENU_CLICK.equals(msgKey)){
							//生成click类型的微信菜单
							CommonButton itemButton = new CommonButton();
							itemButton.setName(MapUtils.getString(menuMap, "menuname"));
							itemButton.setType(msgKey);
							itemButton.setKey(MapUtils.getString(menuMap, "linkurl"));
							menuButton[i] = itemButton ;
						}else if (Constants.WEIXIN_MENU_VIEW.equals(msgKey)) {
							//生成view类型的微信菜单
							ViewButton itemButton = new ViewButton();
							itemButton.setName(MapUtils.getString(menuMap, "menuname"));
							itemButton.setType(msgKey);
							itemButton.setUrl(MapUtils.getString(menuMap, "linkurl"));
							menuButton[i] = itemButton ;
						}
						//=============
					}
					
				}

			}
			menu.setButton(menuButton);
		}
		return menu ;
	}
}
