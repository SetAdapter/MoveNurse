package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.view.XListView;
import com.bigkoo.pickerview.view.XListViewFooter;
import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.DictTypeBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PainbBillDetailsBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 行政管理 退费退药查询Fragment
 * Created by Stefan on 2017/9/22.
 */

public class RefWithQueryFragment extends BaseFragment implements XListView.IXListViewListener {
    @BindView(R.id.list_item)
    XListView list_item;
    @BindView(R.id.edit_search)
    EditText search;
    @BindView(R.id.clear)
    ImageView clear;
    Spinner cost_type;
    RefWithQueryAdapter adapter;
    Bundle bundle;
    private PatientsBean patientsBean;
    private String patID;
    private View footer;
    TextView total_fNumber, total_amount, total_feeAmount;

    private int totalNumber;
    private double totalAmount;
    private double totalFeeAmount;

    private String fNumber;//数量
    private String mount;//总价
    private String fAmount;//自费金额
    private String BillType_S;
    private int fnumber;
    private double amount;
    private double feeAmount;
    private String type;
    boolean mark = true;
    private String[] mItems;

    @Override
    protected int getContentLayout() {
        return R.layout.query_list;
    }

    @Override
    public void onStart() {
        super.onStart();

    }

    @Override
    public void onResume() {
        super.onResume();
        //自动刷新
        if (mark) {
            getPainbBillDetails("");
            mark = false;
        } else {
            list_item.autoRefresh();
        }
        getCostMoney();

    }

    //spinner事件
    private void setCosttype() {
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, mItems);
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

    @Override
    protected void baseInit() {
        super.baseInit();
//        //网络请求费用类型
//        getCostMoney();
//        //spinner事件
//        setCosttype();

        cost_type = (Spinner) getActivity().findViewById(R.id.cost_type);
        bundle = getActivity().getIntent().getExtras();
        patientsBean = (PatientsBean) bundle.getSerializable("PatientsBean");
        patID = patientsBean.getPatID();//住院号
        XListViewFooter.mContentView.setVisibility(View.GONE);
        list_item.setXListViewListener(this);
        list_item.setPullRefreshEnable(true);
        list_item.setPullLoadEnable(true);
        adapter = new RefWithQueryAdapter(mContext, new ArrayList());
        list_item.setAdapter(adapter);
        initListner();
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.footer_query, null);
        list_item.addFooterView(footer);
        total_amount = (TextView) footer.findViewById(R.id.total_amount);//数量合计
        total_fNumber = (TextView) footer.findViewById(R.id.total_fNumber);//金额合计
        total_feeAmount = (TextView) footer.findViewById(R.id.total_feeAmount);//自费金额合计
    }

    private void initListner() {
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clear.setVisibility(View.VISIBLE);
                if (adapter != null) adapter.getFilter().filter(s);
                if (adapter.getFilter() == null) {
                    footer.setVisibility(View.INVISIBLE);
                }
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

    private void setTotalData(ArrayList<PainbBillDetailsBean.ResultDataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            //数量
            fNumber = data.get(i).getFNumber();
            //金额
            mount = data.get(i).getAmount();
            //自费金额
            fAmount = data.get(i).getFeeAmount();

            if (!TextUtils.isEmpty(fNumber)) {
                fnumber = Integer.parseInt(fNumber);
                totalNumber += fnumber;
            }
            if (!TextUtils.isEmpty(mount)) {
                amount = Double.parseDouble(mount);
                totalAmount += amount;
            }
            if (!TextUtils.isEmpty(fAmount)) {
                feeAmount = Double.parseDouble(fAmount);
                totalFeeAmount += feeAmount;
            }
        }
        //类型转换
        if (!TextUtils.isEmpty(String.valueOf(totalNumber))) {
            total_amount.setText(BigDecimalUtil.fromat4S5R(totalNumber, 0) + "");//数量
        }
        if (!TextUtils.isEmpty(String.valueOf(totalAmount))) {
            total_fNumber.setText(BigDecimalUtil.fromat4S5R(totalAmount, 2) + "");//金额合计
        }
        if (!TextUtils.isEmpty(String.valueOf(totalFeeAmount))) {
            total_feeAmount.setText(BigDecimalUtil.fromat4S5R(totalFeeAmount, 2) + "");//自费金额合计
        }
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
                L.i("请求参数: ", params.toString());
                if (been != null && been.size() > 0) {
                    DictTypeBean typeBean = been.get(0);
                    List<DictTypeBean.DictsBean> dicts = typeBean.getDicts();
                    if (null != dicts) {
                        mItems = new String[dicts.size() + 1];
                        mItems[0] = "全部";
                        for (int i = 1; i < dicts.size() + 1; i++) {
                            mItems[i] = dicts.get(i - 1).getDict_TypeName();
                        }
                        setCosttype();
                    }
                }
            }

            @Override
            public void updataLayout(int flag) {
                if ((flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) && adapter != null) {
                    L.i("请求参数: ", params.toString());
                }
            }

            @Override
            protected void onFlaiCacheRequest() {

            }
        });

    }

    private void getPainbBillDetails(String BillType_S) {
//        billType = (String) getArguments().get("BillType");//费用类型
        // bundle_s = getArguments();
        type = "";
        // BillType_S= (String) bundle_s.get("BillType_S");
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
            type = "CL";
        } else if (BillType_S.equals("西药费")) {
            type = "XY";
        } else if (BillType_S.equals("中成药")) {
            type = "ZY";
        } else if (BillType_S.equals("中草药")) {
            type = "CY";
        }
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("patID", patID);
        params.put("BillType", type);
        params.put("UserID", loginBean.getUserID());
        params.put("OptionType", "3");
        params.put("Token", loginBean.getToken());

        NetRequest.getInstens().requestDate(params, Api.GetPainbBillDetails, false, new NetCallBack<ArrayList<PainbBillDetailsBean.ResultDataBean>>() {
            @Override
            public void onSuccess(ArrayList<PainbBillDetailsBean.ResultDataBean> bean) {
                footer.setVisibility(View.VISIBLE);
                if (bean != null && bean.size() > 0) {
                    adapter.setData(bean);
                    totalNumber = 0;
                    totalAmount = 0;
                    totalFeeAmount = 0;
                    setTotalData(bean);
                } else {
                    footer.setVisibility(View.GONE);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        list_item.stopRefresh();
                    }
                }, 500);

            }

            @Override
            public void updataLayout(int flag) {
                if ((flag == RootFrameLayout.REQUEST_FAIL || flag == RootFrameLayout.LAYOUT_NETWORK_ERROR_ID) && adapter != null) {
                    adapter.setData(new ArrayList<>());
                    adapter.notifyDataSetChanged();
                    footer.setVisibility(View.GONE);
                    list_item.stopRefresh();
                }
            }

            @Override
            protected void onFlaiCacheRequest() {
            }
        });
    }

    public boolean onTouchEvent(android.view.MotionEvent event) {
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(getActivity().INPUT_METHOD_SERVICE);
        return imm.hideSoftInputFromWindow(getActivity().getCurrentFocus().getWindowToken(), 0);
    }

    @Override
    public void onRefresh() {
        getPainbBillDetails("");
        if (cost_type.getSelectedItem() != null) {
            cost_type.setSelection(0);
        }

    }

    @Override
    public void onLoadMore() {

    }
}
