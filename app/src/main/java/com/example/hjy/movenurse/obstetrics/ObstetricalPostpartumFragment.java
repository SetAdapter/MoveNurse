package com.example.hjy.movenurse.obstetrics;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.adapter.AntenatalExpectantSoleListAdapter;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantAdapter;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantListAdapter;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.ObstetricalPostpartum;
import com.fy.entity.PatientsBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Stefan on 2017/9/14.
 * 产科产后记录单_编辑
 */

public class ObstetricalPostpartumFragment extends BaseFragment {

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//入院日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.T_et)
    EditText T_et;//温度
    @BindView(R.id.Hr_et)
    EditText Hr_et;//HR
    @BindView(R.id.R_et)
    EditText R_et;//R
    @BindView(R.id.SpO_et)
    EditText SpO_et;//Sp
    @BindView(R.id.editBp)
    EditText editBp;
    @BindView(R.id.editbpTwo)
    EditText editbpTwo;
    @BindView(R.id.In_Project)
    EditText In_Project;
    @BindView(R.id.In_Amount)
    EditText In_Amount;
    @BindView(R.id.Out_Project)
    EditText Out_Project;
    @BindView(R.id.Out_Amount)
    EditText Out_Amount;
    @BindView(R.id.exceptional)
    EditText exceptional;
    @BindView(R.id.ExecutiveNurse)
    TextView ExecutiveNurse;

    String OC_UCSRg = "";//子宫收缩
    String POGBARg = "";//选中的肛门排气
    String FundusHeight_rgARg;//选中的宫底高度
    String LochiaNature_rgARg = "";//选中的恶露性状
    String BreastCondition_rgRg = "";//选中的乳房情况-乳房
    String Lactation_rgRg = "";//选中的乳房情况-泌乳
    String AbdomenWound_rgRg = "";//选中的腹部
    String PerinealWound_rgRg = "";//选中的会阴
    String Catheters_rgRg = "";//选中的尿管
    String OC_UCSRg_No = "";//子宫收缩
    String POGBARg_No = "";//选中的肛门排气
    String FundusHeight_rgARg_No;//选中的宫底高度
    String LochiaNature_rgARg_No = "";//选中的恶露性状
    String BreastCondition_rgRg_No = "";//选中的乳房情况-乳房
    String Lactation_rgRg_No = "";//选中的乳房情况-泌乳
    String AbdomenWound_rgRg_No = "";//选中的腹部
    String PerinealWound_rgRg_No = "";//选中的会阴
    String Catheters_rgRg_No = "";//选中的尿管

    String mRyDisStr = " ";
    AntenatalExpectantAdapter mAdapter;
    private String mPatID = "";
    String ExecutiveNurseStr;
    @Override
    protected int getContentLayout() {
        return R.layout.fragment_obstetrical_postpartum;
    }

    protected void baseInitView() {
        initDepartRv();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        mPatID = patin.getPatID();
        tvHospitalDate.setText(patin.getZyDetail().getIn_Date());
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        ExecutiveNurseStr = loginBean.getUserName();
        ResourceUtils.setText(mContext, ExecutiveNurse, R.string.ExecutiveNurse,ExecutiveNurseStr);
    }


    private void initDepartRv() {
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        mAdapter = new AntenatalExpectantAdapter(mContext, new ArrayList<>());
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(mAdapter);

        getDepartList();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10046");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10046, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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
                UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes3, params);
                T.showShort("临时保存成功");
                break;
            case R.id.btnSave://保存按钮
                ScreenData();
                ScreenData_No();
                saveNursingRecodes();
                break;
        }
    }

    private void ScreenData() {
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++) {
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1) {
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeName();
                switch (i) {
                    case 0:
                        OC_UCSRg = context;
                        break;
                    case 1:
                        FundusHeight_rgARg = context;
                        break;
                    case 2:
                        LochiaNature_rgARg = context;
                        break;
                    case 3:
                        BreastCondition_rgRg = context;
                        break;
                    case 4:
                        Lactation_rgRg = context;
                        break;
                    case 5:
                        AbdomenWound_rgRg = context;
                        break;
                    case 6:
                        PerinealWound_rgRg = context;
                        break;
                    case 7:
                        Catheters_rgRg = context;
                        break;
                    case 8:
                        POGBARg = context;
                        break;
                }
            }
        }
    }
    private void ScreenData_No() {
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++) {
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1) {
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeID();
                switch (i) {
                    case 0:
                        OC_UCSRg_No = context;
                        break;
                    case 1:
                        FundusHeight_rgARg_No = context;
                        break;
                    case 2:
                        LochiaNature_rgARg_No = context;
                        break;
                    case 3:
                        BreastCondition_rgRg_No = context;
                        break;
                    case 4:
                        Lactation_rgRg_No = context;
                        break;
                    case 5:
                        AbdomenWound_rgRg_No = context;
                        break;
                    case 6:
                        PerinealWound_rgRg_No = context;
                        break;
                    case 7:
                        Catheters_rgRg_No = context;
                        break;
                    case 8:
                        POGBARg_No = context;
                        break;
                }
            }
        }
    }

    //保存及获取产科产后记录单列表
    private void saveNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin) return;
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.ObstetricsRecodes3, false, new NetCallBack<ArrayList<ObstetricalPostpartum>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<ObstetricalPostpartum> nursingRecordBeen) {
                if (null != nursingRecordBeen) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes3, params);
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
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "3");
        params.put("PA_ID", mPatID);
        params.put("UserID", loginBean.getUserID());
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("RecodeDate", "");//记录时间 不用传

        params.put("VitalSign_T", T_et.getText().toString());//体温
        params.put("VitalSign_PAndHR", Hr_et.getText().toString());//脉搏/心率
        params.put("VitalSign_R", R_et.getText().toString());//呼吸
        params.put("VitalSign_SP02", SpO_et.getText().toString());//血氧饱和度
        String bp = editBp.getText().toString() + "/" + editbpTwo.getText().toString();
        if (bp.equals("/")) {
            bp = "";
        }
        params.put("VitalSign_BP", bp);//血压

        params.put("OC_UCS", OC_UCSRg);//子宫收缩
        params.put("OC_UCS_No", OC_UCSRg_No);//子宫收缩
        params.put("FundusHeight", FundusHeight_rgARg);//宫底高度
        params.put("FundusHeight_No", FundusHeight_rgARg_No);//宫底高度No
        params.put("LochiaNature", LochiaNature_rgARg);//恶露性状
        params.put("LochiaNature_No", LochiaNature_rgARg_No);//恶露性状
        params.put("BreastCondition", BreastCondition_rgRg);//乳房情况-乳房
        params.put("BreastCondition_No", BreastCondition_rgRg_No);//乳房情况-乳房
        params.put("Lactation", Lactation_rgRg);//乳房情况-泌乳
        params.put("Lactation_No", Lactation_rgRg_No);//乳房情况-泌乳
        params.put("AbdomenWound", AbdomenWound_rgRg);//伤口-腹部
        params.put("AbdomenWound_No", AbdomenWound_rgRg_No);//伤口-腹部
        params.put("PerinealWound", PerinealWound_rgRg);//伤口-会阴
        params.put("PerinealWound_No", PerinealWound_rgRg_No);//伤口-会阴
        params.put("POGBA", POGBARg);//肛门排气
        params.put("POGBA_No", POGBARg_No);//肛门排气
        params.put("Catheters", Catheters_rgRg);//尿管
        params.put("Catheters_No", Catheters_rgRg_No);//尿管

        params.put("In_Project", In_Project.getText().toString());//入内容
        params.put("In_Amount", In_Amount.getText().toString());//入量
        params.put("Out_Project", Out_Project.getText().toString());//出内容
        params.put("Out_Amount", Out_Amount.getText().toString());//出量
        params.put("OtherSituation", exceptional.getText().toString());//特殊情况记录

        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", ExecutiveNurseStr);//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士
        return params;
    }
}
