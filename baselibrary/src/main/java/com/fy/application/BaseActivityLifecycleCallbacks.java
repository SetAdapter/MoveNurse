package com.fy.application;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import butterknife.ButterKnife;

/**
 * activity 生命周期回调 (api 14+)
 * Created by fangs on 2017/5/18.
 */
public class BaseActivityLifecycleCallbacks implements Application.ActivityLifecycleCallbacks {

    @Override
    public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

        BaseActivityBean activityBean = new BaseActivityBean();
        activityBean.setUnbinder(ButterKnife.bind(activity));

        Bundle bundle = new Bundle();
        bundle.putSerializable("ActivityBean", activityBean);
        activity.getIntent().putExtra("BaseActivityBean", bundle);
    }

    @Override
    public void onActivityStarted(Activity activity) {

    }

    @Override
    public void onActivityResumed(Activity activity) {

    }

    @Override
    public void onActivityPaused(Activity activity) {

    }

    @Override
    public void onActivityStopped(Activity activity) {

    }

    @Override
    public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

    }

    @Override
    public void onActivityDestroyed(Activity activity) {
        Bundle bundle = activity.getIntent().getBundleExtra("BaseActivityBean");
        BaseActivityBean activityBean = (BaseActivityBean) bundle.getSerializable("ActivityBean");
        activityBean.getUnbinder().unbind();
    }
}
