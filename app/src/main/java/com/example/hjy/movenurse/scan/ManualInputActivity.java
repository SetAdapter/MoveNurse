package com.example.hjy.movenurse.scan;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.utils.JumpUtils;
import com.fy.utils.KeyBoardUtils;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 手动输入 界面
 * Created by fangs on 2017/9/25.
 */
public class ManualInputActivity extends BaseActivity{

    @BindView(R.id.editIdNum)
    EditText editIdNum;

    @Override
    protected int getContentView() {
        return R.layout.activity_manual_input;
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
                RxBus.getInstance().send(new RxBean("SCAN_RESULT#", editIdNum.getText().toString().trim()));
                KeyBoardUtils.goneKeyboard(mContext, editIdNum);
                JumpUtils.exitActivity(mContext);
                break;
        }
        super.onClick(view);
    }
}
