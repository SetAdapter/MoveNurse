package com.example.hjy.movenurse.newbornnurse;

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
import com.example.hjy.movenurse.widget.CustomDatePicker;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.NewBornNurseRecordBean;
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
import java.util.Locale;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/9/18 0018.
 * 新生儿护理记录单_编辑
 */

public class NewbornNurseRecordFragment extends BaseFragment {

    @BindView(R.id.tvHospitalDate)
    TextView tvHospitalDate;//出生日期
    @BindView(R.id.tvAdmissionDiagnosis)
    TextView tvAdmissionDiagnosis;//入院诊断
    @BindView(R.id.t_et)
    EditText t_et;//温度
    @BindView(R.id.OtherSituation)
    EditText OtherSituation;
    @BindView(R.id.FeedType)
    EditText FeedType;
    @BindView(R.id.OxygenFlowRate)
    EditText OxygenFlowRate;
    @BindView(R.id.ExecutiveNurse)
    TextView ExecutiveNurse;
    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;

    private String mDateKey;

    String ReactionRg = "";//选中的反应
    String CryRg = "";//选中的哭声
    String SuckForceRg = "";//选中的吸吮力
    String FeedingPatternsRg = "";//选中的喂养方式
    String OxygenInhalationRg = "";//选中的吸氧方式
    String UmbilicalRg = "";//选中的脐部情况
    String SkinColorRg = "";//选中的皮肤颜色
    String VomitRg = "";//选中的呕吐
    String UrinationRg = "";//选中的大便情况
    String StoolRg = "";//选中的小便
    String ReactionRg_No = "";//选中的反应
    String CryRg_No = "";//选中的哭声
    String SuckForceRg_No = "";//选中的吸吮力
    String FeedingPatternsRg_No = "";//选中的喂养方式
    String OxygenInhalationRg_No = "";//选中的吸氧方式
    String UmbilicalRg_No = "";//选中的脐部情况
    String SkinColorRg_No = "";//选中的皮肤颜色
    String VomitRg_No = "";//选中的呕吐
    String UrinationRg_No = "";//选中的大便情况
    String StoolRg_No = "";//选中的小便

    String mBirthDateStr = "";//出生日期
    String mRyDisStr = "";
    String ExecutiveNurseStr = "";
    AntenatalExpectantAdapter mAdapter;
    private CustomDatePicker mDiagnoseDatePicker;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_newborn_nurse_record;
    }

    protected void baseInitView() {
        initDepartRv();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        ExecutiveNurseStr = loginBean.getUserName();
        ResourceUtils.setText(mContext, ExecutiveNurse, R.string.ExecutiveNurse,ExecutiveNurseStr);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.CHINA);
        String now = sdf.format(new Date());
        tvHospitalDate.setText(now);
        tvHospitalDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDiagnoseDatePicker.show(tvHospitalDate.getText().toString());
            }
        });
        mDiagnoseDatePicker = new CustomDatePicker(mContext, new CustomDatePicker.ResultHandler() {
            @Override
            public void handle(String time) {// 回调接口，获得选中的时间
                tvHospitalDate.setText(time);
                mBirthDateStr = time;
            }
        }, "1900-01-01 00:00", now);// 初始化日期格式请用：yyyy-MM-dd HH:mm，否则不能正常运行
        mDiagnoseDatePicker.showSpecificTime(true); // 显示时和分
        mDiagnoseDatePicker.setIsLoop(true); // 允许循环滚动
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
        params.put("DictType", "10051");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10051, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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
                UpLoadUtils.cacheRequest(mCache, patin, Api.NeonatalCareRecodes2, params);
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
                        UmbilicalRg = context;
                        break;
                    case 6:
                        SkinColorRg = context;
                        break;
                    case 7:
                        VomitRg = context;
                        break;
                    case 8:
                        UrinationRg = context;
                        break;
                    case 9:
                        StoolRg = context;
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
                        UmbilicalRg_No = context;
                        break;
                    case 6:
                        SkinColorRg_No = context;
                        break;
                    case 7:
                        VomitRg_No = context;
                        break;
                    case 8:
                        UrinationRg_No = context;
                        break;
                    case 9:
                        StoolRg_No = context;
                        break;
                }
            }
        }
    }

    //保存及获取新生儿护理记录单列表
    private void saveNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == patin) return;
        if (null == loginBean) return;
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.NeonatalCareRecodes2, false, new NetCallBack<ArrayList<NewBornNurseRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<NewBornNurseRecordBean> nursingRecordBeen) {
                NewBornNurseRecordBean nurseRecordBean = nursingRecordBeen.get(0);
                mCache.put("NewBornNurseRecordBean", nurseRecordBean);
                if (null != nursingRecordBeen) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) {
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.NeonatalCareRecodes2, params);
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
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());
        params.put("DateKey", mDateKey); //时间戳
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("VitalSign_T", t_et.getText().toString().trim());//体温
        params.put("FeedType", FeedType.getText().toString().trim());//喂养种类
        params.put("OxygenFlowRate", OxygenFlowRate.getText().toString().trim());//吸氧流量

        params.put("Reaction", ReactionRg);//反应
        params.put("Reaction_No", ReactionRg_No);//反应
        params.put("Cry", CryRg);//哭声
        params.put("Cry_No", CryRg_No);//哭声
        params.put("SuckForce", SuckForceRg);//吸吮力
        params.put("SuckForce_No", SuckForceRg_No);//吸吮力
        params.put("SkinColor", SkinColorRg);//皮肤颜色
        params.put("SkinColor_No", SkinColorRg_No);//皮肤颜色
        params.put("Vomit", VomitRg);//呕吐
        params.put("Vomit_No", VomitRg_No);//呕吐
        params.put("Urination", UrinationRg);//小便情况
        params.put("Urination_No", UrinationRg_No);//小便情况
        params.put("Stool", StoolRg);//大便情况
        params.put("Stool_No", StoolRg_No);//大便情况
        params.put("OxygenInhalation", OxygenInhalationRg);//吸氧方式
        params.put("OxygenInhalation_No", OxygenInhalationRg_No);//吸氧方式
        params.put("FeedingPatterns", FeedingPatternsRg);//喂养方式
        params.put("FeedingPatterns_No", FeedingPatternsRg_No);//喂养方式
        params.put("Umbilical", UmbilicalRg);//脐部情况
        params.put("Umbilical_No", UmbilicalRg_No);//脐部情况

        params.put("BirthDate", mBirthDateStr);//出生日期
        params.put("OtherSituation", OtherSituation.getText().toString().trim());//其他情况
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", ExecutiveNurseStr);//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士

        return params;
    }
}
