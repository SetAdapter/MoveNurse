package com.fy.des.gson;

import com.google.gson.annotations.SerializedName;

/**
 * 请求返回数据
 * Created by Alois on 2017/4/21.
 */
public class BaseResponseBean<T> {
    /**
     * 用户授权过期
     */
    public static final int ERROR_TOKEN_TIMEOUT = -1;

    int resultCode = 1;//1:成功; 0:失败;-1:token过期
    String msg;
    @SerializedName("resultData")
    public T data;

    public int getResultCode() {
        return resultCode;
    }

    public String getResultMessage() {
        return msg;
    }

    public boolean isCodeInvalid(){
        return resultCode != 1;
    }

    public boolean isTokenTimeOut(){
        return resultCode == ERROR_TOKEN_TIMEOUT;
    }
}
