package com.example.hjy.movenurse.edit.antenatal;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.nursing.NursingRecordFragment;
import com.example.hjy.movenurse.nursing.PatientInfoFragment;
import com.fy.base.BaseActivity;

import butterknife.BindView;

/**
 * Created by Gab on 2017/9/14 0014.
 * 11_1产科产后记录单_编辑
 */

public class AntenatalRecordsFragment extends BaseActivity {


    @BindView(R.id.tvSelectLabel)
    TextView tvSelectLabel;

    @Override
    protected int getContentView() {
        return R.layout.activity_antenatal_record;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("护理评估");
        tvSelectLabel.setText("产科产后记录单");

        initInfoFragment();
    }

    private void initInfoFragment(){
        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();

        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);

        NursingRecordFragment editFragment = new NursingRecordFragment();
        transaction.replace(R.id.fmContent, editFragment);

        transaction.commit();
    }

}
