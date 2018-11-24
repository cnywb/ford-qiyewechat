package com.fute.reserve.service;

import com.fute.backer.service.BasicService;
import com.fute.common.util.FileDeal;
import com.fute.reserve.message.resp.Article;
import com.fute.reserve.message.resp.NewsMessageResp;
import com.fute.reserve.message.resp.TextMessageResp;
import com.fute.reserve.util.Constants;
import com.fute.reserve.util.MessageUtil;
import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 核心服务类
 * 
 * @author liufeng
 * @date 2013-05-20
 */
public class CoreService {
	private static final Logger LOGGER = Logger.getLogger(CoreService.class);
	private static String projectPath = Constants.PROJECT_PATH;


	private BasicService basicService;
	
	private static CoreService coreService;

	
	public void init() {
		coreService = this;
		coreService.basicService = this.basicService;
	}
	
	public static BasicService getBasicService() {
		return coreService.basicService;
	}

	public void setBasicService(BasicService basicService) {
		this.basicService = basicService;
	}


	// 根据用户录入的关键字，获取图文
	private static Map<String, Object> getKeyContent(
			List<Map<String, Object>> list, String userKey) {
		Map<String, Object> dataMap = null;
		for (Map<String, Object> map : list) {
			if (null != map && !map.isEmpty() && map.containsKey("key")) {
				String key = getMapValue(map, "key");
				if (null != key && !"".equals(key)) {
					String[] keyArray = key.split(",");
					for (String string : keyArray) {
						if (null != string && !"".equals(string)
								&& string.equals(userKey)) {
							// 用户录入的关键字
							dataMap = map;
							break;
						}
					}
				}
				if (null != dataMap) {
					break;
				}
			}
		}
		return dataMap;
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequest(String request) {
		LOGGER
				.info(".................................事件.................................");
		System.out.println("request:===================="+request);
		String respMessage = null;
		try {
			// 默认返回的文本消息内容
			String respContent = "请求处理异常，请稍候尝试！";

			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);
			LOGGER.info("requestMap::::::::::::::::" + requestMap);
			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");
			

			// Resource resource = new FileSystemResource(request
			// .getRealPath("/WEB-INF/applicationContext.xml"));
			// XmlBeanFactory context = new XmlBeanFactory(resource);
			// BatteryServiceImp battaryService = context.getBean(
			// "batteryService", BatteryServiceImp.class);

			BasicService basicService = getBasicService();

			// 回复文本消息
			TextMessageResp textMessageResp = new TextMessageResp();
			textMessageResp.setToUserName(fromUserName);
			textMessageResp.setFromUserName(toUserName);
			textMessageResp.setCreateTime(new Date().getTime());
			textMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);

			NewsMessageResp newsMessageResp = new NewsMessageResp();
			newsMessageResp.setToUserName(fromUserName);
			newsMessageResp.setFromUserName(toUserName);
			newsMessageResp.setCreateTime(new Date().getTime());
			newsMessageResp.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

			LOGGER.info("msgType::::::::::::::::::" + msgType);
			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				String content = requestMap.get("Content");
				
				//用户发送的消息保存到数据库中
				Map<String, Object> message = new HashMap<String, Object>();
				message.put("userid", fromUserName);
				message.put("message", content);
				message.put("createtime", new Date());
				message.put("content", content);
				message.put("contenttype", "1");
				message.put("status", "2");
				basicService.insertMessage(message);
				
				// 创建图文消息
				NewsMessageResp newsMessage = new NewsMessageResp();

				newsMessage.setToUserName(fromUserName);

				newsMessage.setFromUserName(toUserName);

				newsMessage.setCreateTime(new Date().getTime());

				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

				newsMessage.setFuncFlag(0);
				// 单图文消息

				Map<String, Object> paramMap = new HashMap<String, Object>();
				// 文本消息
				paramMap.put("msgtypeKey", msgType);
				// 关键字
				paramMap.put("key", content);
				paramMap.put("ifactive", 1);
				// 检索满足条件的图文列表
//				List<Map<String, Object>> list = getBatteryService()
//						.getWeiXinKeyList(paramMap);
//				if (null != list && list.size() >= 1) {
//					Map<String, Object> tempMap = list.get(0) ;
//					if (null != tempMap) {
//						String respmsgtype = MapUtils.getString(tempMap, "respmsgtype");
//						LOGGER.info("respmsgtype="+respmsgtype);
//						if(Constants.WEIXIN_RESP_TEXT.equals(respmsgtype)){
//							//返回文本指令
//							textMessageResp.setContent(getMapValue(tempMap, "content"));
//							LOGGER.info(getMapValue(tempMap, "content"));
//							respMessage = MessageUtil.textMessageToXml(textMessageResp);
//						}else {
//							// 关键字对应的图文信息
//							Map<String, Object> dataMap = getKeyContent(list, content);
//							//返回图文指令
//							Article article = new Article();
//							article.setTitle(getMapValue(dataMap, "title"));
//							String picUrl = projectPath
//									+ getMapValue(dataMap, "imgurl");
//							LOGGER
//									.info(":::::::::::::::picUrl::::::::::::::"
//											+ picUrl);
//							article.setPicUrl(picUrl);
//							article.setUrl(getMapValue(dataMap, "linkurl"));
//							article.setDescription(getMapValue(dataMap, "content"));
//
//							List<Article> artlist = new ArrayList<Article>();
//							artlist.add(article);
//							newsMessageResp.setArticles(artlist);
//							newsMessageResp.setArticleCount(1);
//							respMessage = MessageUtil
//									.newsMessageToXml(newsMessageResp);
//							LOGGER.info(respMessage);
//						}
//						//============
//					}
//				} else {
//					if(null!=content && content.indexOf("餐厅")>=0){
//						respContent = "感谢您关注触动传媒“食申”频道！我们将陆续推出更多申城的特色美食和创意餐厅，更欢迎各路吃货达人推荐你们爱的餐厅哦，参与推荐的小伙伴们有机会获取触动传媒的精美小礼品！乐享美食，尽在触动传媒“食申”频道";
//					}else {
////						respContent = "回复“充电”，就能获取“出租车内手机充电小教程”哦！";
//						System.out.println("我们未匹配到关键字！");
//						return null ;
//					}
//					textMessageResp.setContent(respContent);
//					respMessage = MessageUtil.textMessageToXml(textMessageResp);
//				}
				return respMessage;

			}
			// 图片消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
                LOGGER.info("++++++++++++++++您发送的是图片消息");
                respContent = "您发送的是图片消息！";
            }
            // 地理位置消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
                LOGGER.info("++++++++++++++++您发送的是地理位置消息！");
                respContent = "您发送的是地理位置消息！";
            }
            // 链接消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
                LOGGER.info("++++++++++++++++您发送的是链接消息！");
                respContent = "您发送的是链接消息！";
            }
            // 音频消息
            else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
                LOGGER.info("++++++++++++++++您发送的是音频消息！");
                respContent = "您发送的是音频消息！";
            }
			// 事件推送
			else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
				// 事件类型
				String eventType = requestMap.get("Event");
				LOGGER.info("eventType::::::::::::::::::::::::::::::::::::::::" + eventType);
				if (eventType.equals(MessageUtil.EVENT_TYPE_SCAN)) {
						//已关注的情况下扫描二维码
						//不更新渠道来源
						//直接弹出欢迎语
						respContent = guanZhuNoQrid();
				}
				
				// 订阅
				else if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
					String eventKey = requestMap.get("EventKey");
					//订阅有两种方式，扫描二维码或直接扫描关注公众号
					String qrid = "";
					if(null!=eventKey && eventKey.contains("qrscene_")){
						//扫描带参二维码进入
						String qrcode = eventKey.substring(8);
						System.out.println(qrcode+":::::::::::::::::::::::扫描带参二维码关注::::::::::::::::::");
						Map<String, Object> temp = new HashMap<String, Object>();
						temp.put("qrcode", qrcode);
//						temp = getWeiXinSetService().getPublicQrcode(temp);
						//关注公众号
						qrid = MapUtils.getString(temp, "qrid") ;
					}else {
						qrid = null ;
					}
					
					//关注公众号
					respContent = guanZhu(fromUserName,qrid);
				}
//				// 取消订阅
//				else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
//					LOGGER.info("进入到取消订阅…………");
//					Map<String, Object> pvMap = new HashMap<String, Object>();
//					pvMap.put("openid", fromUserName);
//					battaryService.cancalMember(pvMap);
//				}
				// 自定义菜单点击事件
				else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
					// 事件KEY值，与创建自定义菜单时指定的KEY值对应
					String eventKey = requestMap.get("EventKey");

					Map<String, Object> paramMap = new HashMap<String, Object>();
					// 自定义菜单点击事件
					paramMap.put("msgtypeKey", eventType);
					// 关键字
					paramMap.put("key", eventKey);
					paramMap.put("ifactive", 1);
					// 检索满足条件的图文列表
//					List<Map<String, Object>> list = getBatteryService()
//							.getTextKeyList(paramMap);
//					if (null != list && list.size() >= 1) {
//						Map<String, Object> dataMap = getKeyContent(list,
//								eventKey);
//						if (null != dataMap) {
//							Article article = new Article();
//							article.setTitle(getMapValue(dataMap, "title"));
//							article.setPicUrl(projectPath
//									+ getMapValue(dataMap, "imgurl"));
//							article.setUrl(getMapValue(dataMap, "linkurl"));
//							article.setDescription(getMapValue(dataMap,
//									"content"));
//
//							List<Article> artlist = new ArrayList<Article>();
//							artlist.add(article);
//							newsMessageResp.setArticles(artlist);
//							newsMessageResp.setArticleCount(1);
//							respMessage = MessageUtil
//									.newsMessageToXml(newsMessageResp);
//							LOGGER.info(respMessage);
//						}
//					}

					return respMessage;
				}else if (MessageUtil.MASSSENDJOBFINISH.equals(eventType.toString())) {
					
					messSendJobFinish(requestMap);
					return null ;
				}else {
					System.out.println("没有找到对应的事件！！");
					return null ;
				}
			}
			
			/*if("请求处理异常，请稍候尝试！".equals(respContent)){
				return null ;
			}*/
			
			textMessageResp.setContent(respContent);
			respMessage = MessageUtil.textMessageToXml(textMessageResp);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return respMessage;
	}
	
	private static String guanZhuNoQrid() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * @function: 群发推送的结果
	 * @datetime:2015-1-26 下午02:00:30
	 * @Author: robin
	 * @param: @param requestMap
	 */
	private static void messSendJobFinish(Map<String, String> requestMap){
		LOGGER.info("messSendJobFinish") ;
		
		//事件推送群发结果
		String msgid = MapUtils.getString(requestMap, "MsgID");
		String Status = MapUtils.getString(requestMap, "Status");
		// 
		String totalCount = MapUtils.getString(requestMap, "TotalCount");
		String sendCount = MapUtils.getString(requestMap, "SentCount");
		String errorCount = MapUtils.getString(requestMap, "ErrorCount");
		//根据消息id，将结果显示到
		Map<String, Object> dataMap = new HashMap<String, Object>();
		dataMap.put("msgid", msgid);
		dataMap.put("totalCount", totalCount);
		dataMap.put("sendcount", sendCount);
		dataMap.put("errorCount", errorCount);
		String sendstatus = "" ;
		if("send success".equals(Status)){
			sendstatus = "3" ;
		}else if ("send fail".equals(Status)) {
			sendstatus = "4" ;
		}
		dataMap.put("sendstatus", sendstatus);
		//微信创建时间
		long createTime = MapUtils.getLongValue(requestMap, "CreateTime");
		dataMap.put("sendtime", FileDeal.formatTimeStamp(createTime));
		
		LOGGER.info(dataMap.toString());
		//将推送的结果存储到数据库中
//		getWeiXinSetService().updateSYNoticeSendByWX(dataMap);
	}
	
	private static String getAreaLike(String name){
		if(null == name || "".equals(name)){
			return null ;
		}
		Map<String, Object> temp = new HashMap<String, Object>();
		temp.put("name", name) ;
//		Map<String, Object> map = getBatteryService().getAreaLike(temp);
//		if(null == map || map.isEmpty()){
//			return null ;
//		}
//		String id = MapUtils.getString(map, "id") ;
//		if(null != id && !"".equals(id)){
//			return id ;
//		}
		return null ;
	}

	/**
	 * xiaoqrobot的主菜单
	 * 
	 * @return
	 */
	public static String getMainMenu() {
		StringBuffer buffer = new StringBuffer();
		buffer.append("您好，我是小q，请回复数字选择服务：").append("\n\n");
		buffer.append("1  天气预报").append("\n");
		buffer.append("2  公交查询").append("\n");
		buffer.append("3  周边搜索").append("\n");
		buffer.append("4  歌曲点播").append("\n");
		buffer.append("5  经典游戏").append("\n");
		buffer.append("6  美女电台").append("\n");
		buffer.append("7  人脸识别").append("\n");
		buffer.append("8  聊天唠嗑").append("\n\n");
		buffer.append("回复“?”显示此帮助菜单");
		buffer.append("\n")
				.append("<a href=\"http://www.baidu.com\">测试超链接</a>");
		return buffer.toString();
	}

	/**
	 * 判断是否是QQ表情
	 * 
	 * @param content
	 * @return
	 */
	public static boolean isQqFace(String content) {
		boolean result = false;

		// 判断QQ表情的正则表达式
		String qqfaceRegex = "/::\\)|/::~|/::B|/::\\||/:8-\\)|/::<|/::$|/::X|/::Z|/::'\\(|/::-\\||/::@|/::P|/::D|/::O|/::\\(|/::\\+|/:--b|/::Q|/::T|/:,@P|/:,@-D|/::d|/:,@o|/::g|/:\\|-\\)|/::!|/::L|/::>|/::,@|/:,@f|/::-S|/:\\?|/:,@x|/:,@@|/::8|/:,@!|/:!!!|/:xx|/:bye|/:wipe|/:dig|/:handclap|/:&-\\(|/:B-\\)|/:<@|/:@>|/::-O|/:>-\\||/:P-\\(|/::'\\||/:X-\\)|/::\\*|/:@x|/:8\\*|/:pd|/:<W>|/:beer|/:basketb|/:oo|/:coffee|/:eat|/:pig|/:rose|/:fade|/:showlove|/:heart|/:break|/:cake|/:li|/:bome|/:kn|/:footb|/:ladybug|/:shit|/:moon|/:sun|/:gift|/:hug|/:strong|/:weak|/:share|/:v|/:@\\)|/:jj|/:@@|/:bad|/:lvu|/:no|/:ok|/:love|/:<L>|/:jump|/:shake|/:<O>|/:circle|/:kotow|/:turn|/:skip|/:oY|/:#-0|/:hiphot|/:kiss|/:<&|/:&>";
		Pattern p = Pattern.compile(qqfaceRegex);
		Matcher m = p.matcher(content);
		if (m.matches()) {
			result = true;
		}
		return result;
	}

	/**
	 * emoji表情转换(hex -> utf-16)
	 * 
	 * @param hexEmoji
	 * @return
	 */
	public static String emoji(int hexEmoji) {
		return String.valueOf(Character.toChars(hexEmoji));
	}

	/**
	 * 处理微信发来的请求
	 * 
	 * @param request
	 * @return
	 */
	public static String processRequestNews(String request) {
		String respMessage = null;
		try {
			// xml请求解析
			Map<String, String> requestMap = MessageUtil.parseXml(request);

			// 发送方帐号（open_id）
			String fromUserName = requestMap.get("FromUserName");
			// 公众帐号
			String toUserName = requestMap.get("ToUserName");
			// 消息类型
			String msgType = requestMap.get("MsgType");

			// 默认回复此文本消息
			TextMessageResp textMessage = new TextMessageResp();
			textMessage.setToUserName(fromUserName);
			textMessage.setFromUserName(toUserName);
			textMessage.setCreateTime(new Date().getTime());
			textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
			// 由于href属性值必须用双引号引起，这与字符串本身的双引号冲突，所以要转义
			textMessage
					.setContent("欢迎访问<a href=\"http://blog.csdn.net/lyq8479\">柳峰的博客</a>!");
			// 将文本消息对象转换成xml字符串
			respMessage = MessageUtil.textMessageToXml(textMessage);

			// 文本消息
			if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {
				// 接收用户发送的文本消息内容
				String content = requestMap.get("Content");

				// 创建图文消息
				NewsMessageResp newsMessage = new NewsMessageResp();
				newsMessage.setToUserName(fromUserName);
				newsMessage.setFromUserName(toUserName);
				newsMessage.setCreateTime(new Date().getTime());
				newsMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_NEWS);

				List<Article> articleList = new ArrayList<Article>();
				// 单图文消息
				if ("1".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					article
							.setDescription("柳峰，80后，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列教程，也希望借此机会认识 更多同行！");
					article
							.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					// 设置图文消息个数
					newsMessage.setArticleCount(articleList.size());
					// 设置图文消息包含的图文集合
					newsMessage.setArticles(articleList);
					// 将图文消息对象转换成xml字符串
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 单图文消息---不含图片
				else if ("2".equals(content)) {
					Article article = new Article();
					article.setTitle("微信公众帐号开发教程Java版");
					// 图文消息中可以使用QQ表情、符号表情
					article
							.setDescription("柳峰，80后，"
									+ emoji(0x1F6B9)
									+ "，微信公众帐号开发经验4个月。为帮助初学者入门，特推出此系列连载教程，也希望借此机会认识更多同行！\n\n目前已推出教程共12篇，包括接口配 置、消息封装、框架搭建、QQ表情发送、符号表情发送等。\n\n后期还计划推出一些实用功能的开发讲解，例如：天气预报、周边搜索、聊天功能等。");
					// 将图片置为空
					article.setPicUrl("");
					article.setUrl("http://blog.csdn.net/lyq8479");
					articleList.add(article);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息
				else if ("3".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程\n引言");
					article1.setDescription("");
					article1
							.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article1
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8937622");

					Article article2 = new Article();
					article2.setTitle("第2篇\n微信公众帐号的类型");
					article2.setDescription("");
					article2
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8941577");

					Article article3 = new Article();
					article3.setTitle("第3篇\n开发模式启用及接口配置");
					article3.setDescription("");
					article3
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article3
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8944988");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息---首条消息不含图片
				else if ("4".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("微信公众帐号开发教程Java版");
					article1.setDescription("");
					// 将图片置为空
					article1.setPicUrl("");
					article1.setUrl("http://blog.csdn.net/lyq8479");

					Article article2 = new Article();
					article2.setTitle("第4篇\n消息及消息处理工具的封装");
					article2.setDescription("");
					article2
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8949088");

					Article article3 = new Article();
					article3.setTitle("第5篇\n各种消息的接收与响应");
					article3.setDescription("");
					article3
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article3
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8952173");

					Article article4 = new Article();
					article4.setTitle("第6篇\n文本消息的内容长度限制揭秘");
					article4.setDescription("");
					article4
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article4
							.setUrl("http://blog.csdn.net/lyq8479/article/details/8967824");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					articleList.add(article4);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
				// 多图文消息---最后一条消息不含图片
				else if ("5".equals(content)) {
					Article article1 = new Article();
					article1.setTitle("第7篇\n文本消息中换行符的使用");
					article1.setDescription("");
					article1
							.setPicUrl("http://0.xiaoqrobot.duapp.com/images/avatar_liufeng.jpg");
					article1
							.setUrl("http://blog.csdn.net/lyq8479/article/details/9141467");

					Article article2 = new Article();
					article2.setTitle("第8篇\n文本消息中使用网页超链接");
					article2.setDescription("");
					article2
							.setPicUrl("http://avatar.csdn.net/1/4/A/1_lyq8479.jpg");
					article2
							.setUrl("http://blog.csdn.net/lyq8479/article/details/9157455");

					Article article3 = new Article();
					article3
							.setTitle("如果觉得文章对你有所帮助，请通过博客留言或关注微信公众帐号xiaoqrobot来支持柳峰！");
					article3.setDescription("");
					// 将图片置为空
					article3.setPicUrl("");
					article3.setUrl("http://blog.csdn.net/lyq8479");

					articleList.add(article1);
					articleList.add(article2);
					articleList.add(article3);
					newsMessage.setArticleCount(articleList.size());
					newsMessage.setArticles(articleList);
					respMessage = MessageUtil.newsMessageToXml(newsMessage);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return respMessage;
	}

	private static String getMapValue(Map<String, Object> map, String key) {
		if (null != map && map.containsKey(key)) {
			Object valueObject = map.get(key);
			return null != valueObject ? valueObject.toString() : "";
		}
		return "";
	}

	public static void main(String[] args) throws URISyntaxException {

		String string = "qrscene_er|" ;
		System.out.println(string.substring(8));
		// String path = new
		// File(CoreService.class.getResource("/").toURI()).getParentFile().getAbsolutePath();
//		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
//				"applicationContext.xml");
//		System.out.println("conttext=" + context);
		//1382694957
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		 String date = sdf.format(new Date(1382694957*1000l));
		 System.out.println(date); 
		 
		 long time=1277106667;
		 SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		 String str=format.format(new Date(time));
		 System.out.println(str);

		 
	}
	
	
	/**
	 * 关注公众号
	 * @datetime:2015-4-11 下午07:55:08
	 * @Author: robin
	 * @return String
	 */
	private static String guanZhu(String fromUserName,String qrid){
			String respContent = "";
			LOGGER.info("进入到订阅…………");
			Map<String, Object> pvMap = new HashMap<String, Object>();
			pvMap.put("openid", fromUserName);
			if(null!=qrid && !"".equals(qrid)){
				pvMap.put("qrid", qrid);
			}
//			getBatteryService().insertMember(pvMap);
//			Map<String, Object> infoMap = WeixinUtil
//					.getUserInfo(fromUserName);
//			LOGGER.info("获取到的粉丝信息="+infoMap);
//			String province = MapUtils.getString(infoMap, "province") ;
//			String city = MapUtils.getString(infoMap, "city") ;
//			LOGGER.info("province="+province+"\tcity="+city+"\n");
//			province = getAreaLike(province) ;
//			city = getAreaLike(city) ;
//			pvMap.put("province", province);
//			pvMap.put("city", city);
//			LOGGER.info("province="+province+"\tcity="+city+"\n");
//			pvMap.put("nickname", infoMap.get("nickname"));
//			LOGGER.info("FileDeal.getSubscribeTime(infoMap)="+FileDeal.getSubscribeTime(infoMap));
//			pvMap.put("gaintime", FileDeal.getSubscribeTime(infoMap));
//			getBatteryService().updateNickName(pvMap);
//			// respContent =
//			// "“触动传媒”欢迎新成员加入！在这里，礼品抽奖、优惠折扣，吃喝玩乐、出行便利、时尚信息，与你一起分享！";
//			Map<String, Object> dataMap = getBatteryService().getHelloMessage(null);
//			if(null!=dataMap && !dataMap.isEmpty()){
//				respContent = MapUtils.getString(dataMap, "content");
//			}else {
//				respContent = "“触动传媒”欢迎新成员加入！在这里，礼品抽奖、优惠折扣，吃喝玩乐、出行便利、时尚信息，与你一起分享！若您的手机正处于电荒状态，回复“充电”，就能获取“出租车内手机充电小教程”哦！";
//			}
//			return respContent ;
//	}
//	
//	//已关注的情况下扫描带参二维码
//	private static String guanZhuNoQrid(){
//		String respContent = "";
//		LOGGER.info("…已关注的情况下扫描带参二维码……");
//		
//		// "“触动传媒”欢迎新成员加入！在这里，礼品抽奖、优惠折扣，吃喝玩乐、出行便利、时尚信息，与你一起分享！";
//		Map<String, Object> dataMap = getBatteryService().getHelloMessage(null);
//		if(null!=dataMap && !dataMap.isEmpty()){
//			respContent = MapUtils.getString(dataMap, "content");
//		}else {
//			respContent = "“触动传媒”欢迎新成员加入！在这里，礼品抽奖、优惠折扣，吃喝玩乐、出行便利、时尚信息，与你一起分享！若您的手机正处于电荒状态，回复“充电”，就能获取“出租车内手机充电小教程”哦！";
//		}
//		return respContent ;
//}
			return respContent;
	}
}
