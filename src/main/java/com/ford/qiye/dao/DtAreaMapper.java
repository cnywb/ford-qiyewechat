package com.ford.qiye.dao;

import com.ford.qiye.model.Area;
import com.ford.qiye.model.AreaTree;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/7.
 */
public interface DtAreaMapper {

    /**查询所有地区信息*/
    List<AreaTree>  getAllArea();

    /**根据父级ID*/
    List<AreaTree> queryByParentId(@Param ("parentId") Long parentId);

    /**
     * 根据ID查询
     * */
    Area findById(@Param("id")Long id);

    /***
     * 新增
     * @param parentId
     * @param name
     * @param code
     * @param remark
     * @param ifActive
     * @param priority
     * @return
     */
    Long insert(@Param("parentId")Long parentId,@Param("name")String name,@Param("code")String code,@Param("remark")String remark,@Param("ifActive")Integer ifActive,@Param("priority")Integer  priority);

    /***
     * 删除
     * @param id
     * @param parentId
     * @param name
     * @param code
     * @param remark
     * @param ifActive
     * @param priority
     */
    void update(@Param("id")Long id,@Param("parentId")Long parentId,@Param("name")String name,@Param("code")String code,@Param("remark")String remark,@Param("ifActive")Integer ifActive,@Param("priority")Integer  priority);

    /***
     * 删除
     * @param id
     */
    void delete(@Param("id")Long id);
}
