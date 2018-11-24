package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtDepartmentMapper;
import com.ford.qiye.model.DtDepartment;
import com.ford.qiye.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * Created by wanglijun on 16/8/8.
 */
@Service
public class DepartmentServiceImpl implements DepartmentService {

    @Autowired
    private DtDepartmentMapper departmentMapper;


    /***
     * @param id
     */
    @Override
    public void delete(Long id) {
        this.departmentMapper.delete (id);
    }

    /***
     * 根据部门名称和ID
     *
     * @param departName
     * @param id
     * @return
     */
    @Override
    public Integer findByName(String departName, Long id) {
            List<DtDepartment> list=this.departmentMapper.findByName(departName,id);
            if(CollectionUtils.isEmpty (list)){
                return 0;
            }
            return list.size ();
    }

    /***
     * 新增
     *
     * @param department
     * @return
     */
    @Override
    public Long insert(DtDepartment department) {
          this.departmentMapper.insert (department);
          return department.getId ();
    }

    /***
     * 更新
     *
     * @param department
     */
    @Override
    public void update(DtDepartment department) {
        this.departmentMapper.update (department.getId (),department.getDepartName (),
                department.getIfActive (),
                department.getParentId ());
    }

    /***
     * 根据部门查询
     *
     * @param id
     * @return
     */
    @Override
    public DtDepartment findById(Long id) {
        List<DtDepartment>  list=departmentMapper.findById (id);
        if(CollectionUtils.isEmpty (list)){
            return null;
        }
        return list.get (0);
    }

    /***
     * 查看所有部门
     *
     * @return List<Department>
     */
    @Override
    public List<DtDepartment> queryDepartmentAll() {
        return departmentMapper.getDepartListAll ();
    }


    /***
     * 查询我的部门信息;
     *
     * @param id
     * @return
     */
    @Override
    public List<DtDepartment> queryMyDepartList(Long id) {
        return departmentMapper.queryMyDepartList (id,1L);
    }
}
