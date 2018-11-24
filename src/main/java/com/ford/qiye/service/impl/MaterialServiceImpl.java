package com.ford.qiye.service.impl;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtMaterialMapper;
import com.ford.qiye.dao.DtMaterialUserMapper;
import com.ford.qiye.dao.DtUserMapper;
import com.ford.qiye.model.DtMaterial;
import com.ford.qiye.model.DtMaterialUser;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.service.MaterialService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglijun on 16/8/9.
 */
@Service
public class MaterialServiceImpl  implements MaterialService{


    @Autowired
    private DtMaterialMapper materialMapper;

    @Autowired
    private DtUserMapper userMapper;


    @Autowired
    private DtMaterialUserMapper materialUserMapper;


    /***
     * 查询物料是否被下发过了
     *
     * @param materialId
     * @param userId
     */
    @Override
    public DtMaterialUser findByUserId(Long materialId, String userId) {
        return materialUserMapper.findByUserId(materialId,userId);
    }

    /***
     * @param userId
     * @param status
     * @return
     */
    @Override
    public List<DtMaterial> findByUserId(String userId, Integer status) {
            return this.materialMapper.findByUserId (userId,null,status);
    }

    @Override
    public List<DtMaterial> findByUserId(String userId) {
        return this.materialMapper.findByUserId (userId,1,0);
    }

    /**
     * @param params
     */
    @Override
    public List<DtUser> findMaterialByUser(Map<String, Object> params) {
        return this.userMapper.findMaterial(params);
    }

    /***
     * 保存
     *
     * @param dtMaterial
     */
    @Override
    public Long insert(DtMaterial dtMaterial) {
        materialMapper.insert(dtMaterial);
        return dtMaterial.getId ();
    }

    @Override
    public DtMaterial findById(Long id) {
        List<DtMaterial> list=this.materialMapper.findById(id);
        if(CollectionUtils.isEmpty (list)){
            return null;
        }
        return list.get (0);
    }

    @Override
    public void saveMaterialUser(DtMaterialUser materialUser) {
        materialUserMapper.insert(materialUser);
    }

    @Override
    public void updateDownload(Long id) {
        materialMapper.updateDownload(id);
    }

    @Override
    public Pagination<DtMaterial> queryPage(PageGrid page,String name, Integer status, Long departId, Date startDate, Date endDate) {
        Page<DtMaterial> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<DtMaterial> list=materialMapper.queryList (name,status,departId,startDate,endDate);

        return new Pagination<> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * 更新状态
     *
     * @param id
     * @param status
     */
    @Override
    public void updateStatus(Long id, Integer status) {
         this.materialMapper.updateStatus (id,status);
    }

    /***
     * 根据用户查询物料下载清单
     *
     * @param page
     * @param realName
     * @param startDate
     * @param endDate
     * @param materialId
     * @param isDownload
     * @return
     */
    @Override
    public Pagination<DtUser> findMaterialByUser(PageGrid page, String realName, Date startDate, Date endDate, Long materialId, Integer isDownload) {
        Page<DtUser> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<DtUser> list=userMapper.findMaterialByUser (realName,startDate,endDate,null,null,null,null,null,materialId,isDownload);

        return new Pagination<> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * 查询用户信息
     *
     * @param page
     * @param realName
     * @param startDate
     * @param endDate
     * @param materialId
     * @param isAgency
     * @return
     */
    @Override
    public Pagination<DtUser> findUserByMaterialId(PageGrid page, String realName, Date startDate, Date endDate, Long materialId, String isAgency,Integer isDownload) {
        Page<DtUser> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<DtUser> list=userMapper.findUserByMaterialId (realName,startDate,endDate,null,null,null,null,null,materialId,isAgency,isDownload);

        return new Pagination<> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }

}
