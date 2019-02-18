package com.example.hjy.movenurse.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.activity.AnesthesiaPostoperationActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.AnesthesiaPostoperationBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.cache.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 麻醉术后预览页面
 * <p/>Created by Gab on 2017/9/11 0011.
 */
public class AnesthesiaPostoperationFragment extends BaseFragment {

    @BindView(R.id.SurgeryEndDate)
    TextView SurgeryEndDate;
    @BindView(R.id.Postoperative)
    TextView Postoperative;
    @BindView(R.id.mConsciousnessStr)
    TextView mConsciousnessStr;
    @BindView(R.id.BreathingStatus)
    TextView BreathingStatus;
    @BindView(R.id.GA)
    TextView GA;
    @BindView(R.id.SA)
    TextView SA;
    @BindView(R.id.GA_BLReason)
    TextView GA_BLReason;
    @BindView(R.id.OffGoingPerson)
    TextView OffGoingPerson;
    @BindView(R.id.OnComingPerson)
    TextView OnComingPerson;
    @BindView(R.id.VitalSign)
    TextView VitalSign;
    @BindView(R.id.MentalStatus)
    TextView MentalStatus;
    @BindView(R.id.GA_KP)
    TextView GA_KP;
    @BindView(R.id.GA_JZL)
    TextView GA_JZL;
    @BindView(R.id.GA_EO)
    TextView GA_EO;
    @BindView(R.id.SA_TT)
    TextView SA_TT;
    @BindView(R.id.SA_NZL)
    TextView SA_NZL;
    @BindView(R.id.SA_SXGY)
    TextView SA_SXGY;
    @BindView(R.id.NB_SS)
    TextView NB_SS;
    @BindView(R.id.NB_HGY)
    TextView NB_HGY;
    @BindView(R.id.OtherAf)
    TextView OtherAf;
    @BindView(R.id.Exceptional)
    TextView Exceptional;
    @BindView(R.id.OrderOtherAf)
    TextView ExceptioOrderOtherAfnal;
    @BindView(R.id.mSignStr)
    TextView SignStr;
    @BindView(R.id.RecConsciousness)
    TextView RecConsciousness;
    @BindView(R.id.tv_CreateDate)
    TextView CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView FZ_Nurse;

    @Override
    protected int getContentLayout() {
        return R.layout.anesthesia_postoperation_fragment;
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

    @SuppressLint("SetTextI18n")
    private void setContent(AnesthesiaPostoperationBean anesthesiaBean) {
        if (null != anesthesiaBean) {
            SurgeryEndDate.setText(anesthesiaBean.getAnesthesiaAfter().getSurgeryEndDate());
            Postoperative.setText(anesthesiaBean.getAnesthesiaAfter().getPostoperative());
            if (anesthesiaBean.getConsciousness().startsWith("清醒")) {
                mConsciousnessStr.setText(anesthesiaBean.getConsciousness());
//                mConsciousnessStr.setText(anesthesiaBean.getConsciousness().substring(0,2) + "麻醉清醒时间：" + anesthesiaBean.getConsciousness().replace("清醒",""));
            } else {
                mConsciousnessStr.setText(anesthesiaBean.getConsciousness());
            }
            BreathingStatus.setText(anesthesiaBean.getAnesthesiaAfter().getBreathingStatus() + "");

            GA.setText(anesthesiaBean.getAnesthesiaAfter().getGA());

            if (!TextUtils.isEmpty(anesthesiaBean.getAnesthesiaAfter().getGA_BLReason())) {
                GA_BLReason.setText("延迟拔管原因：" + anesthesiaBean.getAnesthesiaAfter().getGA_BLReason());
            } else {
                GA_BLReason.setText("");
            }

            if (!TextUtils.isEmpty(anesthesiaBean.getAnesthesiaAfter().getSA())) {
                SA.setText("麻醉平面：" + anesthesiaBean.getAnesthesiaAfter().getSA());
            }
            OffGoingPerson.setText("交班人：" + anesthesiaBean.getAnesthesiaAfter().getOffGoingPerson());
            OnComingPerson.setText("接班人：" + anesthesiaBean.getAnesthesiaAfter().getOnComingPerson());
            SignStr.setText(anesthesiaBean.getAnesthesiaAfter().getVitalSign());
            RecConsciousness.setText(anesthesiaBean.getAnesthesiaAfter().getRecConsciousness());
            if (TextUtils.isEmpty(anesthesiaBean.getAnesthesiaAfter().getVitalSign_HR()) && TextUtils.isEmpty(anesthesiaBean.getVitalSign_R()) && TextUtils.isEmpty(anesthesiaBean.getVitalSign_BP())
                    && TextUtils.isEmpty(anesthesiaBean.getAnesthesiaAfter().getVitalSign_SPO2())) {
                VitalSign.setText("");
            } else {
                VitalSign.setText("HR：" + anesthesiaBean.getAnesthesiaAfter().getVitalSign_HR() + "次/分" + "     " + "R：" + anesthesiaBean.getVitalSign_R() + "次/分" + "     " +
                        "BP：" + anesthesiaBean.getVitalSign_BP() + "mmHg" + "     " + "SpO₂：" + anesthesiaBean.getAnesthesiaAfter().getVitalSign_SPO2() + "%");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getGA_KP().isEmpty()) {
                GA_KP.setText("全麻咳嗽排痰" + anesthesiaBean.getAnesthesiaAfter().getGA_KP());
            } else {
                GA_KP.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getGA_JZL().isEmpty()) {
                GA_JZL.setText("肌张力：" + anesthesiaBean.getAnesthesiaAfter().getGA_JZL());
            } else {
                GA_JZL.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getGA_EO().isEmpty()) {
                GA_EO.setText("恶心呕吐：" + anesthesiaBean.getAnesthesiaAfter().getGA_EO());
            } else {
                GA_EO.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getSA_NZL().isEmpty()) {
                SA_NZL.setText("尿潴留：" + anesthesiaBean.getAnesthesiaAfter().getSA_NZL());
            } else {
                SA_NZL.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getSA_TT().isEmpty()) {
                SA_TT.setText("脊麻头痛：" + anesthesiaBean.getAnesthesiaAfter().getSA_TT());
            } else {
                SA_TT.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getSA_SXGY().isEmpty()) {
                SA_SXGY.setText("脊麻双下肢感觉、运动：" + anesthesiaBean.getAnesthesiaAfter().getSA_SXGY());
            } else {
                SA_SXGY.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getNB_SS().isEmpty()) {
                NB_SS.setText("声音嘶哑：" + anesthesiaBean.getAnesthesiaAfter().getNB_SS());
            } else {
                NB_SS.setText("");
            }
            if (!anesthesiaBean.getAnesthesiaAfter().getNB_HGY().isEmpty()) {
                NB_HGY.setText("患肢感觉运动：" + anesthesiaBean.getAnesthesiaAfter().getNB_HGY());
            } else {
                NB_HGY.setText("");
            }
            OtherAf.setText(anesthesiaBean.getAnesthesiaAfter().getOtherAf());
            Exceptional.setText(anesthesiaBean.getAnesthesiaAfter().getExceptional());
            ExceptioOrderOtherAfnal.setText(anesthesiaBean.getAnesthesiaAfter().getOrderOtherAf());
            MentalStatus.setText(anesthesiaBean.getAnesthesiaAfter().getMentalStatus());
            CreateDate.setText("记录时间：" + anesthesiaBean.getVisitNurseDate());
            FZ_Nurse.setText("负责护士签名：" + anesthesiaBean.getVisitNurse());
        }
    }

    @OnClick({R.id.save_btn, R.id.bt_compile})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                JumpUtils.jump(mContext, AnesthesiaPostoperationActivity.class, null);
                break;
            case R.id.bt_compile:
                JumpUtils.jump(mContext, AnesthesiaPostoperationActivity.class, null);
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
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("DateKey", "");

        NetRequest.getInstens()
                .requestDate(params, Api.AnesthesiaVisit2, false, new NetCallBack<ArrayList<AnesthesiaPostoperationBean>>(mContext, R.string.loading_get) {
                    @Override
                    public void onSuccess(ArrayList<AnesthesiaPostoperationBean> bean) {
                        if (null != bean && bean.size() > 0) {
                            AnesthesiaPostoperationBean entity = bean.get(0);
                            setContent(entity);
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
