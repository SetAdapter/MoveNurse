package com.fy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fy.custom.dialog.DialogLoad;
import com.fy.utils.FileUtils;
import com.fy.utils.cache.ACache;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import io.reactivex.disposables.Disposable;

/**
 * Fragment 基类
 * Created by fangs on 2017/4/26.
 */
public abstract class BaseFragment extends Fragment implements View.OnClickListener {

    protected ACache mCache;
    protected Disposable disposable;
    protected DialogLoad dialogLoad = null;

    protected BaseActivity mContext;
    protected View mRootView;
    protected Unbinder unbinder;

    public static String path = FileUtils.getSDCardPath()+ "01.png";

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mContext = (BaseActivity) context;
        mCache = ACache.get(mContext);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
            unbinder = ButterKnife.bind(this, mRootView);

            baseInitView();
            baseInit();
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onClick(View view) {
    }

    protected void baseInitView() {
    }

    protected void baseInit() {
    }

    protected abstract int getContentLayout();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (null != unbinder){
            unbinder.unbind();
        }

        if (null != disposable) disposable.dispose();
    }

    protected boolean mHidden;
    @Override
    public void onHiddenChanged(boolean hidden) {
        super.onHiddenChanged(hidden);
        mHidden = hidden;
        if (hidden) {// 不在最前端界面显示
            onPause();
        } else {// 重新显示到最前端中
            onResume();
        }
    }
}
