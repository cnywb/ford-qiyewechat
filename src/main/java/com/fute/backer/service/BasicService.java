package com.fute.backer.service;

import java.util.List;
import java.util.Map;

public interface BasicService {
	//员工信息录入及部门管理
	List<Map<String, Object>> getEmployeeList(Map<String, Object> data);
	int getEmployeeListNum(Map<String, Object> data);
	
	void insertEmployee(Map<String, Object> data);
	
	void updateEmployee(Map<String, Object> data);
	
	void deleteEmployee(Map<String, Object> data);
	
	List<Map<String, Object>> getDepartListAll();
	
	Map<String, Object> getEmployee(Map<String, Object> data);
	
	//查询所有标签
	List<Map<String, Object>> getMarkListAll();
	
	//添加标签
	void editMarkEmployee(String id, String markids, List<String> markidlist);
	//给员工添加角色
	void editRoleEmployee(String id, String roleids, List<String> roleidlist);
	
	//标签管理
	List<Map<String, Object>> getMarkList(Map<String, Object> data);
	int getMarkListNum(Map<String, Object> data);
	
	void insertMark(Map<String, Object> data);
	
	void updateMark(Map<String, Object> data);
	
	void deleteMark(Map<String, Object> data);
	
	//消息管理
	List<Map<String, Object>> getMessageList(Map<String, Object> data);

	int getMessageListNum(Map<String, Object> data);
	
	void sendMessage(Map<String, Object> data);
	
	void deleteMessage(Map<String, Object> data);
	
	void insertMessage(Map<String, Object> data);

	//获取下级部门`
	List<Map<String, Object>> getDepartListByparentid(Map<String, Object> data);

	//根据部门获取员工及部门下级员工
	List<Map<String, Object>> getEmployeelistBydepartid(Map<String, Object> data);
	
	List<Map<String, Object>> getEmployeeMarkByDepartid(Map<String, Object> data);
	
	//添加图文信息
	void insertImageContent(Map<String, Object> data);
	
	//得到图文信息 id
	String getImageContentId(Map<String, Object> data);

	List<Map<String, Object>> getMarkListByUserid(Map<String, Object> data);
	
	//部门编辑
	void insertDepart(Map<String, Object> data);
	
	void updateDepart(Map<String, Object> data);
	
	void deleteDepart(Map<String, Object> data);
	
	String getDepartId(Map<String, Object> data);
	//获取所以角色
	List<Map<String, Object>> getRoleListAll(Map<String, Object> data);
	
	//应用管理
	List<Map<String, Object>> getAppList(Map<String, Object> data);
	int getAppListNum(Map<String, Object> data);
	
	void insertApp(Map<String, Object> data);
	
	void updateApp(Map<String, Object> data);
	
	void deleteApp(Map<String, Object> data);
	
	List<Map<String, Object>> getEmployeeListAll();
	
	//获取用户部门
	Map<String, Object> getDepartIdByUserid(Map<String, Object> data);
	
	//获取当前用户的部门
	List<Map<String, Object>> getMyDepartList(Map<String, Object> data);
	//获取应用id
	Map<String, Object> getAppIdByDepartId(Map<String, Object> data);
	
	Map<String, Object> getDepartInfo(Map<String, Object> data);
	
	Map<String, Object> getMaterUser(Map<String, Object> data);
	
	public void idoDeleteAgencyBatch(Map<String, Object> data);
}

