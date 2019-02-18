package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.NoDataBean;
import com.fy.entity.PainbBillDetailsBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 行政管理 病区退药 Fragment
 * Created by Stefan on 2017/9/22. 15:31
 */
public class WardDrugFragment extends BaseFragment {

    @BindView(R.id.rvData)
    RecyclerView rvData;
    WardDrugAdapter rvAdapter;

    @BindView(R.id.all_select)
    CheckBox all_select;

    @BindView(R.id.tvSave)
    TextView tvSave;

    @BindView(R.id.tv_select)
    TextView tv_select;

    @BindView(R.id.edit_search)
    EditText search;

    @BindView(R.id.clear)
    ImageView clear;

    Bundle bundle;
    private String patID;
    private String nurseID;
    private String nurseName;
    private String inBed;
    private String billNo;
    ArrayList<PainbBillDetailsBean.ResultDataBean> beans;

    @Override
    protected int getContentLayout() {
        return R.layout.ward_drug_list;
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        bundle = getActivity().getIntent().getExtras();

        initRv();
        initListner();
        getPainbBillDetails();
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(all_select.isChecked()){
                    all_select.setChecked(false);
                }else {
                    all_select.setChecked(true);
                }
            }
        });

        all_select.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                rvAdapter.setIsAllSelect(isChecked);
            }
        });
    }

    private void initListner() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clear.setVisibility(View.VISIBLE);
                rvAdapter.getFilter().filter(s);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                search.setText("");
                clear.setVisibility(View.INVISIBLE);
            }
        });
    }

    private void initRv(){
        GridLayoutManager gManager = new GridLayoutManager(mContext, 2);
        rvData.setLayoutManager(gManager);

        rvAdapter = new WardDrugAdapter(mContext, new ArrayList<>());
        rvData.setAdapter(rvAdapter);
    }


    private void getPainbBillDetails() {
        PatientsBean patientsBean = (PatientsBean) bundle.getSerializable("PatientsBean");
        patID = patientsBean.getPatID();//住院号
        // billType = (String) getArguments().get("BillType");//费用类型

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("patID", patID);
        params.put("BillType", "");
        params.put("OptionType", "1");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.GetPainbBillDetails, false, new NetCallBack<ArrayList<PainbBillDetailsBean.ResultDataBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<PainbBillDetailsBean.ResultDataBean> bean) {
                if (bean != null && bean.size() > 0) {
                    beans=bean;
                    rvAdapter.setmDatas(bean);
                    rvAdapter.notifyDataSetChanged();
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

    @OnClick({R.id.tvSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvSave:
                getExcutePainbBillDetail();
                break;
        }
    }

    private void getExcutePainbBillDetail() {
        PatientsBean patientsBean = (PatientsBean) bundle.getSerializable("PatientsBean");
        patID = patientsBean.getPatID();//住院号
        //护士ID
        nurseID = patientsBean.getPatID();
        //护士名字
        nurseName = patientsBean.getZyDetail().getPri_NurseName();
        //床位号
        inBed = patientsBean.getZyDetail().getIn_Bed();
        //编号
        SparseBooleanArray array = rvAdapter.getmSelectedPositions();
        for (int i = 0; i < array.size(); ++i){
            if (array.valueAt(i)){
                billNo= beans.get(i).getBillNo()+",";
            }
        }
        if(!TextUtils.isEmpty(billNo)){
            billNo=billNo.substring(0,billNo.length()-1);
        }

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("patID", patID);
        params.put("BillNo", billNo);
        params.put("NurseID", loginBean.getUserID());
        params.put("Nurse", loginBean.getUserName());
        params.put("OptionType", "1");
        params.put("BedNo", inBed);
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());

        NetRequest.getInstens().requestDate(params, Api.ExcutePainbBillDetail, false, new NetCallBack<ArrayList<NoDataBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<NoDataBean> bean) {
                Toast.makeText(getActivity(),"退药成功",Toast.LENGTH_SHORT).show();

                if (all_select.isChecked()){
                    rvAdapter.setmDatas(new ArrayList<>());
                } else {
                    SparseBooleanArray booleanArray = rvAdapter.getmSelectedPositions();
                    for (int i = 0; i < booleanArray.size(); i++) {
                        if (array.valueAt(i)) {
                            rvAdapter.removeData(i);
                        }
                    }
                }

                rvAdapter.cleanChecked();
                rvAdapter.notifyDataSetChanged();
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
