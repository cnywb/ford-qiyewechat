package com.fute.backer.dao;


import com.fute.backer.model.Answer;
import com.fute.backer.model.Question;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public interface SystemMapper {

	//登录验证
	Map<String, Object> getUserInfo(Map<String, Object> data);
	
	public Map<String, Object>  getUserInfoById(Map<String, Object> data);
	
	public Map<String, Object>  getUserInfoByWechatId(Map<String, Object> data);
	
	
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
	Question getQuestionById(int id);
	void addQuestion(Question t);
	Integer countQuestionByDepartmentIdAndContent(Map<String, Object> data);
	Integer getQuestionIdByDepartmentIdAndContent(Map<String, Object> data);
	void addAnswer(Answer t);
	public List<Answer> getAnswerByQuestionById(int questionId);
	void deleteQuestion(int questionId);
	void deleteAnswer(int questionId);
	Integer countQuestionByUserIdAndContent(Map<String, Object> data);
	public void deleteQuestionByUserId(String userId);
	public void deleteAnswerByQuestionId(Integer questionId);
	public List<Question>getQuestionByUserId(String userId);
	public List<Map<String, Object>>getAnswerDetailByQuestionById(Integer questionId);
	
	
	//经销商区域字典表
	List<Map<String, Object>> getAllParentArea(Map<String, Object> data);
	//下载详情
	List<Map<String, Object>> getUserInfoByload(Map<String, Object> data);
	//修改密码
	void updatePassword(Map<String, Object> data);
	
	void updateRole(Map<String, Object> data);
	
	
}