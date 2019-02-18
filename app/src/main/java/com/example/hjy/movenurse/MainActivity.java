package com.example.hjy.movenurse;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;

import com.example.hjy.movenurse.activity.LoginActivity;
import com.example.hjy.movenurse.fragment.MoveFragment;
import com.fy.base.BaseActivity;
import com.fy.start.activity.StartActivity;
import com.fy.statusbar.MdStatusBarCompat;
import com.fy.utils.ConstantUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.SpfUtils;
import com.fy.utils.T;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends BaseActivity {

    private boolean isClicked = false;

    @BindView(R.id.loadImg)
    ImageView loadImg;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_main);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        loadImg.setVisibility(View.VISIBLE);
        //欢迎页 设置点击事件（但是不设置点击回调）
        loadImg.setOnClickListener(this);

        initSLManager();
        hideLoadView();

        MoveFragment mMoveFragment = new MoveFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.liner, mMoveFragment);
        transaction.commit();
    }

    private void initSLManager() {
        Observable.timer(2000, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        //判断是否登录
                        boolean isLogin = SpfUtils.getSpfSaveBoolean(ctx(), ConstantUtils.isLogin);
                        if (!isLogin) {
                            JumpUtils.jump(act(), LoginActivity.class, null);
                        }
                    }
                });
    }

    //延迟200 毫秒 隐藏 加载图片控件
    private void hideLoadView() {
        Observable.timer(2500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(@NonNull Long aLong) throws Exception {
                        if (null != loadImg)loadImg.setVisibility(View.GONE);
                    }
                });
    }

    //保存点击的时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {

                T.showLong(R.string.exit_app);
                exitTime = System.currentTimeMillis();
            } else {
                JumpUtils.exitApp(mContext, StartActivity.class);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

}
