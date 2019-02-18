package com.example.hjy.movenurse.nursing.recordsheet;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.nursing.NursingRecordActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.NursingRecordBean;
import com.fy.entity.PatientsBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 护理记录单 Fragment
 * Created by fangs on 2017/9/11.
 */
public class NursingRecordFragment extends BaseFragment {

    @BindView(R.id.tvHospitalizationDate)
    TextView tvHospitalizationDate;
    @BindView(R.id.tvDiagnosticDetails)
    TextView tvDiagnosticDetails;

    @BindView(R.id.tvQCclerk)
    TextView tvQCclerk;//护士签名

    @BindView(R.id.rvNursingRecord)
    RecyclerView rvNursingRecord;
    NursingRecordAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_nursing_record;
    }

    @Override
    protected void baseInit() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        ResourceUtils.setText(mContext, tvQCclerk, R.string.QCclerkName, loginBean.getUserName());
        tvDiagnosticDetails.setText(patin.getZyDetail().getICD_Name());
        tvHospitalizationDate.setText(patin.getZyDetail().getIn_Date());
        initRv();
    }

    private void initRv(){
        List<NursingRecordBean> data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvNursingRecord.setLayoutManager(layoutManager);
        rvNursingRecord.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));
        adapter = new NursingRecordAdapter(mContext, data);
        rvNursingRecord.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        getNursingRecodes();
    }

    //获取 患者内外科护理记录列表
    private void getNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == patin)return;
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey","");
        params.put("OrderType", "1");
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());
        params.put("RecodeDate", "");
        NetRequest.getInstens().requestDate(params, Api.NursingRecodes1, false, new NetCallBack<ArrayList<NursingRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<NursingRecordBean> obj) {
                adapter.setmDatas(obj);
                adapter.notifyDataSetChanged();
            }

            @Override
            public void updataLayout(int flag) {
//                setStatusLayout(flag);
            }
            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    @OnClick({R.id.tvEdit})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvEdit://编辑按钮
                JumpUtils.jump(mContext, NursingRecordActivity.class, null);
                break;
        }
    }
}
