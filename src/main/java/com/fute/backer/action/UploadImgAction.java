package com.fute.backer.action;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletInputStream;

import org.apache.commons.collections.MapUtils;
import org.apache.log4j.Logger;

import com.fute.common.action.BaseActionImpl;
import com.fute.common.util.UploadFileUtil;

/**
 * @ClassName:UploadImgAction.java
 * @ClassPath:com.fute.backer.action
 * @Desciption:上传图片
 * @Author: robin
 * @Date: 2014-11-5 上午10:47:18 
 * 
 */
public class UploadImgAction extends BaseActionImpl {

	//图片（image）: 1M，支持JPG格式
	private static final int WEIXIN_UPLOAD_IMAGE = 1 * 1024 * 1024 ;
	//缩略图（thumb）：64KB，支持JPG格式
	private static final int WEIXIN_UPLOAD_THUMB = 64 * 1024 ;
	
	private static Logger LOG = Logger.getLogger(UploadImgAction.class);
	private static final long serialVersionUID = 1L;

	private File imgurl;
	private String imgurlFileName;

	public File getImgurl() {
		return imgurl;
	}

	public void setImgurl(File imgurl) {
		this.imgurl = imgurl;
	}

	public String getImgurlFileName() {
		return imgurlFileName;
	}

	public void setImgurlFileName(String imgurlFileName) {
		this.imgurlFileName = imgurlFileName;
	}

	private String uploadid;
	
	
	
	public String getUploadid() {
		return uploadid;
	}

	public void setUploadid(String uploadid) {
		this.uploadid = uploadid;
	}

	//多文件上传
	public String uploadMultiImgurl() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/activity/upload/images/";
			String targetPath = UploadFileUtil.copyFile(imgurl, imgurlFileName,
					realPath, path);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
			getRequest().setAttribute("uploadid", uploadid);
			System.out.println(targetPath);
		}
		return "showImgMulti";
	}
	
	public String uploadImgurl() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/upload/product/";
			String targetPath = UploadFileUtil.copyFile(imgurl, imgurlFileName,
					realPath, path);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
			
			System.out.println(targetPath);
		}
		return "showImg";
	}

	public String uploadImgurl2() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/activity/upload/images/";
			String targetPath = UploadFileUtil.copyFile(imgurl, imgurlFileName,
					realPath, path);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
			System.out.println(targetPath);
		}
		return "showImg2";
	}
	
	//素材管理中的图片上传
	//这里的图片需要进行图片的压缩
	public String uploadBannerImgurl() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/activity/upload/images/";
			String targetPath = UploadFileUtil.copyCompressFile(imgurl, imgurlFileName,
					realPath, path);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
			System.out.println(targetPath);
		}
		return "showImg";
	}

	//通过flash上传图片
	public String uploadImgFlash() throws IOException {
		// 上传图片
		String realPath = getRequest().getRealPath("/");
		String path = "/upload/images/";
		
		ServletInputStream servletInputStream = getRequest().getInputStream() ;
		servletInputStream.available();
		Map<String, Object> map = UploadFileUtil.copyFile(getRequest().getInputStream(), imgurlFileName,
				realPath, path);
		//
		if(null == map || map.isEmpty()){
			this.result = "" ;
			return "returnJson" ;
		}
		String targetPath = MapUtils.getString(map, "filePath") ;
		Integer fileSize = MapUtils.getInteger(map, "fileSize") ;
		File file = new File(getRequest().getRealPath("/")+targetPath);
		System.out.println(file.length());
		/*if(fileSize >= WEIXIN_UPLOAD_IMAGE){
			//超过图片的最大限制1M
			this.result = "sizeout";
			return "returnJson"; 
		}*/
		
		// 返回图片地址
		getRequest().setAttribute("imgPath", targetPath);
		System.out.println(targetPath);
		this.result = targetPath;
		return "returnJson";
	}
	
	//通过flash上传图片 = （微信图文的缩略图）
	public String uploadImgThumbFlash() throws IOException {
		// 上传图片
		String realPath = getRequest().getRealPath("/");
		String path = "/upload/images/";
		Map<String, Object> map = UploadFileUtil.copyFile(getRequest().getInputStream(), imgurlFileName,
				realPath, path);
		//
		if(null == map || map.isEmpty()){
			this.result = "" ;
			return "returnJson" ;
		}
		String targetPath = MapUtils.getString(map, "filePath") ;
		Integer fileSize = MapUtils.getInteger(map, "fileSize") ;
		/*if(fileSize >= WEIXIN_UPLOAD_THUMB){
			//超过图片的最大限制64K
			this.result = "thumbsizeout";
			return "returnJson"; 
		}*/
		
		// 返回图片地址
		getRequest().setAttribute("imgPath", targetPath);
		System.out.println(targetPath);
		this.result = targetPath;
		return "returnJson";
	}
	
}