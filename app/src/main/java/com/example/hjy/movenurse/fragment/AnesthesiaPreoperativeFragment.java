package com.example.hjy.movenurse.fragment;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.activity.AnesthesiaPreoperativeActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.AnesthesiaPreoperativeBean;
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
 * 麻醉术前预览页面
 * <p/>Created by Gab on 2017/9/11 0011.
 */
public class AnesthesiaPreoperativeFragment extends BaseFragment {

    @BindView(R.id.tv_In_Dis)
    TextView tv_In_Dis;
    @BindView(R.id.ModusOperandi)
    TextView ModusOperandi;
    @BindView(R.id.IsHealthy)
    TextView IsHealthy;
    @BindView(R.id.HasHXSick)
    TextView HasHXSick;
    @BindView(R.id.HasAnesthesia)
    TextView HasAnesthesia;
    @BindView(R.id.VitalSign_T)
    TextView VitalSign_T;
    @BindView(R.id.consciousness)
    TextView consciousness;
    @BindView(R.id.toothStatus)
    TextView toothStatus;
    @BindView(R.id.XFFStatus)
    TextView XFFStatus;
    @BindView(R.id.VertebraStatus)
    TextView VertebraStatus;
    @BindView(R.id.MallampatiGrade)
    TextView MallampatiGrade;
    @BindView(R.id.ECG)
    TextView ECG;
    @BindView(R.id.CXR)
    TextView CXR;
    @BindView(R.id.Lung)
    TextView Lung;
    @BindView(R.id.CBC)
    TextView CBC;
    @BindView(R.id.Coagulation)
    TextView Coagulation;
    @BindView(R.id.LFT)
    TextView LFT;
    @BindView(R.id.Renal)
    TextView Renal;
    @BindView(R.id.GLU)
    TextView GLU;
    @BindView(R.id.OtherEx)
    TextView OtherEx;
    @BindView(R.id.ASA)
    TextView ASA;
    @BindView(R.id.OrderOther)
    TextView OrderOther;
    @BindView(R.id.AnesthesiaType)
    TextView AnesthesiaType;
    @BindView(R.id.AnesthesiaIndication)
    TextView AnesthesiaIndication;
    @BindView(R.id.AnesthesiaOther)
    TextView AnesthesiaOther;
    @BindView(R.id.HeartGrade)
    TextView HeartGrade;
    @BindView(R.id.operation_date)
    TextView operation_date;//手术日期
    @BindView(R.id.tv_CreateDate)
    TextView CreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView FZ_Nurse;

    @Override
    protected int getContentLayout() {
        return R.layout.anesthesia_preoperative_fragment;
    }

    @Override
    public void onResume() {
        super.onResume();
        patientData();
    }

    @SuppressLint("SetTextI18n")
    private void setContent(AnesthesiaPreoperativeBean anesthesiaBean) {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin || null != anesthesiaBean) {
            if (!TextUtils.isEmpty(patin.getZyDetail().getICD_Name())) {
                tv_In_Dis.setText(patin.getZyDetail().getICD_Name());
            } else {
                tv_In_Dis.setText(anesthesiaBean.getAnesthesiaBefore().getIn_Dis());
            }
            ModusOperandi.setText(anesthesiaBean.getAnesthesiaBefore().getModusOperandi());
            if (anesthesiaBean.getAnesthesiaBefore().getIsHealthy().contains("过敏史")) {
                IsHealthy.setText(anesthesiaBean.getAnesthesiaBefore().getIsHealthy());
            }
            IsHealthy.setText(anesthesiaBean.getAnesthesiaBefore().getIsHealthy());
            HasHXSick.setText(anesthesiaBean.getAnesthesiaBefore().getHasHXSick());
            HasAnesthesia.setText(anesthesiaBean.getAnesthesiaBefore().getHasAnesthesia());
            String vitalSign_t = anesthesiaBean.getAnesthesiaBefore().getVitalSign_T();
            String vitalSign_p = anesthesiaBean.getAnesthesiaBefore().getVitalSign_P();
            String vitalSign_r = anesthesiaBean.getVitalSign_R();
            String vitalSign_bp = anesthesiaBean.getVitalSign_BP();

            if (TextUtils.isEmpty(vitalSign_t) && TextUtils.isEmpty(vitalSign_p) && TextUtils.isEmpty(vitalSign_r) && TextUtils.isEmpty(vitalSign_bp)) {
                VitalSign_T.setVisibility(View.GONE);
            } else {
                VitalSign_T.setText("T:" + anesthesiaBean.getAnesthesiaBefore().getVitalSign_T() + "°C" + "     " +
                        "P:" + anesthesiaBean.getAnesthesiaBefore().getVitalSign_P() + "次/分" + "     " +
                        "R:" + anesthesiaBean.getVitalSign_R() + "次/分" + "     " +
                        "BP:" + anesthesiaBean.getVitalSign_BP() + " mmHg");
            }
            consciousness.setText(anesthesiaBean.getConsciousness());
            toothStatus.setText(anesthesiaBean.getAnesthesiaBefore().getToothStatus());
            XFFStatus.setText(anesthesiaBean.getAnesthesiaBefore().getXFFStatus());
            VertebraStatus.setText(anesthesiaBean.getAnesthesiaBefore().getVertebraStatus());
            MallampatiGrade.setText(anesthesiaBean.getAnesthesiaBefore().getMallampatiGrade());
            ECG.setText(anesthesiaBean.getAnesthesiaBefore().getECG());
            CXR.setText(anesthesiaBean.getAnesthesiaBefore().getCXR());
            String lung = anesthesiaBean.getAnesthesiaBefore().getLung();
            if (!TextUtils.isEmpty(lung)) {
                if (lung.equals("正常")) {
                    Lung.setText("正常");
                } else {
                    Lung.setText(anesthesiaBean.getAnesthesiaBefore().getLung());
                }
            }
            CBC.setText(anesthesiaBean.getAnesthesiaBefore().getCBC());
            Coagulation.setText(anesthesiaBean.getAnesthesiaBefore().getCoagulation());
            LFT.setText(anesthesiaBean.getAnesthesiaBefore().getLFT());
            Renal.setText(anesthesiaBean.getAnesthesiaBefore().getRenal());
            GLU.setText(anesthesiaBean.getAnesthesiaBefore().getGLU());
            OtherEx.setText(anesthesiaBean.getAnesthesiaBefore().getOtherEx());
            ASA.setText(anesthesiaBean.getAnesthesiaBefore().getASA());
            OrderOther.setText(anesthesiaBean.getAnesthesiaBefore().getOrderOther());
            AnesthesiaType.setText(anesthesiaBean.getAnesthesiaBefore().getAnesthesiaType());
            AnesthesiaIndication.setText(anesthesiaBean.getAnesthesiaBefore().getAnesthesiaIndication());
            AnesthesiaOther.setText(anesthesiaBean.getAnesthesiaBefore().getAnesthesiaOther());
            CreateDate.setText("记录时间:" + anesthesiaBean.getVisitNurseDate());
            FZ_Nurse.setText("负责护士签名:" + anesthesiaBean.getVisitNurse());
            operation_date.setText(anesthesiaBean.getAnesthesiaBefore().getSurgeryDate());
            HeartGrade.setText(anesthesiaBean.getAnesthesiaBefore().getHeartGrade());
        }
    }

    @OnClick({R.id.save_btn, R.id.bt_compile})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                JumpUtils.jump(mContext, AnesthesiaPreoperativeActivity.class, null);
                break;
            case R.id.bt_compile:
                JumpUtils.jump(mContext, AnesthesiaPreoperativeActivity.class, null);
                break;
        }
    }

    private void patientData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin || null == loginBean) return;

        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("OrderType", "1");
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("DateKey", "");

        NetRequest.getInstens()
                .requestDate(params, Api.AnesthesiaVisit1, false, new NetCallBack<ArrayList<AnesthesiaPreoperativeBean>>(mContext, R.string.loading_get) {
                    @Override
                    public void onSuccess(ArrayList<AnesthesiaPreoperativeBean> bean) {
                        if (null != bean && bean.size() > 0) {
                            AnesthesiaPreoperativeBean entity = bean.get(0);
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
