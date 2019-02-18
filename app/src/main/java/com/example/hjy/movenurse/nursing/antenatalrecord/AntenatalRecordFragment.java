package com.example.hjy.movenurse.nursing.antenatalrecord;

import android.annotation.SuppressLint;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.antenatal.AntenatalRecordActivity;
import com.fy.base.BaseFragment;
import com.fy.entity.AntenatalRecordBean;
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
 * 产前待产记录 预览 Fragment
 * Created by fangs on 2017/9/14.
 */
public class AntenatalRecordFragment extends BaseFragment {

    @BindView(R.id.tvHospitalizationDate)
    TextView tvHospitalizationDate;
    @BindView(R.id.tvDiagnosticDetails)
    TextView tvDiagnosticDetails;
    @BindView(R.id.rvNursingRecord)
    RecyclerView rvNursingRecord;
    @BindView(R.id.tvQCclerk)
    TextView tvQCclerk;//护士签名
    @BindView(R.id.tv_Gravidity)
    TextView tv_Gravidity;
    @BindView(R.id.tvParity)
    TextView tvParity;
    @BindView(R.id.GestationalWeeks)
    TextView GestationalWeeks;

    AntenatalRecordAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_antenatal_record;
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

    private void initRv() {
        List<AntenatalRecordBean> data = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvNursingRecord.setLayoutManager(layoutManager);
        rvNursingRecord.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));
        adapter = new AntenatalRecordAdapter(mContext, data);
        rvNursingRecord.setAdapter(adapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getObstetricsRecodes2();
    }

    //获取 产前待产记录 列表
    private void getObstetricsRecodes2() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (null == patin) return;
        Map<String, Object> params = new HashMap<>();
        params.put("Token", mCache.getAsString("token"));
        params.put("DateKey", "");
        params.put("OrderType", "2");
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());
        params.put("RecodeDate", "");
        NetRequest.getInstens().requestDate(params, Api.ObstetricsRecodes2, false, new NetCallBack<ArrayList<AntenatalRecordBean>>(mContext, R.string.loading_get) {
            @Override
            public void onSuccess(ArrayList<AntenatalRecordBean> antenatalRecordBeen) {
                if (null != antenatalRecordBeen) {
                    adapter.setmDatas(antenatalRecordBeen);
                    adapter.notifyDataSetChanged();
                    AntenatalRecordBean bean = antenatalRecordBeen.get(0);
                    setContent(bean);
                }
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

    @SuppressLint("SetTextI18n")
    private void setContent(AntenatalRecordBean antenatalRecordBean) {
        tv_Gravidity.setText(antenatalRecordBean.getAntenatal().getGravidity() + "次");
        tvParity.setText(antenatalRecordBean.getAntenatal().getParity() + "次");
        GestationalWeeks.setText(antenatalRecordBean.getAntenatal().getGestationalWeeks() + "周");
    }

    @OnClick({R.id.tvEdit})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvEdit://编辑按钮
                JumpUtils.jump(mContext, AntenatalRecordActivity.class, null);
                break;
        }
    }
}

