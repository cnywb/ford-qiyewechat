package com.fute.wechat.util;


import java.io.InputStream;

import org.springframework.web.multipart.MultipartFile;


public class MediaUtil {

		public static String qy_media_upload_url="https://qyapi.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=MEDIA_TYPE";
		
		public static String qy_media_download_url="https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		
        public static String media_upload_url="http://file.api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=MEDIA_TYPE";
		
		public static String media_download_url="http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";
		
		
		
		public static String uploadMedia(String accessToken,MultipartFile file,String mediaType){
			String url = media_upload_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_TYPE",mediaType);
			String json = HttpConnectionUtil.uploadMedia(url, file);
			return json;
	    }
		
		public static String uploadQyMedia(String accessToken,InputStream inputStream,String contentType,String fileName,String mediaType){
			String url = qy_media_upload_url.replace("ACCESS_TOKEN",accessToken).replace("MEDIA_TYPE",mediaType);
			String json = HttpConnectionUtil.uploadQyMedia(url, inputStream, contentType, fileName);
			return json;
	    }
		
		 public static String downloadMedia(String accessToken,String savePath,String mediaId){
				String url = media_download_url.replace("ACCESS_TOKEN",accessToken).replace("MEDIA_ID",mediaId);
				String retVal = HttpConnectionUtil.downloadMedia( url, mediaId, savePath);
				return retVal;
		 }
		 public static String downloadQyMedia(String accessToken,String savePath,String mediaId){
				String url = qy_media_download_url.replace("ACCESS_TOKEN", accessToken).replace("MEDIA_ID",mediaId);
				String retVal = HttpConnectionUtil.downloadQyMedia(url, mediaId, savePath);
				return retVal;
		 }
}
