package com.example.hjy.movenurse.edit.antenatal;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantFragment;
import com.example.hjy.movenurse.nursing.PatientInfoFragment;
import com.fy.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Gab on 2017/9/14 0014.
 * 产前待产记录 编辑
 */

public class AntenatalRecordActivity extends BaseActivity {

    @BindView(R.id.tvSelectLabel)
    TextView tvSelectLabel;

    @Override
    protected int getContentView() {
        return R.layout.activity_antenatal_record;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("护理评估");
        tvSelectLabel.setText("产前待产记录");

        initInfoFragment();
    }

    private void initInfoFragment(){
        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);

        AntenatalExpectantFragment editFragment = new AntenatalExpectantFragment();
        transaction.replace(R.id.fmContent, editFragment);

        transaction.commit();
    }

    /**
     * 点击空白位置 隐藏软键盘
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }

}
