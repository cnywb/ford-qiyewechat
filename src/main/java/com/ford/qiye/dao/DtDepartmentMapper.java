package com.ford.qiye.dao;

import com.ford.qiye.model.DtDepartment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by wanglijun on 16/8/8.
 */
public interface DtDepartmentMapper {

    /***
     * 查看所有部门
      * @return  List<Department>
     */
   List<DtDepartment> getDepartListAll();

    /***
     * 根据ID查询部门
     * @param id
     * @return
     */
   List<DtDepartment> findById(@Param ("id") Long id);

    /***
     * 根据部门查询
      * @param departName
     * @return
     */
   List<DtDepartment> findByName(@Param ("departName") String  departName,@Param ("id") Long id);

    /***
     *
     * @param id
     */
   void delete(@Param ("id") Long id);

    /**
     *
     * @param department
     * @return
     */
    Long insert(DtDepartment department);

    /***
     *
     * @param id
     * @param departName
     * @param ifActive
     * @param parentId
     */
    void update(@Param ("id") Long id,@Param ("departName") String departName,@Param ("ifActive") Integer ifActive,@Param ("parentId") Long parentId);

    /***
     *
     * @param id
     * @param excludeId
     * @return
     */
    List<DtDepartment> queryMyDepartList(@Param ("id") Long id,@Param ("excludeId") Long excludeId);
}
