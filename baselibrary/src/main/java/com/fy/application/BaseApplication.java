package com.fy.application;

import android.app.Application;

import com.tencent.bugly.crashreport.CrashReport;

/**
 * 基础 application
 * Created by fangs on 2017/5/5.
 */
public class BaseApplication extends Application {

    protected static BaseApplication mApplication; // 单例模式

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
        CrashReport.initCrashReport(getApplicationContext(), "54b91a4db1", true);

//        设置activity 生命周期回调
//        registerActivityLifecycleCallbacks(new BaseActivityLifecycleCallbacks());

    }

    /**
     * 单例模式，获取BTApplication的实例
     *
     * @return
     */
    public static BaseApplication getApplication() {
        return mApplication;
    }
}
