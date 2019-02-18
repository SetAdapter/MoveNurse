package com.fy.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatDelegate;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

import com.fy.utils.L;

/**
 * 应用 所有dialog 的父类
 * <p/>
 * Created by fangs on 2017/3/13.
 */
public abstract class DialogBase extends DialogFragment {

    protected View mRootView;

    /** dialog显示位置 */
    protected int gravity = Gravity.CENTER;
    /** dialog进出动画 资源ID */
    protected int anim = android.R.style.Animation_Translucent;

    /** 背景 是否透明 (dialog 边框阴影)*/
    protected boolean isTransparent = false;
    /** 是否 清理背景变暗 */
    protected boolean isBgDarkening = false;

    /** 点击window外的区域 是否消失 */
    protected boolean isHide = false;

    /** 宽度 -1(ViewGroup.LayoutParams.MATCH_PARENT)：撑满；-2(ViewGroup.LayoutParams.WRAP_CONTENT)：自适应； 其他固定数值 */
    protected int widthPixels = -2;
    /** 高度 -1：撑满 -2：自适应 其他固定数值 */
    protected int heightPixels = -2;

    static {
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        clickOutside();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //点击window外的区域 是否消失
        getDialog().setCanceledOnTouchOutside(isHide);
        //去掉默认对话框 标题
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);

        setOnKeyListener();

        if (null == mRootView) {
            mRootView = inflater.inflate(getContentLayout(), container, false);
        } else {
            ViewGroup parent = (ViewGroup) mRootView.getParent();
            if (null != parent) {
                parent.removeView(mRootView);
            }
        }
        return mRootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        baseInit();
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        if (null == fragment || !fragment.isAdded()) {
            super.show(manager, tag);
        }
    }

    public void dismiss(FragmentManager manager, String tag) {
        Fragment fragment = manager.findFragmentByTag(tag);
        if (null != fragment && fragment.isAdded()) {
            try {
                super.dismiss();
            } catch (IllegalStateException e){
                L.e("DialogBase", "关闭对话框失败！！！");
            }
        }
    }

    /**
     * 初始化 dialog 样式
     */
    protected void baseInit() {
        Window window = this.getDialog().getWindow();
        window.setGravity(gravity);  //此处可以设置dialog显示的位置
        window.setWindowAnimations(anim);  //添加动画

        if (isTransparent) window.setBackgroundDrawableResource(android.R.color.transparent);//背景透明
        if (isBgDarkening) window.clearFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);//清理 背景变暗

        // 使用ViewGroup.LayoutParams，以便Dialog 宽度或高度充满整个屏幕
        WindowManager.LayoutParams params = window.getAttributes();
        params.width = widthPixels;
        params.height = heightPixels;
        window.setAttributes(params);
    }

    /**
     * 设置dialog 布局
     * @return
     */
    protected abstract int getContentLayout();

    /**
     * 点击window外的区域 是否消失
     */
    protected void clickOutside(){
    }

    /**
     * 设置Dialog 监听物理 返回键
     */
    protected void setOnKeyListener(){
    }


    /**
     * 设置 dialog显示位置
     * @param gravity
     */
    public void setGravity(int gravity) {
        this.gravity = gravity;
    }

    /**
     * 设置 dialog进出动画
     * @param anim
     */
    public void setAnim(int anim) {
        this.anim = anim;
    }

    /**
     * 设置背景 是否透明(dialog 边框阴影)
     * @param transparent
     */
    public void setTransparent(boolean transparent) {
        isTransparent = transparent;
    }

    /**
     * 设置 是否 清理背景变暗
     * @param bgDarkening
     */
    public void setBgDarkening(boolean bgDarkening) {
        isBgDarkening = bgDarkening;
    }

    /**
     * 点击window外的区域 是否消失
     * <p/>此方法 必须在 onCreateView（）执行前 设置 才有效
     * @param hide
     */
    public void setHide(boolean hide) {
        isHide = hide;
    }

    /**
     * 设置 宽度值
     * @param widthPixels
     */
    public void setWidthPixels(int widthPixels) {
        this.widthPixels = widthPixels;
    }

    /**
     * 设置 高度值
     * @param heightPixels
     */
    public void setHeightPixels(int heightPixels) {
        this.heightPixels = heightPixels;
    }
}
