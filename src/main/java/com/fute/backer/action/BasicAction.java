package com.fute.backer.action;

import com.fute.backer.service.BasicService;
import com.fute.backer.service.SystemService;
import com.fute.common.action.BaseActionImpl;
import com.fute.common.util.FileDeal;
import com.fute.common.util.Md5Util;
import com.fute.common.util.UploadFileUtil;
import com.fute.reserve.util.weixinMessage1;
import com.fute.util.PageHelper;
import com.fute.wechat.service.message.MessageService;
import com.opensymphony.xwork2.ActionContext;
import jxl.Cell;
import jxl.Sheet;
import jxl.Workbook;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.*;

public class BasicAction extends BaseActionImpl {
	private BasicService basicService;
	private SystemService systemService;
	private Map<String, Object> paramMap=new HashMap<String, Object>();
	private List<Map<String, Object>> dataList=new ArrayList<Map<String,Object>>();
	private List<Map<String, Object>> departList;
	private List<Map<String, Object>> marklist;
	private List<Map<String, Object>> dictTypeList;
	private List<String> markidlist;
	private List<String> roleidlist;
	private List<String> ids;
	private List<Map<String, Object>> rolelist;
	private List<Map<String, Object>> empList;
	private String state;
	@Resource
	private MessageService messageService;
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}

	public List<Map<String, Object>> getDictTypeList() {
		return dictTypeList;
	}

	public void setDictTypeList(List<Map<String, Object>> dictTypeList) {
		this.dictTypeList = dictTypeList;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}

	public List<Map<String, Object>> getEmpList() {
		return empList;
	}

	public void setEmpList(List<Map<String, Object>> empList) {
		this.empList = empList;
	}
	weixinMessage1 wx=new weixinMessage1();
	
	public List<String> getRoleidlist() {
		return roleidlist;
	}

	public void setRoleidlist(List<String> roleidlist) {
		this.roleidlist = roleidlist;
	}

	public List<Map<String, Object>> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Map<String, Object>> rolelist) {
		this.rolelist = rolelist;
	}

	public List<String> getMarkidlist() {
		return markidlist;
	}

	public void setMarkidlist(List<String> markidlist) {
		this.markidlist = markidlist;
	}

	public List<Map<String, Object>> getMarklist() {
		return marklist;
	}

	public void setMarklist(List<Map<String, Object>> marklist) {
		this.marklist = marklist;
	}

	public List<Map<String, Object>> getDepartList() {
		return departList;
	}

	public void setDepartList(List<Map<String, Object>> departList) {
		this.departList = departList;
	}
	private Map<String, Object> pvMap=new HashMap<String, Object>();
	
	public Map<String, Object> getPvMap() {
		return pvMap;
	}

	public void setPvMap(Map<String, Object> pvMap) {
		this.pvMap = pvMap;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public BasicService getBasicService() {
		return basicService;
	}

	public void setBasicService(BasicService basicService) {
		this.basicService = basicService;
	}
	/**
	 * 员工信息
	 * @return
	 */
	public String getEmployeeList(){
		FileDeal.parseSimpleForm(pvMap);
		Map<String, Object> map=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
		pvMap.put("roleid", map.get("roleid"));
		PageHelper pageHelper = new PageHelper(this.getRequest());
		departList=this.basicService.getMyDepartList(map);
		// 处理分页参数
		initPage(pvMap, pageHelper);
		pvMap.put("isagency", "0");
		if(pvMap!=null){
			if(!pvMap.containsKey("departid")){
				pvMap.put("departid",map.get("departid"));
			}
		}
		dataList = this.basicService.getEmployeeList(pvMap);
		int num = this.basicService.getEmployeeListNum(pvMap);
		marklist=this.basicService.getMarkListAll();
		rolelist=this.basicService.getRoleListAll(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		HttpServletRequest request=ServletActionContext.getRequest();
		request.getSession().setAttribute("roleid", map.get("roleid")+"");
		System.out.println("state+++++++++++++++++++++++++++"+state);
		return "getEmployeeList";
	}
	public String editEmployee(){
		FileDeal.parseSimpleForm(paramMap);
		
		if(paramMap.get("id")!=null && !paramMap.get("id").equals("")){
			wx.updateEmployee(paramMap.get("id")+"",paramMap.get("realname")+"",paramMap.get("departid")+"", paramMap.get("position")+"", paramMap.get("phone")+"", paramMap.get("sex")+"", paramMap.get("email")+"", paramMap.get("wxnum")+"", "");
			this.basicService.updateEmployee(paramMap);
			
		}
		else {
			Map<String, Object> phonemap=new HashMap<String, Object>();
			if(!paramMap.get("phone").equals("") && paramMap.get("phone")!=null){
				phonemap=this.basicService.getEmployee(paramMap);
			}
			if(phonemap==null || phonemap.size()==0){
				paramMap.put("id", FileDeal.getUUID());
				paramMap.put("isagency",0);
				wx.insertEmployee(paramMap.get("id")+"",paramMap.get("realname")+"",paramMap.get("departid")+"", paramMap.get("position")+"", paramMap.get("phone")+"", paramMap.get("sex")+"", paramMap.get("email")+"", paramMap.get("wxnum")+"", "");
				paramMap.put("password",Md5Util.getMD5(paramMap.get("password")+""));
				this.basicService.insertEmployee(paramMap);
			}
			else{
				if(phonemap.get("isagency").equals("1")){
					state="1";
				}
				else{
					state="2";
				}
				return getEmployeeList();
			}
		}
		return getEmployeeList();
	}
	public String deleteEmployee(){
		FileDeal.parseSimpleForm(paramMap);
		
		this.basicService.deleteEmployee(paramMap);
		wx.deleteEmployee(paramMap.get("id")+"");
		return getEmployeeList();
	}
	/**
	 * 
	 * 员工修改自己的标签
	 */
	public String editMarkEmployee(){
		FileDeal.parseSimpleForm(paramMap);
		System.out.println("-----------------------------"+markidlist);
		if(markidlist!=null && markidlist.size()!=0){
		this.basicService.editMarkEmployee(paramMap.get("id")+"",paramMap.get("markids")+"",markidlist);
		}
		return getEmployeeList();
	}
	public String editRoleEmployee(){
		FileDeal.parseSimpleForm(paramMap);
		if(roleidlist!=null && roleidlist.size()!=0){
		this.basicService.editRoleEmployee(paramMap.get("id")+"",paramMap.get("roleids")+"",roleidlist);
		}
		return getEmployeeList();
	}
	/**
	 * 标签管理
	 */
	public String getMarkList(){
		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		// 处理分页参数
		initPage(pvMap, pageHelper);
		dataList = this.basicService.getMarkList(pvMap);
		int num = this.basicService.getMarkListNum(pvMap);

		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "getMarkList";
	}
	public String editMark(){
		FileDeal.parseSimpleForm(paramMap);
		if(paramMap.get("markid")!=null && !paramMap.get("markid").equals("")){
			wx.updateMark(paramMap.get("markname")+"", paramMap.get("markid")+"");
			this.basicService.updateMark(paramMap);
		}else{
			Map<String, Object> data=wx.insertMark(paramMap.get("markname")+"",paramMap);
			this.basicService.insertMark(data);
		}
		return getMarkList();
	}
	public String deleteMark(){
		FileDeal.parseSimpleForm(paramMap);
		this.basicService.deleteMark(paramMap);
		wx.deleteMark(paramMap.get("markid")+"");
		return getMarkList();
	}
	/**
	 * 应用管理
	 */
	
	
	/**
	 * 消息管理
	 */
	public String getMessageList(){

		FileDeal.parseSimpleForm(pvMap);
 		PageHelper pageHelper = new PageHelper(this.getRequest());
 		marklist=new ArrayList<Map<String,Object>>();
		marklist=this.basicService.getMarkListAll();
		// 处理分页参数
		initPage(pvMap, pageHelper);
		pvMap.put("parentid",0);
		dictTypeList=this.systemService.getAllParentArea(pvMap);
		dataList = this.basicService.getMessageList(pvMap);
		int num = this.basicService.getMessageListNum(pvMap);
		departList=this.basicService.getDepartListAll();
		empList=this.basicService.getEmployeeListAll();
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",pageHelper.paginate1().toString());
		return "getMessageList";
	}
	/**
	 * 发送信息
	 * @return
	 */
	public String sendMessage(){
		//获取应用id
	    Map<String, Object> userinfo =new HashMap<String, Object>();
	    userinfo=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
	    userinfo=this.basicService.getAppIdByDepartId (userinfo);
	    String qiyeappid="";
	    if(userinfo==null ||userinfo.size()==0){
	    	qiyeappid="0";
	    }else{
	    	qiyeappid=userinfo.get("qiyeappid")+"";
	    }
		FileDeal.parseSimpleForm(paramMap);
		FileDeal.parseSimpleForm(pvMap);
		
         if(paramMap.get("contenttype").equals("1")&&(paramMap.get("content")==null||"".equals(paramMap.get("content").toString()))){
			 return getMessageList(); 
		 }
         if(paramMap.get("contenttype").equals("2")&&(paramMap.get("imageSrc")==null||"".equals(paramMap.get("imageSrc").toString()))){
			 return getMessageList(); 
		 }
		
		List<Map<String, Object>> userList=new ArrayList<Map<String,Object>>();
		if(paramMap.get("employeeid").equals("") || paramMap.get("employeeid")==null){
		
			if(paramMap.get("areaname").equals("")){//大区不存在 小区也不存在
					 userList=this.basicService.getEmployeelistBydepartid(paramMap);
					 sendMessageBatch(qiyeappid, userList);
			}else{//大区存在
				
				if(ids!=null){//小区存在刚只发小区的
					 for(int i=0;i<ids.size();i++){
			    		  paramMap.put("smallname",ids.get(i));
			    		  userList=this.basicService.getEmployeelistBydepartid(paramMap);
						  sendMessageBatch(qiyeappid, userList);
					 }
				}else{//小区不存在就发大区全部
					userList=this.basicService.getEmployeelistBydepartid(paramMap);
					sendMessageBatch(qiyeappid, userList);
				}
			}
		}else{//发送给单个用户
		   
		    if(paramMap.get("contenttype").equals("1")){
		    	messageService.sendQyMessage("text", paramMap.get("employeeid").toString(), "0", paramMap.get("content").toString());
		    }else if(paramMap.get("contenttype").equals("2")){
		    	String imageMessage=getImageMessageContent();
		    	messageService.sendQyMessage("text", paramMap.get("employeeid").toString(), "0", imageMessage);
		    	paramMap.put("content", imageMessage);
		    }
			this.basicService.sendMessage(paramMap);
		}
		return getMessageList();
		/*
		if(paramMap.get("employeeid").equals("") || paramMap.get("employeeid")==null){
			if(paramMap.get("departid").equals("")){
				departList=this.basicService.getDepartListAll();
				if(paramMap.get("markid").equals("")){
					for(Map<String, Object> da:departList){
						List<Map<String, Object>> userList=new ArrayList<Map<String,Object>>();
						userList=this.basicService.getEmployeelistBydepartid(da);
						for(Map<String, Object> data:userList){
								paramMap.put("employeeid",data.get("userid"));
								//调用接口发送信息
							    WeixinSendMessage send= new WeixinSendMessage();
							    if(paramMap.get("contenttype").equals("1")){
							    	send.sendMsg( "text", data.get("userid").toString(),qiyeappid, "0", paramMap.get("content").toString());	
							    }else{
							    	//send.sendMsg( "image", data.get("userid"), 1, 0, "", mediaId, title, description, picurl)
							    }
								
								this.basicService.sendMessage(paramMap);
						}
					}
				}
				else{
					for(Map<String, Object> da:departList){
						List<Map<String, Object>> userList=new ArrayList<Map<String,Object>>();
						userList=this.basicService.getEmployeelistBydepartid(da);
						for(Map<String, Object> user:userList){
							List<Map<String, Object>> marklist=new ArrayList<Map<String,Object>>();
							marklist=this.basicService.getMarkListByUserid(user);
							for(Map<String, Object> ma:marklist){
								if(ma.get("markid").equals(paramMap.get("markid"))){
									paramMap.put("employeeid",user.get("userid"));
									//调用接口发送信息
								    WeixinSendMessage send= new WeixinSendMessage();
								    if(paramMap.get("contenttype").equals("1")){
								    	send.sendMsg( "text", user.get("userid").toString(),qiyeappid, "0", paramMap.get("content").toString());	
								    }else{
								    	//send.sendMsg( "image", data.get("userid"), 1, 0, "", mediaId, title, description, picurl)
								    }
									
									
									this.basicService.sendMessage(paramMap);
									break;
								}
							}
						}
					}
				}
				return getMessageList();
			}
			else{
				if(paramMap.get("markid").equals("")){
					List<Map<String, Object>> employeeList=new ArrayList<Map<String,Object>>();
					employeeList=this.basicService.getEmployeeMarkByDepartid(paramMap);
					for(Map<String, Object> data:employeeList){
							paramMap.put("employeeid",data.get("userid"));
							
							//调用接口发送信息
						    WeixinSendMessage send= new WeixinSendMessage();
						    if(paramMap.get("contenttype").equals("1")){
						    	send.sendMsg( "text", data.get("userid").toString(), qiyeappid, "0", paramMap.get("content").toString());	
						    }else{
						    	//send.sendMsg( "image", data.get("userid"), 1, 0, "", mediaId, title, description, picurl)
						    }
							
							this.basicService.sendMessage(paramMap);
					}
				}
				else{
					List<Map<String, Object>> employeeList=new ArrayList<Map<String,Object>>();
					employeeList=this.basicService.getEmployeeMarkByDepartid(paramMap);
					for(Map<String, Object> data:employeeList){
						List<Map<String, Object>> marklist=new ArrayList<Map<String,Object>>();
						marklist=this.basicService.getMarkListByUserid(data);
						for(Map<String, Object> ma:marklist){
							if(ma.get("markid").equals(paramMap.get("markid"))){
								paramMap.put("employeeid",data.get("userid"));
								//调用接口发送信息
							    WeixinSendMessage send= new WeixinSendMessage();
							    if(paramMap.get("contenttype").equals("1")){
							    	send.sendMsg( "text", data.get("userid").toString(), qiyeappid, "0", paramMap.get("content").toString());	
							    }else{
							    	//send.sendMsg( "image", data.get("userid"), 1, 0, "", mediaId, title, description, picurl)
							    }
								
								this.basicService.sendMessage(paramMap);
								break;
							}
						}
					}
				}
			}
		}
		else{
			//调用接口发送信息
		    WeixinSendMessage send= new WeixinSendMessage();
		    if(paramMap.get("contenttype").equals("1")){
		    	send.sendMsg( "text", paramMap.get("employeeid").toString(), qiyeappid, "0", paramMap.get("content").toString());	
		    }else{
		    	//send.sendMsg( "image", data.get("userid"), 1, 0, "", mediaId, title, description, picurl)
		    }
			
			this.basicService.sendMessage(paramMap);
			
		}*/

	}

	private void sendMessageBatch(String qiyeappid,
			List<Map<String, Object>> userList) {
		for(Map<String, Object> data:userList){
			paramMap.put("employeeid",data.get("userid"));
			//调用接口发送信息
		
		    if(paramMap.get("contenttype").equals("1")){
		    	messageService.sendQyMessage("text",data.get("userid").toString(), "0", paramMap.get("content").toString());
		    	//send.sendMsg( "text", data.get("userid").toString(),qiyeappid, "0", paramMap.get("content").toString());	
		    }else  if(paramMap.get("contenttype").equals("2")){
		    	String imageMessage = getImageMessageContent();
				//send.sendMsg( "text", data.get("userid").toString(),qiyeappid, "0",imageMessage);
		    	messageService.sendQyMessage("text", data.get("userid").toString(), "0", imageMessage);
		    	paramMap.put("content", imageMessage);
		    }
			this.basicService.sendMessage(paramMap);
		}
	}

	private String getImageMessageContent() {
		String basePath=request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":":"+request.getServerPort());
		String imageSrc=basePath+paramMap.get("imageSrc").toString();
		String imageMessage="您收到图片,<a href=\""+imageSrc+"\" target=\"_blank\" >点击查看</a>";
		return imageMessage;
	}
	public String deleteMessage(){
		FileDeal.parseSimpleForm(paramMap);
		this.basicService.deleteMessage(paramMap);
		return getMessageList();
	}
	public String getNextDepart(){
		FileDeal.parseSimpleForm(paramMap);
		List<Map<String, Object>> departlist=this.basicService.getDepartListByparentid(paramMap);
		List<Map<String, Object>> employeelist=this.basicService.getEmployeelistBydepartid(paramMap);
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("departlist", departlist);
		data.put("employeelist", employeelist);
		System.out.println(data);
		this.result=parseJsonData(data);
		return "returnJson";
	}
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
	
	// 压缩图片的宽高
	private static final int UPLOAD_EXERCISE_WIDTH = 300;
	private static final int UPLOAD_EXERCISE_HEIGHT = 500;
	
	public String uploadEmployeeImg() {
		// 上传图片
		if (null != imgurl) {
			String realPath = getRequest().getRealPath("/");
			String path = "/upload/images/";
			String targetPath = UploadFileUtil.copyResolutionFile(imgurl, imgurlFileName,
					realPath, path,UPLOAD_EXERCISE_WIDTH,UPLOAD_EXERCISE_HEIGHT);
			// 返回图片地址
			getRequest().setAttribute("imgPath", targetPath);
		}
		return "showImg";
	}
	
	/**
	 * 初始化部门tree
	 * @return
	 */
	public String getDepartTree(){
		System.out.println("...........................");
		Map<String, Object> depart=new HashMap<String, Object>();
		depart=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
		List<Map<String, Object>> listObj=basicService.getMyDepartList(depart);
		List<Map<String,Object>> returnList=new ArrayList<Map<String,Object>>();
		if(listObj.size()>0){
			for(Map<String,Object> mapObj:listObj){
				Map<String, Object> returnMap = new HashMap<String,Object>();
				returnMap.put("treeId", mapObj.get("departid"));
				returnMap.put("treeName", mapObj.get("departname"));
				returnMap.put("treePid", mapObj.get("parentid"));
				returnList.add(returnMap);
			}
		}

		this.result=parseJsonData(returnList);

		return "returnJson";
	}
	/**
	 * 增加/修改/删除
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String editDepart() throws UnsupportedEncodingException{
		FileDeal.parseSimpleForm(paramMap);
		String op = paramMap.get("op").toString();
		String departId = paramMap.get("departId").toString();	//部门ID  (新增时为上级部门ID)
		String departName ="";
		if(paramMap.containsKey("departName") && !paramMap.get("departName").equals("")){
			departName=new String((paramMap.get("departName")+"").getBytes("ISO-8859-1"),"utf-8");
		}
		Map<String,Object> returnMap = new HashMap<String,Object>();
	
		System.out.println("请求协议为："+op);
		System.out.println("部门ID为："+departId);
		System.out.println("部门名称为："+departName);
		paramMap.put("departid", departId);
		paramMap.put("departname", departName);
		paramMap.put("ifactive", "1");
		paramMap.put("createtime", new Date());
		if(op.equals("0")){//新增
			Map<String, Object> data=wx.insertDepart(paramMap.get("departname")+"", paramMap.get("departid")+"", departId,paramMap);
			this.basicService.insertDepart(data);
			departId=this.basicService.getDepartId(paramMap);
			returnMap.put("status", "yes");
			returnMap.put("departId", departId);
			this.result=parseJsonData(returnMap);
			return "returnJson";
		}else if(op.equals("1")){		//修改
			this.basicService.updateDepart(paramMap);
			wx.updateDepart(paramMap.get("departid")+"",paramMap.get("departname")+"",departId);
			returnMap.put("status", "yes");
			this.result=parseJsonData(returnMap);
			return "returnJson";
		}else if(op.equals("2")){		//删除
			this.basicService.deleteDepart(paramMap);
			wx.deleteDepart(paramMap.get("departid")+"");
			returnMap.put("status", "yes");
			return getEmployeeList();
		}else{
			this.result=parseJsonData(returnMap);
			return "returnJson";
		}

	}
	/**
	 * 
	 * 应用管理
	 */
	public String getAppList(){
		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		// 处理分页参数
		initPage(pvMap, pageHelper);
		dataList = this.basicService.getAppList(pvMap);
		int num = this.basicService.getAppListNum(pvMap);
		departList=this.basicService.getDepartListAll();
		
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "getAppList";
	}
	public String editApp(){
		FileDeal.parseSimpleForm(paramMap);
		if(paramMap.get("appid")!=null && !paramMap.get("appid").equals("")){
			this.basicService.updateApp(paramMap);
		}else{
			this.basicService.insertApp(paramMap);
		}
		return getAppList();
	}
	public String deleteApp(){
		FileDeal.parseSimpleForm(paramMap);
		this.basicService.deleteApp(paramMap);
		return getAppList();
	}
	//插入最外层部门
	public String insertMaxDepart(){
		FileDeal.parseSimpleForm(pvMap);
		pvMap.put("departId", "1");
		pvMap.put("ifactive", "1");
		Map<String, Object> data=wx.insertDepart(pvMap.get("departname")+"", "1", "1",pvMap);
		this.basicService.insertDepart(data);
		return "orgtree";
	}
	public String validateDepart(){
		Map<String, Object> departMap=new HashMap<String, Object>();
		FileDeal.parseSimpleForm(paramMap);
		Map<String, Object> departmap=new HashMap<String, Object>();
		departmap=this.basicService.getDepartInfo(paramMap);
		this.result=parseJsonData(departmap);
		return "returnJson";
	}
	public String initagencyinto(){
		System.out.println("---------------");
		return "initagency";
	}
	public String initemployeeinto(){
		System.out.println("---------------");
		return "initemployee";
	}
    private File file;// 对应文件域的file，封装文件内容
    private String fileContentType;// 封装文件类型  
    private String fileFileName;// 封装文件名  
    
	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String fileContentType) {
		this.fileContentType = fileContentType;
	}

	public String getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String fileFileName) {
		this.fileFileName = fileFileName;
	}
	Map<String, Object> session=ActionContext.getContext().getSession();
	HttpServletRequest request=ServletActionContext.getRequest();
	//导入经销商
	public String SaveAgencyInto(){
			List<Map<String, Object>> agencyList=new ArrayList<Map<String,Object>>();

			try {
				if(fileFileName==null){
					request.setAttribute("error", "请选择上传文件");
					return "initagency";
				}
				// 获得文件输入流
				InputStream is = new FileInputStream(new File(fileFileName));
				// 得到工作本
				Workbook book = Workbook.getWorkbook(is);
				// 得到工作本中的所有表
				Sheet[] sheets = book.getSheets();
				for (int i = 0; i < sheets.length; i++) {
					// 循环获取每一个工作表
				Sheet sheet = sheets[i];
					// 得到表中的所有行数
					int rows = sheet.getRows();
					System.out.println(rows);
					Map<String, Object> dataMap = null;
					for (int j = 1; j < rows; j++) {
						// 循环行，获取每行中的列
						Cell[] cells = sheet.getRow(j);
						// 循环获取每一行每一列中的值
						dataMap = new HashMap<String, Object>();
						storeMap(cells,dataMap);
						System.out.println("dataMap++++++++++++++++++++++++++++++"+dataMap);
						agencyList.add(dataMap);
						Map<String, Object> phonemap=new HashMap<String, Object>();
						if(!dataMap.get("phone").equals("") && dataMap.get("phone")!=null){
							phonemap=this.basicService.getEmployee(dataMap);
						}
						if(phonemap!=null && phonemap.size()>0){
							request.setAttribute("error", "电话号码"+phonemap.get("phone")+"已存在请重新导入！");
							session.remove("agencyList");
							return "initagency";
						}
					}
					session.put("agencyList", agencyList);
				}
			} catch (Exception e) {
				request.setAttribute("error", "文件内容格式不正确，请重新选择文件上传！");
				return "initagency";
			}
		return agencyInfoResult();
	}
	public String agencyInfoResult(){
		List<Map<String, Object>> agencyList=(List<Map<String, Object>>)session.get("agencyList");
		for(Map<String, Object> da:agencyList){
				da.put("id", FileDeal.getUUID());
				da.put("isagency",1);
				wx.insertEmployee(da.get("id")+"",da.get("realname")+"","1",da.get("position")+"", da.get("phone")+"", da.get("sex")+"", da.get("email")+"", da.get("wxnum")+"", "");
				this.basicService.insertEmployee(da);
		}
		System.out.println("上传成功！");
		return "agencyInfoResult";
	}
	public String SaveEmployeeInto(){
		List<Map<String, Object>> employeeList=new ArrayList<Map<String,Object>>();
		try {
			if(fileFileName==null){
				request.setAttribute("error", "请选择上传文件");
				return "initemployee";
			}
			// 获得文件输入流
			InputStream is = new FileInputStream(new File(fileFileName));
			// 得到工作本
			Workbook book = Workbook.getWorkbook(is);
			// 得到工作本中的所有表
			Sheet[] sheets = book.getSheets();
			for (int i = 0; i < sheets.length; i++) {
				// 循环获取每一个工作表
			Sheet sheet = sheets[i];
				// 得到表中的所有行数
				int rows = sheet.getRows();
				System.out.println(rows);
				Map<String, Object> dataMap = null;
				for (int j = 1; j < rows; j++) {
					// 循环行，获取每行中的列
					Cell[] cells = sheet.getRow(j);
					// 循环获取每一行每一列中的值
					dataMap = new HashMap<String, Object>();
					storeMap1(cells,dataMap);
					System.out.println("dataMap++++++++++++++++++++++++++++++"+dataMap);
					employeeList.add(dataMap);
					Map<String, Object> phonemap=new HashMap<String, Object>();
					if(!dataMap.get("phone").equals("") && dataMap.get("phone")!=null){
						phonemap=this.basicService.getEmployee(dataMap);
					}
					if(phonemap!=null && phonemap.size()>0){
						request.setAttribute("error", "电话号码"+phonemap.get("phone")+"已存在请重新导入！");
						session.remove("employeeList");
						return "initemployee";
					}
				}
				session.put("employeeList", employeeList);
			}
		} catch (Exception e) {
			request.setAttribute("error", "文件内容格式不正确，请重新选择文件上传！");
			return "initemployee";
		}
	return employeeInfoResult();
	}
	public String employeeInfoResult(){
		List<Map<String, Object>> agencyList=(List<Map<String, Object>>)session.get("employeeList");
		for(Map<String, Object> da:agencyList){
				da.put("id", FileDeal.getUUID());
				da.put("password", "123456");
				da.put("isagency",0);
				wx.insertEmployee(da.get("id")+"",da.get("realname")+"",da.get("departid")+"",da.get("position")+"", da.get("phone")+"", da.get("sex")+"", da.get("email")+"", da.get("wxnum")+"", "");
				this.basicService.insertEmployee(da);
		}
		System.out.println("上传成功！");
		return "employeeInfoResult";
	}
	//查询excel传来的值及
	public void storeMap(Cell[] cells, Map<String, Object> dataMap) {	
		String realname = cells[0].getContents() + "";
		dataMap.put("realname", realname);
		String username = cells[1].getContents() + "";
		dataMap.put("username", username);
		String sex = cells[2].getContents() + "";
		dataMap.put("sex", sex);
		String position = cells[3].getContents() + "";
		dataMap.put("position", position);
		String phone = cells[4].getContents() + "";
		dataMap.put("phone", phone);
		String wxnum = cells[5].getContents() + "";
		dataMap.put("wxnum", wxnum);
		String email = cells[6].getContents() + "";
		dataMap.put("email", email);
		String areaname = cells[7].getContents() + "";
		dataMap.put("areaname", areaname);
		String smallname = cells[8].getContents() + "";
		dataMap.put("smallname", smallname);
		String servecode = cells[9].getContents() + "";
		dataMap.put("servecode", servecode);
		String sellcode = cells[10].getContents() + "";
		dataMap.put("sellcode", sellcode);
	}
	//查询excel传来的值及
	public void storeMap1(Cell[] cells, Map<String, Object> dataMap) {	
		String realname = cells[0].getContents() + "";
		dataMap.put("realname", realname);
		String username = cells[1].getContents() + "";
		dataMap.put("username", username);
		String sex = cells[2].getContents() + "";
		dataMap.put("sex", sex);
		String position = cells[3].getContents() + "";
		dataMap.put("position", position);
		String phone = cells[4].getContents() + "";
		dataMap.put("phone", phone);
		String wxnum = cells[5].getContents() + "";
		dataMap.put("wxnum", wxnum);
		String email = cells[6].getContents() + "";
		dataMap.put("email", email);
		String departid = cells[7].getContents() + "";
		dataMap.put("departid", departid);
	}
}