package com.example.hjy.movenurse.widget;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.utils.FileUtils;
import com.fy.utils.T;

import java.io.IOException;

/**
 * Created by Gab on 2017/10/17 0017.
 * 签名Dialog框
 */

public class SignDialog extends Dialog implements View.OnClickListener {

    private LinePathView mLinePathView;
    private Context mContext;

    public SignDialog(@NonNull Context context) {
        super(context, R.style.Dialog);
        this.mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_sign);
        setCanceledOnTouchOutside(true);
        initView();
    }

    private void initView() {
        TextView signed_tv = (TextView) findViewById(R.id.confirm_btn);
        signed_tv.setOnClickListener(this);
        TextView clear_tv = (TextView) findViewById(R.id.clear_tv);
        clear_tv.setOnClickListener(this);
        mLinePathView = (LinePathView) findViewById(R.id.linePathView);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.clear_tv: //清除
                mLinePathView.clear();
                break;
            case R.id.confirm_btn://完成
                if (mLinePathView.getTouched()) {
                    try {
                        if (mOnDoodleListener != null) {
                            if (FileUtils.isSDCardEnable()) {
                                mLinePathView.save(BaseFragment.path, false, 10);
                                mOnDoodleListener.OnConfrim(BaseFragment.path);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    dismiss();
                } else {
                    T.showLong("您还没有签名");
                }
                break;
        }
    }


    private OnDoodleListener mOnDoodleListener;

    public interface OnDoodleListener {
        void OnConfrim(String path);
    }

    public void setOnDoodleListener(OnDoodleListener mOnDoodleListener) {
        this.mOnDoodleListener = mOnDoodleListener;
    }
}