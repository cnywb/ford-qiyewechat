package com.fute.reserve.message.req;

import com.fute.reserve.message.BaseMessageReq;
/**
 * 文本消息
 * @author zhouqiuming
 *
 */
public class TextMessageReq extends BaseMessageReq{
	private String Content;

	public String getContent() {
		return Content;
	}

	public void setContent(String content) {
		Content = content;
	}

}
