package com.ford.qiye.service;

import com.ford.qiye.model.DtDepartment;

import java.util.List;

/**
 * Created by wanglijun on 16/8/8.
 */
public interface DepartmentService {

    /***
     * 查看所有部门
     * @return  List<Department>
     */
    List<DtDepartment> queryDepartmentAll();

    /***
     * 查询我的部门信息;
     * @param id
     * @return
     */
    List<DtDepartment> queryMyDepartList(Long id);

    /***
     * 根据部门查询
     * @param id
     * @return
     */
    DtDepartment findById(Long id);

    /***
     *
     * @param id
     */
    void delete(Long id);

    /***
     * 根据部门名称和ID
     * @param departName
     * @param id
     * @return
     */
    Integer findByName(String departName,Long id);

    /***
     * 新增
     * @param department
     * @return
     */
    Long insert(DtDepartment department);

    /***
     * 更新
     * @param department
     */
    void update(DtDepartment department);
}
