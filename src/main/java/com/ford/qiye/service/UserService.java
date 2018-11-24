package com.ford.qiye.service;

import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.model.UserAuthList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import java.util.Date;
import java.util.List;

/**
 * Created by wanglijun on 16/8/10.
 */
public interface UserService {


    DtUser findById(String id);

    void updateOpenIdById(String id, String openId);

    /**
     *
     * @param wxNum
     * @return
     */
    DtUser findByWxNum(String wxNum);

    /**插入*/
    void insertDealer(DtUser user);

    /**更新*/
    void updateDealer(DtUser user);

    void insertEmployee(DtUser user);

    /***
     * 删除用户
     * @param id
     */
    void delete(String id);

    /***
     *  @param id
     * @param password
     * @param credentialExpiredDate
     */
    void updatePassword(String id, String password, Date credentialExpiredDate);

    /***
     * 查询所有用户
     * @return
     */
    List<DtUser> findAll();


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
    List<DtUser> findUserByDepartId(Long departId,
                                    String position,
                                    String areaName,
                                    String smallName,
                                    String serveCode,
                                    String sellCode,
                                    Long markId);

    /***
     * 查询经销商
     * @param page
     * @param realName
     * @param userName
     * @param position
     * @param email
     * @param phone
     * @param departId
     * @param isAgency
     * @param serveCode
     * @param sellCode
     * @param  userId
     * @return Pagination<DtUser>
     */
    Pagination<DtUser> queryPage(PageGrid page, String realName, String userName, String  position, String email, String phone, Long departId,
                                 String isAgency, String serveCode, String sellCode,String userId);

    /***
     *
     * @param page
     * @param realName
     * @param userName
     * @param position
     * @param email
     * @param departId
     * @param  isAgency
     * @return
     */
    Pagination<DtUser> queryPage(PageGrid page, String realName, String userName, String  position, String email,Long departId,String isAgency);

    /***
     *
     * @param pageGrid
     * @param userName
     * @param roleName
     * @param functionName
     * @return
     */
    Pagination<UserAuthList> findUserAuthPage(PageGrid pageGrid, String userName, String roleName, String functionName);

    HSSFWorkbook createHSSFWorkbook( String realName,String userName,String position,String email,Long departId);
}
