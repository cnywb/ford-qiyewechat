package com.fute.reserve.message.req;

import com.fute.reserve.message.BaseMessageReq;

public class VoiceMessageReq extends BaseMessageReq {
	private String MediaId;
	private String Format;
	public String getMediaId() {
		return MediaId;
	}
	public void setMediaId(String mediaId) {
		MediaId = mediaId;
	}
	public String getFormat() {
		return Format;
	}
	public void setFormat(String format) {
		Format = format;
	}
	
}
