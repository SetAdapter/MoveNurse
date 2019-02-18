package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.DictTypeBean;
import com.fy.entity.LoginBean;
import com.fy.entity.NoDataBean;
import com.fy.entity.PainbBillDetailsBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 行政管理 病区退费 Fragment
 * Created by Stefan on 2017/9/25 10:12
 */
public class WardRefundFragment extends BaseFragment {

    @BindView(R.id.rvData)
    RecyclerView rvData;
    WardRefundAdapter rvAdapter;

    @BindView(R.id.all_select)
    CheckBox all_select;
    @BindView(R.id.tv_select)
    TextView tv_select;
    @BindView(R.id.edit_search)
    EditText search;
    @BindView(R.id.clear)
    ImageView clear;
    private PatientsBean patientsBean;
    Bundle bundle;
    private String patID;
    private String nurseID;
    private String nurseName;
    Spinner cost_type;
    private String inBed;
    private String BillType_S;
    private String type;
    private String billNo;
    ArrayList<PainbBillDetailsBean.ResultDataBean> beans;
    private String[] mItems;

    @Override
    protected int getContentLayout() {
        return R.layout.ward_refund_list;
    }

    @Override
    public void onResume() {
        super.onResume();
        getPainbBillDetails("");
        getCostMoney();
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        bundle = getActivity().getIntent().getExtras();

        initRv();
        initListner();
        tv_select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (all_select.isChecked()) {
                    all_select.setChecked(false);
                } else {
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

    private void initRv() {
        GridLayoutManager gManager = new GridLayoutManager(mContext, 2);
        rvData.setLayoutManager(gManager);
        rvAdapter = new WardRefundAdapter(mContext, new ArrayList<>());
        rvData.setAdapter(rvAdapter);
    }

    //spinner事件
    private void setCostType() {
        cost_type = (Spinner) getActivity().findViewById(R.id.cost_type);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, mItems);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        cost_type.setAdapter(adapter);
        cost_type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                BillType_S = cost_type.getSelectedItem().toString();
                getPainbBillDetails(BillType_S);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    //网络请求费用类型
    private void getCostMoney() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        params.put("DictType", "10053");
        NetRequest.getInstens().requestDate(params, Api.getDicts10053, false, new NetCallBack<ArrayList<DictTypeBean>>() {
            @Override
            public void onSuccess(ArrayList<DictTypeBean> been) {

                if (been != null && been.size() > 0) {
                    DictTypeBean typeBean = been.get(0);
                    List<DictTypeBean.DictsBean> dicts = typeBean.getDicts();
                    if (null != dicts){
                        mItems = new String[dicts.size() + 1];
                        mItems[0] = "全部";
                        for (int i = 1; i < dicts.size() + 1; i++) {
                            mItems[i] = dicts.get(i - 1).getDict_TypeName();
                        }
                        setCostType();
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

    private void getPainbBillDetails(String BillType_S) {
        patientsBean = (PatientsBean) bundle.getSerializable("PatientsBean");
        patID = patientsBean.getPatID();//住院号
        // billType = (String) getArguments().get("BillType");//费用类型
        type = "";
        if (BillType_S.equals("床位费")) {
            type = "B";
        } else if (BillType_S.equals("诊查费")) {
            type = "C";
        } else if (BillType_S.equals("检查费")) {
            type = "D";
        } else if (BillType_S.equals("治疗费")) {
            type = "E";
        } else if (BillType_S.equals("护理费")) {
            type = "F";
        } else if (BillType_S.equals("手术费")) {
            type = "G";
        } else if (BillType_S.equals("化验费")) {
            type = "H";
        } else if (BillType_S.equals("特殊服务费")) {
            type = "I";
        } else if (BillType_S.equals("材料费")) {
            type = "GL";
        } else if (BillType_S.equals("西药费")) {
            type = "XY";
        } else if (BillType_S.equals("中成药")) {
            type = "ZY";
        } else if (BillType_S.equals("中草药")) {
            type = "CY";
        } else if (BillType_S.equals("")) {
            type = "";
        }

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("patID", patID);
        params.put("BillType", type);
        params.put("OptionType", "2");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());

        NetRequest.getInstens().requestDate(params, Api.GetPainbBillDetails, false, new NetCallBack<ArrayList<PainbBillDetailsBean.ResultDataBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<PainbBillDetailsBean.ResultDataBean> bean) {
                if (bean != null && bean.size() > 0) {
                    beans = bean;
                    rvAdapter.setmDatas(bean);
                    rvAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void updataLayout(int flag) {
                if (flag == RootFrameLayout.REQUEST_FAIL && rvAdapter != null) {
                    rvAdapter.setmDatas(new ArrayList<>());
                    rvAdapter.notifyDataSetChanged();
                }
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
        patientsBean = (PatientsBean) bundle.getSerializable("PatientsBean");
        patID = patientsBean.getPatID();//住院号
        //护士ID
        nurseID = patientsBean.getPatID();
        //护士名字
        nurseName = patientsBean.getZyDetail().getPri_NurseName();
        //床位号
        inBed = patientsBean.getZyDetail().getIn_Bed();
        //编号
        SparseBooleanArray array = rvAdapter.getmSelectedPositions();
        for (int i = 0; i < array.size(); i++) {
            if (array.valueAt(i)) {
                billNo = beans.get(i).getBillNo() + ",";
            }
        }
        if (!TextUtils.isEmpty(billNo)) {
            billNo = billNo.substring(0, billNo.length() - 1);
        }

        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("patID", patID);
        params.put("BillNo", billNo);
        params.put("NurseID", loginBean.getUserID());
        params.put("Nurse", loginBean.getUserName());
        params.put("OptionType", "2");
        params.put("UserID", loginBean.getUserID());
        params.put("BedNo", inBed);
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());

        NetRequest.getInstens().requestDate(params, Api.ExcutePainbBillDetail, false, new NetCallBack<ArrayList<NoDataBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<NoDataBean> bean) {
                Toast.makeText(getActivity(), "退费成功", Toast.LENGTH_SHORT).show();

                if (all_select.isChecked()) {
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
