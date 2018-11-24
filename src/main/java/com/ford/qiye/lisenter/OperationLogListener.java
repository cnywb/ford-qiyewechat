package com.ford.qiye.lisenter;


import com.ford.qiye.model.OperationLog;
import com.ford.qiye.service.OperationLogService;
import com.ford.qiye.service.UserService;
import com.google.common.eventbus.Subscribe;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wanglijun on 16/12/3.
 */
@Component
public class OperationLogListener {
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (OperationLogListener.class);

    @Resource
    private OperationLogService operationLogService;

    @Autowired
    UserService userService;

    /**
     * 写入日志
     * @param operationLog
     */
    @Subscribe
    public void saveOperationLog(OperationLog operationLog){
        logger.debug (operationLog.toString ());
        if(operationLog!=null&& StringUtils.isNotEmpty (LoginUtils.getUserName ())){
                operationLog.setUserName (LoginUtils.getUserName ());
                operationLog.setRealName (LoginUtils.getRealName ());
        }
        operationLogService.insert (operationLog);
    }
}
