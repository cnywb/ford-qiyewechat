package com.fute.common.util;

import java.awt.Point;
import java.awt.Polygon;
import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;

import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;

public class ELUtil {
	private final static double PI = 3.14159265358979323;// 圆周率
	private final static double R = 6371229; // 地球的半径

	public static void main(String[] args) throws Exception {
		 
 
		System.out.println(convert("121.60661272092484", "31.256208978338623"));


	}

	/**
	 * 判断点是否在多边形内方法
	 * 
	 * @param pointx
	 *            点的纬度
	 * @param pointy
	 *            点的经度
	 * @param polygonXArr
	 *            多边形纬度数组集合 需要与经度数据的大小一样
	 * @param polygonYArr
	 *            多边形度数组集合需要与纬度数组的大小一样
	 * @return
	 */
	public static boolean isInPolygon(double pointx, double pointy,
			double[] polygonXArr, double[] polygonYArr) {
		int cs = 1000000;
		int px = (int) (pointx * cs);
		int py = (int) (pointy * cs);
		int edges = polygonXArr.length;
		if (edges > 0 && edges == polygonYArr.length) {
			int[] polygonXA = new int[edges];
			int[] polygonYA = new int[edges];
			for (int i = 0; i < polygonXArr.length; i++) {
				polygonXA[i] = ((int) (polygonXArr[i] * cs));
			}
			for (int i = 0; i < polygonYArr.length; i++) {
				polygonYA[i] = ((int) (polygonYArr[i] * cs));
			}
			Polygon polygon = new Polygon(polygonXA, polygonYA, edges);
			Point point = new Point(px, py);
			return polygon.contains(point);
		}
		return false;
	}

	/** 根据cid 和lac 来获得经纬度 **/
	public static String getLocation(int CID, int LAC) {
		String result = "";
		try {

//			CID = 0x00d8;
//			LAC = 0x378e;
			URL url = new URL("http://www.google.com/glm/mmap");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStream outStream = conn.getOutputStream();
			DataOutputStream os = new DataOutputStream(outStream);
			os.writeShort(21);
			os.writeLong(0);
			os.writeUTF("ch");
			os.writeUTF("Android");
			os.writeUTF("4.0.3");
			os.writeUTF("cellphone");
			os.writeByte(27);
			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(3);
			os.writeUTF("");
			os.writeInt(CID); // CELL-ID
			os.writeInt(LAC); // LAC
			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(0);
			os.writeInt(0);

			os.flush();
			os.close();
			InputStream in = new BufferedInputStream(conn.getInputStream());
			DataInputStream dis = new DataInputStream(in);
			// Read some prior data
			dis.readShort();
			dis.readByte();
			int errorCode = dis.readInt();
			if (errorCode == 0) {
				double lat = (double) dis.readInt() / 1000000D;
				double lng = (double) dis.readInt() / 1000000D;
				// 读取结果
//				Double lng1 = Double.valueOf(lng).doubleValue() + 0.0111;
//				Double lat1 = Double.valueOf(lat).doubleValue() + 0.0044;
				Double lng1 = Double.valueOf(lng).doubleValue() + 0.0123;
				Double lat1 = Double.valueOf(lat).doubleValue() + 0.0057;
				//113.638753,34.789634 
				result = Double.toString(lng1) + "," + Double.toString(lat1) ;
				dis.readInt();
				dis.readInt();
				dis.readUTF();

			} else
				System.out.println("请求码错误");

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("连接错误");
		}
		return result;
	}

	/** 百度地图纠偏处理 **/
	public static String convert(String x, String y) throws IOException {
		URL url = new URL(
				"http://api.map.baidu.com/ag/coord/convert?from=0&to=4&x=" + x
						+ "&y=" + y);
		URLConnection connection = url.openConnection();
		connection.setDoOutput(true);
		OutputStreamWriter out = new OutputStreamWriter(connection
				.getOutputStream(), "utf-8");
		// remember to clean up
		out.flush();
		out.close();
		// 一旦发送成功，用以下方法就可以得到服务器的回应：
		String sCurrentLine;
		String sTotalString;
		sCurrentLine = "";
		sTotalString = "";
		InputStream l_urlStream;
		String jwd = "";
		l_urlStream = connection.getInputStream();
		BufferedReader l_reader = new BufferedReader(new InputStreamReader(
				l_urlStream));
		while ((sCurrentLine = l_reader.readLine()) != null) {
			if (!sCurrentLine.equals(""))
				sTotalString += sCurrentLine;
		}
		System.out.println(sTotalString);
		sTotalString = sTotalString.substring(1, sTotalString.length() - 1);
		System.out.println(sTotalString);
		String[] results = sTotalString.split("\\,");
		if (results.length == 3) {
			if (results[0].split("\\:")[1].equals("0")) {
				String mapX = results[1].split("\\:")[1];
				String mapY = results[2].split("\\:")[1];
				mapX = mapX.substring(1, mapX.length() - 1);
				mapY = mapY.substring(1, mapY.length() - 1);
				mapX = new String(Base64.decode(mapX));
				mapY = new String(Base64.decode(mapY));
				jwd = jwd + mapX + "," + mapY;
			}
		}
		return jwd;

	}
 

		
		/**
		* 把int转为十六进制，并且格式为8位，前补足0。
		* @param int
		* @return
		*/
		public static String int2hex(int value) {
			String str = Integer.toHexString(value); 
			String strfmt ="00000000";
			return strfmt.substring(0, strfmt.length()-str.length()) + str;
		}
		
		/**
		* 把16进制字符串转换成字节数组
		* @param hex
		* @return
		*/
		public static byte[] hexStringToByte(String hex) {
			int len = (hex.length() / 2);
			byte[] result = new byte[len];
			char[] achar = hex.toCharArray();
			for (int i = 0; i < len; i++) {
				int pos = i * 2;
				result[i] = (byte) (toByte(achar[pos]) << 4 | toByte(achar[pos + 1]));
			}
			return result;
		}

		private static byte toByte(char c) {
			byte b = (byte) "0123456789ABCDEF".indexOf(c);
			return b;
		}

		/**
		* 把字节数组转换成16进制字符串
		* @param bArray
		* @return
		*/
		public static final String bytesToHexString(byte[] bArray) {
			StringBuffer sb = new StringBuffer(bArray.length);
			String sTemp;
			for (int i = 0; i < bArray.length; i++) {
				sTemp = Integer.toHexString(0xFF & bArray[i]);
				if (sTemp.length() < 2)
					sb.append(0);
				sb.append(sTemp.toUpperCase());
			}
			return sb.toString();
		}
		
		/**
		* 发送数据格式化，cid+lac+mnc+mcc 转为byte[]
		* @param int cid, int lac, int mnc, int mcc
		* @return
		*/
		public static byte[] sendDataFormat(int cid, int lac, int mnc, int mcc){
			String string1 = "000E00000000000000000000000000001B0000000000000000000000030000";
			String string2 = "FFFFFFFF00000000";
			
			String retStr = string1 + int2hex(cid) + int2hex(lac) + int2hex(mnc) + int2hex(mcc) + string2;

			return hexStringToByte(retStr.toUpperCase());
		}
		
		/**
		* 接收数据格式化，截取出lat、lon
		* @param byte[]
		* @return
		*/
		public static double[] getDataFormat(byte[] byteArr){
			
			double[] retArr = new double[2];
			
			String resHexStr = bytesToHexString(byteArr);
//			System.out.println(resHexStr);
			
			String latHexStr = resHexStr.substring(14, 22);
			String lonHexStr = resHexStr.substring(22, 30);

			retArr[0] = Integer.parseInt(latHexStr,16) / 1000000.0;
			retArr[1] = Integer.parseInt(lonHexStr,16) / 1000000.0;

			return retArr;
		}
		
		/**
		* 获取lat、lon总入口
		* @param int cid, int lac, int mnc, int mcc
		* @return
		*/
		public static double[] getLatLon(int cid, int lac, int mnc, int mcc) throws Exception {
//			int cid = 20465;
//			int lac = 495;
//			int mnc = 3;
//			int mcc = 262;
			
			double[] retArr = new double[2];
			
			String urlAddr = "http://www.google.com/glm/mmap";
			URL url = new URL("http://www.google.com/glm/mmap");
			byte[] postByteArr = sendDataFormat(cid, lac, mnc, mcc);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setDoOutput(true);
			OutputStream outStream = conn.getOutputStream();
			DataOutputStream os = new DataOutputStream(outStream);

			os.write(postByteArr);
			os.flush();
			os.close();
			InputStream in = new BufferedInputStream(conn.getInputStream());
			ByteArrayOutputStream swapStream = new ByteArrayOutputStream();  
	        byte[] buff = new byte[100];  
	        int rc = 0;  
	        while ((rc = in.read(buff, 0, 100)) > 0) {  
	            swapStream.write(buff, 0, rc);  
	        }  
	        byte[] in2b = swapStream.toByteArray();  
			
			
			
			//SendPostMessage sendObj = new SendPostMessage();
			
				//byte[] res = sendObj.send(urlAddr, postByteArr);
				retArr = getDataFormat(in2b);
			return retArr;
		}
		



}
