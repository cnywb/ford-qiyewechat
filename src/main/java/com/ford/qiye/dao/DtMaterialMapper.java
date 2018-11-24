package com.ford.qiye.dao;

import com.ford.qiye.model.DtMaterial;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/9.
 */
public interface DtMaterialMapper {
    /***
     *
     * @param name
    * @param status
    * @param departId
    * @param startDate
    * @param endDate
    * @return  List<DtMaterial>
    */
    List<DtMaterial> queryList(@Param ("name") String name,@Param ("status") Integer status,@Param ("departId") Long departId,
                               @Param ("startDate")Date startDate, @Param ("endDate")Date endDate);

    /***
     * 更新状态
     * @param id
     * @param status
     */
    void updateStatus(@Param ("id") Long id,@Param ("status") Integer status);

    /***
     *
     * @param id
     * @return
     */
    List<DtMaterial> findById(@Param ("id") Long id);

    void updateDownload(@Param ("id") Long id);

    /***
     *
     * @param material
     * @return
     */
    Long insert(DtMaterial material);

    /***
     *
     * @param userId
     * @param isDownload
     * @param status
     * @return
     */
    List<DtMaterial> findByUserId(@Param("userId")String userId,@Param("isDownload")Integer isDownload,@Param ("status") Integer status);
}
