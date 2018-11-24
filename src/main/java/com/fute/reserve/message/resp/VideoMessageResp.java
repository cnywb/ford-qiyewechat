package com.fute.reserve.message.resp;

import com.fute.reserve.message.BaseMessageResp;

public class VideoMessageResp extends BaseMessageResp {
	private Video Video;

	public Video getVideo() {
		return Video;
	}

	public void setVideo(Video video) {
		Video = video;
	}
}
