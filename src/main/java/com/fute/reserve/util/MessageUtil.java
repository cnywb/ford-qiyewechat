package com.fute.reserve.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.fute.reserve.message.resp.Article;
import com.fute.reserve.message.resp.MusicMessageResp;
import com.fute.reserve.message.resp.NewsMessageResp;
import com.fute.reserve.message.resp.TextMessageResp;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 *消息工具类
 * 
 * @author zhouqiuming
 * 
 */
public class MessageUtil {

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";
	
	/**
	 * 事件类型：（消息推送的结果）
	 */
	public static final String MASSSENDJOBFINISH = "MASSSENDJOBFINISH";


	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";
	
	/**
	 * @Fields EVENT_TYPE_SCAN: 多参数二维码
	 */
	public static final String EVENT_TYPE_SCAN = "SCAN";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "CLICK";

	/**
	 * 菜单类型key：关于我们
	 */
	public static final String MENU_KEY_ABOUT = "about";
	/**
	 * 菜单类型key：深夜的士
	 */
	public static final String MENU_KEY_SHENYE = "shenye";
	/**
	 * 菜单类型key：趣味调研
	 */
	public static final String MENU_KEY_QUWEI = "quwei";
	/**
	 * 菜单类型key：中奖查询
	 */
	public static final String MENU_KEY_ZHONGJIANG = "zhongjiang";

	/**
	 * 菜单类型key：大宁音乐季
	 */
	public static final String MENU_KEY_DANING = "daning";
	/**
	 * 菜单类型key：店家照片
	 */
	public static final String MENU_KEY_STOREPHOTOS = "storePhotos";
	/**
	 * 菜单类型key：造型师群
	 */
	public static final String MENU_KEY_STYLISTGROUP = "stylistGroup";
	/**
	 * 菜单类型key：服务列表
	 */
	public static final String MENU_KEY_SERVICESLIST = "servicesList";
	/**
	 * 菜单类型key：我的会员卡
	 */
	public static final String MENU_KEY_MYCARD = "myCard";
	/**
	 * 菜单类型key：我的商城
	 */
	public static final String MENU_KEY_MYSHOP = "myShop";
	/**
	 * 菜单类型key：我的优惠券
	 */
	public static final String MENU_KEY_MYCOUPONS = "myCoupons";
	/**
	 * 菜单类型key：马上预约
	 */
	public static final String MENU_KEY_RESERVATIONIMMEDIATELY = "reservationImmediately";
	/**
	 * 菜单类型key：预约记录
	 */
	public static final String MENU_KEY_RESERVATIONRECORD = "reservationRecord";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(String request)
			throws Exception {
		// 将解析结果存储在HashMap中
		Map<String, String> map = new HashMap<String, String>();

		// 从request中取得输入流
		 InputStream inputStream = new ByteArrayInputStream(request.getBytes("UTF-8"));  
		// 读取输入流
		SAXReader reader = new SAXReader();
		Document document = reader.read(inputStream);
		// 得到xml根元素
		Element root = document.getRootElement();
		// 得到根元素的所有子节点
		List<Element> elementList = root.elements();

		// 遍历所有子节点
		for (Element e : elementList)
			map.put(e.getName(), e.getText());

		// 释放资源
		inputStream.close();
		inputStream = null;

		return map;
	}

	/**
	 * 文本消息对象转换成xml
	 * 
	 * @param textMessageResp
	 *            文本消息对象
	 * @return xml
	 */
	public static String textMessageToXml(TextMessageResp textMessageResp) {
		xstream.alias("xml", textMessageResp.getClass());
		return xstream.toXML(textMessageResp);
	}

	/**
	 * 音乐消息对象转换成xml
	 * 
	 * @param musicMessageResp
	 *            音乐消息对象
	 * @return xml
	 */
	public static String musicMessageToXml(MusicMessageResp musicMessageResp) {
		xstream.alias("xml", musicMessageResp.getClass());
		return xstream.toXML(musicMessageResp);
	}

	/**
	 * 图文消息对象转换成xml
	 * 
	 * @param newsMessageResp
	 *            图文消息对象
	 * @return xml
	 */
	public static String newsMessageToXml(NewsMessageResp newsMessageResp) {
		xstream.alias("xml", newsMessageResp.getClass());
		xstream.alias("item", new Article().getClass());
		return xstream.toXML(newsMessageResp);
	}

	/**
	 * 扩展xstream，使其支持CDATA块
	 * 
	 * @date 2013-05-19
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				// 对所有xml节点的转换都增加CDATA标记
				boolean cdata = true;

				@SuppressWarnings("unchecked")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
}
