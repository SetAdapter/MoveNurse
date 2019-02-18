package com.fy.entity;

import java.util.List;

/**
 * Created by Gab on 2017/10/24 0024.
 */

public class LogOut  {

    /**
     * ResultCode : 1
     * ResultMes : 成功
     * ResultData : []
     */

    private int ResultCode;
    private String ResultMes;
    private List<?> ResultData;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMes() {
        return ResultMes;
    }

    public void setResultMes(String ResultMes) {
        this.ResultMes = ResultMes;
    }

    public List<?> getResultData() {
        return ResultData;
    }

    public void setResultData(List<?> ResultData) {
        this.ResultData = ResultData;
    }
}
