package com.ford.qiye.lisenter;

import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by wanglijun on 16/9/15.
 */
@Component
public class EventBusUtils implements InitializingBean {

    /**初始化EventBus*/
    private static EventBus eventBus;



    /**系统日志-监听器*/
    @Resource
    private OperationLogListener operationLogListener;



    @Override
    public void afterPropertiesSet() throws Exception {
        if(eventBus==null){
            eventBus=new EventBus(OperationLogListener.class.getName ());
            eventBus.register(operationLogListener);
        }
    }

    /***
     * 监控对象
     * @param obj
     */
    public static void post(Object obj){
        eventBus.post(obj);
    }
}
