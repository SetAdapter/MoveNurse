package com.example.hjy.movenurse.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.activity.PressureSoresActivity;
import com.fy.base.BaseFragment;
import com.fy.custom.swiperefreshlayout.SwipyRefreshLayout;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.PressureSoreBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/9/11 0011.
 * 压疮风险评估
 */
public class PressureSoresFragment extends BaseFragment {

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    @BindView(R.id.swipe_refresh_widget)
    SwipyRefreshLayout mSwipyRefreshLayout;
    PressureSoresAdapter mAdapter;

    @Override
    protected int getContentLayout() {
        return R.layout.pressure_sores_fragment;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        initDepartRv();
    }

    private void initDepartRv() {
        mSwipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                getDepartList();
                mSwipyRefreshLayout.setRefreshing(false);
                mAdapter.setIsAllSelect(false);//设置反选
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onLoad(int index) {

            }
        });
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        mAdapter = new PressureSoresAdapter(mContext, new ArrayList<>());
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(mAdapter);
    }

    @OnClick({R.id.bt_compile})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.bt_compile:
                JumpUtils.jump(mContext, PressureSoresActivity.class, null);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDepartList();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        params.put("Token", loginBean.getToken());
        params.put("DateKey", ""); //时间戳
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());//住院号
        NetRequest.getInstens().requestDate(params, Api.PSEvaluation, false, new NetCallBack<ArrayList<PressureSoreBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<PressureSoreBean> bean) {
                if (null != bean) {
                    mAdapter.setmDatas(bean);
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
}
