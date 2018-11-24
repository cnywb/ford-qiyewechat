package com.ford.qiye.dao;

import com.ford.qiye.model.DtUser;
import com.ford.qiye.model.UserAuthList;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 用户管理
 * Created by wanglijun on 16/8/5.
 */
public interface DtUserMapper {


    List<DtUser> findAll();

    /***
     * 更新openId
     * @param id
     * @param openId
     */
    void updateOpenIdById(@Param ("id")String id,@Param ("openId") String openId);

    /***
     * 企业号
     * @param wxNum
     * @return
     */
    List<DtUser> findByWxNum(@Param ("wxNum")String wxNum);



    List<DtUser> findById(@Param ("id")String id);

    Long insertDealer(@Param ("userName")String userName,
                      @Param ("realName")String realName,
                      @Param("sex")Integer sex,
                      @Param ("position")String position,
                      @Param ("email")String email,
                      @Param ("phone")String phone,
                      @Param ("areaName")String areaName,
                      @Param ("smallName")String smallName,
                      @Param ("serveCode")String serveCode,
                      @Param ("sellCode")String sellCode,
                      @Param ("status")Integer status,
                      @Param ("isAgency")String isAgency,
                      @Param("headImage")String headImage,
                      @Param("wxNum")String wxNum);


    Long insertEmployee(@Param ("userName")String userName,
                        @Param ("realName")String realName,
                        @Param("sex")Integer sex,
                        @Param ("position")String position,
                        @Param ("email")String email,
                        @Param ("phone")String phone,
                        @Param ("status")Integer status,
                        @Param("headImage")String headImage,
                        @Param("wxNum")String wxNum,
                        @Param("password") String password,
                        @Param("departId") Long  departId,
                        @Param("departName") String departName,@Param("credentialExpiredDate") Date credentialExpiredDate);

    void updateDealer(@Param ("id")String id,@Param ("userName")String userName,@Param ("realName")String realName,
                      @Param("sex")Integer sex,@Param ("position")String position,
                      @Param ("email")String email,@Param ("phone")String phone,
                      @Param ("areaName")String areaName,@Param ("smallName")String smallName,
                      @Param ("serveCode")String serveCode,@Param ("sellCode")String sellCode,@Param("headImage")String headImage,
                      @Param("wxNum")String wxNum,
                      @Param("password") String password,
                      @Param("departId") Long  departId,
                      @Param("departName") String departName
                      );

    void updateUser(@Param ("id")String id,@Param ("userName")String userName,@Param ("realName")String realName,
                      @Param("sex")Integer sex,@Param ("position")String position,
                      @Param ("email")String email,@Param ("phone")String phone,
                      @Param ("areaName")String areaName,@Param ("smallName")String smallName,
                      @Param ("serveCode")String serveCode,@Param ("sellCode")String sellCode,@Param("headImage")String headImage,
                      @Param("wxNum")String wxNum,
                      @Param("password") String password,
                      @Param("departId") Long  departId,
                      @Param("departName") String departName,@Param("credentialExpiredDate") Date credentialExpiredDate
    );

    /***
     * 根据用户名称
     * @param userName
     * @return
     */
    List<DtUser> queryUsersByUserName(@Param ("userName") String userName);

    /***
     * 更新密码
     * @param id
     * @param password
     * @param credentialExpiredDate
     */
    void updatePassword(@Param("id") String id, @Param("password") String password,  @Param("credentialExpiredDate")Date credentialExpiredDate);

    List<UserAuthList> findUserAuthPage(@Param ("userName") String userName,
                                        @Param ("roleName") String roleName,
                                        @Param ("functionName") String functionName);

    /***
     * @param departId
     * @param position
     * @param areaName
     * @param smallName
     * @param serveCode
     * @param sellCode
     * @param markId
     * @return
     */
    List<DtUser> findUserByDepartId(@Param ("departId") Long departId,
                                    @Param ("position")String position,
                                    @Param ("areaName")String areaName,
                                    @Param ("smallName")String smallName,
                                    @Param ("serveCode")String serveCode,
                                    @Param ("sellCode")String sellCode,
                                    @Param ("markId") Long markId);

    List<DtUser> findMaterialByUser(@Param ("realName")String realName,
                                    @Param ("startDate")Date startDate, @Param ("endDate")Date endDate,
                                    @Param ("areaName")String areaName, @Param ("smallName")String smallName,
                                    @Param ("serveCode")String serveCode, @Param ("sellCode")String sellCode,
                                    @Param ("position")String position,
                                    @Param ("materialId")Long  materialId,@Param ("isDownload") Integer isDownload);


    List<DtUser> findUserByMaterialId(@Param ("realName")String realName,
                                    @Param ("startDate")Date startDate, @Param ("endDate")Date endDate,
                                    @Param ("areaName")String areaName, @Param ("smallName")String smallName,
                                    @Param ("serveCode")String serveCode, @Param ("sellCode")String sellCode,
                                    @Param ("position")String position,
                                    @Param ("materialId")Long  materialId,@Param ("isAgency") String  isAgency,@Param ("isDownload") Integer isDownload);

    /***
     * 查询经销商
     * @param realName
     * @param userName
     * @param position
     * @param email
     * @param phone
     * @param departId
     * @param isAgency
     * @param serveCode
     * @param sellCode
     * @return
     */
    List<DtUser> findByPage(@Param ("realName") String realName,@Param ("userName") String userName,@Param ("position") String
            position,@Param ("email")String email,@Param ("phone")String phone,@Param ("departId")Long departId,
                                 @Param ("isAgency")String isAgency,@Param ("serveCode")String serveCode,@Param ("sellCode")String sellCode,
                            @Param ("userId")String userId);

    void deleteById(@Param ("id") String id);

    /***
     *
     * @param params
     * @return
     */
    List<DtUser> findMaterial(Map<String, Object> params);

    List<Map<String, Object>> findAllObjectMap(@Param ("realName") String realName,@Param ("userName") String userName,@Param ("position") String
            position,@Param ("email")String email,@Param ("phone")String phone,@Param ("departId")Long departId,
                                               @Param ("isAgency")String isAgency,@Param ("serveCode")String serveCode,@Param ("sellCode")String sellCode,
                                               @Param ("userId")String userId);
}
