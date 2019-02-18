package com.example.hjy.movenurse.auxiliary;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.adapter.RespiratorAuxiliaryRecordAdapter;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantAdapter;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantListAdapter;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RespiratorAuxiliary;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/9/18 0018.
 * 新生儿科呼吸机辅助呼吸记录单_编辑
 */

public class RespiratorAuxiliaryRecordFragment extends BaseFragment {

    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//入院日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.FiO2)
    EditText FiO2;
    @BindView(R.id.RR)
    EditText RR;
    @BindView(R.id.PIP)
    EditText PIP;
    @BindView(R.id.PEEP)
    EditText PEEP;
    @BindView(R.id.IE)
    EditText IE;
    @BindView(R.id.SaO2)
    EditText SaO2;
    @BindView(R.id.PH)
    EditText PH;
    @BindView(R.id.PO2)
    EditText PO2;
    @BindView(R.id.PCO2)
    EditText PCO2;
    @BindView(R.id.HCO2)
    EditText HCO2;
    @BindView(R.id.BE)
    EditText BE;
    @BindView(R.id.LacticAcid)
    EditText LacticAcid;
    @BindView(R.id.TCType)
    EditText TCType;
    @BindView(R.id.TCDepth)
    EditText TCDepth;
    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    @BindView(R.id.ExecutiveNurse)
    TextView ExecutiveNurse;
    String ExecutiveNurseStr;
    String mDateKey;
    String mRyDisStr = "";
    String VentilationModeRg = "";//呼吸机参数-通气模式
    String VentilationModeRg_No = "";//呼吸机参数-通气模式
    RespiratorAuxiliaryRecordAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_respirator_auxiliary;
    }

    protected void baseInitView() {
        initDepartRv();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        tvHospitalDate.setText(patin.getZyDetail().getIn_Date());
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        ExecutiveNurseStr = loginBean.getUserName();
        ResourceUtils.setText(mContext, ExecutiveNurse, R.string.ExecutiveNurse,ExecutiveNurseStr);
        }

    private void initDepartRv(){
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        mAdapter = new RespiratorAuxiliaryRecordAdapter(mContext, new ArrayList<>());
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(mAdapter);

        getDepartList();
    }

    private void getDepartList(){
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10049");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10049, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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

    @OnClick({R.id.btnSave,R.id.fab})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.fab: //临时保存
                ScreenData();
                Map<String, Object> params = patientCashData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.NCAssistanceRecodes, params);
                T.showShort("临时保存成功");
                break;
            case R.id.btnSave://保存按钮
                ScreenData();
                ScreenData_No();
                SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
                Date date = new Date(System.currentTimeMillis());//获取当前时间
                String mDateKeys = format.format(date);
                long l = TimeUtils.timeString2long(mDateKeys, "yyyy-MM-dd HH:mm");
                mDateKey = l + "";
                saveNursingRecodes();
                break;
        }
    }

    private void ScreenData() {
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++){
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1){
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeName();
                switch (i){
                    case 0:
                        VentilationModeRg = context;
                        break;
                }
            }
        }
    }
    private void ScreenData_No() {
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++){
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1){
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeID();
                switch (i){
                    case 0:
                        VentilationModeRg_No = context;
                        break;
                }
            }
        }
    }

    //保存及获取新生儿护理记录单列表
    private void saveNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin) return;
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.NCAssistanceRecodes, false, new NetCallBack<ArrayList<RespiratorAuxiliary>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<RespiratorAuxiliary> nursingRecordBeen) {
                if (null != nursingRecordBeen) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.NCAssistanceRecodes, params);
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
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "2");
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("DateKey", mDateKey); //时间戳
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("FiO2", FiO2.getText().toString().trim());//FiO2
        params.put("RR", RR.getText().toString().trim());//RR
        params.put("PIP", PIP.getText().toString().trim());//PIP
        params.put("PEEP", PEEP.getText().toString().trim());//PEEP
        params.put("IE", IE.getText().toString().trim());//IE
        params.put("SaO2", SaO2.getText().toString().trim());//血氧浓度
        params.put("PH", PH.getText().toString().trim());//PH
        params.put("PO2", PO2.getText().toString().trim());//PO2
        params.put("PCO2", PCO2.getText().toString().trim());//PCO2
        params.put("HCO2", HCO2.getText().toString().trim());//HCO2
        params.put("BE", BE.getText().toString().trim());//BE
        params.put("LacticAcid", LacticAcid.getText().toString().trim());//乳酸
        params.put("TCType", TCType.getText().toString().trim());//TCType
        params.put("TCDepth", TCDepth.getText().toString().trim());//TCDepth
        params.put("VentilationMode", VentilationModeRg);//呼吸机参数-通气模式
        params.put("VentilationMode_No", VentilationModeRg_No);//呼吸机参数-通气模式
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse",ExecutiveNurseStr);//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士
        return params;
    }
}
