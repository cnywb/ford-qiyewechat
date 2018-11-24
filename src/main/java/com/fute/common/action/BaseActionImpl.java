package com.fute.common.action;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONObject;

import org.apache.commons.collections.MapUtils;
import org.apache.struts2.ServletActionContext;

import com.fute.common.ui.StringUtil;
import com.fute.common.util.FileDeal;
import com.fute.util.PageHelper;
import com.opensymphony.xwork2.ActionSupport;

public class BaseActionImpl extends ActionSupport implements BaseAction {

	/**
	 * @Fields serialVersionUID: TODO
	 */
	private static final long serialVersionUID = 1L;
	// 返回结果给客户端
	protected String result;

	protected int currentPage = 1;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}
	
	public HttpServletRequest getRequest() {
		return ServletActionContext.getRequest();
	}

	public HttpServletResponse getResponse() {
		return ServletActionContext.getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}
	
	public String getRequestParameterValue(String key){
		if(!StringUtil.isNullOrEmpty(key)){
			return null!=getRequest().getParameter(key) ? getRequest().getParameter(key).toString():"" ;
		}
		return "" ;
	}
	
	public String parseJsonData(Object data){
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("data", data);
		JSONObject jsonObject = JSONObject.fromObject(map) ;
		return jsonObject.toString();
	}
	
	
	/**
	 * @function:将json字符串转成Map
	 * @datetime:2014-10-30 下午08:51:15
	 * @Author: robin
	 * @param: @param json
	 * @return Map<String,Object>
	 */
	public Map<String, Object> parseJsonToMap(String json){
		JSONObject jsonObject = JSONObject.fromObject(json);
		Iterator<String> keyIterator = jsonObject.keys();
		Map<String, Object> map = new HashMap<String, Object>();
		while (keyIterator.hasNext()) {
			String key = keyIterator.next();
			map.put(key, jsonObject.get(key));
		}
		return map ;
	}
	
	/**
	 * @function:Map中是否存在主键值
	 * @datetime:2014-11-9 上午11:38:59
	 * @Author: robin
	 * @param: @param data 需要保存的数据
	 * @param: @param pk 主键列
	 * @return boolean 存在主键对应的列，则表示插入
	 */
	protected boolean hasSavePK(Map<String, Object> data,String pk){
		return data.containsKey(pk) && null!=data.get(pk) && !"".equals(data.get(pk).toString()) ;
	}
	
	
	/**
	 * @function: 检查开始时间和结束时间
	 * @datetime:2015-1-19 下午03:16:27
	 * @Author: robin
	 * @param: @param map
	 * @param: @param startKey
	 * @param: @param endKey
	 * @return void
	 */
	protected void  checkStartEndTime(Map<String, Object> map,String startKey,String endKey) {
		String startValue = MapUtils.getString(map, startKey);
		if(null!=startValue && startValue.length()==10){
			map.put(startKey, startValue + " 00:00:00");
		}
		String endValue = MapUtils.getString(map, endKey);
		if(null!=endValue && endValue.length()==10){
			map.put(endKey, endValue + " 23:59:59");
		}
	}
	
	protected void  formatStartEndTime(Map<String, Object> map,String startKey,String endKey) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		if(null!=map && map.containsKey(startKey) && !"".equals(map.get(startKey))){
			Date startTime = getMapDate(map, startKey) ;
			if(null!=startTime){
				System.out.println("\n$$$$$"+format.format(startTime)+"\n");
				map.put(startKey, format.format(startTime));
			}
		}
		if(null!=map && map.containsKey(endKey) && !"".equals(map.get(endKey))){
			Date endTime = getMapDate(map, endKey);
			if(null!=endTime){
				System.out.println("\n$$$$$$$$$"+format.format(endTime)+"\n");
				map.put(endKey, format.format(endTime));
			}
		}
	}
	
	private Date getMapDate(Map<String, Object> map,String key){
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			if(null==map || map.isEmpty())
				return null ;
			if(null == key || "".equals(key)){
				return null ;
			}
			
			return format.parse(map.get(key).toString());
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null ;
		
	}
	
	
	public static void main(String[] args) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("test", "2014-10-10 10:10:10");
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		try {
			System.out.println(format.parse(map.get("test").toString()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	
	
	protected void initPage(Map<String, Object> paramMap, PageHelper pageHelper) {
		int rowPage = pageHelper.getRowPage(); // 每页显示的记录
//		currentPage = pageHelper.getCurrentPage();
		if (currentPage < 1){
			currentPage = 1;
		}
		if (rowPage < 1)
			rowPage = 1;
		int startnum = (currentPage - 1) * rowPage; // 分页查询开始的序号
		int rownum = rowPage; // 每页显示的条数
		paramMap.put("startnum", startnum);
		paramMap.put("rownum", rownum);
	}
	
	
	protected PageHelper pageHelperStart(Map<String, Object> paramMap) {
		PageHelper pageHelper = new PageHelper(this.getRequest());
		FileDeal.parseSimpleForm(paramMap);
		// 处理分页参数
		initPage(paramMap, pageHelper);
		return pageHelper;
	}
	
	protected void pageHelperEnd(PageHelper pageHelper,int num) {
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		
	}
	
	protected void splitTime(Map<String, Object> map,String key,String starttime,String endtime){
		if(null!=map && null!=key && map.containsKey(key)){
			String valueString = StringUtil.getMapValue(map, key);
			if(valueString.contains("至")){
				String[] valueArray = valueString.split("至");
				int i = 0;
				for (String string : valueArray) {
					string = string.trim();
					valueArray[i] = string ;
					i++;
				}
				if(null!=valueArray && valueArray.length==2){
					map.put(starttime, valueArray[0]+" 00:00:00");
					map.put(endtime, valueArray[1]+" 23:59:59");
				}
			}
		}
	}
	
	
}
