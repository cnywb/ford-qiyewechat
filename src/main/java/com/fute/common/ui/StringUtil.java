package com.fute.common.ui;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.security.MessageDigest;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.InflaterInputStream;

import org.apache.commons.collections.MapUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public final class StringUtil {

	public static List stockList = new ArrayList(); // 存储股票数据
	private static String DOUHAO = ",";
	private static String MULTI_CHRA_SPLIT = "[m]";

	private static Map<String, String> stock_Map = null;

	/**
	 * �Ƿ�Ϊ�ջ���ַ�
	 * 
	 * @param value
	 * @return
	 */
	public static boolean isBlankOrNull(String value) {
		return ((value == null) || (value.trim().length() == 0));
	}

	/**
	 * �滻�ַ�
	 * 
	 * @param source
	 * @param replacestr
	 * @param replacewith
	 * @return
	 */
	public static String replace(String source, String replacestr,
			String replacewith) {
		if (StringUtil.isBlankOrNull(source)) {
			return "";
		}
		return source.replaceAll(replacestr, replacewith);
	}

	/**
	 * ���з��滻 source:ԭ�ַ� replace
	 * 
	 * @param source
	 * @param replace
	 * @return
	 */
	public static String replace(String source, String replace) {
		if (StringUtil.isBlankOrNull(source)) {
			return "";
		}
		return StringUtil.replace(source, "(\r\n|\r|\n|\n\r)", replace);
	}

	public static String replaceSpecialStrnews(String source) {
		if (StringUtil.isBlankOrNull(source)) {
			return "";
		}
		source = StringUtil.replace(source, "\r\n", "<br/>");
		return splitAndFilterString(source.replace("<br/>", "$"));
	}

	/*
	 * ɾ��input�ַ��е�html��ʽ @param input @param length @return
	 */
	public static String splitAndFilterString(String input) {
		if (input == null || input.trim().equals("")) {
			return "";
		}
		// ȥ������htmlԪ��,
		String str = input.replaceAll("\\&[a-zA-Z]{1,10};", "").replaceAll(
				"<[^>]*>", "");
		str = str.replaceAll("[(>)<]", "");
		return str.replace("$", "<br/>").replace("<br/><br/>", "<br/>");
	}

	/**
	 * ��ݵĴ���
	 * 
	 * @param number
	 *            ԭ���
	 * @param bl
	 *            ��Сλ
	 * @param ws
	 *            ��β��
	 * @return
	 */
	public static String opNumberStr(String number, int numfs, int bl, String ws) {
		String opstr = "";
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(bl);
		df.setGroupingUsed(false);
		df.applyPattern(ws);
		double op_num = 0;
		if (!"".equals(number) && null != number) {
			op_num = Double.parseDouble(number) / numfs;
			opstr = df.format(op_num);
		}
		return opstr;
	}

	public static double opNumber(double number, int numfs, int bl, String ws) {
		String opstr = "";
		double rs_num = 0;
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(bl);
		df.setGroupingUsed(false);
		df.applyPattern(ws);
		if (number != 0 && number != 0.0 && number != 0.00) {
			opstr = df.format(number / numfs);
			rs_num = Double.parseDouble(opstr);
		} else {
			rs_num = number;
		}
		return rs_num;
	}

	public static double opNumber(String number, int numfs, int bl, String ws) {
		String opstr = "";
		double rs_num = 0.00;
		DecimalFormat df = (DecimalFormat) NumberFormat.getInstance();
		df.setMaximumFractionDigits(bl);
		df.setGroupingUsed(false);
		df.applyPattern(ws);
		if (number != null) {
			number = number.trim();
			if (!"".equals(number)) {
				rs_num = Double.valueOf(number) / numfs;
				if (rs_num != 0 && rs_num != 0.0 && rs_num != 0.00) {
					opstr = df.format(rs_num);
					rs_num = Double.parseDouble(opstr);
				} else {
					if ("0".equals(ws)) {
						rs_num = 0;
					} else {
						rs_num = 0.0;
					}
				}
			}
		}
		return rs_num;
	}

	/**
	 * ��������URL�����Զ���ļ����������� ADD BY ZZM 20120102
	 * 
	 * @param url
	 * @return
	 */
	public static String getTxtbyUrl(String url) {
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		byte[] output = null;
		DataInputStream fis = null;
		String strResultTxt = ""; // �����ı�
		try {
			fis = (DataInputStream) getTxtFromZlib(url);
			if (fis == null) {
				return null;
			}
			output = decompress(fis);
			out.write(output, 0, output.length);
			strResultTxt = new String(output, "gbk");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return strResultTxt;
	}

	/**
	 * ��ѹas3��ʽ��zlib�ļ� ADD BY ZZM
	 * 
	 * @param is
	 * @return
	 */
	public static byte[] decompress(InputStream is) {
		InflaterInputStream in = new InflaterInputStream(is);
		ByteArrayOutputStream out = new ByteArrayOutputStream(1024);
		try {
			int i = 1024;
			byte[] buf = new byte[i];
			while ((i = in.read(buf, 0, i)) > 0) {
				out.write(buf, 0, i);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return out.toByteArray();
	}

	/**
	 * ����URL ���Զ���ļ������� ADD BY ZZM
	 * 
	 * @param url
	 * @return DataInputStream
	 * @throws IOException
	 */
	public static DataInputStream getTxtFromZlib(String url) {
		DataInputStream in = null;
		URL r;
		try {
			r = new URL(url);
		} catch (MalformedURLException e) {
			return null;
		}
		HttpURLConnection connection;
		try {
			connection = (HttpURLConnection) r.openConnection();
			in = new DataInputStream(connection.getInputStream());
		} catch (IOException e) {
			return null;
		}
		return in;
	}

	/**
	 * ��õ������ֵ�Ascii������"-"���ӳ�һ���ַ�
	 * 
	 * @param cn
	 *            char �����ַ�
	 * @return string ���󷵻� ���ַ�,���򷵻�ascii
	 */
	public static String getCnAscii(char cn) {
		byte[] bytes;
		try {
			bytes = (String.valueOf(cn)).getBytes("gbk");
		} catch (UnsupportedEncodingException e) {
			return "";
		}
		if (bytes == null || bytes.length > 2 || bytes.length <= 0) { // ����
			return "";
		}
		if (bytes.length == 1) { // Ӣ���ַ�
			return new String(bytes);
		}
		if (bytes.length == 2) { // �����ַ�
			int hightByte = 256 + bytes[0];
			int lowByte = 256 + bytes[1];

			String ascii = hightByte + "-" + lowByte;
			return ascii;
		}

		return ""; // ����
	}

	/**
	 * �����ַ��ȫƴ,�Ǻ���ת��Ϊȫƴ,�����ַ����ת��
	 * 
	 * @param cnStr
	 *            String�ַ�
	 * @return String ת����ȫƴ����ַ�
	 */
	public static String getFullSpell(String cnStr) {
		if (StringUtil.isBlankOrNull(cnStr)) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			if (ascii.length() == 0) { // ȡasciiʱ����
				retuBuf.append(chars[i]);
			} else {
				String spell = getSpellByAscii(ascii);
				if (spell == null) {
					retuBuf.append(chars[i]);
				} else {
					retuBuf.append(spell.trim());
				}
			}
		}

		return retuBuf.toString();
	}

	public static String getSpellByAscii(String ascii) {
		if (ascii.indexOf("-") > -1) {
			return (String) CnSpell.spellMap.get(ascii);
		} else {
			return ascii;
		}
	}

	/**
	 * ��ȡ�����ַ����ĸ��ϣ�ÿ������ȡƴ���ĵ�һ���ַ���ɵ�һ���ַ�
	 * 
	 * @param cnStr
	 *            ���ֵ��ַ�
	 * @return ÿ������ƴ���ĵ�һ����ĸ����ɵĺ���,��������"[m]"����� ���硰���С�
	 *         ����ph[m]x
	 */
	public static String getFirstSpell(String cnStr) {
		if (StringUtil.isBlankOrNull(cnStr)) {
			return cnStr;
		}

		char[] chars = cnStr.toCharArray();
		StringBuffer retuBuf = new StringBuffer();
		for (int i = 0, Len = chars.length; i < Len; i++) {
			String ascii = getCnAscii(chars[i]);
			if (ascii.length() == 0) { // ȡasciiʱ����
				retuBuf.append(chars[i]);
			} else {
				String a = getSpellByAscii(ascii);
				if (a == null) {
					retuBuf.append(chars[i]);
					continue;
				}
				String[] pinyinArr = a.split(DOUHAO);
				List<String> firstList = new ArrayList<String>();
				for (String s : pinyinArr) {
					String first = s.substring(0, 1);
					if (!firstList.contains(first)) {
						firstList.add(first);
					}
				}
				for (int j = 1; j <= firstList.size(); j++) {
					retuBuf.append(firstList.get(j - 1).substring(0, 1));
					if (j != firstList.size()) {
						retuBuf.append(MULTI_CHRA_SPLIT);
					}
				}
			}
		}
		return retuBuf.toString();
	}

	/**
	 * ȥ���ַ��е�һЩ��Ƿ�ţ���HTML��׼ת������ " &quot; | < &lt; | > &gt; | �C &ndash;
	 * | add by zzm 20120227
	 * 
	 * @param str
	 * @return
	 */
	public static String tranHtmlSign(String str) {
		String strTmp = str;
		strTmp = strTmp.replace("\"", "&quot;");
		strTmp = strTmp.replace("<", "&lt;");
		strTmp = strTmp.replace(">", "&gt;");
		strTmp = strTmp.replace("�C", "&ndash;");
		return strTmp;
	}

	/**
	 * �滻�ַ��� add by zzm 20120113
	 * 
	 * @param str
	 * @return String
	 */
	private static String filterHtml(String str, String old, String newstr) {
		Pattern pattern = Pattern.compile(old);
		Matcher matcher = pattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		boolean result1 = matcher.find();
		while (result1) {
			matcher.appendReplacement(sb, newstr);
			result1 = matcher.find();
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/**
	 * ȥ���ı��� �� < Ϊ��ͷ�� >Ϊ��β�ı�ǩ add by zzm 20120409
	 * 
	 * @param strTxt
	 * @return
	 */
	public static String getZY(String strTxt) {
		String regxpForHtmlall = "<([^>]*)>"; // ����������<��ͷ��>��β�ı�ǩ
		String zy = "";
		zy = strTxt;
		zy = filterHtml(zy, regxpForHtmlall, ""); // ȥ������HTML��ǩ

		return zy;
	}

	/**
	 * @author frank
	 * @date 2012-04-12
	 * @doc ��ʽ���ַ���ʾ
	 */
	public static String strFormatShow(String formatstr) {
		String opstrFormat = null;
		if (formatstr != null && !"".equals(formatstr.trim())) {
			if (formatstr.contains("\r")) {
				formatstr = formatstr.replace("\r", "<br/>   ");
			} else if (formatstr.contains("\n")) {
				formatstr = formatstr.replace("\n", "<br/>   ");
			} else if (formatstr.contains("\r\n")) {
				formatstr = formatstr.replace("\r\n", "<br/>   ");
			} else if (formatstr.contains("\n\r")) {
				formatstr = formatstr.replace("\n\r", "<br/>   ");
			} else {
				formatstr = formatstr;
			}
			opstrFormat = formatstr;
		}
		return opstrFormat;
	}

	public static String MD5(String s) {
		try {
			byte[] btInput = s.getBytes("utf-8");
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			mdInst.update(btInput);
			byte[] md = mdInst.digest();
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < md.length; i++) {
				int val = ((int) md[i]) & 0xff;
				if (val < 16)
					sb.append("0");
				sb.append(Integer.toHexString(val));
			}
			return sb.toString();
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * @author 韦守勤
	 * @doc 获取股票代码 以及股票名称对应 关系 allStockInfo.json
	 */
	public static Map<String, String> AllStockInfoMap() {
		Map<String, String> stockMap = null;
		String url_path = "http://mnews.fute.com.cn/wap/data/ipad/stock/allStockOfAbHkCode.json";
		URL url;
		try {
			url = new URL(url_path);
			URLConnection uc = url.openConnection();
			InputStream inputstream = uc.getInputStream();
			BufferedReader reader = new BufferedReader(new InputStreamReader(
					inputstream, "utf-8"));
			String repStr = null;
			if ((repStr = reader.readLine()) != null) {
				stockMap = new HashMap<String, String>();
				JSONObject jObj = JSONObject.fromObject(repStr);
				JSONArray jary = jObj.getJSONArray("data");
				JSONObject obj = null;
				for (int i = 0; i < jary.size(); i++) {
					obj = jary.getJSONObject(i);
					stockMap.put(obj.getString("dm"), obj.getString("mc"));
				}
			}
		} catch (Exception e) {
			return null;
		}
		return stockMap;
	}

	/**
	 * @author 韦守勤
	 * @version 1.0 2013-03-08 添加
	 * @doc 处理股票代码和 股票名称对应关系
	 * @return Map<Strirng,String> stockMap 出现异常返回null
	 * @param i
	 *            int 1:获取AB股 2：获取港股 3：获取基金 4：获取板块 0：获取全部
	 */
	public static Map<String, String> StockInfoMap(int i) {
		Map<String, String> rsMap = new HashMap<String, String>();
		if (stock_Map == null || stock_Map.isEmpty()) {
			stock_Map = AllStockInfoMap();
		}
		String qzcode = null;
		if (i == 1) {// AB股
			for (String key : stock_Map.keySet()) {
				qzcode = key.substring(0, 2);
				if (qzcode.equals("SH") || qzcode.equals("SZ")) {
					rsMap.put(key, stock_Map.get(key));
				}
			}
		} else if (i == 2) {// 港股
			for (String key : stock_Map.keySet()) {
				qzcode = key.substring(0, 2);
				if (qzcode.equals("HK")) {
					rsMap.put(key, stock_Map.get(key));
				}
			}
		} else if (i == 3) {// 基金
			for (String key : stock_Map.keySet()) {
				qzcode = key.substring(0, 2);
				if (qzcode.equals("OF")) {
					rsMap.put(key, stock_Map.get(key));
				}
			}
		} else if (i == 4) {// 板块
			for (String key : stock_Map.keySet()) {
				qzcode = key.substring(0, 2);
				if (qzcode.equals("BI")) {
					rsMap.put(key, stock_Map.get(key));
				}
			}
		} else {// 全部
			rsMap = stock_Map;
		}
		return rsMap;
	}

	public static boolean isNullOrEmpty(String value){
		return null==value || "".equals(value) ;
	}
	
	public static String getMapValue(Map<String, Object> data,String key){
		if(null==data){
			return "";
		}
		if(!data.containsKey(key)){
			return "";
		}
		String valueObject = MapUtils.getString(data, key);
		return null != valueObject ? valueObject : "";
	}
	
	
}
