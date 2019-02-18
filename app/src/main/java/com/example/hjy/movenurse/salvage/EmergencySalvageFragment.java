package com.example.hjy.movenurse.salvage;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.EmergencyRecordBean;
import com.fy.entity.LoginBean;
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
 * Created by Gab on 2017/9/18 0018.
 * 急诊抢救记录单_预览
 */

public class EmergencySalvageFragment extends BaseFragment {

    @BindView(R.id.rvObstetricalRecord)
    RecyclerView rvObstetricalRecord;
    @BindView(R.id.tvHospitalizationDate)
    TextView tvHospitalizationDate;

    @BindView(R.id.tvDiagnosticDetails)
    TextView tvDiagnosticDetails;
    @BindView(R.id.tvQCclerk)
    TextView tvQCclerk;//护士签名

    EmergencySalvageAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.emergency_salvage;
    }

    protected void baseInitView() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        ResourceUtils.setText(mContext, tvQCclerk, R.string.QCclerkName, loginBean.getUserName());
        tvDiagnosticDetails.setText(patin.getZyDetail().getICD_Name());
        tvHospitalizationDate.setText(patin.getZyDetail().getIn_Date());

        initRv();
    }

    private void initRv() {
        List<EmergencyRecordBean> data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvObstetricalRecord.setLayoutManager(layoutManager);
        rvObstetricalRecord.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));
        mAdapter = new EmergencySalvageAdapter(mContext, data);
        rvObstetricalRecord.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getNursingRecodes();
    }

    //获取 急诊抢救记录单
    private void getNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == patin)return;
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", "");
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "3");
        params.put("RecodeDate", "");
        NetRequest.getInstens().requestDate(params, Api.NursingRecodes3, false, new NetCallBack<ArrayList<EmergencyRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<EmergencyRecordBean> obj) {
                mAdapter.setmDatas(obj);
                mAdapter.notifyDataSetChanged();
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
        switch (view.getId()) {
            case R.id.tvEdit://编辑按钮
                JumpUtils.jump(mContext, EmergencySalvageActivity.class, null);
                break;
        }
    }
}
