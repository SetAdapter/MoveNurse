package com.example.hjy.movenurse.fragment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.adapter.SkinConditionListAdapter;
import com.example.hjy.movenurse.adapter.SkinConditionListAdapter1;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.HealthGuidanceBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * Created by Gab on 2017/10/16 0016.
 * 4_3首次护理记录单_压疮_身体部位选择 背面Fragment
 */

public class SkinContraryFragment extends BaseFragment {
    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    SkinConditionListAdapter departAdapter;

    @BindView(R.id.rvDepartInfo)
    RecyclerView rvDepartInfo;
    SkinConditionListAdapter1 adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.skin_condition_fragment;
    }
    @Override
    protected void baseInitView() {

        initDepartRv();

        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepartInfo.setLayoutManager(gManager);

        adapter = new SkinConditionListAdapter1(mContext, new ArrayList<>());
        adapter.setmRv(rvDepartInfo);
        departAdapter.setListner(new SkinConditionListAdapter.OnItemClickListner() {
            @Override
            public void onClick(HealthGuidanceBean dictsBean) {
                adapter.setmDatas(dictsBean.getDicts());
                adapter.notifyDataSetChanged();
            }
        });
        rvDepartInfo.setAdapter(adapter);
        adapter.setListner(new SkinConditionListAdapter1.OnItemClickListner() {
            @Override
            public void onClick(String data) {
                mCache.put("Tag",data);
                getActivity().finish();
            }
        });
    }

    private void initDepartRv(){
        departAdapter = new SkinConditionListAdapter(mContext, new ArrayList<>());
        departAdapter.setmRv(rvDepart);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(departAdapter);

        getDepartList();
    }

    private void getDepartList(){
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10008");
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        NetRequest.getInstens().requestDate(params, Api.getDicts1, true, new NetCallBack<ArrayList<HealthGuidanceBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<HealthGuidanceBean> data) {
                if (null != data) {
                    departAdapter.setmDatas(data);
                    departAdapter.notifyDataSetChanged();
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
