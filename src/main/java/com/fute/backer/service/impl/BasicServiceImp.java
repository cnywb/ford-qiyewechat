package com.fute.backer.service.impl;

import com.fute.backer.dao.BasicMapper;
import com.fute.backer.dao.SystemMapper;
import com.fute.backer.model.Question;
import com.fute.backer.service.BasicService;
import com.github.pagehelper.PageHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BasicServiceImp implements BasicService {
	private BasicMapper basicMapper;
	
	private SystemMapper systemMapper;

	public BasicMapper getBasicMapper() {
		return basicMapper;
	}

	public void setBasicMapper(BasicMapper basicMapper) {
		this.basicMapper = basicMapper;
	}

	public SystemMapper getSystemMapper() {
		return systemMapper;
	}

	public void setSystemMapper(SystemMapper systemMapper) {
		this.systemMapper = systemMapper;
	}

	@Override
	public void deleteEmployee(Map<String, Object> data) {
		this.basicMapper.deleteEmployee(data);
	}

	@Override
	public List<Map<String, Object>> getEmployeeList(Map<String, Object> data) {
		return this.basicMapper.getEmployeeList(data);
	}

	@Override
	public int getEmployeeListNum(Map<String, Object> data) {
		return this.basicMapper.getEmployeeListNum(data);
	}

	@Override
	public void insertEmployee(Map<String, Object> data) {
		this.basicMapper.insertEmployee(data);
	}

	@Override
	public void updateEmployee(Map<String, Object> data) {
		this.basicMapper.updateEmployee(data);
	}

	@Override
	public List<Map<String, Object>> getDepartListAll() {
		return this.basicMapper.getDepartListAll();
	}

	@Override
	public void deleteMark(Map<String, Object> data) {
		// TODO Auto-generated method stub
		this.basicMapper.deleteMark(data);
	}

	@Override
	public List<Map<String, Object>> getMarkList(Map<String, Object> data) {
		return this.basicMapper.getMarkList(data);
	}

	@Override
	public int getMarkListNum(Map<String, Object> data) {
		return this.basicMapper.getMarkListNum(data);
	}

	@Override
	public void insertMark(Map<String, Object> data) {
		this.basicMapper.insertMark(data);
	}

	@Override
	public void updateMark(Map<String, Object> data) {
		this.basicMapper.updateMark(data);
	}

	@Override
	public void deleteMessage(Map<String, Object> data) {
		this.basicMapper.deleteMessage(data);
	}

	@Override
	public List<Map<String, Object>> getMessageList(Map<String, Object> data) {
		PageHelper.startPage (1,10);
		return this.basicMapper.getMessageList(data);
	}

	@Override
	public int getMessageListNum(Map<String, Object> data) {
		return this.basicMapper.getMessageListNum(data);
	}

	@Override
	public void sendMessage(Map<String, Object> data) {
		this.basicMapper.sendMessage(data);
	}

	@Override
	public List<Map<String, Object>> getMarkListAll() {
		return this.basicMapper.getMarkListAll();
	}

	@Override
	public void editMarkEmployee(String id, String markids,
			List<String> markidlist) {
		String[] jiumarkids = markids.split(",");  
		List<Map<String, Object>> deletelist = new ArrayList<Map<String,Object>>(); 

		Map<String, Object> data = new HashMap<String, Object>();
		if(markids!=null && !markids.equals("")){
			for(String i:jiumarkids){
				boolean ok = true;
				for(String j:markidlist){
					if(i.equals(j)){
						ok= false;
					}
				}
				if(ok){
					Map<String, Object> a=new HashMap<String, Object>();
					a.put("markid", i);
					deletelist.add(a);
				}
			}
			if(deletelist.size()>0){
				data.put("id", id);
				data.put("list", deletelist);
				basicMapper.deleteUserMark(data);
			}
		}
		data.put("id", id);
		basicMapper.deleteUserMark(data);
		List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
		for(String i:markidlist){
				Map<String, Object> dat = new HashMap<String, Object>();
				dat.put("id", id);
				dat.put("markid", i);
				list.add(dat);
		}
		if(list.size()>0){
			data.put("userMarkList", list);
			System.out.println("----------------------------------"+list);
			basicMapper.saveUserMark(data);
		}
	}

	@Override
	public List<Map<String, Object>> getDepartListByparentid(
			Map<String, Object> data) {
		return this.basicMapper.getDepartListByparentid(data);
	}

	@Override
	public List<Map<String, Object>> getEmployeelistBydepartid(
			Map<String, Object> data) {
		return this.basicMapper.getEmployeelistBydepartid(data);
	}

	@Override
	public List<Map<String, Object>> getEmployeeMarkByDepartid(
			Map<String, Object> data) {
		return this.basicMapper.getEmployeeMarkByDepartid(data);
	}

	@Override
	public void insertImageContent(Map<String, Object> data) {
		this.basicMapper.insertImageContent(data);
	}

	@Override
	public String getImageContentId(Map<String, Object> data) {
		return this.basicMapper.getImageContentId(data);
	}

	@Override
	public List<Map<String, Object>> getMarkListByUserid(
			Map<String, Object> data) {
		return this.basicMapper.getMarkListByUserid(data);
	}

	@Override
	public void deleteDepart(Map<String, Object> data) {
		this.basicMapper.deleteDepart(data);
	}

	@Override
	public void insertDepart(Map<String, Object> data) {
		this.basicMapper.insertDepart(data);
	}

	@Override
	public void updateDepart(Map<String, Object> data) {
		this.basicMapper.updateDepart(data);
	}

	@Override
	public String getDepartId(Map<String, Object> data) {
		return this.basicMapper.getDepartId(data);
	}

	@Override
	public List<Map<String, Object>> getRoleListAll(Map<String, Object> data) {
		return this.basicMapper.getRoleListAll(data);
	}

	@Override
	public void editRoleEmployee(String id, String roleids,
			List<String> roleidlist) {
			String[] jiuroleids = roleids.split(",");  
			List<Map<String, Object>> deletelist = new ArrayList<Map<String,Object>>(); 
			Map<String, Object> data = new HashMap<String, Object>();
			if(roleids!=null && !roleids.equals("")){
				for(String i:jiuroleids){
					boolean ok = true;
					for(String j:roleidlist){
						if(i.equals(j)){
							ok= false;
						}
					}
					if(ok){
						Map<String, Object> a=new HashMap<String, Object>();
						a.put("roleid", i);
						deletelist.add(a);
					}
				}
				if(deletelist.size()>0){
					data.put("id", id);
					data.put("list", deletelist);
					basicMapper.deleteUserRole(data);
				}
			}
			data.put("id", id);
			basicMapper.deleteUserRole(data);
			List<Map<String, Object>> list = new ArrayList<Map<String,Object>>();
			for(String i:roleidlist){
					Map<String, Object> dat = new HashMap<String, Object>();
					dat.put("id", id);
					dat.put("roleid", i);
					list.add(dat);
			}
			if(list.size()>0){
				data.put("userRoleList", list);
				System.out.println("----------------------------------"+list);
				basicMapper.saveUserRole(data);
			}
	}

	@Override
	public List<Map<String, Object>> getAppList(Map<String, Object> data) {
		return this.basicMapper.getAppList(data);
	}

	@Override
	public int getAppListNum(Map<String, Object> data) {
		return this.basicMapper.getAppListNum(data);
	}

	@Override
	public void deleteApp(Map<String, Object> data) {
		this.basicMapper.deleteApp(data);
	}
	
	@Override
	public void insertApp(Map<String, Object> data) {
		this.basicMapper.insertApp(data);
	}
	
	@Override
	public void updateApp(Map<String, Object> data) {
		this.basicMapper.updateApp(data);
	}

	@Override
	public List<Map<String, Object>> getEmployeeListAll() {
		return this.basicMapper.getEmployeeListAll();
	}

	@Override
	public Map<String, Object> getDepartIdByUserid(Map<String, Object> data) {
		return this.basicMapper.getDepartIdByUserid(data);
	}

	@Override
	public List<Map<String, Object>> getMyDepartList(Map<String, Object> data) {
		return this.basicMapper.getMyDepartList(data);
	}

	@Override
	public Map<String, Object> getAppIdByDepartId(Map<String, Object> data) {
		return this.basicMapper.getAppidByDepartid(data);
	}

	@Override
	public Map<String, Object> getDepartInfo(Map<String, Object> data) {
		return this.basicMapper.getDepartInfo(data);
	}

	@Override
	public void insertMessage(Map<String, Object> data) {
		this.basicMapper.insertMessage(data);
	}

	@Override
	public Map<String, Object> getEmployee(Map<String, Object> data) {
		return this.basicMapper.getEmployee(data);
	}

	@Override
	public Map<String, Object> getMaterUser(Map<String, Object> data) {
		return this.basicMapper.getMaterUser(data);
	}

	@Override
	public void idoDeleteAgencyBatch(Map<String, Object> data) {
		List<Map<String, Object>> list=basicMapper.getEmployeeIdList(data);
		for(Map<String, Object> map:list){
			String userId=map.get("id").toString();
			List<Question> questionList=systemMapper.getQuestionByUserId(userId);
			for(Question question:questionList){
				systemMapper.deleteAnswerByQuestionId(question.getId());//删掉问题的所有答案
			}
			systemMapper.deleteQuestionByUserId(map.get("id").toString());//删掉问题
			basicMapper.deleteUserByUserId(map.get("id").toString());//删掉用户
		}
	}
}