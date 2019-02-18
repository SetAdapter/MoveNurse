package com.fy.application;

import java.io.Serializable;

import butterknife.Unbinder;

/**
 * 使用 ActivityLifecycleCallbacks 实现给所有 Activity 执行 ButterKnife.bind
 * Created by fangs on 2017/5/18.
 */
public class BaseActivityBean implements Serializable {

    protected Unbinder unbinder;

    public Unbinder getUnbinder() {
        return unbinder;
    }

    public void setUnbinder(Unbinder unbinder) {
        this.unbinder = unbinder;
    }
}
