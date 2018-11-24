package com.ford.qiye.service.impl;

import com.ford.qiye.service.AccessTokenService;
import com.fute.backer.service.SystemParameterService;
import com.fute.wechat.model.auth.AccessToken;
import com.fute.wechat.util.AccessTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by wanglijun on 17/1/3.
 */
@Service
public class AccessTokenServiceImpl  implements AccessTokenService{



    @Autowired
    private SystemParameterService systemParameterService;

    /***
     * 获取AccessToken
     *
     * @return
     */
    @Override
    public String getAccessToken() {
        String corpId=systemParameterService.getValueByKey("WECHAT_QY_CORP_ID");
        String corpSecret=systemParameterService.getValueByKey("WECHAT_QY_CORP_SECRET");
        AccessToken accessToken = AccessTokenUtil.getQyAccessToken(corpId, corpSecret);
        return accessToken.getAccess_token ();
    }
}
