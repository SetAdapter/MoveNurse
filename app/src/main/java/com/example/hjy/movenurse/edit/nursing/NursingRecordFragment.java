package com.example.hjy.movenurse.edit.nursing;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.upload.UpLoadUtils;
import com.example.hjy.movenurse.widget.SonnyJackDragView;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.NursingRecordBean;
import com.fy.entity.PatientsBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 护理记录单编辑 fragment
 * Created by fangs on 2017/9/13.
 */
public class NursingRecordFragment extends BaseFragment {

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
    @BindView(R.id.user_name)
    TextView user_name;

    String selectRg = "";//选中的意识
    String editLeftReflexRg = "";//
    String editRightReflexRg = "";//
    String selectRgNo = "";//选中的意识
    String editLeftReflexRgNo = "";//
    String editRightReflexRgNo = "";//

    String mRyDisStr = "";//术前诊断

    @BindView(R.id.editInContent)
    EditText editInContent;//入 内容
    @BindView(R.id.editInNum)
    EditText editInNum;//入量
    @BindView(R.id.editOutContent)
    EditText editOutContent;//出 内容
    @BindView(R.id.editOutNum)
    EditText editOutNum;//出 量
    @BindView(R.id.editLeftDiameter)
    EditText editLeftDiameter;//瞳孔左侧 直径
    @BindView(R.id.editRightDiameter)
    EditText editRightDiameter;//瞳孔右侧 直径
    @BindView(R.id.exceptional)
    EditText exceptional;//术后观察及措施
    AntenatalExpectantAdapter mAdapter;
    private String mPatID = "";
    LoginBean loginBean;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_nursing_edit;
    }

    @Override
    protected void baseInit() {
        initDepartRv();
        SonnyJackDragView.Builder builder = new SonnyJackDragView.Builder()
                .setActivity(mContext)
                .setDefaultLeft(30)
                .setDefaultTop(30)
                .setNeedNearEdge(true)
                .setSize(100);
        ImageView imageView = new ImageView(mContext);
        imageView.setScaleType(ImageView.ScaleType.FIT_START);
        imageView.setImageResource(R.mipmap.save);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ScreenData();
                Map<String, Object> params = patientCashData();
                PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
                UpLoadUtils.cacheRequest(mCache, patin, Api.NursingRecodes1, params);
                T.showShort("临时保存成功");
            }
        });
        SonnyJackDragView mSonnyJackDragView = SonnyJackDragView.addDragView(builder, imageView);

        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        tvHospitalDate.setText(patin.getZyDetail().getIn_Date());

        //入院诊断
        mRyDisStr = patin.getZyDetail().getICD_Name();
        tvAdmissionDiagnosis.setText(mRyDisStr);

        loginBean = (LoginBean) mCache.getAsObject("UserName");
        user_name.setText("护士签名:" + loginBean.getUserName());

        mPatID = patin.getPatID();

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
        params.put("DictType", "10044");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10044, true, new NetCallBack<ArrayList<AntenatalExpectant>>(mContext, R.string.data_loading) {
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
                UpLoadUtils.cacheRequest(mCache, patin, Api.NursingRecodes1, params);
                T.showShort("临时保存成功");
                break;
            case R.id.btnSave://保存按钮
                ScreenData();
                ScreenDataNo();
                saveNursingRecodes();
                break;

        }
    }

    private void ScreenData(){
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++){
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1){
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeName();
                switch (i){
                    case 0:
                        selectRg = context;
                        break;
                    case 1:
                        editLeftReflexRg = context;
                        break;
                    case 2:
                        editRightReflexRg = context;
                        break;
                }
            }
        }
    }

    private void ScreenDataNo(){
        SparseArray<AntenatalExpectantListAdapter> data = mAdapter.getAdpters();
        for (int i = 0; i < data.size(); i++){
            AntenatalExpectantListAdapter adapter = data.valueAt(i);
            int selected = adapter.getmSelectedPos();
            if (null != adapter && selected > -1){
                List<AntenatalExpectant.DictsBean> sunData = adapter.getmDatas();
                String context = sunData.get(selected).getDict_TypeID();
                switch (i){
                    case 0:
                        selectRgNo = context;
                        break;
                    case 1:
                        editLeftReflexRgNo = context;
                        break;
                    case 2:
                        editRightReflexRgNo = context;
                        break;
                }
            }
        }
    }


    //保存及获取患者内外科护理记录列表
    private void saveNursingRecodes() {
        Map<String, Object> params = patientCashData();
        NetRequest.getInstens().requestDate(params, Api.NursingRecodes1, false, new NetCallBack<ArrayList<NursingRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<NursingRecordBean> obj) {
                if (null != obj) {
                    getActivity().finish();
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
//                if (flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID){
//                    PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
//                    UpLoadUtils.cacheRequest(mCache, patin, Api.NursingRecodes, params);
//                }
            }
            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    /**
     * 临时 护理记录单编辑 页面
     */
    private Map<String, Object> patientCashData() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
//        params.put("Token", mCache.getAsString("token"));
        params.put("Token", loginBean.getToken());
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "1");
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", mPatID);
        params.put("In_Dis", mRyDisStr);//诊断
        params.put("RecodeDate", "");//记录时间 不用传
        params.put("VitalSign_T", hr_et.getText().toString().trim());//体温
        params.put("VitalSign_PAndHR", r_et.getText().toString().trim());//脉搏/心率
        params.put("VitalSign_R", bp_qet.getText().toString().trim());//呼吸
        String bp = editBp.getText().toString().trim() + "/" + editbpTwo.getText().toString().trim();
        if (bp.equals("/")){
            bp = "";
        }
        params.put("VitalSign_BP", bp);//血压
        params.put("Consciousness", selectRg);//意识
        params.put("Consciousness_No", selectRgNo);//意识编号
        params.put("In_Content", editInContent.getText().toString().trim());//入内容
        params.put("In_Amount", editInNum.getText().toString().trim());//入量
        params.put("Out_Content", editOutContent.getText().toString().trim());//出内容
        params.put("Out_Amount", editOutNum.getText().toString().trim());//出量
        params.put("OtherSituation", exceptional.getText().toString().trim());//病情观察及措施
        params.put("LeftPD", editLeftDiameter.getText().toString().trim());//左瞳孔直径
        params.put("LeftIrisCR", editLeftReflexRg);//左瞳孔反射
        params.put("LeftIrisCR_No", editLeftReflexRgNo);//左瞳孔反射
        params.put("RightPD", editRightDiameter.getText().toString().trim());//右瞳孔直径
        params.put("RightIrisCR", editRightReflexRg);//右瞳孔反射
        params.put("RightIrisCR_No", editRightReflexRgNo);//右瞳孔反射
        params.put("ExecutiveNurseID", loginBean.getUserID());//执行护士ID
        params.put("ExecutiveNurse", loginBean.getUserName());//执行护士
        params.put("QualityControlNurseID", "");//质控护士ID
        params.put("QualityControlNurse", "");//质控护士
        return params;
    }
}
