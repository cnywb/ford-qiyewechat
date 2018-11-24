package com.fute.backer.service;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SystemService {
	//登录验证
	Map<String, Object> getUserInfo(Map<String, Object> data);
	
	public Map<String, Object>  getUserInfoById(String id);//根据用户ID得到用户信息
	
	public Map<String, Object> getUserInfoByWechatId(String wechatId);//根据微信Id得到用户信息
	
	//获得菜单权限
	List<Map<String, Object>> getMenuList(Map<String, Object> data);
	//角色
	List<Map<String, Object>> getRoleList(Map<String, Object> data) ;

	List<Map<String, Object>>  findFactFunByRole(Map<String, Object> data);

	List<Map<String, Object>> getFunctionListAll();
	//功能
	void insertFunction(Map<String, Object> data) ;


	void updateFunction(Map<String, Object> data) ;

	void deleteFunction(Map<String, Object> data) ;

	List<LinkedHashMap<String, Object>> getFunctionList(Map<String, Object> data) ;

	int getFunctionListNum(Map<String, Object> data) ;
	

	
	//角色功能
	void saveRoleFunction(Map<String, Object> data);

	void deleteRoleFunction(Map<String, Object> data);

	void editRoleFunction(String roleid, String funids, List<String> ids);
	

//	物料信息查询
	List<Map<String, Object>> getMaterialList(Map<String, Object> data);

	int insertMaterial(Map<String, Object> data);

	void updateMaterial(Map<String, Object> data);

	int getMaterialListNum(Map<String, Object> data);

	//根据物料下载记录查询用户
	List<Map<String, Object>> getUserInfoByMater(Map<String, Object> data);

	List<Map<String, Object>> getMaterByUserid(Map<String, Object> data);

	String getMaterId(Map<String, Object> data);

	void insertMarterUser(Map<String, Object> data);

	void updateMaterDownload(Map<String, Object> data);
	
	//咨询解答
	List<Map<String, Object>> getQuestionList(Map<String, Object> data);

	int getQuestionListNum(Map<String, Object> data);

	void insertAnswer(Map<String, Object> data);

	void updateQuestionStatus(Map<String, Object> data);

	void insertQuestion(Map<String, Object> data);
	//经销商区域字典表
	List<Map<String, Object>> getAllParentArea(Map<String, Object> data);
	//下载详情
	List<Map<String, Object>> getUserInfoByload(Map<String, Object> data);
	
	//修改密码
	void updatePassword(Map<String, Object> data);
	
	void updateRole(Map<String, Object> data);
	
	public  HSSFWorkbook createHSSFWorkbookForQuestion(Map<String, Object> pvMap);
	public long idoShareQuestion(Integer questionId, String departmentIds);
	public long idoDeleteQuestionAndAnswer(Integer questionId);
	public long idoCloseQuestion(Integer questionId);
	public Boolean checkRole(String role);
	public Boolean existQuestionByUserIdAndContent(String userId, String content);
	public List<Map<String, Object>>getAnswerDetailByQuestionById(Integer questionId);
	public List<Map<String, Object>> getPersonalQuestionList(Map<String, Object> data);
}
