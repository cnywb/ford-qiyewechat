package com.fute.common.util;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * @ClassName:UploadFileUtil.java
 * @ClassPath:com.fute.common.util
 * @Desciption:上传文件工具类
 * @Author: robin
 * @Date: 2014-10-18 上午11:35:50
 * 
 */
public class UploadFileUtil {
	
	
	/**
	 * @function:获取文件后缀
	 * @datetime:2014-10-18 上午11:39:57
	 * @Author: robin
	 * @param: @param fileName 文件名称
	 * @return String 文件后缀名
	 */
	private static String getFileExtension(String fileName){
		if(null!=fileName && !"".equals(fileName)){
			return fileName.substring(fileName.lastIndexOf("."));
		}
		return "" ;
	}
	
	/**
	 * @function:上传文件
	 * @datetime:2014-10-18 上午11:37:29
	 * @Author: robin
	 * @param: @param src 需要上传的文件
	 * @param: @param fileName 文件名称
	 * @param: @param url 文件需要上传的url地址
	 * @return String 上传后的文件路径
	 */
	public static String copyFile(File src,String fileName,String webRootPath,String url) {
		int BUFFER_SIZE = 16 * 1024;
		InputStream in = null;
		OutputStream out = null;
		String extension = getFileExtension(fileName);
		
		String temp = url + new Random().nextLong() + extension ;
		try {
			in = new BufferedInputStream(new FileInputStream(src), BUFFER_SIZE);
			out = new FileOutputStream(webRootPath+temp);
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}
	
	// 压缩图片的宽高
	private static final int IMG_COMPRESS_WIDTH = 400;
	private static final int IMG_COMPRESS_HEIGHT = 400;
	

	/**
	 * @function:上传图片后，压缩图片
	 * @datetime:2014-11-21 下午12:43:02
	 * @Author: robin
	 * @param: @param src
	 * @param: @param fileName
	 * @param: @param webRootPath
	 * @param: @param url
	 * @return String
	 */
	public static String copyCompressFile(File src,String fileName,String webRootPath,String url) {
		InputStream in = null;
		OutputStream out = null;
		String extension = getFileExtension(fileName);
		
		String temp = url + new Random().nextLong() + extension ;
		try {
			temp = parsePNGImage(temp);
			//对图片进行压缩
			ImgCompress imgCom = new ImgCompress(src);
			imgCom.resizeFix(IMG_COMPRESS_WIDTH, IMG_COMPRESS_HEIGHT, webRootPath + temp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}
	
	
	/**
	 * @function:上传图片后，压缩图片
	 * @datetime:2014-11-21 下午12:43:02
	 * @Author: robin
	 * @param: @param src
	 * @param: @param fileName
	 * @param: @param webRootPath
	 * @param: @param url
	 * @return String
	 */
	public static String copyResolutionFile(File src,String fileName,String webRootPath,String url,int width,int height) {
		InputStream in = null;
		OutputStream out = null;
		String extension = getFileExtension(fileName);
		
		String temp = url + new Random().nextLong() + extension;
		try {
			temp = parsePNGImage(temp);
			//对图片进行压缩
			ImgCompress imgCom = new ImgCompress(src);
			imgCom.resize(width, height, webRootPath + temp);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return temp;
	}
	
	private static String parsePNGImage(String sourcePath) {
		return sourcePath.substring(0, sourcePath.lastIndexOf(".")) + ".png";
	}
	
	/**
	 * @function:上传文件
	 * @datetime:2014-10-18 上午11:37:29
	 * @Author: robin
	 * @param: @param src 需要上传的文件
	 * @param: @param fileName 文件名称
	 * @param: @param url 文件需要上传的url地址
	 * @return String 上传后的文件路径
	 */
	public static Map<String, Object> copyFile(InputStream in,String fileName,String webRootPath,String url) {
		int BUFFER_SIZE = 16 * 1024;
		OutputStream out = null;
		String extension = "";
		if(null==fileName || "".equals(fileName)){
			extension = ".png";
		}else {
			extension = getFileExtension(fileName);
		}
		
		String temp = url + new Random().nextLong() + extension ;
		try {
			out = new FileOutputStream(webRootPath+temp);
			
			byte[] buffer = new byte[BUFFER_SIZE];
			int len = 0;
			//总数据大小
			int total = 0;
			while ((len = in.read(buffer)) > 0) {
				out.write(buffer, 0, len);
				total += len;
			}
			        /* int b =0; 
			         int total = 0 ;
			         while(b!= -1) 
			         { 
			             in.available(); 
			            b = in.read();
			            total += b ;
			             if(b!= -1) 
			                out.write(b);
			        } 
			        out.close(); */
			
			
			System.out.println("总文件大小="+total);
			//验证是否符合大小的微信大小的限制
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("filePath", temp) ;
			map.put("fileSize", total) ;
			
			return map ;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (null != in) {
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if (null != out) {
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
}