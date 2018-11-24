package com.ford.qiye.web.wechat.controller.vo;

/**
 * Created by wanglijun on 16/8/17.
 */
public class ValidateVo {


    private String json;

    private String msg_signature;

    private String timestamp;

    private String nonce;

    private String echostr;


    public String getEchostr() {
        return echostr;
    }

    public void setEchostr(String echostr) {
        this.echostr = echostr;
    }

    public String getJson() {
        return json;
    }

    public void setJson(String json) {
        this.json = json;
    }

    public String getMsg_signature() {
        return msg_signature;
    }

    public void setMsg_signature(String msg_signature) {
        this.msg_signature = msg_signature;
    }

    public String getNonce() {
        return nonce;
    }

    public void setNonce(String nonce) {
        this.nonce = nonce;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }


    @Override
    public String toString() {
        return "ValidateVo{" +
                "echostr='" + echostr + '\'' +
                ", json='" + json + '\'' +
                ", msg_signature='" + msg_signature + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", nonce='" + nonce + '\'' +
                '}';
    }
}
