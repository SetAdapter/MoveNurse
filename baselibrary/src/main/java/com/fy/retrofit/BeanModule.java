package com.fy.retrofit;

import com.fy.base.BaseBean;

/**
 * 网络请求 返回数据 格式化对象
 * email：fy310518@163.com
 * Created by fangs on 2017/8/28.
 */
public class BeanModule<T> extends BaseBean {

    public int ResultCode;
    public String ResultMes = "测试失败情况";
    public T ResultData;


    public boolean success(){
        return ResultCode == 1;
    }
}
