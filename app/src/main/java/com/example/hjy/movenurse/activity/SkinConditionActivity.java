package com.example.hjy.movenurse.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.fragment.SkinConditionFragment;
import com.example.hjy.movenurse.fragment.SkinContraryFragment;
import com.example.hjy.movenurse.nursing.PatientInfoFragment;
import com.example.hjy.movenurse.utils.MyUtils;
import com.fy.base.BaseActivity;
import com.fy.entity.PatientsBean;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/10/13 0013.
 * 首次护理记录单_压疮_身体部位选择 Activity
 */

public class SkinConditionActivity extends BaseActivity {

    @BindView(R.id.tv_front)
    RadioButton tv_front;
    @BindView(R.id.tv_contrary)
    RadioButton tv_contrary;
    @BindView(R.id.tv_sex)
    TextView tv_sex;

    @Override
    protected int getContentView() {
        return R.layout.activity_skincondition;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("护理评估");
        tv_front.setChecked(true);
        initFrontFragment();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        tv_sex.setText(MyUtils.getSex(patin.getSex()));

    }


    @OnClick({R.id.tv_front, R.id.tv_contrary})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tv_front://正面
                showFrontFragment();
                break;
            case R.id.tv_contrary://后面
                showContraryFragment();
                break;
        }
    }

    //获取对应的 fragment 的tag
    private String showFrontFragment() {
        String label = tv_front.getText().toString();
        if (label.equals("正面")) {
            initFrontFragment();
        }
        return label;
    }

    private String showContraryFragment() {
        String label = tv_contrary.getText().toString();
        if (label.equals("反面")) {
            initContraryFragment();
        }
        return label;
    }

    //前面
    private void initFrontFragment() {
        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);

        SkinConditionFragment editFragment = new SkinConditionFragment();
        transaction.replace(R.id.fmContent, editFragment);

        transaction.commit();
    }

    //后面
    private void initContraryFragment() {
        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);

        SkinContraryFragment editFragment = new SkinContraryFragment();
        transaction.replace(R.id.fmContent, editFragment);

        transaction.commit();
    }
}
