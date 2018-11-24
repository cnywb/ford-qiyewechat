package com.ford.qiye.lisenter;


import com.ford.qiye.model.OperationLog;
import com.ford.qiye.model.OperationType;

import java.text.MessageFormat;
import java.util.Date;

/**
 * 操作日志记录
 * Created by wanglijun on 16/12/3.
 */
public class LogUtil {

    /**
     * 敏感词替换数据
     */
    public static final String SENSITIVE_WORDS="**********";


    /***
     * 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(OperationType type, String ... params){
        writer (null,type,params);
    }

    /***
     *
     * @param userName 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(String userName,OperationType type,String  ... params){
        writer (userName,null,type,params);
    }


    /***
     *
     * @param userName 用户名
     * @param type 操作类型
     * @param params 参数
     */
    public static void writer(String userName,String  realName,OperationType type,String  ... params){
        OperationLog log=new OperationLog ();
        log.setUserName (userName);
        log.setOperationType (type);
        log.setOperationContent (MessageFormat.format (type.getDesc (),params));
        log.setOperationDate (new Date ());
         //写入监听
         EventBusUtils.post (log);
    }




}
