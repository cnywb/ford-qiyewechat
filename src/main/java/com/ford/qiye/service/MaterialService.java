package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtMaterial;
import com.ford.qiye.model.DtMaterialUser;
import com.ford.qiye.model.DtUser;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglijun on 16/8/9.
 */
public interface MaterialService {


    List<DtMaterial> findByUserId(String userId);

    /***
     *
     * @param userId
     * @param status
     * @return
     */
    List<DtMaterial> findByUserId(String userId,Integer status);

    /***
     *
     * @param page
     * @param name
     * @param status
     * @param departId
     * @param startDate
     * @param endDate
     * @return
     */

    Pagination<DtMaterial> queryPage(PageGrid page, String name, Integer status, Long departId,
                                     Date startDate, Date endDate);


    /***
     * 更新状态
     * @param id
     * @param status
     */
    void updateStatus(Long id,Integer status);

    /***
     * 根据用户查询物料下载清单
     * @param page
     * @param realName
     * @param startDate
     * @param endDate
     * @param materialId
     * @param isDownload
     * @return
     */
    Pagination<DtUser> findMaterialByUser(PageGrid page,String realName,
                                  Date startDate,Date endDate,
                                    Long  materialId,Integer isDownload);

    /***
     * 查询用户信息
     * @param pageGrid
     * @param realName
     * @param startDate
     * @param endDate
     * @param materialId
     * @param isAgency
     * @return
     */
    Pagination<DtUser> findUserByMaterialId(PageGrid pageGrid, String realName, Date startDate, Date endDate, Long materialId, String isAgency,Integer isDownload);

    /***
     * 查询物料是否被下发过了
     * @param materialId
     * @param userId
     */
    DtMaterialUser findByUserId(Long materialId,String userId);

    void saveMaterialUser(DtMaterialUser materialUser);

    void updateDownload(Long id);

    DtMaterial findById(Long id);

    /***
     * 保存
     * @param dtMaterial
     */
    Long insert(DtMaterial dtMaterial);

    /**
     * @param params
     */
     List<DtUser> findMaterialByUser(Map<String, Object> params);
}
