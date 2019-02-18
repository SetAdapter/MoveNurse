package com.example.hjy.movenurse.wardmanage.leave;

import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.entity.BedBean;
import com.fy.entity.ChangeBedBean;
import com.fy.entity.LoginBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 请假 操作界面
 * Created by fangs on 2017/11/9.
 */
public class LeaveActivity extends BaseActivity {

    @BindView(R.id.tv_patient_id)
    TextView tv_patient_id;
    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvSelectPackBed)
    TextView tvSelectPackBed;


    @BindView(R.id.rgStatus)
    RadioGroup rgStatus;
    @BindView(R.id.rbtnDisease)
    RadioButton rbtnDisease;
    @BindView(R.id.rbtnLeave)
    RadioButton rbtnLeave;

    @BindView(R.id.tvSave)
    TextView tvSave;

    BedBean bedBean;
    String In_Dept_ID = "";//科室id
    String Depart = "";//科室
    String Bed = "";//包床床位
    String In_AreaID = "";//旧病区
    String stutes = "";//病床状态

    @Override
    protected int getContentView() {
        return R.layout.activity_leave;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("病房管理");

        tvSave.setEnabled(false);
        initPatinInfo();
    }

    @OnClick({R.id.tvSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvSave:
                ChangeBed();//保存选中包床
                break;
        }
    }


    private void initPatinInfo(){
        bedBean = (BedBean) getIntent().getExtras().getSerializable("#BedBean#");

        if (null != bedBean) {
            ResourceUtils.setText(mContext, tv_patient_id, R.string.hospitalizationID, bedBean.getPatID());
            ResourceUtils.setText(mContext, tvName, R.string.name, bedBean.getPatName());
            String sexlabel = bedBean.getSex();
            String sex = sexlabel.equals("F") ? "女" : (sexlabel.equals("M") ? "男" : "未知");
            ResourceUtils.setText(mContext, tvSex, R.string.sex, sex);

            BedBean.ZyDetailBean zyDetail = bedBean.getZyDetail();
            if (null != zyDetail) {
                In_Dept_ID= zyDetail.getIn_Dept_ID();
                Depart = zyDetail.getIn_Dept_Name();
                Bed = zyDetail.getIn_Bed();
                In_AreaID = zyDetail.getIn_AreaID();
                ResourceUtils.setText(mContext, tvDepartment, R.string.department, Depart);//当前科室
                ResourceUtils.setText(mContext, tvSelectPackBed, R.string.leavepackBed, Bed);

                //设置患者状态
                switch (zyDetail.getBedStatus()) {
                    case "2"://患者在院
                        rbtnDisease.setChecked(true);
                        stutes = "2";
                        break;
                    case "4"://请假
                        rbtnLeave.setChecked(true);
                        stutes = "4";
                        break;
                }
            }
        }

        rgStatus.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            switch (checkedId) {
                case R.id.rbtnDisease://患者在院
                    stutes = "2";
                    tvSave.setEnabled(true);
                    break;
                case R.id.rbtnLeave://请假
                    stutes = "4";
                    tvSave.setEnabled(false);
                    break;
            }
        });
    }

    //2.13.	修改患者科室/床位信息
    private void ChangeBed(){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");

        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("PatID", bedBean.getPatID());//住院ID
        params.put("DeptID", In_Dept_ID);//科室id
        params.put("OldAreaID", In_AreaID);//旧病区
        params.put("AreaID", In_AreaID);//病区
        params.put("Status", stutes);//床位状态
        params.put("OldBedID", Bed);//旧床号
        params.put("UserID", loginBean.getUserID());
        params.put("BedID", Bed);//病床号

        NetRequest.getInstens().requestDate(params, Api.ChangeBed, false,
                new NetCallBack<ArrayList<ChangeBedBean>>(mContext, R.string.data_loading) {
                    @Override
                    public void onSuccess(ArrayList<ChangeBedBean> data) {
                        T.showLong("修改成功!!!");
                        JumpUtils.jumpResult(mContext, null);
                    }

                    @Override
                    public void updataLayout(int flag) {
                    }
                    @Override
                    protected void onFlaiCacheRequest() {

                    }
                });
    }
}
