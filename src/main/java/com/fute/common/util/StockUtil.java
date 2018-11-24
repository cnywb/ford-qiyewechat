package com.fute.common.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class StockUtil {
	
	/**
	 * 处理股票代码和
	 * 股票名称对应关系
	 * @return Map<Strirng,String> stockMap
	 * 出现异常返回null
	 * */
	public static Map<String,String>  getStockInfoMap(){
		Map<String,String> stockMap=null;
		//String url_path="http://mnews.fute.com.cn/wap/data/ipad/stock/allStockOfAbHkCode.json";
		String url_path="http://mnews.fute.com.cn/wap/data/ipad/stock/allStockInfo.json";
		URL url;
		try{
			url = new URL(url_path);
			URLConnection uc = url.openConnection();
	        InputStream inputstream = uc.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"utf-8"));
	        String repStr = null;
	        if ((repStr = reader.readLine()) != null) {
	        	stockMap=new HashMap<String,String>();
	            JSONObject jObj = JSONObject.fromObject(repStr);
	            JSONArray jary=jObj.getJSONArray("data");
	            JSONObject obj=null;
	            for (int i=0;i<jary.size();i++) {
	            	obj = jary.getJSONObject(i);
	            	stockMap.put(obj.getString("dm"), obj.getString("mc"));
	            }
	         }
		}catch(Exception e){
			return null;
		}
		return stockMap;
	}
	
	
	public static List<Map<String,String>>  getStockList(){
		List<Map<String,String>> stockList = new ArrayList<Map<String,String>>();
		String url_path="http://mnews.fute.com.cn/wap/data/ipad/stock/allStockInfo.json";
		URL url;
		try{
			url = new URL(url_path);
			URLConnection uc = url.openConnection();
	        InputStream inputstream = uc.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"utf-8"));
	        String repStr = null;
	        if ((repStr = reader.readLine()) != null) {
	        	System.out.println(repStr);
	        	Map<String,String> stockMap=new HashMap<String,String>();
	            JSONObject jObj = JSONObject.fromObject(repStr);
	            JSONArray jary=jObj.getJSONArray("data");
	            JSONObject obj=null;
	            for (int i=0;i<jary.size();i++) {
	            	obj = jary.getJSONObject(i);
	            	stockMap.put("code", obj.getString("dm"));
	            	stockMap.put("name", obj.getString("mc"));
	            	stockList.add(stockMap);
	            }
	         }
		}catch(Exception e){
			return null;
		}
		return stockList;
	}
	
	public static Set<String> extractStockKeywords(String content){
		Set<String> keywordSet= new HashSet<String>();
		Map<String,String> stockMap= getStockInfoMap();
		for(String stcokCode:stockMap.keySet()){
			String stockName= stockMap.get(stcokCode);
			if(content.contains(stockName)){
				keywordSet.add(stockName);
			}
			if(content.contains(stcokCode)){
				keywordSet.add(stcokCode);
			}
		}
		return keywordSet;
	}
	
	public static List<Map<String,String>>  searchStockList(String input){
		Map<String,String> stockMap= getStockInfoMap();
		List<Map<String,String>> stockList = new ArrayList<Map<String,String>>();
		for(String stcokCode:stockMap.keySet()){
			 Map<String,String> map = new HashMap<String, String>();
			 String stockName= stockMap.get(stcokCode);
			 map.put(stcokCode, stockName);
			 if(stcokCode.indexOf(input)!=-1){
				 stockList.add(map);
				 continue;
			 }
			 if(stockName.indexOf(input)!=-1){
				 stockList.add(map);
			 }
		 }
		return stockList;
	}
	public static Map<String,String> getStockInfo(String stockCode){
		if(stockCode==null||stockCode.length()<8){
			return null;
		}
		String dm=stockCode.substring(stockCode.length()-2);
		if("0".equals(dm.substring(0,1)))
			dm=dm.substring(1);
		String jsonPath="http://114.80.157.126:8080/image/stockData/"+stockCode.substring(0,2)+"/"
				+dm+"/"+stockCode+"/"+stockCode+"JYMX.json";
		URL url;
        Map<String,String> stockMap=new HashMap<String,String>();
		try{
			System.out.println(jsonPath);
			url = new URL(jsonPath);
			URLConnection uc = url.openConnection();
	        InputStream inputstream = uc.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"utf-8"));
	        String repStr = "";
	        String jsonstr="";
	        while((jsonstr=reader.readLine())!= null) {
	        	repStr+=jsonstr;
	         }
	        if(repStr!=null&&repStr.length()>1){
	        	repStr=repStr.substring(1,repStr.length()-1);
	            JSONObject jObj = JSONObject.fromObject(repStr);
	            stockMap.put("name", jObj.getString("name"));
	            stockMap.put("code", jObj.getString("code"));
	            stockMap.put("price", jObj.getString("lp"));	
	        }
	        
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return stockMap;
	}
	public static Map<String,String> getHistoryStockInfo(String stockCode,String dateStr){
		if(stockCode==null||stockCode.length()<8){
			return null;
		}
		String dm=stockCode.substring(stockCode.length()-2);
		if("0".equals(dm.substring(0,1)))
			dm=dm.substring(1);
		String jsonPath="http://114.80.157.126:8080/image/stockData/history/"+stockCode.substring(0,2)+"/"
				+dm+"/"+stockCode+"/"+dateStr+"-"+stockCode+"JYMX.json";
		URL url;
        Map<String,String> stockMap=new HashMap<String,String>();
		try{
			url = new URL(jsonPath);
			URLConnection uc = url.openConnection();
	        InputStream inputstream = uc.getInputStream();
	        BufferedReader reader = new BufferedReader(new InputStreamReader(inputstream,"utf-8"));
	        String repStr = "";
	        String jsonstr="";
	        while((jsonstr=reader.readLine())!= null) {
	        	repStr+=jsonstr;
	         }
	        if(repStr!=null&&repStr.length()>1){
	        	repStr=repStr.substring(1,repStr.length()-1);
	            JSONObject jObj = JSONObject.fromObject(repStr);
	            stockMap.put("name", jObj.getString("name"));
	            stockMap.put("code", jObj.getString("code"));
	            stockMap.put("price", jObj.getString("lp"));
	            stockMap.put("zf", jObj.getString("zf"));
	            
	        }
	        
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
		return stockMap;
	}
	public static void main(String[] args) {
//		long start = System.currentTimeMillis();
//		 Map<String,String> stockMap= getStockInfoMap();
//		 String input="夏";
//		 List<Map<String,String>> stockList = new ArrayList<Map<String,String>>();
//		 for(String stcokCode:stockMap.keySet()){
//			 Map<String,String> map = new HashMap<String, String>();
//			 String stockName= stockMap.get(stcokCode);
//			 map.put(stcokCode, stockName);
//			 if(stcokCode.indexOf(input)!=-1){
//				 stockList.add(map);
//				 continue;
//			 }
//			 if(stockName.indexOf(input)!=-1){
//				 stockList.add(map);
//			 }
//		 }
//		 System.out.println(stockList);
//		 System.out.println(System.currentTimeMillis()-start);
		String stockCode="SZ300255";
		String dm=stockCode.substring(stockCode.length()-2);
		System.out.println(dm.substring(0,1));
		//getStockInfo(stockCode);
//		Date date=new Date("2013/08/16");
//		String dateStr=DateUtil.formatDate(date);
//		int day=date.getDay();
//		int da=date.getDate();
//		int month=date.getMonth()+1;
//		String year=dateStr.substring(0,4);
//		
//		System.out.println(day);
//		System.out.println( da);
//		System.out.println(month);
//		System.out.println(year);
		//getStockList();
//		String jsonPath="http://114.80.157.126:8080/image/stockData/"+stockCode.substring(0,2)+"/"
//				+stockCode.substring(stockCode.length()-2)+"/"+stockCode+"/"+stockCode+"JYMX.json";
//		System.out.println(jsonPath);
	}

}
