package com.fute.backer.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.fute.backer.dao.AreaMapper;
import com.fute.backer.model.Area;
import com.fute.backer.service.AreaService;



@Service("areaService")
public class AreaServiceImpl implements AreaService {
	
	@Resource()
	private AreaMapper areaMapper;

	@Override
	public List<Area> getAllAreaByParentId(Integer parentId) {
		// TODO Auto-generated method stub
		return areaMapper.getAllAreaByParentId(parentId);
	}

	@Override
	public List<Area> getAllArea() {
		// TODO Auto-generated method stub
		return areaMapper.getAllArea();
	}

	@Override
	public long idoAddArea(Area t) {
		Area dt=areaMapper.getAreaByCode(t.getCode());
		if(dt!=null){
			return 0L;
		}
		if(t.getIfActive()==null){
			t.setIfActive(1);
		}
		if(t.getPriority()==null){
			t.setPriority(1);
		}
		areaMapper.addArea(t);
		return 1L;
	}

	@Override
	public long idoUpdateArea(Area t) {
		Map<String, Object> data=new HashMap<String,Object>();
		data.put("code", t.getCode());
		data.put("id", t.getId());
		Area dt=areaMapper.getAreaByCodeNotWithId(data);
		if(dt!=null){
			return 0L;
		}
		if(t.getIfActive()==null){
			t.setIfActive(1);
		}
		if(t.getPriority()==null){
			t.setPriority(1);
		}
		areaMapper.updateArea(t);
		return 1L;
	}

	@Override
	public long idoDeleteAreaById(Integer id) {
		Integer countChild=areaMapper.countChildAreaByParentId(id);
		if(countChild>0){
			return 0L;
		}
		Integer countUsed=areaMapper.countUsedAreaById(id);
		if(countUsed>0){
			return 1L;
		}
		areaMapper.deleteAreaById(id);
		return 2L;
		
	}

	@Override
	public Integer countChildAreaByParentId(Integer parentId) {
		return areaMapper.countChildAreaByParentId(parentId);
	}

	@Override
	public Integer countUsedAreaById(Integer id) {
	
		return areaMapper.countUsedAreaById(id);
	}
	
	
	

}
