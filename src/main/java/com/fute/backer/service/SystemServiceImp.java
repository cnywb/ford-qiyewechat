package com.fute.backer.service;

import com.fute.backer.dao.SystemMapper;
import com.fute.backer.model.Answer;
import com.fute.backer.model.Question;
import com.fute.common.constant.WebConstant;
import com.fute.common.util.FileDeal;
import com.fute.util.DateUtils;
import com.github.pagehelper.PageHelper;
import com.opensymphony.xwork2.ActionContext;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.*;
public class SystemServiceImp implements SystemService {

	private SystemMapper systemMapper;

	private BasicService basicService;

	public BasicService getBasicService() {
		return basicService;
	}

	public void setBasicService(BasicService basicService) {
		this.basicService = basicService;
	}

	public SystemMapper getSystemMapper() {
		return systemMapper;
	}

	public void setSystemMapper(SystemMapper systemMapper) {
		this.systemMapper = systemMapper;
	}


	private String getMapValue(Map<String, Object> map,String key){
		if(null!=map && !map.isEmpty() && map.containsKey(key)){
			Object object = map.get(key) ;
			return null!=object && !"".equals(object) ? object.toString():"" ; 
		}
		return "" ;
	}
	
	@Override
	public void deleteFunction(Map<String, Object> data) {
		//1. 删除关系表中functionid
		if(null!=data && data.containsKey("id")){
			Map<String, Object> paramMap = new HashMap<String, Object>();
			paramMap.put("funId", getMapValue(data, "id")) ;
			this.systemMapper.deleteRoleFunction(paramMap);
		}
		//2.删除function表中的数据
		this.systemMapper.deleteFunction(data);
	}

	

	

	@Override
	public List<LinkedHashMap<String, Object>> getFunctionList(Map<String, Object> data) {
		List<LinkedHashMap<String, Object>> temp =  this.systemMapper.getFunctionList(data);
		return temp ;
	}
	
	private List<LinkedHashMap<String, Object>> getFunctionChildRen(List<LinkedHashMap<String, Object>> list,String parentid){
		List<LinkedHashMap<String, Object>> tempList = new ArrayList<LinkedHashMap<String,Object>>();
		if(null!=list && !list.isEmpty()){
			for (LinkedHashMap<String, Object> map : list) {
				if(null!=map && !map.isEmpty()){
					String tparentid = getMapValue(map, "parentid");
					if(null!=parentid && tparentid.equals(parentid)){
						//表示是顶级节点
						List<LinkedHashMap<String, Object>> dataList = getFunctionChildRen(list, getMapValue(map, "id")) ;
						if(null!=dataList){
							map.put("childRen", dataList);
							tempList.add(map);
						}
					}
				}
			}
		}
		return tempList;
	}
	

	@Override
	public int getFunctionListNum(Map<String, Object> data) {
		return this.systemMapper.getFunctionListNum(data);
	}

	
	


	@Override
	public void insertFunction(Map<String, Object> data) {
		this.systemMapper.insertFunction(data);
	}

	

	
	@SuppressWarnings("unchecked")
	private Set<String> getKeyList(Map<String, Object> data,String key,String itemKey){
		Set<String> keySet = new HashSet<String>();
 		if(null!=data && !data.isEmpty() && data.containsKey(key)){
 			List<Map<String, Object>> itemList = (List<Map<String, Object>>)data.get(key) ;
 			if(null!=itemList){
 				for (Map<String, Object> map : itemList) {
 					keySet.add(getMapValue(map, itemKey)) ;
				}
 			}
 		}
		return keySet ;
	}
	
	@Override
	public void saveRoleFunction(Map<String, Object> data) {
		//删除需要的角色的关联
		if(null!=data && !data.isEmpty() ){
			Set<String> roleIdSet = getKeyList(data, "roleFunList", "roleId") ;
			Map<String, Object> paramMap = new HashMap<String, Object>();
			if(null!=roleIdSet){
				for (String string : roleIdSet) {
					paramMap.put("roleId", string);
					this.systemMapper.deleteRoleFunction(paramMap);
				}
			}
		}
		if(saveASCondition(data, "roleFunList", "roleId", "funId")){
			this.systemMapper.saveRoleFunction(data);
		}
	}


	
	@SuppressWarnings("unchecked")
	private boolean saveASCondition(Map<String, Object> data,String key,String key1,String key2){
		if(null!=data && !data.isEmpty()){
			List<Map<String, Object>> dataList = (List<Map<String,Object>>)data.get(key) ;
			if(null!=dataList && dataList.size()>=1){
				Map<String, Object> map = dataList.get(0) ;
				if(null!=map && map.containsKey(key1) && map.containsKey(key2)
						&& null!=map.get(key1) && null!=map.get(key2)
						&& !"".equals(map.get(key1).toString()) && !"".equals(map.get(key2).toString())){
					return true ;
				}
			}
		}
		return false ;
	}
	
	@Override
	public void updateFunction(Map<String, Object> data) {
		this.systemMapper.updateFunction(data);
	}



	

	@Override
	public void deleteRoleFunction(Map<String, Object> data) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public List<Map<String, Object>> findFactFunByRole(Map<String, Object> data) {
		return this.systemMapper.findFactFunByRole(data);
	}

	@Override
	public List<Map<String, Object>> getFunctionListAll() {
		return this.systemMapper.getFunctionListAll();
	}

	@Override
	public void editRoleFunction(String roleid, String funids, List<String> ids) {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("ROLEID",roleid);
		systemMapper.deleteRoleFunction(data);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		if(ids!=null){
			for(String i:ids){
					Map<String, Object> dat = new HashMap<String, Object>();
					dat.put("ROLEID", roleid);
					dat.put("FUNID", i);
					list.add(dat);
			}
			if(list.size()>0){
				data.put("roleFunList", list);

				systemMapper.saveRoleFunction(data);
			}
		}
		
	}

	@Override
	public List<Map<String, Object>> getRoleList(Map<String, Object> data) {
		return this.systemMapper.getRoleList(data);
	}

	@Override
	public List<Map<String, Object>> getMaterialList(Map<String, Object> data) {
		PageHelper.startPage (1,10);
		return this.systemMapper.getMaterialList(data);
	}
	@Override
	public Map<String, Object> getUserInfo(Map<String, Object> data) {
		return this.systemMapper.getUserInfo(data);
	}
	@Override
	public int getMaterialListNum(Map<String, Object> data) {
		return this.systemMapper.getMaterialListNum(data);
	}


	@Override
	public void updateMaterial(Map<String, Object> data) {
		this.systemMapper.updateMaterial(data);
	}



	@Override
	public int insertMaterial(Map<String, Object> data) {
		return this.systemMapper.insertMaterial(data);
	}


	@Override
	public List<Map<String, Object>> getMenuList(Map<String, Object> data) {
		return this.systemMapper.getMenuList(data);
	}

	@Override
	public List<Map<String, Object>> getQuestionList(Map<String, Object> data) {
		PageHelper.startPage(1, 20); // 核心分页代码
		return this.systemMapper.getQuestionList(data);
	}

	@Override
	public int getQuestionListNum(Map<String, Object> data) {
		return this.systemMapper.getQuestionListNum(data);
	}

	@Override
	public void insertAnswer(Map<String, Object> data) {
       this.systemMapper.insertAnswer(data);		
	}

	@Override
	public void updateQuestionStatus(Map<String, Object> data) {
       this.systemMapper.updateQuestionStatus(data);		
	}

	@Override
	public List<Map<String, Object>> getUserInfoByMater(Map<String, Object> data) {
		
		return this.systemMapper.getUserInfoByMater(data);
	}

	@Override
	public List<Map<String, Object>> getMaterByUserid(Map<String, Object> data) {
		// TODO Auto-generated method stub
		return this.systemMapper.getMaterByUserid(data);
	}
	@Override
	public void insertQuestion(Map<String, Object> data) {
	   this.systemMapper.insertQuestion(data);
	}

	@Override
	public String getMaterId(Map<String, Object> data) {
		return this.systemMapper.getMaterId(data);
	}

	@Override
	public void insertMarterUser(Map<String, Object> data) {
		this.systemMapper.insertMarterUser(data);
	}

	@Override
	public void updateMaterDownload(Map<String, Object> data) {
		this.systemMapper.updateMaterDownload(data);
	}

	@Override
	public List<Map<String, Object>> getAllParentArea(Map<String, Object> data) {
		
		return this.systemMapper.getAllParentArea(data);
	}

	@Override
	public List<Map<String, Object>> getUserInfoByload(Map<String, Object> data) {
		return this.systemMapper.getUserInfoByload(data);
	}

	@Override
	public void updatePassword(Map<String, Object> data) {
		this.systemMapper.updatePassword(data);
	}

	@Override
	public void updateRole(Map<String, Object> data) {
		this.systemMapper.updateRole(data);
	}

	
	public  HSSFWorkbook createHSSFWorkbookForQuestion(Map<String, Object> pvMap){
		FileDeal.parseSimpleForm(pvMap);
		int totalCount=getQuestionListNum(pvMap);
		pvMap.put("startnum",0);
		pvMap.put("rownum", totalCount);
		List<Map<String, Object>> dataList = getQuestionList(pvMap);
	    String[] header={"编号","经销商姓名","服务代码","所属大区","所属小区","发送部门",	"内容摘要",	"提问时间","解答时间","解答人","状态","回复内容"};
		HSSFWorkbook wb = new HSSFWorkbook(); // create new workbook object
	    HSSFSheet sheet = wb.createSheet(); // create new sheet for workbook
	    HSSFRow row;
	    HSSFCell cell;
	    row = sheet.createRow(0);
	    for(int i=0;i<header.length;i++){
	   	 cell=row.createCell((short)i);
	   	 cell.setCellValue(header[i]);
	    }
	   	for(int i=0;i<dataList.size();i++){
		    row = sheet.createRow((short) i + 1); // create new row for
		    Map<String, Object> t=dataList.get(i);
			for(int j=0;j<header.length;j++){
				cell =row.createCell((short) (j));
				switch ( j ){
				case 0:
				      cell.setCellValue(t.get("QUID").toString());
				      break;
				case 1:
					  cell.setCellValue(t.get("QUIZNAME")==null?"":t.get("QUIZNAME").toString());
					  break;
				case 2:	
				      cell.setCellValue(t.get("SERVECODE")==null?"":t.get("SERVECODE").toString());
				      break;
				case 3:	
					  cell.setCellValue(t.get("AREANAME")==null?"":t.get("AREANAME").toString());
					  break;
				case 4:	
					  cell.setCellValue(t.get("SMALLNAME")==null?"":t.get("SMALLNAME").toString());
					  break;
				case 5:	
					  cell.setCellValue(t.get("DEPARTNAME")==null?"":t.get("DEPARTNAME").toString());
					  break;
				case 6:	
					  cell.setCellValue(t.get("QUCONTENT")==null?"":t.get("QUCONTENT").toString());
					  break;
				case 7:	
					  cell.setCellValue(DateUtils.format((Date)t.get("QUIZTIME"), DateUtils.FORMAT_DATE_TIME_DEFAULT));
					  break;
				case 8:	
					  cell.setCellValue(t.get("ANSWERTIME")==null?"":DateUtils.format((Date)t.get("ANSWERTIME"), DateUtils.FORMAT_DATE_TIME_DEFAULT));
					  break;
				case 9:	
					  cell.setCellValue(t.get("ANSWERNAME")==null?"":t.get("ANSWERNAME").toString());
					  break;
				case 10:	
					  int status=Integer.parseInt (String.valueOf(t.get("STATUS")));
					  if(status==0){
						  cell.setCellValue("未回复");
					  }else if(status==1){
						  cell.setCellValue("已回复");
					  }else if(status==2){
						  cell.setCellValue("已关闭");
					  }
					  break;
				case 11:	
					  cell.setCellValue(t.get("ANSWERCONTENT")==null?"":t.get("ANSWERCONTENT").toString());
					  break;
					  
				}
		    }
		}
	   	return wb;
	}
	
	/**
	 * 分享问题,即把已经回复的提问与答案复制一份给指定的部门
	 * Service类中ido开头的方法会创建一个新事务,保证实现数据库的ACID特性
	 */
	public long idoShareQuestion(Integer questionId,String departmentIds){
		Question t=systemMapper.getQuestionById(questionId);
		if(t==null){
			return 0L;
		}
		String[] dpIds=departmentIds.split(",");
	    for(String departmentId:dpIds){
	    	Map<String, Object> data=new HashMap<String, Object>();
	    	data.put("departmentId", Integer.parseInt(departmentId));
	    	data.put("content",t.getContent());
	    	if(systemMapper.countQuestionByDepartmentIdAndContent(data)==0){
	    		t.setId(null);
		    	t.setDepartmentId(Integer.parseInt (departmentId));
		    	systemMapper.addQuestion(t);//添加问题
		    	List<Answer> answerList=systemMapper.getAnswerByQuestionById(questionId);
		    	for (Answer answer:answerList){
		    		answer.setId(null);
		    		Integer newQuestionId=systemMapper.getQuestionIdByDepartmentIdAndContent(data);
		    		answer.setQuestionId(newQuestionId);
		    		systemMapper.addAnswer(answer);//添加回复
		    	}
		   	}
	    } 
		return 1L;
	}
	
	public long idoDeleteQuestionAndAnswer(Integer questionId){
		if(!checkRole("管理员")){
			return 0L;
		}
		systemMapper.deleteAnswer(questionId);
		systemMapper.deleteQuestion(questionId);
		return 1L;
	}
	
	public long idoCloseQuestion(Integer questionId){
		if(!checkRole("管理员")){
			return 0L;
		}
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("quid", questionId);
		data.put("status",2);
		systemMapper.updateQuestionStatus(data);
		return 1L;
	}
	
	public Boolean checkRole(String role){
		Map<String, Object> userinfo=(Map<String, Object>) ActionContext.getContext().getSession().get(WebConstant.USER_SESSION_KEY);
		if(userinfo==null){
			return false;
		}
		if(role.equals(userinfo.get("ROLENAME").toString())){
			return true;
		}
		return false;
	}
	public Boolean existQuestionByUserIdAndContent(String userId,String content){
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("content", content);
		data.put("userId",userId);
		Integer totalCount=systemMapper.countQuestionByUserIdAndContent(data);
		return totalCount>0;
	}

	@Override
	public Map<String, Object> getUserInfoById(String id) {
		// TODO Auto-generated method stub
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("id", id);
		return systemMapper.getUserInfoById(data);
	}
	
	public Map<String, Object> getUserInfoByWechatId(String wechatId) {
		// TODO Auto-generated method stub
		Map<String, Object> data=new HashMap<String, Object>();
		data.put("wechatId", wechatId);
		return systemMapper.getUserInfoByWechatId(data);
	}
	
	 public List<Map<String, Object>>getAnswerDetailByQuestionById(Integer questionId){
		 return systemMapper.getAnswerDetailByQuestionById(questionId);
	 }
	 
	 
	 //查询出个人的问题与答案
	 public List<Map<String, Object>> getPersonalQuestionList(Map<String, Object> data) {
		 List<Map<String, Object>>  questionList=systemMapper.getQuestionList(data);
		 for(Map<String, Object> question:questionList){
			 Integer questionId=Integer.parseInt(question.get("quid").toString());
			 List<Map<String, Object>>  answerList=getAnswerDetailByQuestionById(questionId);
			 question.put("answerList", answerList);
		 }
         return questionList;
	 }
	 

}