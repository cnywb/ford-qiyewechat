package com.fute.backer.service;

import java.util.List;

import com.fute.backer.model.Area;

public interface AreaService {
	public List<Area> getAllAreaByParentId(Integer parentId);

	public List<Area>getAllArea();
	
	public long idoAddArea(Area t);
	
	public long idoUpdateArea(Area t);
	
	public long idoDeleteAreaById(Integer id);
	
	public Integer countChildAreaByParentId(Integer parentId);
	
	public Integer countUsedAreaById(Integer id);
}
