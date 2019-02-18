package com.example.hjy.movenurse.restraint;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MotionEvent;
import android.view.inputmethod.InputMethodManager;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.nursing.PatientInfoFragment;
import com.fy.base.BaseActivity;

/**
 * Created by Gab on 2017/10/17 0017.
 *  约束带
 */

public class RestraintStrapActivity extends BaseActivity {

    @Override
    protected int getContentView() {
        return R.layout.activity_restraomt_strap;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initInfoFragment();
        tvTitle.setText("护理评估");
    }

    private void initInfoFragment() {
        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);

        RestraintStrapNurseFragment editFragment = new RestraintStrapNurseFragment();
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
