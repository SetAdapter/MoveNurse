package com.example.hjy.movenurse.edit.nursing;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.adapter.AntenatalExpectantSoleListAdapter;
import com.example.hjy.movenurse.adapter.AntenatalSoleAdapter;
import com.example.hjy.movenurse.restraint.AntenatalEvent;
import com.example.hjy.movenurse.restraint.AntenatalsEvent;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.AntenatalRecordBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/9/14 0014.
 * 产前待产记录单 fragment
 */

public class AntenatalExpectantFragment extends BaseFragment {

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;

    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//入院日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.ExecutiveNurse)
    TextView ExecutiveNurse;
    @BindView(R.id.hr_et)
    EditText hr_et;//温度
    @BindView(R.id.et_Hr)
    EditText et_Hr;//HR
    @BindView(R.id.et_R)
    EditText et_R;//R
    @BindView(R.id.editBp)
    EditText editBp;
    @BindView(R.id.editbpTwo)
    EditText editbpTwo;
    @BindView(R.id.r_et)
    EditText r_et;
    @BindView(R.id.bp_qet)
    EditText bp_qet;
    @BindView(R.id.gravidity)
    EditText gravidity;
    @BindView(R.id.fetal_et)
    EditText fetal_et;
    @BindView(R.id.quickening)
    EditText quickening;
    @BindView(R.id.editS)
    EditText editS;
    @BindView(R.id.editMin)
    EditText editMin;
    @BindView(R.id.Out_Project)
    EditText Out_Project;
    @BindView(R.id.Out_Amount)
    EditText Out_Amount;
    @BindView(R.id.Aerophose)
    EditText Aerophose;
    @BindView(R.id.exceptional)
    EditText exceptional;
    @BindView(R.id.In_Project)
    EditText In_Project;
    @BindView(R.id.In_Amount)
    EditText In_Amount;
    @BindView(R.id.spo2)
    EditText spo2;

    private String mPatID = "";
    String OC_AFIRg = "";//选中的羊水性状
    String OC_POTFRg = "";//选中的胎位
    String KneeJerk = "";//选中的膝反射
    String OC_UCSStr = "";//选中的宫缩
    String OC_CS_rgStr = "";//选中的宫颈质
    String OC_Caul_rgStr = "";//选中的胎膜
    String OC_CDStr = "";//选中的宫颈扩张
    String OC_SOStr = "";//选中的先露高低

    String OC_AFIRg_No = "";//选中的羊水性状No
    String OC_POTFRg_No = "";//选中的胎位No
    String KneeJerk_No = "";//选中的膝反射No
    String OC_UCSStr_No = "";//选中的宫缩No
    String OC_CS_rgStr_No = "";//选中的宫颈质No
    String OC_Caul_rgStr_No = "";//选中的胎膜No
    String OC_CDStr_No = "";//选中的宫颈扩张No
    String OC_SOStr_No = "";//选中的先露高低No

    String editSSr = "";//持续时间开始
    String editMinSr = "";//持续时间结束
    String AerophoseStr = " ";
    String gravidityStr = " ";
    String ParityStr = " ";
    String GestationalWeeksStr = " ";
    String mRyDisStr = " ";
    String ExecutiveNurseStr = " ";
    LoginBean loginBean;
    AntenatalSoleAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_antenatal_expectant;
    }

    @Override
    protected void baseInit() {
        initDepartRv();
//        EventBus.getDefault().register(this);
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        loginBean = (LoginBean) mCache.getAsObject("UserName");
        mPatID = patin.getPatID();
        tvHospitalDate.setText(patin.getZyDetail().getIn_Date());
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);
        ExecutiveNurseStr = loginBean.getUserName();
        ResourceUtils.setText(mContext, ExecutiveNurse, R.string.ExecutiveNurse,ExecutiveNurseStr);

        editS.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editSSr = s.toString();
            }
        });
        editMin.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                editMinSr = s.toString();
            }
        });

        Aerophose.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                AerophoseStr = s.toString();
            }
        });
        gravidity.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                gravidityStr = s.toString();
            }
        });
        //产次
        r_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                ParityStr = s.toString();
            }
        });
        //孕周
        bp_qet.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                GestationalWeeksStr = s.toString();
            }
        });
    }

    private void initDepartRv() {
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        mAdapter = new AntenatalSoleAdapter(mContext, new ArrayList<>());
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(mAdapter);

        getDepartList();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10045");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10045, false, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<AntenatalExpectant> data) {
                if (null != data) {
                    mAdapter.setmDatas(data);
                    mAdapter.notifyDataSetChanged();
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


//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getData(AntenatalEvent antenatalEvent) {
//        editS = antenatalEvent.getEditS();
//    }
//
//    @Subscribe(threadMode = ThreadMode.MAIN)
//    public void getData(AntenatalsEvent antenatalEvent) {
//        editMin = antenatalEvent.getEditMin();
//    }

    @OnClick({R.id.btnSave, R.id.fab})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.fab: //临时保存
                ScreenData();
                Map<String, Object> params = patientCashData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes2, params);
                T.showShort("临时保存成功");
                break;
            case R.id.btnSave://保存按钮
                ScreenData();
                ScreenDataNo();
                saveNursingRecodes();
                break;
        }
    }

    private void ScreenData() {
        SparseArray<AntenatalExpectantSoleListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++) {
            AntenatalExpectantSoleListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1) {
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeName();
                switch (i) {
                    case 0:
                        OC_POTFRg = context;
                        break;
                    case 1:
                        OC_UCSStr = context;
                        break;
                    case 2:
                        OC_CDStr = context;
                        break;
                    case 3:
                        OC_CS_rgStr = context;
                        break;
                    case 4:
                        OC_SOStr = context;
                        break;
                    case 5:
                        OC_Caul_rgStr = context;
                        break;
                    case 6:
                        OC_AFIRg = context;
                        break;
                    case 7:
                        KneeJerk = context;
                        break;
                }
            }
        }
    }
    private void ScreenDataNo() {
        SparseArray<AntenatalExpectantSoleListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++) {
            AntenatalExpectantSoleListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1) {
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeID();
                switch (i) {
                    case 0:
                        OC_POTFRg_No = context;
                        break;
                    case 1:
                        OC_UCSStr_No = context;
                        break;
                    case 2:
                        OC_CDStr_No = context;
                        break;
                    case 3:
                        OC_CS_rgStr_No = context;
                        break;
                    case 4:
                        OC_SOStr_No = context;
                        break;
                    case 5:
                        OC_Caul_rgStr_No = context;
                        break;
                    case 6:
                        OC_AFIRg_No = context;
                        break;
                    case 7:
                        KneeJerk_No = context;
                        break;
                }
            }
        }
    }


    //保存及获取产科产后记录单列表
    private void saveNursingRecodes() {
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.ObstetricsRecodes2, false, new NetCallBack<ArrayList<AntenatalRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<AntenatalRecordBean> nursingRecordBeen) {
//                saveNursingRecodes2();
                if (null != nursingRecordBeen) {
                    AntenatalRecordBean antenatalRecordBean = nursingRecordBeen.get(0);
                    mCache.put("AntenatalRecordBean", antenatalRecordBean);
                    getActivity().finish();
                    T.showShort("保存成功");
                }

            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes1, params);
//                }
            }

            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }


    /**
     * 临时 保存 页面
     */
    private Map<String, Object> patientCashData() {
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "2");
        params.put("PA_ID", mPatID);
        params.put("UserID", loginBean.getUserID());
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("VitalSign_T", hr_et.getText().toString().trim());//体温
        params.put("VitalSign_PAndHR", et_Hr.getText().toString().trim());//脉搏/心率
        params.put("VitalSign_R", et_R.getText().toString().trim());//呼吸
        params.put("VitalSign_SP02", spo2.getText().toString().trim());//呼吸
        String bp = editBp.getText().toString().trim() + "/" + editbpTwo.getText().toString().trim();
        if (bp.equals("/")) {
            bp = "";
        }
        params.put("VitalSign_BP", bp);//血压
        params.put("OC_FHt", fetal_et.getText().toString().trim());//胎心
        params.put("OC_FM", quickening.getText().toString().trim());//自动记胎

        params.put("OC_POTF", OC_POTFRg);//胎位
        params.put("OC_POTF_No", OC_POTFRg_No);//胎位No
        params.put("OC_UCS", OC_UCSStr);//宫缩强度
        params.put("OC_UCS_No", OC_UCSStr_No);//宫缩强度
        params.put("OC_CD", OC_CDStr);//宫颈扩张
        params.put("OC_CD_No", OC_CDStr_No);//宫颈扩张
        params.put("OC_CS", OC_CS_rgStr);//宫缩质
        params.put("OC_CS_No", OC_CS_rgStr_No);//宫缩质
        params.put("OC_SO", OC_SOStr);//先露高低
        params.put("OC_SO_No", OC_SOStr_No);//先露高低
        params.put("OC_Caul", OC_Caul_rgStr);//胎膜
        params.put("OC_Caul_No", OC_Caul_rgStr_No);//胎膜
        params.put("OC_AFI", OC_AFIRg);//羊水形状
        params.put("OC_AFI_No", OC_AFIRg_No);//羊水形状
        params.put("KneeJerk", KneeJerk);//膝反射
        params.put("KneeJerk_No", KneeJerk_No);//膝反射

        String OC_UCI = editSSr + "/" + editMinSr;
        if (OC_UCI.equals("/")) {
            OC_UCI = "";
        }
        params.put("Gravidity", gravidityStr);//孕次
        params.put("Parity", ParityStr);//产次
        params.put("GestationalWeeks", GestationalWeeksStr);//孕周
        params.put("OC_UCI", OC_UCI);//宫缩持续间歇

        params.put("In_Project", In_Project.getText().toString().trim());//入内容
        params.put("In_Amount", In_Amount.getText().toString().trim());//入量
        params.put("Out_Project", Out_Project.getText().toString().trim());//出内容
        params.put("Out_Amount", Out_Amount.getText().toString().trim());//出量
        params.put("OtherSituation", exceptional.getText().toString().trim());//特殊情况记录
        params.put("Aerophose", AerophoseStr);//氧气吸入
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", ExecutiveNurseStr);//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士
        return params;
    }
}
