package com.ford.qiye.service.impl;

import com.ford.qiye.dao.DtUserMapper;
import com.ford.qiye.model.DtUser;
import com.ford.qiye.service.LoginService;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wanglijun on 16/8/5.
 */
@Service
public class LoginServiceImpl implements LoginService {


    @Resource()
    protected DtUserMapper dtUserMapper;
    /***
     * 根据用户名查询用户信息
     *
     * @param userName
     * @return
     */
    @Override
    public DtUser doLogin(String userName) {
        List<DtUser> list= dtUserMapper.queryUsersByUserName (userName);
        if(CollectionUtils.isEmpty (list)){
            return null;
        }
        return list.get (0);
    }
}
