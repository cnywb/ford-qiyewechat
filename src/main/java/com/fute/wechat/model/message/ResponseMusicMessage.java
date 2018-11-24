package com.fute.wechat.model.message;
import com.fute.wechat.model.message.Music;



/**
 * 音乐消息
 * 
 * @author 
 * @date
 */
public class ResponseMusicMessage extends ResponseBaseMessage {
	// 音乐
	private Music Music;

	public Music getMusic() {
		return Music;
	}

	public void setMusic(Music music) {
		Music = music;
	}
}
