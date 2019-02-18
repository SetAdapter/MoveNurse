package com.example.hjy.movenurse.fragment;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.activity.LoginActivity;
import com.example.hjy.movenurse.activity.PatientDataActivity;
import com.example.hjy.movenurse.upload.UpLoadListActivity;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.example.hjy.movenurse.wardmanage.WardManageListActivity;
import com.fy.base.BaseFragment;
import com.fy.custom.dialog.ExitDialog;
import com.fy.entity.LogOut;
import com.fy.entity.LoginBean;
import com.fy.entity.UploadBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/8/29 0029.
 *
 */
public class MoveFragment extends BaseFragment {

    @BindView(R.id.name_administrative)
    TextView name_administrative;
    @BindView(R.id.name_tv)
    TextView name_tv;
    @BindView(R.id.not_log_in_tv)
    TextView not_log_in_tv;
    @BindView(R.id.ph_tv)
    TextView ph_tv;
    String UserID, token;

    @Override
    protected int getContentLayout() {
        return R.layout.move_fragment;
    }

    @Override
    protected void baseInitView() {
    }

    @Override
    public void onResume() {
        super.onResume();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null != loginBean) {
            String jobCode = loginBean.getJobCode();
            String userID = loginBean.getUserID();
            ResourceUtils.setText(mContext, name_administrative, R.string.dept, loginBean.getDeptName());
            ResourceUtils.setText(mContext, name_tv, R.string.name, loginBean.getUserName());
            not_log_in_tv.setText("工号：" + userID);
            ph_tv.setText("职称：" + jobCode);
            UserID = loginBean.getUserID();
            token = loginBean.getToken();
        }
    }

    @OnClick({R.id.tv_patient_data, R.id.tv_doctor, R.id.tv_nurse, R.id.tv_inpatient, R.id.tv_administration,
            R.id.tv_btn_scan, R.id.tvOutLogin, R.id.tvUploadList})
    public void onClick(View view) {
        super.onClick(view);
        Bundle bundle = new Bundle();
        switch (view.getId()) {
            case R.id.tv_patient_data://患者资料
                bundle.putString("PatientDataActivity", "activity.PatientDataItemActivity");
                JumpUtils.jump(mContext, PatientDataActivity.class, bundle);
                break;
            case R.id.tv_doctor://医嘱管理
                bundle.putString("PatientDataActivity", "doctor.manage.DoctorManageActivity");
                JumpUtils.jump(mContext, PatientDataActivity.class, bundle);
                break;
            case R.id.tv_nurse://护理评估
                bundle.putString("PatientDataActivity", "nursing.FirstNurseOrderActivity");
                JumpUtils.jump(mContext, PatientDataActivity.class, bundle);
                break;
            case R.id.tv_inpatient://病房管理
                JumpUtils.jump(mContext, WardManageListActivity.class, null);
                break;
            case R.id.tv_administration://退费退药
                bundle.putString("PatientDataActivity", "adminmanage.AdministrativeManagementActivity");
                JumpUtils.jump(mContext, PatientDataActivity.class, bundle);
                break;
            case R.id.tv_btn_scan:
                T.showLong(getString(R.string.No_Open));
//                JumpUtils.jump(mContext, ScanCaptureActivity.class, null);
                break;
            case R.id.tvOutLogin://退出登录
                List<UploadBean> uploadBeanList = UpLoadUtils.getData(mCache);
                if (uploadBeanList.size() > 0){
                    new ExitDialog.Builder()
                            .setTitle("系统提示")
                            .setMessage("您有未上传的护理评估单，\n退出后将被删除，确定退出登录吗?")
                            .setLeftListener("确定", v -> LogOut(UserID, token))
                            .setRightListener("上传", v -> JumpUtils.jump(mContext, UpLoadListActivity.class, null))
                            .create()
                            .show(mContext.getSupportFragmentManager(), "WarningDialog");
                } else {
                    new ExitDialog.Builder()
                            .setTitle("系统提示")
                            .setMessage("确定退出登录吗?")
                            .setRightListener("确定", v -> LogOut(UserID, token))
                            .setLeftListener("取消", v -> {})
                            .create()
                            .show(mContext.getSupportFragmentManager(), "WarningDialog");
                }
                break;
            case R.id.tvUploadList://上传列表
                JumpUtils.jump(mContext, UpLoadListActivity.class, null);
                break;
        }
    }

    /**
     * 退出登录
     *
     * @param UserID
     * @param Token
     */
    private void LogOut(String UserID, String Token) {
        Map<String, Object> params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("Token", Token);
        NetRequest.getInstens().requestDate(params, Api.LogOut, true, new NetCallBack<ArrayList<LogOut>>((AppCompatActivity) getActivity(), R.string.exit_loading) {
            @Override
            public void onSuccess(ArrayList<LogOut> logOuts) {
                UpLoadUtils.clienUPload(mCache);
                JumpUtils.jump(mContext, LoginActivity.class, null);
                T.showShort("退出登录成功");
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
