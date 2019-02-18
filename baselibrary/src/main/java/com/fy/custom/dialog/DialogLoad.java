package com.fy.custom.dialog;

import android.content.DialogInterface;
import android.support.graphics.drawable.AnimatedVectorDrawableCompat;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.widget.ImageView;
import android.widget.TextView;

import com.fy.base.DialogBase;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.utils.L;

import fy.library.com.baselibrary.R;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 加载 dialog
 * Created by fangs on 2017/3/10.
 */
public class DialogLoad extends DialogBase {

    private TextView txtLoadHint;
    private Disposable disposable;
    private String msg;

    public DialogLoad(Builder builder) {
        if (null != builder.disposable){
            this.disposable = builder.disposable;
        }

        if (!TextUtils.isEmpty(builder.msg)){
            this.msg = builder.msg;
        }
    }

    @Override
    protected void clickOutside() {
        setHide(true);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_loading;
    }

    @Override
    protected void baseInit() {
        initRxEvent();

//        ImageView imgLoadAnim = (ImageView) mRootView.findViewById(R.id.imgLoadAnim);
//        ((AnimatedVectorDrawableCompat) imgLoadAnim.getDrawable()).start();

        txtLoadHint = (TextView) mRootView.findViewById(R.id.txtLoadHint);
        if (!TextUtils.isEmpty(msg)){
            txtLoadHint.setText(msg);
        }

        setTransparent(true);
        setBgDarkening(true);
        super.baseInit();
    }

    @Override
    protected void setOnKeyListener() {
        this.getDialog().setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                L.v("dialog onkey", "按下返回键");
                if (keyCode == KeyEvent.KEYCODE_BACK) {
                    dismiss();

                    //等待对话框dismiss() 同时取消 网络请求
                    L.v("dialog onkey1", "取消请求");
                    if (null != disposable && !disposable.isDisposed())disposable.dispose();

                    return true;
                }
                return false;
            }
        });
    }

    //TODO 实时修改加载框 内容 待测
    protected void initRxEvent() {
        Flowable<RxBean> f1 = RxBus.getInstance().register(RxBean.class);
        disposable = f1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event.getSendAction().equals("DialogLoad#")) {
                        if (null != txtLoadHint){
                            L.e(event.getContent());
                            txtLoadHint.setText(event.getContent());
                        }
                    }
                });
    }

    public static class Builder {
        private String msg;
        private Disposable disposable;

        public Builder setMsg(String msg) {
            this.msg = msg;
            return this;
        }

        public Builder setDisposable(Disposable disposable) {
            this.disposable = disposable;
            return this;
        }

        /**
         * 创建dialog
         * @return
         */
        public DialogLoad create() {
            DialogLoad dialog = new DialogLoad(this);

            return dialog;
        }
    }
}
