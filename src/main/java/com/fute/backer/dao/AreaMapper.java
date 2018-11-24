package com.fute.backer.dao;

import java.util.List;
import java.util.Map;

import com.fute.backer.model.Area;

public interface AreaMapper {
	
	public List<Area> getAllAreaByParentId(Integer parentId);

	public List<Area>getAllArea();
	
	public void addArea(Area t);
	
	public void updateArea(Area t);
	
	public void deleteAreaById(Integer id);
	
	public Integer countChildAreaByParentId(Integer parentId);
	
	public Integer countUsedAreaById(Integer id);
	
	public Area getAreaByCode(String code);
	
	public Area getAreaByCodeNotWithId(Map<String, Object> data);
	
}
