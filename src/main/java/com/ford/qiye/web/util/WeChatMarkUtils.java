package com.ford.qiye.web.util;

import com.fute.reserve.api.HttpRequest;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Created by wanglijun on 17/1/3.
 */
public class WeChatMarkUtils {

    /**日志*/
    private static final Logger logger= LoggerFactory.getLogger (WeChatMarkUtils.class);

    /**
     *
     *添加标签
     */
    public static  Map<String, Object> insertMark(String accessToken,String tagName, Map<String, Object> data){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/create?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("tagname", tagName);
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
        //需得到此次标签自增ID
        data.put("tagid", jsonObject.get("tagid"));
        return data;
    }
    /**
     *
     *修改标签
     */
    public static void updateMark(String accessToken,String tagName,String markId){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/update?access_token="+accessToken;
        JSONObject json=new JSONObject();
        json.put("tagid", markId);
        json.put("tagname", tagName);
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", json.toString());
        logger.info (jsonObject.toString ());
    }
    /**
     *
     *删除标签
     */
    public static void deleteMark(String id,String accessToken){
        String url = "https://qyapi.weixin.qq.com/cgi-bin/tag/delete?access_token="+accessToken+"&tagid="+id;
        JSONObject jsonObject = HttpRequest.httpRequest(url, "GET", null);
        logger.info (jsonObject.toString ());
    }
}
