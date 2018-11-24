package com.ford.qiye.service.impl;

import com.alibaba.fastjson.JSON;
import com.ford.qiye.common.ExcelUtils;
import com.ford.qiye.common.PageGrid;
import com.ford.qiye.common.Pagination;
import com.ford.qiye.dao.DtUserMapper;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.model.QiyeUser;
import com.ford.qiye.model.UserAuthList;
import com.ford.qiye.service.AccessTokenService;
import com.ford.qiye.service.UserService;
import com.ford.qiye.web.util.WeChatMemberUtils;
import com.fute.backer.service.SystemParameterService;
import com.fute.wechat.model.auth.AccessToken;
import com.fute.wechat.service.qy.txl.WechatQyUserService;
import com.fute.wechat.util.AccessTokenUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Created by wanglijun on 16/8/10.
 */
@Service
public class UserServiceImpl  implements UserService {

    /***
     * 日志
     */
    private static final Logger logger= LoggerFactory.getLogger (UserServiceImpl.class);


    @Autowired
    private DtUserMapper dtUserMapper;


    @Autowired
    WechatQyUserService wechatQyUserService;


    @Resource
    private SystemParameterService systemParameterService;


    @Autowired
    AccessTokenService accessTokenService;


    @Override
    public void updateOpenIdById(String id, String openId) {
         this.dtUserMapper.updateOpenIdById (id,openId);
    }

    @Override
    public void insertEmployee(DtUser user) {

        Long id=dtUserMapper.insertEmployee (
                user.getUserName (),
                user.getRealName (),
                user.getSex (),
                user.getPosition (),
                user.getEmail (),
                 user.getPhone (),
                 user.getStatus (),
                 user.getHeadImage (),
                 user.getWxNum (),
                 user.getPassword (),
                 user.getDepartId (),user.getDepartName (),user.getCredentialExpiredDate ());

        //创建用户
        QiyeUser qiyeUser=new QiyeUser ();
        qiyeUser.setDepartment (new Long[]{user.getDepartId ()});
        qiyeUser.setUserid (user.getUserName ());
        qiyeUser.setName (user.getRealName ());
        qiyeUser.setPosition (user.getPosition ());
        qiyeUser.setMobile (user.getPhone ());
        qiyeUser.setGender (String.valueOf (user.getSex ()));
        qiyeUser.setEmail (user.getEmail ());
        qiyeUser.setWexinid (user.getWxNum ());
        qiyeUser.setEnable ("1");

        String qiyeUserStr= JSON.toJSONString (qiyeUser);

        logger.info ("qiyeUserStr:{}",qiyeUserStr);

       // String  result=wechatQyUserService.create (JSON.toJSONString (qiyeUserStr));

        WeChatMemberUtils.insertEmployee (user.getUserName (),user.getRealName (),user.getDepartId (),
                user.getPosition (),user.getPhone (),user.getSex (),user.getEmail (),user.getWxNum (),null,accessTokenService.getAccessToken ());

        //logger.info ("result:{}",result);
    }

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
    @Override
    public List<DtUser> findUserByDepartId(Long departId, String position, String areaName, String smallName, String serveCode, String sellCode, Long markId) {
        return dtUserMapper.findUserByDepartId (departId, position, areaName, smallName, serveCode, sellCode, markId);
    }

    /**
     * 插入
     *
     * @param user
     */
    @Override
    public void insertDealer(DtUser user) {
        dtUserMapper.insertDealer (user.getUserName (),user.getRealName (),user.getSex (),user.getPosition ()
                ,user.getEmail (),user.getPhone (),user.getAreaName (),user.getSmallName (),
                user.getServeCode (),user.getSellCode (),user.getStatus (),user.getIsAgency (),user.getHeadImage (),user.getWxNum ());
        //创建用户

        List<DtUser> userList=dtUserMapper.queryUsersByUserName(user.getUserName());
        if (userList!=null&&userList.size()>0){
            user.setId(userList.get(0).getId());
        }

        Long departId=3L;

        //注意此处应将id传给微信企业号作为账号而不是用户名,因为之前老的供应商产生了旧数据需要与之兼容
        WeChatMemberUtils.insertEmployee (user.getId(),user.getRealName (),departId,
                user.getPosition (),user.getPhone (),user.getSex (),user.getEmail (),user.getWxNum (),null,accessTokenService.getAccessToken ());
    }

    /**
     * 更新
     *
     * @param user
     */
    @Override
    public void updateDealer(DtUser user) {
        dtUserMapper.updateUser (user.getId (),user.getUserName (),user.getRealName (),user.getSex (),user.getPosition (),
                user.getEmail (),user.getPhone (),user.getAreaName (),user.getSmallName (),
                user.getServeCode (),user.getSellCode (),user.getHeadImage (),user.getWxNum (),user.getPassword (),user.getDepartId (),user.getDepartName (),user.getCredentialExpiredDate());
    }

    @Override
    public DtUser findById(String id) {
        List<DtUser> dtUsers= dtUserMapper.findById (id);
        if(CollectionUtils.isEmpty (dtUsers)){
            return null;
        }
        return dtUsers.get (0);
    }

    /**
     * @param wxNum
     * @return
     */
    @Override
    public DtUser findByWxNum(String wxNum) {
        List<DtUser> dtUsers= dtUserMapper.findByWxNum (wxNum);
        if(CollectionUtils.isEmpty (dtUsers)){
            return null;
        }
        return dtUsers.get (0);
    }

    /***
     * 删除用户
     *
     * @param id
     */
    @Override
    public void delete(String id) {
         this.dtUserMapper.deleteById(id);
        //删除企业号用户
        String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
        String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
        AccessToken accessToken = AccessTokenUtil.getQyAccessToken(corpId, corpSecret);
        WeChatMemberUtils.deleteEmployee (id,accessToken.getAccess_token ());
    }

    /***
     * 更新测试环境
     * @param id
     * @param password
     * @param credentialExpiredDate
     */
    @Override
    public void updatePassword(String id, String password, Date credentialExpiredDate) {
        this.dtUserMapper.updatePassword (id,password,credentialExpiredDate);
    }

    /***
     * 查询经销商
     *
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
     * @return
     */
    @Override
    public Pagination<DtUser> queryPage(PageGrid page, String realName, String userName, String position, String email,
                                        String phone, Long departId, String isAgency, String serveCode, String sellCode,String userId) {

        Page<DtUser> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<DtUser>   list= dtUserMapper.findByPage (realName, userName, position, email, phone, departId, isAgency, serveCode, sellCode,userId);

        return new Pagination<DtUser> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }


    /***
     * @param page
     * @param realName
     * @param userName
     * @param position
     * @param email
     * @param departId
     * @return
     */
    @Override
    public Pagination<DtUser> queryPage(PageGrid page, String realName, String userName, String position, String email, Long departId,String isAgency) {
        return this.queryPage(page,realName,userName,position,email,null,departId,isAgency,null,null,DtUser.ADMIN_ID);
    }

    /***
     * @param page
     * @param userName
     * @param roleName
     * @param functionName
     * @return
     */
    @Override
    public Pagination<UserAuthList> findUserAuthPage(PageGrid page, String userName, String roleName, String functionName) {
        Page<DtUser> pageInfo= PageHelper.startPage (page.getPageIndex (),page.getPageSize (),Boolean.TRUE);

        List<UserAuthList>   list= dtUserMapper.findUserAuthPage (userName,roleName,functionName);

        return new Pagination<UserAuthList> (list,pageInfo.getTotal (),page.getPageIndex (),page.getPageSize ());
    }

    @Override
    public List<DtUser> findAll() {
        return this.dtUserMapper.findAll ();
    }



    public List<Map<String, Object>> findAll( String realName,String userName,String position,String email,Long departId){



        return dtUserMapper.findAllObjectMap(realName,userName,
                position,email,"",departId,
                "0","","",
                "");
    }



    public HSSFWorkbook createHSSFWorkbook( String realName,String userName,String position,String email,Long departId){


        List<Map<String, Object>> dataList =findAll( realName,userName, position,email,departId);

        List<Object[]> list=new ArrayList<Object[]>();
        HSSFWorkbook wb = new HSSFWorkbook(); // create new workbook object
        for(int i=0;i<dataList.size();i++) {
            Map<String, Object> t = dataList.get(i);
            Object[] obj=new Object[]{t.get("USERNAME"),t.get("REALNAME"),t.get("PHONE"),t.get("EMAIL"),t.get("DEPARTNAME"),t.get("POSITION")};
            list.add(obj);
        }
        return ExcelUtils.createExcelSheet(wb, list, "sheet0", new String[]{"用户名","姓名","电话","邮箱","部门","职位"});
    }

}
