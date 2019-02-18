package com.example.hjy.movenurse.postpartum;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantAdapter;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantListAdapter;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.PostpartumBean;
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
 * 产后观察记录单_编辑
 */

public class PostpartumObserveFragment extends BaseFragment {

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    AntenatalExpectantAdapter mAdapter;
    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//入院日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.r_et)
    EditText r_et;//HR
    @BindView(R.id.bp_qet)
    EditText bp_qet;//R
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
    String mDateKey;
    String mRyDisStr = " ";

    String OC_UCSRg = "";//子宫收缩
    String FundusHeight_rgRg = "";//宫底高度收缩
    String BladderCondition_rgRg = "";//膀胱情况收缩
    String OC_UCSRg_No = "";//子宫收缩
    String FundusHeight_rgRg_No = "";//宫底高度收缩
    String BladderCondition_rgRg_No = "";//膀胱情况收缩

    String ExecutiveNurseStr = "";

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_postpartum_observe;
    }

    protected void baseInitView() {
        initDepartRv();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        tvHospitalDate.setText(patin.getZyDetail().getIn_Date());
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);

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
        params.put("DictType", "10047");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10047, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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
                UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes4, params);
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
                        FundusHeight_rgRg = context;
                        break;
                    case 2:
                        BladderCondition_rgRg = context;
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
                        FundusHeight_rgRg_No = context;
                        break;
                    case 2:
                        BladderCondition_rgRg_No = context;
                        break;
                }
            }
        }
    }

    //保存及获取产后观察记录单列表
    private void saveNursingRecodes() {
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.ObstetricsRecodes4, false, new NetCallBack<ArrayList<PostpartumBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<PostpartumBean> nursingRecordBeen) {
                if (null != nursingRecordBeen) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.ObstetricsRecodes4, params);
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
        params.put("UserID", loginBean.getUserID());
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "4");
        params.put("PA_ID", patin.getPatID());
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("VitalSign_PAndHR", r_et.getText().toString().trim());//脉搏/心率
        params.put("VitalSign_R", bp_qet.getText().toString().trim());//呼吸
        String bp = editBp.getText().toString().trim() + "/" + editbpTwo.getText().toString().trim();
        if (bp.equals("/")) {
            bp = "";
        }
        params.put("VitalSign_BP", bp);//血压

        params.put("OC_UCS", OC_UCSRg);//子宫收缩
        params.put("OC_UCS_No", OC_UCSRg_No);//子宫收缩
        params.put("FundusHeight", FundusHeight_rgRg);//宫底高度
        params.put("FundusHeight_No", FundusHeight_rgRg_No);//宫底高度
        params.put("BladderCondition", BladderCondition_rgRg);//膀胱情况
        params.put("BladderCondition_No", BladderCondition_rgRg_No);//膀胱情况

        params.put("In_Project", In_Project.getText().toString().trim());//入内容
        params.put("In_Amount", In_Amount.getText().toString().trim());//入量
        params.put("Out_Project", Out_Project.getText().toString().trim());//出内容
        params.put("Out_Amount", Out_Amount.getText().toString().trim());//出量
        params.put("OtherSituation", exceptional.getText().toString().trim());//特殊情况记录
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", ExecutiveNurseStr);//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士
        return params;
    }
}
