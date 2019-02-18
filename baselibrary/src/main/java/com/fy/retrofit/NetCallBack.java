package com.fy.retrofit;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.fy.application.BaseApplication;
import com.fy.custom.dialog.DialogLoad;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.FileUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.L;
import com.fy.utils.NetUtils;
import com.fy.utils.T;

import java.net.ConnectException;
import java.net.SocketTimeoutException;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Jam on 16-7-21
 * Description: 自定义Subscribe
 */
public abstract class NetCallBack<V> implements Observer<V> {

    private AppCompatActivity mContext;
    DialogLoad dialog;
    private String msg;

    public NetCallBack() {
    }

    public NetCallBack(AppCompatActivity mContext, int msgId) {
        this.mContext = mContext;

        msg = mContext.getString(msgId);
    }

    public NetCallBack(AppCompatActivity context, String msg) {
        this.mContext = context;
        this.msg = msg;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        L.e("test---onSubscribe ", "onSubscribe()");
        if (showDialog() && !d.isDisposed()) {
            dialog = new DialogLoad.Builder()
                    .setMsg(msg)
                    .setDisposable(d)
                    .create();

            dialog.show(mContext.getSupportFragmentManager(), "DialogLoad" + msg);
        }
    }

    @Override
    public void onNext(V t) {
        L.e("test---onNext ", "onNext()");

        closeDialog();
        updataLayout(RootFrameLayout.LAYOUT_CONTENT_ID);
        if (null != t) {
            onSuccess(t);
        } else {
            actionResponseError("请求失败");
        }
    }

    @Override
    public void onError(Throwable e) {
        L.e("test---onError ", "onError()");
        e.printStackTrace();
        if (!NetUtils.isConnected(BaseApplication.getApplication())) {
            actionResponseError("网络不可用");
            updataLayout(RootFrameLayout.LAYOUT_NETWORK_ERROR_ID);
        } else if (e instanceof ServerException) {
            if (e.getMessage().equals("登录超时，请重新登录")){//token 失效 进入登录页面
                try {
                    Class cla = Class.forName("com.example.hjy.movenurse.activity.LoginActivity");
                    Context context = BaseApplication.getApplication();
                    Intent intent = new Intent(context, cla);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET );
                    context.startActivity(intent);
                } catch (ClassNotFoundException e1) {
                    e1.printStackTrace();
                }

                actionResponseError(e.getMessage());
            }else {
                actionResponseError(e.getMessage());
                updataLayout(RootFrameLayout.REQUEST_FAIL);
            }
        } else if (e instanceof ConnectException){
            actionResponseError("请求超时，请稍后再试...");
            onFlaiCacheRequest();
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        } else if (e instanceof SocketTimeoutException){
            actionResponseError("服务器响应超时，请稍后再试...");
            onFlaiCacheRequest();
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        } else {
            FileUtils.fileToInputContent("log", "日志.txt", e.toString());
            actionResponseError("请求失败，请稍后再试...");
            onFlaiCacheRequest();
            updataLayout(RootFrameLayout.REQUEST_FAIL);
        }

        closeDialog();
    }

    @Override
    public void onComplete() {
        L.e("test---onComplete ", "onComplete()");
        closeDialog();
    }

    private void closeDialog() {
        try {
            if (showDialog() && null != dialog && null != mContext){
                dialog.dismiss(mContext.getSupportFragmentManager(), "DialogLoad" + msg);
                mContext = null;
                dialog = null;
            }
        } catch (Exception e){
            L.e("异常", e.toString());
        }

    }


    /**
     * 是否显示 加载对话框（msg 不为空 显示对话框）
     *
     * @return
     */
    protected boolean showDialog() {
        return !TextUtils.isEmpty(msg);
    }

    /**
     * 显示提示信息
     *
     * @param msg
     */
    private void actionResponseError(String msg) {
        T.showShort(msg);
    }


    /**
     * 请求成功 回调
     *
     * @param t 请求返回的数据
     */
    protected abstract void onSuccess(V t);

    /**
     * 更新activity 界面（多状态视图）
     *
     * @param flag
     */
    protected abstract void updataLayout(int flag);

    /**
     * 缓存 失败的请求
     */
    protected abstract void onFlaiCacheRequest();
}
