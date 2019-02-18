package com.fy.custom;

import java.io.Serializable;
import java.util.List;

/**
 * email：fy310518@163.com
 * Created by fangs on 2017/8/16.
 */
public class ImgEntity implements Serializable{

    /**
     * msg : 返回成功
     * resultCode : 0
     * resultData : ["/upload/41351415f8c04556b7ef5f34f668be4f.octet-stream"]
     */

    private String msg;
    private String resultCode;
    private List<String> resultData;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getResultCode() {
        return resultCode;
    }

    public void setResultCode(String resultCode) {
        this.resultCode = resultCode;
    }

    public List<String> getResultData() {
        return resultData;
    }

    public void setResultData(List<String> resultData) {
        this.resultData = resultData;
    }
}
