package com.example.hjy.movenurse.auxiliary;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RespiratorAuxiliary;
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
 * 新生儿科呼吸机辅助呼吸记录单
 */

public class RespiratorAuxiliaryFragment extends BaseFragment {

    @BindView(R.id.rvObstetricalRecord)
    RecyclerView rvObstetricalRecord;
    @BindView(R.id.tvHospitalizationDate)
    TextView tvHospitalizationDate;
    @BindView(R.id.tvDiagnosticDetails)
    TextView tvDiagnosticDetails;
    @BindView(R.id.tvQCclerk)
    TextView tvQCclerk;//护士签名
    RespiratorAuxiliaryAdapter mAdapter;


    @Override
    protected int getContentLayout() {
        return R.layout.respirator_auxiliary;
    }

    protected void baseInitView() {
    }

    protected void baseInit() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        ResourceUtils.setText(mContext, tvQCclerk, R.string.QCclerkName, loginBean.getUserName());
        tvDiagnosticDetails.setText(patin.getZyDetail().getICD_Name());
        tvHospitalizationDate.setText(patin.getZyDetail().getIn_Date());

        initRv();

    }

    private void initRv() {
        List<RespiratorAuxiliary> data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvObstetricalRecord.setLayoutManager(layoutManager);
        rvObstetricalRecord.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));

        mAdapter = new RespiratorAuxiliaryAdapter(mContext, data);
        rvObstetricalRecord.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getNursingRecodes();
    }

    //获取 新生儿科呼吸机辅助呼吸记录
    private void getNursingRecodes() {
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == patin) return;
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", "");
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("RecodeDate", "");
//        params.put("ExecutiveNurseID", recordBean.getExecutiveNurseID());
//        params.put("ExecutiveNurse", recordBean.getExecutiveNurse());
        NetRequest.getInstens().requestDate(params, Api.NCAssistanceRecodes, false, new NetCallBack<ArrayList<RespiratorAuxiliary>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<RespiratorAuxiliary> obj) {
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
                JumpUtils.jump(mContext, RespiratorAuxiliaryActivity.class, null);
                break;
        }
    }
}
