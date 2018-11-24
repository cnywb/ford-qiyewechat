
package com.fute.backer.action;

import com.fute.backer.service.BasicService;
import com.fute.backer.service.BatteryService;
import com.fute.backer.service.SystemParameterService;
import com.fute.backer.service.SystemService;
import com.fute.common.action.BaseActionImpl;
import com.fute.common.util.*;
import com.fute.reserve.util.weixinMessage1;
import com.fute.util.PageHelper;
import com.fute.wechat.service.message.MessageService;
import com.fute.wechat.service.qy.txl.WechatQyUserService;
import com.opensymphony.xwork2.ActionContext;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.struts2.ServletActionContext;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

public class SystemAction extends BaseActionImpl {

	private static final long serialVersionUID = 1L;

	private SystemService systemService;

	private BatteryService batteryService;

	private BasicService basicService;

	private String corpid;

	private String reurl;

	private String state;

	private Integer questionId;//问题ID

	private String departmentIds;//部门ID

	private Boolean isAdmin;//是否为管理员

	private Boolean existQuestion;//是否已经存在相同的问题

	private Boolean canAsk;//是否可以提问
	
		
	
	@Resource
	private MessageService messageService;
	@Resource
	private WechatQyUserService wechatQyUserService;
	
	@Resource
	private SystemParameterService systemParameterService;
	
	HttpServletRequest request=ServletActionContext.getRequest();
	
	private String baseContextPath=request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":":"+request.getServerPort())+request.getContextPath();

	
	public Boolean getCanAsk() {
		return canAsk;
	}

	public void setCanAsk(Boolean canAsk) {
		this.canAsk = canAsk;
	}

	public Boolean getExistQuestion() {
		return existQuestion;
	}

	public void setExistQuestion(Boolean existQuestion) {
		this.existQuestion = existQuestion;
	}

	public Boolean getIsAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(Boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public Integer getQuestionId() {
		return questionId;
	}

	public void setQuestionId(Integer questionId) {
		this.questionId = questionId;
	}

	public String getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String departmentIds) {
		this.departmentIds = departmentIds;
	}
	HttpServletResponse response = ServletActionContext.getResponse();  
	


	public String getCorpid() {
		return corpid;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public void setCorpid(String corpid) {
		this.corpid = corpid;
	}

	public String getReurl() {
		return reurl;
	}

	public void setReurl(String reurl) {
		this.reurl = reurl;
	}
	weixinMessage1 wx=new weixinMessage1();
	
	public BasicService getBasicService() {
		return basicService;
	}

	public void setBasicService(BasicService basicService) {
		this.basicService = basicService;
	}

	public BatteryService getBatteryService() {
		return batteryService;
	}

	public void setBatteryService(BatteryService batteryService) {
		this.batteryService = batteryService;
	}

	public SystemService getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemService systemService) {
		this.systemService = systemService;
	}
	private Map<String, Object> pvMap = new HashMap<String, Object>();
	
	public Map<String, Object> getPvMap() {
		return pvMap;
	}

	public void setPvMap(Map<String, Object> pvMap) {
		this.pvMap = pvMap;
	}
	private int currentPage = 1;
	
	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public void initPage(Map<String, Object> paramMap, PageHelper pageHelper) {
		int rowPage = pageHelper.getRowPage(); // 每页显示的记录
		if (currentPage < 1)
			currentPage = 1;
		if (rowPage < 1)
			rowPage = 1;
		int startnum = (currentPage - 1) * rowPage; // 分页查询开始的序号
		int rownum = rowPage; // 每页显示的条数
		pvMap.put("startnum", startnum);
		pvMap.put("rownum", rownum);
	}
	
	private List<Map<String, Object>> dictTypeList ;
	private List<Map<String, Object>> dictDataList ;
	private List<Map<String, Object>> dataGridList;
	private List<Map<String, Object>> dataList ;
	private List<Map<String, Object>> marklist ;
	private List<Map<String, Object>> rolelist ;
	private Map<String, Object> paramMap=new HashMap<String, Object>();
    private List<String>  ids;
    private List<String>  carids;
    private List<String>  markidlist;
    private List<String>  roleidlist;
    private String roleid;
    private String parentid;
    private File imgurl;
    private String imgurlFileName;
    private Map<String, Object> map=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
 
    
 
	public String getParentid() {
		return parentid;
	}

	public void setParentid(String parentid) {
		this.parentid = parentid;
	}

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

	public List<String> getRoleidlist() {
		return roleidlist;
	}

	public void setRoleidlist(List<String> roleidlist) {
		this.roleidlist = roleidlist;
	}

	public List<String> getMarkidlist() {
		return markidlist;
	}

	public void setMarkidlist(List<String> markidlist) {
		this.markidlist = markidlist;
	}

	public List<String> getCarids() {
		return carids;
	}

	public Map<String, Object> getParamMap() {
		return paramMap;
	}

	public void setParamMap(Map<String, Object> paramMap) {
		this.paramMap = paramMap;
	}

	public List<Map<String, Object>> getMarklist() {
		return marklist;
	}

	public void setMarklist(List<Map<String, Object>> marklist) {
		this.marklist = marklist;
	}

	public List<Map<String, Object>> getRolelist() {
		return rolelist;
	}

	public void setRolelist(List<Map<String, Object>> rolelist) {
		this.rolelist = rolelist;
	}

	public void setCarids(List<String> carids) {
		this.carids = carids;
	}
	private List<LinkedHashMap<String, Object>> funDGList ;
	
	public List<LinkedHashMap<String, Object>> getFunDGList() {
		return funDGList;
	}

	public void setFunDGList(List<LinkedHashMap<String, Object>> funDGList) {
		this.funDGList = funDGList;
	}
    
    
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public List<Map<String, Object>> getDataList() {
		return dataList;
	}

	public void setDataList(List<Map<String, Object>> dataList) {
		this.dataList = dataList;
	}

	public List<Map<String, Object>> getDataGridList() {
		return dataGridList;
	}

	public void setDataGridList(List<Map<String, Object>> dataGridList) {
		this.dataGridList = dataGridList;
	}

	public List<Map<String, Object>> getDictDataList() {
		return dictDataList;
	}

	public void setDictDataList(List<Map<String, Object>> dictDataList) {
		this.dictDataList = dictDataList;
	}

	public List<Map<String, Object>> getDictTypeList() {
		return dictTypeList;
	}

	public void setDictTypeList(List<Map<String, Object>> dictTypeList) {
		this.dictTypeList = dictTypeList;
	}
	


	public List<String> getIds() {
		return ids;
	}

	public void setIds(List<String> ids) {
		this.ids = ids;
	}
	private Integer parentDictType ; 
	
	public Integer getParentDictType() {
		return parentDictType;
	}

	public void setParentDictType(Integer parentDictType) {
		this.parentDictType = parentDictType;
	}

	
	private Map<String, Object> paramCondition ;

	public Map<String, Object> getParamCondition() {
		return paramCondition;
	}

	public void setParamCondition(Map<String, Object> paramCondition) {
		this.paramCondition = paramCondition;
	}


	//得到菜单列表
	public String findAdminMenuList() {

		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		initPage(pvMap, pageHelper);
		int num=this.systemService.getFunctionListNum(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",pageHelper.paginate1().toString());

		List<LinkedHashMap<String, Object>> funDGList = this.systemService.getFunctionList(pvMap);
		this.getRequest().setAttribute("funcDGList", funDGList);
		Map<String, Object> paramMap = new HashMap<String, Object>();
		paramMap.put("parentid", "0");
		
		List<LinkedHashMap<String, Object>> funTopList = this.systemService.getFunctionList(paramMap);
		this.getRequest().setAttribute("funTopList", funTopList);
		if(pvMap.containsKey("status") && (pvMap.get("status").equals("") || pvMap.get("status")==null)){
			pvMap.put("status", "3");
		}
		return "adminMenu";
	}
	public String deleteMenu() {
		if(null!=paramCondition && !paramCondition.isEmpty()){
			FileDeal.parseSimpleForm(paramCondition);
			this.systemService.deleteFunction(paramCondition);
		}
		return findAdminMenuList();
	}
	//保存菜单
	public String saveMenu() {
		if(null!=paramCondition && !paramCondition.isEmpty()){
			FileDeal.parseSimpleForm(paramCondition);
			if(paramCondition.containsKey("id") && null!=paramCondition.get("id") && !"".equals(paramCondition.get("id").toString())){
				this.systemService.updateFunction(paramCondition);
			}else {
				this.systemService.insertFunction(paramCondition);
			}
		}
		return findAdminMenuList();
	}
	

    /**
     * 获取角色信息
     * @return
     */
	public String findAdminRoleList() {
		//获取角色列表
		dataGridList=new ArrayList<Map<String,Object>>();
		dataGridList = this.basicService.getRoleListAll(null);
		//查询所有菜单列表
		dataList=new ArrayList<Map<String,Object>>();
		dataList = this.systemService.getFunctionListAll();
		return "adminRole";
	}
	//查询角色下的菜单列表
	public String findFactFunByRole() {
		pvMap.put("roleid", roleid);
		dataList = this.systemService.findFactFunByRole(pvMap);
		//获取菜单列表
		this.result=parseJsonData(dataList);
		return "returnJson";
	}
	//给角色添加菜单权限
	public String editRoleFunction(){
		FileDeal.parseSimpleForm(pvMap);
	    this.systemService.editRoleFunction((String)pvMap.get("roleid"),"", ids);
		
		return findAdminRoleList();
	}

	
	/**
	 * 物料信息
	 * @return
	 */
	public String getMaterialList(){
		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		// 处理分页参数
		initPage(pvMap, pageHelper);
		pvMap.put("deptid",map.get("departid"));
		pvMap.put("parentid", 0);
		//获取大区
		dictTypeList =this.systemService.getAllParentArea(pvMap);
		dataList = this.systemService.getMaterialList(pvMap);
		int num = this.systemService.getMaterialListNum(pvMap);
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",pageHelper.paginate1().toString());
		if(pvMap.containsKey("status") && (pvMap.get("status").equals("") || pvMap.get("status")==null)){
			pvMap.put("status", "3");
		}
		return "getMaterialList";
	}
	
	//注意，file并不是指前端jsp上传过来的文件本身，而是文件上传过来存放在临时文件夹下面的文件
    private File[] file;
    
    //提交过来的file的名字
    private  String[] fileFileName;
    
    //提交过来的file的MIME类型
    private  String[] fileContentType;

    

public File[] getFile() {
		return file;
	}

	public void setFile(File[] file) {
		this.file = file;
	}

	public String[] getFileFileName() {
		return fileFileName;
	}

	public void setFileFileName(String[] fileFileName) {
		this.fileFileName = fileFileName;
	}

	public String[] getFileContentType() {
		return fileContentType;
	}

	public void setFileContentType(String[] fileContentType) {
		this.fileContentType = fileContentType;
	}

  //保存信息
  public String saveMaterial(){
	  FileDeal.parseSimpleForm(paramCondition);
	  paramMap=new HashMap<String, Object>();
      List<String> servercode = new ArrayList<String>();
      List<String> sellcode1 = new ArrayList<String>();
	  if(paramCondition.get("areaname")!=null){
		  paramMap.put("areaname",paramCondition.get("areaname"));
	  }
	  if(paramCondition.get("servecode")!=null){
		   String servecode=paramCondition.get("servecode")+"";
	        StringTokenizer st = new StringTokenizer(servecode, ";");
	        while (st.hasMoreElements()) {
	        	servercode.add(st.nextToken());
	        }
	        System.err.println(servercode);
	        paramMap.put("servercode", servercode);
	  }
	  if(paramCondition.get("sellcode")!=null){
		   	String sellcode=paramCondition.get("sellcode")+"";
	        StringTokenizer st = new StringTokenizer(sellcode, ";");
	        while (st.hasMoreElements()) {
	        	sellcode1.add(st.nextToken());
	        }
	        System.err.println(sellcode1);
	        paramMap.put("sellcode1", sellcode1);
	  }
	  if(ids!=null){
			System.out.println(ids); 
			 paramMap.put("smallarea",ids);
	  }
	  if(paramMap!=null){

		  try{
			  String root = ServletActionContext.getServletContext().getRealPath("/upload");
			  File[] files=file;
				for(int i=0;i<files.length;i++){
					FileOutputStream fos=new FileOutputStream(new File(root,fileFileName[i]));
					FileInputStream fis=new FileInputStream(files[i]);
					byte[] buffer=new byte[1024];
					int len=0;
					while((len=fis.read(buffer))>0){
						fos.write(buffer, 0, len);
					}
					fos.close();
					fis.close();
				}
			  paramCondition.put("createtime", new Date());
			  SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMddHHmmsss");
			  paramCondition.put("timestamp", sdf.format(new Date()));
		      paramCondition.put("userid",map.get("userid"));
		      paramCondition.put("deptid",map.get("departid"));
		      paramCondition.put("saveurl",root+"/"+fileFileName[0]);
		      paramCondition.put("materialname", fileFileName[0]);
		      this.systemService.insertMaterial(paramCondition);
		      String materid=this.systemService.getMaterId(paramCondition);
		      //都有
		      paramMap.put("position", paramCondition.get("position"));
		      if(ids!=null && servercode.size()>0 && sellcode1.size()>0){
		    	  for(int i=0;i<ids.size();i++){
		    		  paramMap.put("smallname",ids.get(i));
		    		  for(int j=0;j<servercode.size();j++){
		    			  paramMap.put("servecode",servercode.get(j));
		    			  for(int k=0;k<sellcode1.size();k++){
		    				  paramMap.put("sellcode",sellcode1.get(k));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				
		    				  Map<String, Object> userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    				
		    					  String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    				
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    				  }
		    			  }
		    		  }
		    	  }
		      //没有小区
		      }else if(servercode.size()>0 && sellcode1.size()>0){
		    	  for(int j=0;j<servercode.size();j++){
	    			  paramMap.put("servecode",servercode.get(j));
	    			  for(int k=0;k<sellcode1.size();k++){
	    				  paramMap.put("sellcode",sellcode1.get(k));
	    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
	    				
	    				  Map<String, Object> userinfo =new HashMap<String, Object>();
	    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
	    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
	    				  for(Map<String, Object> da:userlist){
	    					  da.put("materid", materid);
	    					  String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
	    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
	    					  da.put("isdownload", "0");
	    					  this.systemService.insertMarterUser(da);
	    				  }
	    			  }
	    		  }
		      }
		      //没有销售代码
		      else if(ids!=null && servercode.size()>0){
		    	  for(int i=0;i<ids.size();i++){
		    		  paramMap.put("smallname",ids.get(i));
		    		  for(int j=0;j<servercode.size();j++){
		    			  paramMap.put("servecode",servercode.get(j));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				
		    				  Map<String, Object> userinfo =new HashMap<String, Object>();
		    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    				  	 String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    				  }
		    		  }
		    	  }
		      }
		    //没有服务代码
		      else if(ids!=null && sellcode1.size()>0){
		    	  for(int i=0;i<ids.size();i++){
		    		  paramMap.put("smallname",ids.get(i));
		    		  for(int k=0;k<sellcode1.size();k++){
	    				  paramMap.put("sellcode",sellcode1.get(k));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				
		    				  Map<String, Object> userinfo =new HashMap<String, Object>();
		    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    				       String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    				  }
		    		  }
		    	  }
		      }
		    //只有小区
		      else if(ids!=null){
		    	  for(int i=0;i<ids.size();i++){
		    		  paramMap.put("smallname",ids.get(i));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				
		    				  Map<String, Object> userinfo =new HashMap<String, Object>();
		    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    					  String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    				  }
		    	  }
		      }
			  //只有销售代码
		      else if(sellcode1.size()>0){
		    		  for(int k=0;k<sellcode1.size();k++){
	    				  paramMap.put("sellcode",sellcode1.get(k));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				 
		    				  Map<String, Object> userinfo =new HashMap<String, Object>();
		    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    				    String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    				  }
		    		  }
		      }
		    //只有服务代码
		      else if(servercode.size()>0){
		    		  for(int j=0;j<servercode.size();j++){
		    			  paramMap.put("servecode",servercode.get(j));
		    				  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
		    				
		    				  Map<String, Object> userinfo =new HashMap<String, Object>();
		    				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		    				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
		    				  for(Map<String, Object> da:userlist){
		    					  da.put("materid", materid);
		    					  String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
		    					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
		    					  da.put("isdownload", "0");
		    					  this.systemService.insertMarterUser(da);
		    			}
		    	  }
		      }else{
		    	  paramMap=new HashMap<String, Object>();
		    	  paramMap.put("isagency","1");
		    	  List<Map<String, Object>> userlist=this.systemService.getUserInfoByMater(paramMap);
				  
				  Map<String, Object> userinfo =new HashMap<String, Object>();
				  userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
				  userinfo=this.basicService.getAppIdByDepartId (userinfo);
				  for(Map<String, Object> da:userlist){
					  da.put("materid", materid);
				  String txt="您收到"+map.get("realname")+"给你下发的物料："+fileFileName[0]+"   <a href='"+baseContextPath+"/backer/system!getUserid.action'>点此查收</a>";
					  messageService.sendQyMessage("text", da.get("id").toString(), "0", txt);
					  da.put("isdownload", "0");
					  this.systemService.insertMarterUser(da);
				  }
		      }
			  }catch(Exception e){
				  e.printStackTrace();
				  this.getRequest().setAttribute("errMsg", "文件上传失败，请重新上传");
			  }
	  	 }
	  return this.getMaterialList();
	  
  } 
    //修改物料信息状态
	public String updateMaterialStatus(){
		FileDeal.parseSimpleForm(paramCondition);
		this.systemService.updateMaterial(paramCondition);
		return this.getMaterialList();
	}
	
	/**
	 * 咨询解答
	 * @return
	 */
	public String getQuestionList(){
		FileDeal.parseSimpleForm(pvMap);
		Map<String, Object> userinfo=( Map<String, Object>)ActionContext.getContext().getSession().get("user");
		pvMap.put("userDeparmentId",userinfo.get("departid"));
	    pvMap.put("deptid",map.get("departid"));
		PageHelper pageHelper = new PageHelper(this.getRequest());


		Date startDate= DateUtils.parser (pvMap.get ("gainstarttime")+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM);
		Date endtDate= DateUtils.parser (pvMap.get ("gainendtime")+" 00:00:00",DateUtils.FORMAT_DATETIME_YYYY_MM_DD_HH_MM);

		pvMap.put ("gainstarttime",startDate);
		pvMap.put ("gainendtime",endtDate);


		// 处理分页参数
		initPage(pvMap, pageHelper);
	    dataList = this.systemService.getQuestionList(pvMap);
		int num = this.systemService.getQuestionListNum(pvMap);
		isAdmin=systemService.checkRole("管理员");
		dictDataList=this.basicService.getDepartListAll();
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",pageHelper.paginate1().toString());
		if(pvMap.containsKey("STATUS") && (pvMap.get("STATUS").equals("") || pvMap.get("STATUS")==null)){
			pvMap.put("STATUS", "3");
		}
		return "getQuestionList";
	}
	
	
	public void exportQuestionToExcel(){
	    try {
			response.setHeader("Content-Disposition","attachment;filename=questions.xls");
			response.setContentType("application/vnd.ms-excel");
			systemService.createHSSFWorkbookForQuestion(pvMap).write(response.getOutputStream());
			response.getOutputStream().flush();
		    response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void shareQuestion(){
	    try {
		    Long retVal=systemService.idoShareQuestion(questionId, departmentIds);
			response.getWriter().write(retVal+"");
			response.getOutputStream().flush();
		    response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void deleteDeleteQuestionAndAnswer(){
	    try {
		    Long retVal=systemService.idoDeleteQuestionAndAnswer(questionId);
			response.getWriter().write(retVal+"");
			response.getOutputStream().flush();
		    response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public void closeQuestion(){
	    try {
		    Long retVal=systemService.idoCloseQuestion(questionId);
			response.getWriter().write(retVal+"");
			response.getOutputStream().flush();
		    response.getOutputStream().close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	//查询所有问题信息
	public String getAllQuestionList(){
		reurl =URLEncoder.encode(baseContextPath+"/backer/system!getAllQuestionList1.action");
		corpid = systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		state="456";
		return "getuserid";
	}
	public String getAllQuestionList1(){
		FileDeal.parseSimpleForm(pvMap);
	    String userid = MapUtils.getString(pvMap, "userid");
		String scode = request.getParameter("code");
		if(null==userid || "".equals(userid)&&!StringUtils.isEmpty(scode)){//当前是从微信端跳转过来的
			String wechatId=wechatQyUserService.getAuthUserInfo(scode).getUserId();
			LOG.info("=======wehchat userid="+wechatId);
		    Map<String, Object> userinfo=systemService.getUserInfoById(wechatId);
		    ActionContext.getContext().getSession().put("user", userinfo);
		    userid=userinfo.get("userid").toString();
		    ActionContext.getContext().getSession().put("userid",userid);
		}
	    pvMap.put("quizid", userid);//提问者id
		//dataList = this.systemService.getQuestionList(pvMap);
		dataList = this.systemService.getPersonalQuestionList(pvMap);
		return "getAllQuestionList";//此处是页面不是方法/management/allQuestion.jsp
	}
	//初始化添加问题页面
	public String findDepartListAll(){
		dictDataList=this.basicService.getDepartListAll();
		return "findDepartListAll";//这名取得也是够了,明明是添加问题,view也是addQuestion.jsp这里居然是findDepartListAll
	}
	//保存提交的问题
	public String saveQuestion(){
		FileDeal.parseSimpleForm(paramMap);
		String qucontent="";
		if(paramMap.containsKey("qucontent")){
			qucontent=paramMap.get("qucontent")+"";
		}
		Map<String, Object> userinfo=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
	    if(userinfo==null){//未登录
	    	return this.getAllQuestionList();
	    }
	    String userid=userinfo.get("userid").toString();
		if("1".equals(userinfo.get("isagency").toString())){//经消商
			canAsk=true;
		}else{
			canAsk=false;//福特内部员工不可以提问
			return findDepartListAll();
		}
		existQuestion=systemService.existQuestionByUserIdAndContent(userid,paramMap.get("qucontent").toString());
		if(existQuestion==true){//如果问题已经存在
			return findDepartListAll();//其实这是添加问题页面,很二逼吧
		}
		paramMap.put("quizid",userid);
		if(paramMap.size()>0 && paramMap!=null&&!qucontent.equals("")){//如果用户已经登录
			this.systemService.insertQuestion(paramMap);
		}
		pvMap.put("quizid", userid);//提问者id
		dataList = this.systemService.getPersonalQuestionList(pvMap);
		return "getAllQuestionList";
	}
	public String edtAnswers(){
		Map<String, Object>  	userinfo=(Map<String, Object>)ActionContext.getContext().getSession().get("user");
	  	userinfo=this.basicService.getAppIdByDepartId (userinfo);
		FileDeal.parseSimpleForm(paramCondition);
		//回答问题
		String baseContextPath=request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":":"+request.getServerPort())+request.getContextPath();
		String txt=map.get("REALNAME")+"解答了您的问题,<a href='"+baseContextPath+"/backer/system!getAllQuestionList.action'>请点此查看。</a>";
		messageService.sendQyMessage("text", paramCondition.get("USERID").toString(), "0", txt);
		paramCondition.put("ANSWERID",map.get("USERID"));
		paramCondition.put("STATUS",1);
		this.systemService.insertAnswer(paramCondition);
		if(paramCondition.get("imageSrc")!=null&&!StringUtils.isEmpty(paramCondition.get("imageSrc").toString())){
			String basePath=request.getScheme()+"://"+request.getServerName()+(request.getServerPort()==80?"":":"+request.getServerPort());
			String imageSrc=basePath+paramCondition.get("imageSrc").toString();
			String content="<img src=\""+imageSrc+"\" />";
			paramCondition.put("ANSWERCONTENT", content);
		    this.systemService.insertAnswer(paramCondition);
		}
		Integer quid=Integer.parseInt(paramCondition.get("quid").toString());
		paramCondition.put("quid", quid);
		this.systemService.updateQuestionStatus(paramCondition);
		return this.getQuestionList();
	}
	
	
	public String transformQuestion(){
		FileDeal.parseSimpleForm(paramCondition);
		if(paramCondition.get("deptid")!=null&&!StringUtils.isEmpty(paramCondition.get("deptid").toString())){//转移问题
			this.systemService.updateQuestionStatus(paramCondition);
		}
		return this.getQuestionList();
	}
	
	
	//经销商agency
	/**
	 * 经销商
	 * @return
	 */
	public String getAgencyList(){
		FileDeal.parseSimpleForm(pvMap);
		PageHelper pageHelper = new PageHelper(this.getRequest());
		dictDataList =this.basicService.getDepartListAll();
		pvMap.put("parentid", 0);
		//获取大区
		dictTypeList =this.systemService.getAllParentArea(pvMap);
		// 处理分页参数
		initPage(pvMap, pageHelper);
		pvMap.put("isagency", "1");
		dataList = this.basicService.getEmployeeList(pvMap);
		int num = this.basicService.getEmployeeListNum(pvMap);
		marklist=this.basicService.getMarkListAll();
		pageHelper.setTotalCount(num);
		this.getRequest().setAttribute("pager",
				pageHelper.paginate1().toString());
		return "getAgencyList";
	}
	
	public String deleteAgencyBatch(){
		FileDeal.parseSimpleForm(pvMap);
		basicService.idoDeleteAgencyBatch(pvMap);
		return getAgencyList();
	}
	//获得小区信息
	public String ajaxArea(){
		pvMap.put("parentid", parentid);
		dataList = this.systemService.getAllParentArea(pvMap);
		this.result=parseJsonData(dataList);
		return "returnJson";
	}
	public String editAgency(){
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
				paramMap.put("isagency", 1);
				paramMap.put("id", FileDeal.getUUID());
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
				return getAgencyList();
			}
		}
		return getAgencyList();
	}             
	public String deleteAgency(){
		FileDeal.parseSimpleForm(paramMap);
		wx.deleteEmployee(paramMap.get("id")+"");
		this.basicService.deleteEmployee(paramMap);
		return getAgencyList();
	}
	/**
	 * 
	 * 经销商修改自己的标签
	 */
	public String editMarkAgency(){
		FileDeal.parseSimpleForm(paramMap);
		System.out.println("-----------------------------"+markidlist);
		if(markidlist!=null && markidlist.size()!=0){
		this.basicService.editMarkEmployee(paramMap.get("id")+"",paramMap.get("markids")+"",markidlist);
		}
		return getAgencyList();
	}
	
	public String editRoleAgency(){
		FileDeal.parseSimpleForm(paramMap);
		if(roleidlist!=null && roleidlist.size()!=0){
		this.basicService.editRoleEmployee(paramMap.get("id")+"",paramMap.get("roleids")+"",roleidlist);
		}
		return getAgencyList();
	}
	
	// 压缩图片的宽高
	private static final int UPLOAD_EXERCISE_WIDTH = 300;
	private static final int UPLOAD_EXERCISE_HEIGHT = 500;
	
	public String uploadAgencyImg() {
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
	//下发详情
	public String getUserInfoByMater(){
		FileDeal.parseSimpleForm(pvMap);
		dataList=this.systemService.getUserInfoByMater(pvMap);
		return "getUserInfoByMater";
	}
	//下载详情
	public String getUserInfoByload(){
		FileDeal.parseSimpleForm(pvMap);
		dataList=this.systemService.getUserInfoByload(pvMap);
		return "getUserInfoByload";
	}
	
	public String getMaterByUserid(){
		FileDeal.parseSimpleForm(paramMap);
		String userid=ActionContext.getContext().getSession().get("userid").toString();
		paramMap.put("userid",userid);
		LOG.info("getMaterByUserid--------------------------userid:--"+userid);
		dataList=this.systemService.getMaterByUserid(paramMap);
		HttpServletRequest request=ServletActionContext.getRequest();
		request.setAttribute("userid",paramMap.get("userid"));
		return "getMaterByuserid";
	}
	public String getUserid(){
		reurl = URLEncoder.encode(baseContextPath+"/backer/system!getUserid1.action");
		corpid= systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
		state="123";
		return "getuserid";
	}
	public String getUserid1(){
		FileDeal.parseSimpleForm(paramMap);
		String userid = MapUtils.getString(paramMap, "userid");
		String scode = request.getParameter("code");
		if(null==userid || "".equals(userid)&&!StringUtils.isEmpty(scode)){//从微信跳转过来
			String wechatId=wechatQyUserService.getAuthUserInfo(scode).getUserId();
			LOG.info("=======getUserid1 wehchat userid="+wechatId);
		    Map<String, Object> userinfo=systemService.getUserInfoById(wechatId);
		    ActionContext.getContext().getSession().put("user", userinfo);
		    userid=userinfo.get("userid").toString();
		    ActionContext.getContext().getSession().put("userid",userid);
		}
		return "loadjsp";
	}
	private String stamp;
	private String filename;
	private String user;
	//下载模版
	public String execute() throws Exception {
		// TODO Auto-generated method stub
		return super.execute();
	}
	
	public String getStamp() {
		return stamp;
	}

	public void setStamp(String stamp) {
		this.stamp = stamp;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public InputStream getMyFile(){
		System.out.println(filename);
		Map<String, Object> data=new HashMap<String, Object>();
		if(stamp!=null && !stamp.equals("")){
			data.put("timestamp",stamp);
			String materialid=this.systemService.getMaterId(data);
			Map<String, Object> down=new HashMap<String, Object>();
			down.put("materid", materialid);
			System.out.println("***********************userid*****"+ActionContext.getContext().getSession().get("userid"));
			if(user!=null){
				down.put("id",user);
			}
			else if(ActionContext.getContext().getSession().get("userid")==null){
				down.put("id", map.get("userid"));
			}else{
				down.put("id", ActionContext.getContext().getSession().get("userid"));
			}
			down.put("isdownload", "1");
			Map<String, Object> downmap=new HashMap<String, Object>();
			downmap=this.basicService.getMaterUser(down);
			if(downmap==null){
				this.systemService.updateMaterDownload(data);
				this.systemService.insertMarterUser(down);
			}
		}
		System.out.println(ServletActionContext.getServletContext().getResourceAsStream("upload/"+filename));
		return ServletActionContext.getServletContext().getResourceAsStream("upload/"+filename);
	}
	public String getFilename(){
		try {
			filename=new String(filename.getBytes("utf-8"),"ISO-8859-1");
		} catch (UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return filename;
	}
	public void setFilename(String filename){
		try {
			filename=new String(filename.getBytes("ISO-8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.filename =filename;
	}
	public String mainjsp(){
		
		return "mainjsp";
	}
	public String yanzheng(){
		
		return "tishi";
	}
	//修改密码
	public String initpassword(){
		String username=map.get("username")+"";
		String userid=map.get("userid")+"";
		this.getRequest().setAttribute("username", username);
		this.getRequest().setAttribute("userid", userid);
		return "initpassword";
	}
	//修改密码
	public String updatepassword(){
		FileDeal.parseSimpleForm(pvMap);
		System.out.println("------------"+pvMap);
		pvMap.put("password",Md5Util.getMD5(pvMap.get("password")+""));
		this.systemService.updatePassword(pvMap);
		return "login";
	}
	//修改角色名称
	public String saveRole(){
		FileDeal.parseSimpleForm(paramCondition);
		this.systemService.updateRole(paramCondition);
		return findAdminRoleList();
	}

	
}