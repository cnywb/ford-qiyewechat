package com.ford.qiye.service;

import com.ford.qiye.model.Area;
import com.ford.qiye.model.AreaTree;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface AreaService {
    /***
     * 查询所有区域
     * @return
     */
    List<AreaTree> queryAllArea();

    /***
     * 根据父级ID查询区域信息
     * @param parentId
     * @return
     */
    List<AreaTree> queryByParentId(Long parentId);

    /**
     * id
     * */
    Area findById(Long id);

    /***
     * 新增
     * @param area
     * @return
     */
    Long insert(Area area);

    /***
     * 删除
     * @param area
     * @return
     */
    void update(Area area);

    /***
     * 删除
     * @param id
     */
    void delete(Long id);
}
