package com.fute.wechat.service.impl.message;

import com.ford.qiye.model.DtMessage;
import com.ford.qiye.service.BiMessageService;
import com.fute.backer.service.SystemParameterService;
import com.fute.wechat.model.message.ResponseTextMessage;
import com.fute.wechat.service.message.MessageService;
import com.fute.wechat.util.MessageUtil;
import com.fute.wechat.util.SignUtil;
import net.sf.json.JSONObject;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Map;





@Service("messageService")
public class MessageServiceImpl implements MessageService {
	
	
	private static final Logger logger = LoggerFactory.getLogger (MessageServiceImpl.class);
	
	@Resource
	private SystemParameterService systemParameterService;


	@Autowired
	private BiMessageService messageService;
	
	
	  public String checkQySignature(String msg_signature,String timestamp,String nonce,String echostr){
		   String token=systemParameterService.getValueByKey("WECHAT_QY_APP_TOKEN");
		   String corpID=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		   String encodingAESKey=systemParameterService.getValueByKey("WECHAT_QY_AES_KEY");
		   return SignUtil.checkQySignature(msg_signature, timestamp, nonce, echostr, encodingAESKey, token, corpID);
	   }


	@Override
	public String idoProcessQyRequest(String msg_signature, String timestamp,
			String nonce, HttpServletRequest request) {	String respMessage = null;
			   String token=systemParameterService.getValueByKey("WECHAT_QY_APP_TOKEN");
			   String corpID=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
			   String encodingAESKey=systemParameterService.getValueByKey("WECHAT_QY_AES_KEY");
			try {
				// 默认返回的文本消息内容
				String respContent = "";
				String requestXml = IOUtils.toString(request.getInputStream (), "UTF-8");

				logger.info("requestXml:{}",requestXml);

				String decryptRequestXml=SignUtil.decryptMsg(msg_signature, timestamp, nonce, requestXml, encodingAESKey, token, corpID);
				// xml请求解析
				Map<String, String> requestMap = MessageUtil.parseQyXml(decryptRequestXml);

				logger.info("requestMap:{}",requestMap);

				// 发送方帐号（open_id）
				String fromUserName = requestMap.get("FromUserName");
				// 公众帐号
				String toUserName = requestMap.get("ToUserName");
				// 消息类型
				String msgType = requestMap.get("MsgType");

				// 回复文本消息
				ResponseTextMessage textMessage = new ResponseTextMessage();
				textMessage.setToUserName(fromUserName);
				textMessage.setFromUserName(toUserName);
				textMessage.setCreateTime(new Date().getTime());
				textMessage.setMsgType(MessageUtil.RESP_MESSAGE_TYPE_TEXT);
				textMessage.setFuncFlag(0);

				// 文本消息
				if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_TEXT)) {

					respContent = "您发送的是文本消息！";

					String content=requestMap.get ("Content");

					DtMessage dtMessage=new DtMessage ();
					dtMessage.setUserId (fromUserName);
					dtMessage.setCreateTime (new Date());
					dtMessage.setContent (content);
					dtMessage.setContentType (1);
					dtMessage.setStatus (2);
					logger.info ("message:{}",dtMessage);
					messageService.insert (dtMessage);
				}
				// 图片消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_IMAGE)) {
					//downloadQyMedia(request, requestMap, fromUserName);
					respContent = "您发送的是图片消息！";
				}
				// 地理位置消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LOCATION)) {
					respContent = "您发送的是地理位置消息！";
				}
				// 链接消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_LINK)) {
					respContent = "您发送的是链接消息！";
				}
				// 音频消息
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_VOICE)) {
					//downloadQyMedia(request, requestMap, fromUserName);
					respContent = "您发送的是音频消息！";
				}
				// 事件推送
				else if (msgType.equals(MessageUtil.REQ_MESSAGE_TYPE_EVENT)) {
					// 事件类型
					String eventType = requestMap.get("Event");
					// 订阅
					
					if (eventType.equals(MessageUtil.EVENT_TYPE_SUBSCRIBE)) {
						logger.info("企业号关注事件被触发-----------");
						respContent = "谢谢您的关注！";
						String welcome=systemParameterService.getValueByKey("WECHAT_QY_WELCOME_WORDS");
						if(StringUtils.isNotEmpty(welcome)){
							respContent=welcome;
						}
					}
					if (eventType.equals(MessageUtil.EVENT_TYPE_ENTER_AGENT)) {
						respContent = "";
					}
					// 取消订阅
					else if (eventType.equals(MessageUtil.EVENT_TYPE_UNSUBSCRIBE)) {
						logger.info("企业号取消关注事件被触发-----------");
						// TODO 取消订阅后用户再收不到公众号发送的消息，因此不需要回复消息
					}
					// 自定义菜单点击事件
					else if (eventType.equals(MessageUtil.EVENT_TYPE_CLICK)) {
						// TODO 自定义菜单权没有开放，暂不处理该类消息
					}
				}

				textMessage.setContent(respContent);

				respMessage = MessageUtil.textMessageToXml(textMessage);

				logger.info(respMessage);

				respMessage=SignUtil.encryptMsg(timestamp, nonce, respMessage, encodingAESKey, token, corpID);
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			return respMessage;
	}
	
	
	public String sendQyMessage(String msgType,String touser,String safe,String msgContent){
		String agentid=systemParameterService.getValueByKey("WECHAT_QY_AGENT_ID");
		JSONObject jo= new JSONObject();
		jo.put("touser", touser);
		jo.put("msgtype", msgType);
		jo.put("agentid", agentid);
		JSONObject jo1= new JSONObject();
		jo1.put("content", msgContent);
		jo.put("text", jo1);
		jo.put("safe", safe);
		return sendQyMessage(jo.toString());
	}
	
	
	public String sendQyMessage(String json) {
		String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
		return MessageUtil.sendQyMessage(corpId, corpSecret, json);
	}
}
