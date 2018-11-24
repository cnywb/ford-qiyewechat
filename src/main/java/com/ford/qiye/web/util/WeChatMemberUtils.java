package com.ford.qiye.web.util;

import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanglijun on 17/1/3.
 */
public class WeChatMemberUtils {
    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (WeChatMemberUtils.class);

    /**
     *
     *添加员工
     */
    public static  void insertEmployee(String userId, String name, Long departmentId, String position,
                                       String mobile, Integer gender, String email, String weiXinId,
                                       String avatarMediaId, String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/create?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("userid", userId);
        json.put("name", name);
        json.put("department", new Long[]{departmentId});
        json.put("mobile", mobile);
        json.put("position",  position);
        json.put("gender", gender);
        json.put("email", email);
        json.put("weixinid", weiXinId);
        JSONObject jsonObject = HttpUtils.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
    }


    /**
     *
     *修改员工
     */
    public static void updateEmployee(String userId,String name,String department,String position,String mobile,
                               String gender,String email,String weixinid,String avatarMediaId,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/update?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("userid", userId);
        json.put("name", name);
        json.put("department", department);
        json.put("mobile", mobile);
        json.put("position",  position);
        json.put("gender", gender);
        json.put("email", email);
        json.put("weixinid", weixinid);
        JSONObject jsonObject = HttpUtils.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
    }
    /**
     * 删除员工
     *
     */
    public static void deleteEmployee(String userId,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/delete?access_token="+accessToken+"&userid="+userId;
        JSONObject jsonObject = HttpUtils.httpRequest(url, "GET", null);
        logger.info (jsonObject.toString ());
    }


    /***
     * 获取openId;
     * @param userId
     * @param accessToken
     * @return
     */
    public static String convertToOpenId(String userId,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/user/convert_to_openid?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("userid", userId);
        JSONObject jsonObject = HttpUtils.httpRequest(url, "GET",json.toString ());
        logger.info ("jsonObject:{}",jsonObject);
        return jsonObject.getString ("openid");
    }

}
