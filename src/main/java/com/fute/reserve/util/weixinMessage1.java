package com.fute.reserve.util;

import com.fute.reserve.api.HttpRequest;
import net.sf.json.JSONObject;

import java.util.Map;

public class weixinMessage1 {
	
	WeiXinCenterProxy wx=new WeiXinCenterProxy();
	private  String Token_id=wx.getAccessToken();
	/**
	 * 
	 *添加员工
	 */
	public void insertEmployee(String userid,String name,String department,String position,
							   String mobile,String gender,String email,String weixinid,
							   String avatar_mediaid){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("userid", userid);
		json.put("name", name);
		json.put("department", department);
		json.put("mobile", mobile);
		json.put("position",  position);
		json.put("gender", gender);
		json.put("email", email);
		json.put("weixinid", weixinid);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject);
	}
	/**
	 * 
	 *修改员工 
	 */
	public void updateEmployee(String userid,String name,String department,String position,String mobile,String gender,String email,String weixinid,String avatar_mediaid){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("userid", userid);
		json.put("name", name);
		json.put("department", department);
		json.put("mobile", mobile);
		json.put("position",  position);
		json.put("gender", gender);
		json.put("email", email);
		json.put("weixinid", weixinid);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject.toString());
	}
	/**
	 * 删除员工
	 *
	 */
	public void deleteEmployee(String id){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token="+Token_id+"&userid="+id;
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", null);
		System.out.println(jsonObject.toString());
	}
	/**
	 * 
	 *添加部门
	 */
	public Map<String, Object> insertDepart(String name,String parentid,String order,Map<String, Object> data){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("name", name);
		json.put("parentid", parentid);
		json.put("order", order);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject.toString());
		//需得到此次部门自增ID
		data.put("id", jsonObject.get("id"));
		return data;
	}
	/**
	 * 
	 *修改部门 
	 */
	public void updateDepart(String id,String name,String order){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("name", name);
		json.put("order", order);
		json.put("id", id);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject.toString());
	}
	/**
	 * 
	 *删除部门 
	 */
	public void deleteDepart(String id){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+Token_id+"&id="+id;
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", null);
		System.out.println(jsonObject.toString());
	}
	/**
	 * 
	 *添加标签
	 */
	public Map<String, Object> insertMark(String tagname,Map<String, Object> data){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("tagname", tagname);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject.toString());
		//需得到此次标签自增ID
		data.put("tagid", jsonObject.get("tagid"));
		return data;
	}
	/**
	 * 
	 *修改标签 
	 */
	public void updateMark(String tagname,String markid){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token="+Token_id;
		JSONObject json=new JSONObject();
		json.put("tagid", markid);
		json.put("tagname", tagname);
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
		System.out.println(jsonObject.toString());
	}
	/**
	 * 
	 *删除标签 
	 */
	public void deleteMark(String id){
		String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token="+Token_id+"&tagid="+id;
		JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", null);
		System.out.println(jsonObject.toString());
	}
}
