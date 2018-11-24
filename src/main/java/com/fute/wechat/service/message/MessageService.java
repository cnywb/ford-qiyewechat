package com.fute.wechat.service.message;

import javax.servlet.http.HttpServletRequest;

public interface MessageService {

	  String checkQySignature(String msg_signature, String timestamp, String nonce, String echostr);

	  String idoProcessQyRequest(String msg_signature, String timestamp, String nonce, HttpServletRequest request);

	  String sendQyMessage(String json) ;

	  String sendQyMessage(String msgType, String touser, String safe, String msgContent);
}
