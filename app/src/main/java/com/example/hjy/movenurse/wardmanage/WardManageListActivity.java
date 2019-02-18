package com.example.hjy.movenurse.wardmanage;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.wardmanage.leave.LeaveActivity;
import com.example.hjy.movenurse.wardmanage.packbed.PackBedActivity;
import com.fy.base.BaseActivity;
import com.fy.entity.BedBean;
import com.fy.entity.LoginBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 病房管理列表 Activity
 * Created by fangs on 2017/9/15.
 */
public class WardManageListActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.edit_search)
    EditText search;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.grid_patient)
    GridView mGridView;
    WardManageListAdapter mWardManageListAdapter;

    @Override
    protected int getContentView() {
        return R.layout.activity_ward_manage_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("病房管理");
        tv_huanzhe.setVisibility(View.INVISIBLE);

        initListner();
        mWardManageListAdapter = new WardManageListAdapter(mContext, new ArrayList<>());
        mGridView.setAdapter(mWardManageListAdapter);

        patientData("1");
    }

    private void initListner(){
        mGridView.setOnItemClickListener(this);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after){}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clear.setVisibility(View.VISIBLE);
                mWardManageListAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                clear.setVisibility(View.INVISIBLE);
            }
        });
    }

    /**
     * 患者资料请求数据
     */
    private void patientData(String OptionType) {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("DeptID", loginBean.getDeptID());
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        params.put("OptionType", OptionType);
        NetRequest.getInstens().requestDate(params, Api.GetBeds, true, new NetCallBack<ArrayList<BedBean>>(this, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<BedBean> bean) {
                if (null != bean) {
                    mWardManageListAdapter.setData(bean);
                    mWardManageListAdapter.clearCache();
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == 7788){
            patientData("1");
        }
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        BedBean bedBean = (BedBean) view.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("#BedBean#", bedBean);

        BedBean.ZyDetailBean zyDetail = bedBean.getZyDetail();
        if (null != zyDetail) {
            //设置患者状态
            switch (zyDetail.getBedStatus()) {
                case "2"://患者在院
                    JumpUtils.jump(mContext, WardManageAcitivity.class, bundle, 7788);
                    break;
                case "4"://请假
                    JumpUtils.jump(mContext, LeaveActivity.class, bundle, 7788);
                    break;
                case "3"://包床
                    JumpUtils.jump(mContext, PackBedActivity.class, bundle, 7788);
                    break;
            }
        }
    }
}
