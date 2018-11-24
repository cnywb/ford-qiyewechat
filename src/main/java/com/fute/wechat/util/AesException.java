package com.fute.wechat.util;

/**
 * Created by wanglijun on 16/7/25.
 */
public class AesException extends Exception {


    private static final long serialVersionUID = -5405994730982007089L;

    private int code;


    public int getCode() {
        return code;
    }

    AesException(String message,int code){
        super(message);
        this.code=code;
    }

    AesException(int code) {
        this(AesError.getMessage (code),code);
    }

}