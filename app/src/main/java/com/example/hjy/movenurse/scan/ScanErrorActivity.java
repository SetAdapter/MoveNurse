package com.example.hjy.movenurse.scan;

import android.os.Bundle;
import android.view.View;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.utils.JumpUtils;

import butterknife.OnClick;

/**
 * 扫描二维码 错误 或 输入ID 错误 界面
 * Created by fangs on 2017/9/25.
 */
public class ScanErrorActivity extends BaseActivity{
    @Override
    protected int getContentView() {
        return R.layout.activity_scan_eror;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        setActTitle(R.string.scanQRcode);
    }

    @OnClick({R.id.tvConfirm})
    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case fy.library.com.baselibrary.R.id.tvBack:
                JumpUtils.jump(mContext, ScanCaptureActivity.class, null);
                break;
            case R.id.tvConfirm://确认按钮
                JumpUtils.jump(mContext, ScanCaptureActivity.class, null);
                finish();
                break;
        }
        super.onClick(view);
    }
}
