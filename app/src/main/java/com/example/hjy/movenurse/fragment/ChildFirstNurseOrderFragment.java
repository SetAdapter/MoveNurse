package com.example.hjy.movenurse.fragment;

import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.activity.ChildFirstNurseOrderActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.ChildFirstNurseBean;
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
 * 儿童首次护理预览
 * <p/>Created by Gab on 2017/9/6 0006.
 */
public class ChildFirstNurseOrderFragment extends BaseFragment {

    @BindView(R.id.tv_EducationLevel)
    TextView mEducationLevel;
    @BindView(R.id.DataSources)
    TextView DataSources;
    @BindView(R.id.RY_DIS)
    TextView RY_DIS;
    @BindView(R.id.RY_Type)
    TextView RY_Type;
    @BindView(R.id.Allergic)
    TextView Allergic;
    @BindView(R.id.Favourer)
    TextView Favourer;
    @BindView(R.id.ConsciousState)
    TextView ConsciousState;
    @BindView(R.id.ConsciousState_dui)
    TextView ConsciousState_dui;
    @BindView(R.id.Mood_Chird)
    TextView Mood_Chird;
    @BindView(R.id.Mood_Adult)
    TextView Mood_Adult;
    @BindView(R.id.LanguagePerformance)
    TextView LanguagePerformance;
    @BindView(R.id.Diet)
    TextView Diet;
    @BindView(R.id.Nutriture)
    TextView Nutriture;
    @BindView(R.id.OralMucosa)
    TextView OralMucosa;
    @BindView(R.id.Dysphagia)
    TextView Dysphagia;
    @BindView(R.id.Sleep)
    TextView Sleep;
    @BindView(R.id.DefecateType)
    TextView DefecateType;
    @BindView(R.id.tv_DefecateTime)
    TextView DefecateTime;
    @BindView(R.id.Urinate)
    TextView Urinate;
    @BindView(R.id.LimbMovement)
    TextView LimbMovement;
    @BindView(R.id.ChestAppearance)
    TextView ChestAppearance;
    @BindView(R.id.SkinCondition)
    TextView SkinCondition;
    @BindView(R.id.SelfcareAbility)
    TextView SelfcareAbility;
    @BindView(R.id.SkinCondition_YC)
    TextView SkinCondition_YC;
    @BindView(R.id.SkinCondition_YCType)
    TextView SkinCondition_YCType;
    @BindView(R.id.SkinCondition_YCMJ)
    TextView SkinCondition_YCMJ;
    @BindView(R.id.OtherDict)
    TextView OtherDict;
    @BindView(R.id.In_Notify)
    TextView In_Notify;
    @BindView(R.id.tv_CreateDate)
    TextView CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView FZ_Nurse;
    @BindView(R.id.HeadCondition)
    TextView HeadCondition;
    @BindView(R.id.FrontalSuture)
    TextView FrontalSuture;

    @Override
    protected int getContentLayout() {
        return R.layout.child_first_nurse_order_activity;
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

    private void setContent(ChildFirstNurseBean childFirstNurseBean) {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        RY_DIS.setText(patin.getZyDetail().getICD_Name()); //入院诊断
        if (null != childFirstNurseBean) {
            mEducationLevel.setText(childFirstNurseBean.getHabitation()); //生活环境
            DataSources.setText(childFirstNurseBean.getDataSources()); //资料来源
            RY_Type.setText(childFirstNurseBean.getRY_Type()); //入院方式
            Allergic.setText(childFirstNurseBean.getAllergic()); //过敏史
            Favourer.setText(childFirstNurseBean.getFavourer()); //日常照顾者
            if (!TextUtils.isEmpty(childFirstNurseBean.getConsciousState())) {
                ConsciousState.setText(childFirstNurseBean.getConsciousState());//意识状态 呼之
            } else {
                ConsciousState.setText("");
            }
            if (!TextUtils.isEmpty(childFirstNurseBean.getConsciousState_DD())) {
                if (childFirstNurseBean.getConsciousState_DD().startsWith("其他")) { //对答
                    ConsciousState_dui.setText("对答:" + childFirstNurseBean.getConsciousState_DD());
                } else {
                    ConsciousState_dui.setText(childFirstNurseBean.getConsciousState_DD());
                }
            } else {
                ConsciousState_dui.setText("");
            }
            Mood_Chird.setText(childFirstNurseBean.getMood_Chird()); //患儿情绪
            Mood_Adult.setText(childFirstNurseBean.getMood_Adult()); //家属情绪
            LanguagePerformance.setText(childFirstNurseBean.getLanguagePerformance()); //语言表达
            Diet.setText(childFirstNurseBean.getDiet()); //饮食
            Nutriture.setText(childFirstNurseBean.getNutriture()); //营养状况
            OralMucosa.setText(childFirstNurseBean.getOralMucosa()); //口腔黏膜
            Dysphagia.setText(childFirstNurseBean.getDysphagia()); //吞咽困难
            Sleep.setText(childFirstNurseBean.getSleep()); //睡眠情况
            Urinate.setText(childFirstNurseBean.getUrinate()); //排尿情况
            LimbMovement.setText(childFirstNurseBean.getLimbMovement()); //四肢活动
            ChestAppearance.setText(childFirstNurseBean.getChestAppearance()); //胸部外观
            if (!TextUtils.isEmpty(childFirstNurseBean.getHeadCondition())) {
                HeadCondition.setText("头颅:" + childFirstNurseBean.getHeadCondition()); //头颅
            } else {
                HeadCondition.setText("");
            }
            if (!TextUtils.isEmpty(childFirstNurseBean.getFrontalSuture())) {
                FrontalSuture.setText("前囟:" + childFirstNurseBean.getFrontalSuture()); //前囟
            } else {
                FrontalSuture.setText(""); //前囟
            }

            if (TextUtils.isEmpty(childFirstNurseBean.getDefecateType())) {
                DefecateType.setVisibility(View.GONE);
            } else {
                DefecateType.setText(childFirstNurseBean.getDefecateType()); // 排便情况
            }
            if (TextUtils.isEmpty(childFirstNurseBean.getDefecateTime())) {
                DefecateType.setVisibility(View.GONE);
            } else {
                DefecateTime.setText(childFirstNurseBean.getDefecateTime()); // 排便情况
            }
            SkinCondition.setText(childFirstNurseBean.getSkinCondition()); //皮肤黏膜
            SelfcareAbility.setText(childFirstNurseBean.getSelfcareAbility()); //生活能力
            SkinCondition_YC.setText(childFirstNurseBean.getSkinCondition_YC()); //压疮部位
            SkinCondition_YCType.setText(childFirstNurseBean.getSkinCondition_YCType()); //压疮部位
            SkinCondition_YCMJ.setText(childFirstNurseBean.getSkinCondition_YCMJ()); //压疮面积
            OtherDict.setText(childFirstNurseBean.getOtherDict()); //其他情况
            In_Notify.setText(childFirstNurseBean.getIn_Notify()); //住院告知
            CreateDate.setText("记录时间:" + childFirstNurseBean.getCreateDate());
            FZ_Nurse.setText("负责护士签名:" + childFirstNurseBean.getFZ_Nurse());
        }
    }

    @OnClick({R.id.by_bj, R.id.bt_compile})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.by_bj:
                JumpUtils.jump(mContext, ChildFirstNurseOrderActivity.class, null);
                break;
            case R.id.bt_compile:
                JumpUtils.jump(mContext, ChildFirstNurseOrderActivity.class, null);
                break;
        }
    }

    private void patientData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin || null == loginBean) return;

        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("OrderType", "2");
        params.put("DateKey", "");
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());


        NetRequest.getInstens().requestDate(params, Api.FirstChildNursingRecord2, false, new NetCallBack<ArrayList<ChildFirstNurseBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<ChildFirstNurseBean> bean) {
                if (null != bean && bean.size() > 0) {
                    ChildFirstNurseBean childFirstNurseBean = bean.get(0);
                    setContent(childFirstNurseBean);
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
