package com.example.hjy.movenurse.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.firstnursing.NurseEditActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.FirstNurseBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 首次护理记录 预览页面
 * Created by Gab on 2017/9/4 0004.
 */
public class FirstNurseOrderFragment extends BaseFragment {

    @BindView(R.id.tv_EducationLevel)
    TextView tv_EducationLevel;
    @BindView(R.id.tv_DataSources)
    TextView tv_DataSources;
    @BindView(R.id.tv_RY_DIS)
    TextView tv_RY_DIS;
    @BindView(R.id.tv_RY_Type)
    TextView tv_RY_Type;
    @BindView(R.id.tv_Allergic)
    TextView tv_Allergic;
    @BindView(R.id.tv_Favourer)
    TextView tv_Favourer;
    @BindView(R.id.tv_ConsciousState)
    TextView tv_ConsciousState;
    @BindView(R.id.tv_Mood)
    TextView tv_Mood;
    @BindView(R.id.tv_LanguagePerformance)
    TextView tv_LanguagePerformance;
    @BindView(R.id.tv_Diet)
    TextView tv_Diet;
    @BindView(R.id.tv_Nutriture)
    TextView tv_Nutriture;
    @BindView(R.id.tv_OralMucosa)
    TextView tv_OralMucosa;
    @BindView(R.id.tv_Dysphagia)
    TextView tv_Dysphagia;
    @BindView(R.id.tv_Sleep)
    TextView tv_Sleep;
    @BindView(R.id.tv_DefecateType)
    TextView tv_DefecateType;
    @BindView(R.id.tv_DefecateTime)
    TextView tv_DefecateTime;
    @BindView(R.id.Urinate)
    TextView Urinate;
    @BindView(R.id.tv_LimbMovement)
    TextView tv_LimbMovement;
    @BindView(R.id.tv_SelfcareAbility)
    TextView tv_SelfcareAbility;
    @BindView(R.id.tv_SkinCondition)
    TextView tv_SkinCondition;
    @BindView(R.id.tv_SkinCondition_YC)
    TextView tv_SkinCondition_YC;
    @BindView(R.id.tv_SkinCondition_YCType)
    TextView tv_SkinCondition_YCType;
    @BindView(R.id.tv_SkinCondition_YCMJ)
    TextView SkinCondition_YCMJ;
    @BindView(R.id.tv_OtherDict)
    TextView tv_OtherDict;
    @BindView(R.id.tv_In_Notify)
    TextView tv_In_Notify;
    @BindView(R.id.tv_CreateDate)
    TextView tv_CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.tv_Habit)
    TextView tv_Habit;
    @BindView(R.id.tv_HabitTime)
    TextView tv_HabitTime;
    @BindView(R.id.tv_HabitTime_Wine)
    TextView tv_HabitTime_Wine;
    @BindView(R.id.ConsciousState_dui)
    TextView ConsciousState_dui;

    @Override
    protected int getContentLayout() {
        return R.layout.first_nurse_order_fragment_compile;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
    }

    @Override
    public void onResume() {
        super.onResume();
        patientData();
    }

    @OnClick({R.id.by_bj, R.id.bt_compile})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.by_bj:
                JumpUtils.jump(mContext, NurseEditActivity.class, null);
                break;
            case R.id.bt_compile:

                JumpUtils.jump(mContext, NurseEditActivity.class, null);
                break;
        }
    }

    private void patientData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin || null == loginBean) return;
        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("OptionType", "100540301");
        params.put("DateKey", "");
        params.put("UserID", loginBean.getUserID());
        params.put("PatID", patin.getPatID());

        NetRequest.getInstens().requestDate(params, Api.GetFirstNursing, false,
                new NetCallBack<ArrayList<FirstNurseBean>>(mContext, R.string.loading_get) {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onSuccess(ArrayList<FirstNurseBean> bean) {
                        if (null != bean && bean.size() > 0) {
                            FirstNurseBean firstNurseBean = bean.get(0);
                            tv_EducationLevel.setText(firstNurseBean.getEducationLevel());
                            tv_DataSources.setText(firstNurseBean.getDataSources());
//                            tv_RY_DIS.setText(patin.getZyDetail().getICD_Name());
                            tv_RY_DIS.setText(firstNurseBean.getRY_DIS());
                            tv_RY_Type.setText(firstNurseBean.getRY_Type());
                            tv_Allergic.setText(firstNurseBean.getAllergic());
                            tv_Favourer.setText(firstNurseBean.getFavourer());

                            if (!TextUtils.isEmpty(firstNurseBean.getConsciousState_DD())) {
                                if (firstNurseBean.getConsciousState_DD().startsWith("其他")) { //对答
                                    ConsciousState_dui.setText("对答:" + firstNurseBean.getConsciousState_DD());
                                } else {
                                    ConsciousState_dui.setText(firstNurseBean.getConsciousState_DD());
                                }
                            } else {
                                ConsciousState_dui.setText("");
                            }

                            if (!TextUtils.isEmpty(firstNurseBean.getConsciousState())) {
                                tv_ConsciousState.setText(firstNurseBean.getConsciousState());
                            } else {
                                tv_ConsciousState.setText("");
                            }
                            tv_Mood.setText(firstNurseBean.getMood_Adult());
                            tv_LanguagePerformance.setText(firstNurseBean.getLanguagePerformance());
                            tv_Diet.setText(firstNurseBean.getDiet());
                            tv_Nutriture.setText(firstNurseBean.getNutriture());
                            tv_OralMucosa.setText(firstNurseBean.getOralMucosa());
                            tv_Dysphagia.setText(firstNurseBean.getDysphagia());
                            tv_Sleep.setText(firstNurseBean.getSleep());
                            //排便情况
                            if (null != firstNurseBean && firstNurseBean.getDefecateType().contains("便秘")) {
                                tv_DefecateType.setText(firstNurseBean.getDefecateType() + "天");
                            } else if (firstNurseBean.getDefecateType().contains("腹泻")) {
                                tv_DefecateType.setText(firstNurseBean.getDefecateType() + "天");
                            } else {
                                tv_DefecateType.setText(firstNurseBean.getDefecateType());
                            }
                            tv_DefecateTime.setText(firstNurseBean.getDefecateTime());
                            Urinate.setText(firstNurseBean.getUrinate());
                            tv_LimbMovement.setText(firstNurseBean.getLimbMovement() + firstNurseBean.getLimbMovement_BW());
                            tv_SelfcareAbility.setText(firstNurseBean.getSelfcareAbility());
                            tv_SkinCondition.setText(firstNurseBean.getSkinCondition());
                            tv_SkinCondition_YC.setText("");
                            if (!TextUtils.isEmpty(firstNurseBean.getSkinCondition_YC())) {
                                tv_SkinCondition_YC.setText(firstNurseBean.getSkinCondition_YC());
                            } else {
                                tv_SkinCondition_YC.setText("");
                            }
                            if (!TextUtils.isEmpty(firstNurseBean.getSkinCondition_YCType())) {
                                tv_SkinCondition_YCType.setText(firstNurseBean.getSkinCondition_YCType());
                            } else {
                                tv_SkinCondition_YCType.setText("");
                            }
                            SkinCondition_YCMJ.setText(firstNurseBean.getSkinCondition_YCMJ());

                            String habit = firstNurseBean.getHabit();
                            String habitTime = firstNurseBean.getHabitTime();
                            String habitTimeWine = firstNurseBean.getHabitTime_Wine();

                            tv_Habit.setText(habit);

                            if (null != habitTime && habitTime.startsWith("烟")) {
                                tv_HabitTime.setText(habitTime);
                            } else {
                                tv_HabitTime.setText("");
                            }

                            if (null != habitTimeWine && habitTimeWine.startsWith("酒")) {
                                tv_HabitTime_Wine.setText(habitTimeWine);
                                tv_HabitTime_Wine.setVisibility(View.VISIBLE);
                            } else {
                                tv_HabitTime_Wine.setText("");
                            }

                            tv_OtherDict.setText(firstNurseBean.getOtherDict());
                            tv_CreateDate.setText("记录时间:" + firstNurseBean.getCreateDate());
                            tv_FZ_Nurse.setText("负责护士签名:" + firstNurseBean.getFZ_Nurse());
                            tv_In_Notify.setText(firstNurseBean.getIn_Notify());
                        }
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
