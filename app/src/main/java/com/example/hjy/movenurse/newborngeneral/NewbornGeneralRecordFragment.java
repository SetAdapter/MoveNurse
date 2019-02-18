package com.example.hjy.movenurse.newborngeneral;

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
import com.fy.entity.NewBornRecordBean;
import com.fy.entity.PatientsBean;
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
 * 新生儿一般护理记录单_编辑
 */

public class NewbornGeneralRecordFragment extends BaseFragment {

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//入院日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.hr_et)
    EditText hr_et;//温度
    @BindView(R.id.r_et)
    EditText r_et;//HR
    @BindView(R.id.bp_qet)
    EditText bp_qet;//R
    @BindView(R.id.editBp)
    EditText editBp;
    @BindView(R.id.editbpTwo)
    EditText editbpTwo;
    @BindView(R.id.sqo2_et)
    EditText sqo2_et;
    @BindView(R.id.IVT)
    EditText IVT;
    @BindView(R.id.Urination)
    EditText Urination;
    @BindView(R.id.MilkAmount)
    EditText MilkAmount;
    @BindView(R.id.Stool)
    EditText Stool;
    @BindView(R.id.MBS)
    EditText MBS;
    @BindView(R.id.Phototherapy)
    EditText Phototherapy;
    @BindView(R.id.OtherSituation)
    EditText OtherSituation;
    @BindView(R.id.BoxTemperature)
    EditText BoxTemperature;
    @BindView(R.id.OxygenFlowRate)
    EditText OxygenFlowRate;//吸氧方式量
    @BindView(R.id.ExecutiveNurse)
    TextView ExecutiveNurse;

    String mDateKey;

    String FeedingPatternsRg = "";//选中的喂养方式
    String OralMucosaRg1 = "";//选中的口腔黏膜
    String UmbilicalRg = "";//选中的脐部情况
    String ReactionRg = "";//选中的 反应
    String CryRg = "";//选中的 哭
    String SuckForceRg = "";//选中的 吸吮力
    String PerianalSkinRg = "";//选中的 肛周皮肤
    String OxygenInhalationRg = "";//选中的 吸氧方式
    String FeedingPatternsRg_No = "";//选中的喂养方式
    String OralMucosaRg1_No = "";//选中的口腔黏膜
    String UmbilicalRg_No = "";//选中的脐部情况
    String ReactionRg_No = "";//选中的 反应
    String CryRg_No = "";//选中的 哭
    String SuckForceRg_No = "";//选中的 吸吮力
    String PerianalSkinRg_No = "";//选中的 肛周皮肤
    String OxygenInhalationRg_No = "";//选中的 吸氧方式

    String mRyDisStr = "";
    String ExecutiveNurseStr = "";
    AntenatalExpectantAdapter mAdapter;


    @Override
    protected int getContentLayout() {
        return R.layout.fragment_newborn_general_record;
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
        mAdapter = new AntenatalExpectantAdapter(mContext, new ArrayList<>());
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(mAdapter);

        getDepartList();
    }

    private void getDepartList(){
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10050");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10050, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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
                UpLoadUtils.cacheRequest(mCache, patin, Api.NeonatalCareRecodes1, params);
                T.showShort("临时保存成功");
                break;
            case R.id.btnSave://保存按钮
                ScreenData();
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
                        OxygenInhalationRg = context;
                        break;
                    case 1:
                        ReactionRg = context;
                        break;
                    case 2:
                        CryRg = context;
                        break;
                    case 3:
                        SuckForceRg = context;
                        break;
                    case 4:
                        FeedingPatternsRg = context;
                        break;
                    case 5:
                        OralMucosaRg1 = context;
                        break;
                    case 6:
                        UmbilicalRg = context;
                        break;
                    case 7:
                        PerianalSkinRg = context;
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
                        OxygenInhalationRg_No = context;
                        break;
                    case 1:
                        ReactionRg_No = context;
                        break;
                    case 2:
                        CryRg_No = context;
                        break;
                    case 3:
                        SuckForceRg_No = context;
                        break;
                    case 4:
                        FeedingPatternsRg_No = context;
                        break;
                    case 5:
                        OralMucosaRg1_No = context;
                        break;
                    case 6:
                        UmbilicalRg_No = context;
                        break;
                    case 7:
                        PerianalSkinRg_No = context;
                        break;
                }
            }
        }
    }

    //保存及获取急诊抢救记录单列表
    private void saveNursingRecodes() {
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.NeonatalCareRecodes1, false, new NetCallBack<ArrayList<NewBornRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<NewBornRecordBean> nursingRecordBeen) {
                if (null != nursingRecordBeen) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.NeonatalCareRecodes1, params);
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
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "1");
        params.put("PA_ID", patin.getPatID());
        params.put("DateKey", mDateKey); //时间戳
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("VitalSign_T", hr_et.getText().toString().trim());//体温
        params.put("VitalSign_PAndHR", r_et.getText().toString().trim());//脉搏/心率
        params.put("VitalSign_R", bp_qet.getText().toString().trim());//呼吸
        String bp = editBp.getText().toString().trim() + "/" + editbpTwo.getText().toString().trim();
        if (bp.equals("/")){
            bp = "";
        }
        params.put("VitalSign_BP", bp);//血压
        params.put("VitalSign_SP02", sqo2_et.getText().toString().trim());//SqO2

        params.put("OxygenInhalation", OxygenInhalationRg);//吸氧方式
        params.put("OxygenInhalation_No", OxygenInhalationRg_No);//吸氧方式
        params.put("Reaction", ReactionRg);//反应
        params.put("Reaction_No", ReactionRg_No);//反应
        params.put("Cry", CryRg);//哭声
        params.put("Cry_No", CryRg_No);//哭声
        params.put("SuckForce", SuckForceRg);//吸吮力
        params.put("SuckForce_No", SuckForceRg_No);//吸吮力
        params.put("FeedingPatterns", FeedingPatternsRg);//喂养方式
        params.put("FeedingPatterns_No", FeedingPatternsRg_No);//喂养方式
        params.put("OralMucosa", OralMucosaRg1);//口腔黏膜
        params.put("OralMucosa_No", OralMucosaRg1_No);//口腔黏膜
        params.put("Umbilical", UmbilicalRg);//脐部情况
        params.put("Umbilical_No", UmbilicalRg_No);//脐部情况
        params.put("PerianalSkin", PerianalSkinRg);//肛周皮肤
        params.put("PerianalSkin_No", PerianalSkinRg_No);//肛周皮肤


        params.put("OxygenFlowRate", OxygenFlowRate.getText().toString().trim());//吸氧流量
        params.put("IVT", IVT.getText().toString().trim());//入静脉
        params.put("MilkAmount", MilkAmount.getText().toString().trim());//入奶量
        params.put("Urination", Urination.getText().toString().trim());//出小便
        params.put("Stool", Stool.getText().toString().trim());//大便性质
        params.put("MBS", MBS.getText().toString().trim());//MBS
        params.put("Phototherapy", Phototherapy.getText().toString().trim());//光疗
        params.put("OtherSituation", OtherSituation.getText().toString().trim());//其他情况
        params.put("BoxTemperature", BoxTemperature.getText().toString().trim());//箱温
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", ExecutiveNurseStr);//执行护士
        return params;
    }
}
