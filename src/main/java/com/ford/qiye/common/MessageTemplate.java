package com.ford.qiye.common;

import java.text.MessageFormat;

/**
 * Created by wanglijun on 16/8/16.
 */
public class MessageTemplate {
    /**发送物料消息*/
    public static final String MSG_MATERIAL="您收到{0}给你下发的物料:{1}  <a href=\"{2}/we/userId.do\">点此查收</a>";

    /**问题答复*/
    public static final String MSG_QUESTION="{0}解答了您的问题,<a href=\"{1}/we/questionAll.do\">请点此查看。</a>";

    /**图片*/
    public static final String MSG_IMAGE="您收到图片,<a href=\"{0}\" target=\"_blank\" >点击查看</a>";

    public static String format(String template,String ... params){
       return  MessageFormat.format (template,params);
    }


    public static void main(String[] args) {
        String name=MessageFormat.format (MSG_MATERIAL,"wanglijun","1111","context");
        System.out.println(name);
    }


}
