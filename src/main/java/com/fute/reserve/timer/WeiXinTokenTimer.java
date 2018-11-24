package com.fute.reserve.timer;

import com.fute.reserve.util.Constants;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.util.TimerTask;

public class WeiXinTokenTimer  extends TimerTask implements ServletContextAware {

	@Override
	public void run() {
		System.out.println("加载token和 jsapi_ticket");
		// 第三方用户唯一凭证
		String appId = Constants.CORPID;
		// 第三方用户唯一凭证密钥
		String appSecret = Constants.CORPSECRET;
		System.out.println(appId);
		//刷新微信token
	}

	@Override
	public void setServletContext(ServletContext arg0) {
		
		
		
	}

	
}
