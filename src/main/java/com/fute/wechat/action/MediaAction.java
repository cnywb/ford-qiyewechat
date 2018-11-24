package com.fute.wechat.action;

import com.fute.common.constant.WebConstant;
import com.fute.util.DateUtils;
import com.fute.util.PropUtils;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.fileupload.util.Streams;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.json.simple.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.*;






@Scope("prototype")
@Controller
@ParentPackage("struts-default")
@Namespace("/wechat/media")
public class MediaAction {
	
	private File[] imgFile;
	private String[] imgFileFileName; //文件名称
    private String[] imgFileContentType; //文件类型

	private String dir;
	private String imgWidth;
	private String imgHeight;
	private String align;
	private String imgTitle;
	private String localUrl;
	
	HttpServletResponse response = ServletActionContext.getResponse();  
	
	HttpServletRequest request=ServletActionContext.getRequest();
	
	@Action(value = "uploadQyMedia")
	public void uploadQyMedia() {
	  uploadQyMedia(imgFile, dir, imgWidth, imgHeight, align, imgTitle, localUrl, response, request);
	}
	
	
	public void uploadQyMedia(File[] imgFile,String dir,String imgWidth,String imgHeight,String align,String imgTitle,String localUrl,HttpServletResponse response,HttpServletRequest request){
		 
		try{
		
		Map<String, Object> userinfo=(Map<String, Object>) ActionContext.getContext().getSession().get(WebConstant.USER_SESSION_KEY);
			
			
		String userId=userinfo.get("userid").toString();
		
      
		String savePath = request.getSession().getServletContext().getRealPath("/") + PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";

		//文件保存目录URL
		String saveUrl  = request.getContextPath()+PropUtils.getPropertyValue("editor.properties","attached_save_path")+"/";

		//定义允许上传的文件扩展名
		HashMap<String, String> extMap = new HashMap<String, String>();
		extMap.put("image", "gif,jpg,jpeg,png,bmp");
		extMap.put("voice","amr");
		extMap.put("video","mp4");
		extMap.put("file", "doc,docx,xls,xlsx,ppt,htm,html,txt,zip,rar,gz,bz2");

		//最大文件大小
		long maxSize = 1000000;

		response.setContentType("text/html; charset=UTF-8");

		if(!ServletFileUpload.isMultipartContent(request)){
			try {
				response.getWriter().println(getError("请选择文件。"));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return;
		}
		//检查目录
		File uploadDir = new File(savePath);
		if(!uploadDir.isDirectory()){
				response.getWriter().println(getError("上传目录不存在。"));
				return;
		}
		//检查目录写权限
		if(!uploadDir.canWrite()){
			response.getWriter().println(getError("上传目录没有写权限。"));
			return;
		}
		if (dir == null) {
			dir = "image";
		}
		if(!extMap.containsKey(dir)){
			response.getWriter().println(getError("目录名不正确。"));
			return;
		}
		//创建文件夹
		savePath += dir +"/"+userId+"/";
		saveUrl += dir +"/"+userId+"/";
		File saveDirFile = new File(savePath);
		if (!saveDirFile.exists()) {
			saveDirFile.mkdirs();
		}
		String ymd = DateUtils.format(new Date(),DateUtils.FORMAT_DATE_YYYYMMDD);
		savePath += ymd + "/";
		saveUrl += ymd + "/";
		File dirFile = new File(savePath);
		if (!dirFile.exists()) {
			dirFile.mkdirs();
		}
				BufferedInputStream bis = null;      
				BufferedOutputStream bos = null; 
				for(int i = 0,size = imgFile.length;i<size;i++){      
					File file = imgFile[i];      
				    try { 
	
				    	String uploadFileName=imgFileFileName[i];
				    	if(!checkFileType(uploadFileName, extMap.get(dir))){
				    		response.getWriter().print(getError("不允许上传该文件类型！"));
				    		return;
				    	}
				    	String fileFullName =DateUtils.format(new Date(), DateUtils.FORMAT_DATE_TIME_YYYYMMDDHHMMSS)+"_"+ new Random().nextInt(1000)+"_";
				    	FileInputStream fileInputStream=new FileInputStream(file);
				    	bis = new BufferedInputStream(fileInputStream); 
				       	String fileType=uploadFileName.substring(uploadFileName.lastIndexOf(".") + 1,uploadFileName.length()).toLowerCase();
				        String newFileName=fileFullName+"."+fileType;
				        bos = new BufferedOutputStream(new FileOutputStream(new File(savePath,newFileName)));      
				        Streams.copy(bis, bos, true);  
				        JSONObject obj = new JSONObject();
						obj.put("error",0);
						obj.put("url",saveUrl+newFileName);
						response.getWriter().println(obj.toJSONString());
					    } catch (Exception e) {      
				    	response.getWriter().print(getError("上传出错！"));
				        e.printStackTrace();      
				    }          
				}
        } catch (Exception e) {      
	    	
	        e.printStackTrace(); 
        }
	}
	
	
	
	public class NameComparator implements Comparator {
		@SuppressWarnings("rawtypes")
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filename")).compareTo((String)hashB.get("filename"));
			}
		}
	}
	public class SizeComparator implements Comparator {
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				if (((Long)hashA.get("filesize")) > ((Long)hashB.get("filesize"))) {
					return 1;
				} else if (((Long)hashA.get("filesize")) < ((Long)hashB.get("filesize"))) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
	public class TypeComparator implements Comparator {
		@SuppressWarnings("rawtypes")
		public int compare(Object a, Object b) {
			Hashtable hashA = (Hashtable)a;
			Hashtable hashB = (Hashtable)b;
			if (((Boolean)hashA.get("is_dir")) && !((Boolean)hashB.get("is_dir"))) {
				return -1;
			} else if (!((Boolean)hashA.get("is_dir")) && ((Boolean)hashB.get("is_dir"))) {
				return 1;
			} else {
				return ((String)hashA.get("filetype")).compareTo((String)hashB.get("filetype"));
			}
		}
	}


	
	@SuppressWarnings("unchecked")
	private String getError(String message) {
		JSONObject obj = new JSONObject();
		obj.put("error", 1);
		obj.put("message", message);
		return obj.toJSONString();
	}
	
	private boolean checkFileType(String fileName,String authFileType){
		String fileType=fileName.substring(fileName.lastIndexOf(".") + 1,fileName.length()).toLowerCase();
		if(authFileType.indexOf(fileType)==-1){
			return false;
		}
		return true;
	}


	


	public File[] getImgFile() {
		return imgFile;
	}


	public void setImgFile(File[] imgFile) {
		this.imgFile = imgFile;
	}


	public String getDir() {
		return dir;
	}


	public void setDir(String dir) {
		this.dir = dir;
	}


	public String getImgWidth() {
		return imgWidth;
	}


	public void setImgWidth(String imgWidth) {
		this.imgWidth = imgWidth;
	}


	public String getImgHeight() {
		return imgHeight;
	}


	public void setImgHeight(String imgHeight) {
		this.imgHeight = imgHeight;
	}


	public String getAlign() {
		return align;
	}


	public void setAlign(String align) {
		this.align = align;
	}


	public String getImgTitle() {
		return imgTitle;
	}


	public void setImgTitle(String imgTitle) {
		this.imgTitle = imgTitle;
	}


	public String getLocalUrl() {
		return localUrl;
	}


	public void setLocalUrl(String localUrl) {
		this.localUrl = localUrl;
	}


	public String[] getImgFileFileName() {
		return imgFileFileName;
	}


	public void setImgFileFileName(String[] imgFileFileName) {
		this.imgFileFileName = imgFileFileName;
	}


	public String[] getImgFileContentType() {
		return imgFileContentType;
	}


	public void setImgFileContentType(String[] imgFileContentType) {
		this.imgFileContentType = imgFileContentType;
	}


	



}
