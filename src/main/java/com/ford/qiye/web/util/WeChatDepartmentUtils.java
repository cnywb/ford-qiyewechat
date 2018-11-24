package com.ford.qiye.web.util;

import com.fute.reserve.api.HttpRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by wanglijun on 17/1/3.
 */
public class WeChatDepartmentUtils {

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (WeChatMemberUtils.class);
    /**
     *
     *添加部门
     */
    public static JSONObject  insertDepart(String name, String parentId, String order,String departmentId, String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/create?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("name", name);
        json.put("parentid", parentId);
        json.put("order", order);
        json.put("id", departmentId);
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
        return jsonObject;
    }
    /**
     *
     *修改部门
     */
    public static void updateDepart(String id,String name,String order,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/update?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("name", name);
        json.put("order", order);
        json.put("id", id);
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
    }
    /**
     *
     *删除部门
     */
    public static void deleteDepart(String id,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/department/delete?access_token="+accessToken+"&id="+id;
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", null);
        logger.info (jsonObject.toString ());
    }
}
