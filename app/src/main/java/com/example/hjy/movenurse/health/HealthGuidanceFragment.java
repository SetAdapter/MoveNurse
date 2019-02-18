package com.example.hjy.movenurse.health;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.HealthGuidancesBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.SignInformedBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.imgload.ImgLoadUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/10/16 0016.
 * 3_出院健康指导_预览
 */

public class HealthGuidanceFragment extends BaseFragment {

    @BindView(R.id.tv_mCreateDate)
    TextView tv_mCreateDate;
    @BindView(R.id.Health_Ry)
    RecyclerView mRecyclerView;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.tv_EXENurseName)
    TextView tv_EXENurseName;
    @BindView(R.id.relation_iv)
    ImageView relation_iv;

    HealthGuidanceAdapter mAdapter;
    String mCreateDate;
    private List<HealthGuidancesBean> mMDatas;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_health_guidance;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        initDepartRv();
        EventBus.getDefault().register(this);
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
    }

    private void initDepartRv() {
        mAdapter = new HealthGuidanceAdapter(mContext, new ArrayList<>());
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        mRecyclerView.setLayoutManager(gManager);
        mRecyclerView.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        getDetails();
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(HealthGuidanceEvent healthGuidanceEvent) {
        mMDatas = healthGuidanceEvent.getDatas();
        mAdapter.setmDatas(mMDatas);
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.save_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                JumpUtils.jump(mContext, HealthGuidanceActivity.class, null);
                break;
        }
    }

    /**
     * 预览
     */
    private void getDetails() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        params.put("Token", loginBean.getToken());
        params.put("DateKey", "");
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "3");
        params.put("PA_ID", patin.getPatID());

        NetRequest.getInstens().requestDate(params, Api.SignInformedConsent, false,
                new NetCallBack<ArrayList<SignInformedBean>>() {
                    @Override
                    public void onSuccess(ArrayList<SignInformedBean> data) {
                        if (null != data) {
                            SignInformedBean signInformedBean = data.get(0);
                            setContent(signInformedBean);
                            if (!TextUtils.isEmpty(signInformedBean.getNodes())) {
                                getDepartList(signInformedBean.getNodes());
                            }
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

    /**
     * 出院健康指导 字典信息
     */
    private void getDepartList(String nodes) {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10001");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10001, false, new NetCallBack<ArrayList<HealthGuidancesBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<HealthGuidancesBean> data) {
                if (null != data) {
                    List<HealthGuidancesBean> mDatas = new ArrayList<>();

                    for (HealthGuidancesBean healthGuidancesBean : data) {
                        List<HealthGuidancesBean.DictsBean> temp = new ArrayList<>();

                        List<HealthGuidancesBean.DictsBean> Dicts = healthGuidancesBean.getDicts();
                        if (null != Dicts) {

                            for (HealthGuidancesBean.DictsBean dictsBean : Dicts) {
                                if (nodes.contains(dictsBean.getDict_TypeID())) {
                                    temp.add(dictsBean);
                                }
                            }
                        }

                        if (temp.size() > 0) {
                            HealthGuidancesBean resultData = new HealthGuidancesBean();
                            resultData.setDict_TypeName(healthGuidancesBean.getDict_TypeName());
                            resultData.setDicts(temp);
                            mDatas.add(resultData);
                        }
                    }
                    mAdapter.setmDatas(mDatas);
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

    private void setContent(SignInformedBean signInformedBean) {
        if (null != signInformedBean) {
            ImgLoadUtils.loadImage(mContext, signInformedBean.getPatFamilyURL(), relation_iv);
            tv_FZ_Nurse.setText("患者与家属关系:" + signInformedBean.getPatFamilyType());
            tv_mCreateDate.setText("签字日期:" + mCreateDate);
            tv_EXENurseName.setText("宣教护士：" + signInformedBean.getEXENurseName());
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }
}
