package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtAreaMapper;
import com.ford.qiye.model.Area;
import com.ford.qiye.model.AreaTree;
import com.ford.qiye.service.AreaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
@Service
public class AreaServiceImpl implements AreaService {

    @Autowired
    private DtAreaMapper dtAreaMapper;

    /***
     * 查询所有区域
     *
     * @return
     */
    @Override
    public List<AreaTree> queryAllArea() {
        return dtAreaMapper.getAllArea ();
    }


    /***
     * 根据父级ID查询区域信息
     *
     * @param parentId
     * @return
     */
    @Override
    public List<AreaTree> queryByParentId(Long parentId) {
        return dtAreaMapper.queryByParentId (parentId);
    }


    /***
     * 删除
     *
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.dtAreaMapper.delete (id);
    }

    /**
     * 根据ID查询
     *
     * @param id
     */
    @Override
    public Area findById(Long id) {
        return this.dtAreaMapper.findById (id);
    }

    /***
     * 新增
     *
     * @param area
     * @return
     */
    @Override
    public Long insert(Area area) {
        return this.dtAreaMapper.insert (area.getParentId (),area.getName (),area.getCode (),area.getRemark (),area.getIfActive (),area.getIfActive ());
    }

    /***
     * 删除
     *
     * @param area
     * @return
     */
    @Override
    public void update(Area area) {
        this.dtAreaMapper.update (area.getId (),area.getParentId (),area.getName (),area.getCode (),area.getRemark (),area.getIfActive (),area.getIfActive ());
    }
}
