package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.bigkoo.pickerview.view.XListView;
import com.bigkoo.pickerview.view.XListViewFooter;
import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.DateSelectUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.ExpenseListBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.L;
import com.fy.utils.TimeUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 退费退药——费用清单
 * Created by Stefan on 2017/10/26.
 */

public class ExpenseListFragment extends BaseFragment implements XListView.IXListViewListener{

    @BindView(R.id.rvDailyList)
    XListView rvDailyList;
    @BindView(R.id.tvDateSelect)
    TextView tvDateSelect;

    TextView tvNumber,tvMoney,tvPreSum,tvTreatAmount,tvPayAmount;

    ExpenseListAdapter adapter;
    PatientsBean bean;
    private List data;
    private View footer;
    private int totalNumber;
    private double totalMoney;
    private double totalPreSum;
    private double totalTreatAmount;
    private double totalPayAmount;

    private String fNumber;//数量
    private String mount;//总价
    private String preSum;//处方金额
    private String treatAmount;//治疗金额
    private String payAmount;//支付金额
    private int fnumber;
    private double amount;
    private double PreSumAmount;
    private double TreatAmount;
    private double PayAmount;
    private String currentTime;

    @Override
    protected int getContentLayout() {
        return R.layout.expense_list;
    }

    @Override
    protected void baseInitView() {
        tvDateSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy年MM月dd日"));
    }

    @Override
    protected void baseInit() {
        super.baseInit();
        XListViewFooter.mContentView.setVisibility(View.GONE);
        rvDailyList.setXListViewListener(this);
        rvDailyList.setPullRefreshEnable(true);
        rvDailyList.setPullLoadEnable(true);
        Bundle bundle = getArguments();
        bean = (PatientsBean) bundle.getSerializable("PatientsBean");
        initRv();
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.footer_exlist, null);
        rvDailyList.addFooterView(footer);
        tvNumber = (TextView) footer.findViewById(R.id.tvNumber);//数量合计
        tvMoney = (TextView) footer.findViewById(R.id.tvMoney);//金额合计
        tvPreSum = (TextView) footer.findViewById(R.id.tvPreSum);//处方金额合计
        tvTreatAmount = (TextView) footer.findViewById(R.id.tvTreatAmount);//治疗金额合计
        tvPayAmount = (TextView) footer.findViewById(R.id.tvPayAmount);//支付金额合计

    }

    private void initRv() {
        //默认时间数据列表
        currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        runRequest(currentTime, currentTime);
        adapter = new ExpenseListAdapter(mContext, new ArrayList<>());
        adapter.setBean(bean);
        rvDailyList.setAdapter(adapter);
    }

    @OnClick({R.id.tvDateSelect})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvDateSelect:
                DateSelectUtils.getDatePicker(mContext, date -> {
                    String time = TimeUtils.Data2String(date, "yyyy年MM月dd日");
                    tvDateSelect.setText(time);
                    long longtime = TimeUtils.timeString2long(time, "yyyy年MM月dd日");
                    String TimeString = TimeUtils.Long2DataString(longtime, "yyyy-MM-dd");
                    runRequest(TimeString, TimeString);
                }).show();
                break;
        }
    }

    private void setTotalData(ArrayList<ExpenseListBean.ResultDataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            //数量
            fNumber = data.get(i).getFNumber();
            //金额
            mount = data.get(i).getAmount();
            //处方金额
            preSum=data.get(i).getAmount();
            //治疗金额
            treatAmount=data.get(i).getAmount();
            //支付金额
            payAmount=data.get(i).getAmount();
            //数量总数
            if(!TextUtils.isEmpty(fNumber)){
                fnumber = Integer.parseInt(fNumber);
                totalNumber += fnumber;
            }
            //金额总数
            if(!TextUtils.isEmpty(mount)){
                amount = Double.parseDouble(mount);
                totalMoney += amount;
            }
            //处方金额总数
            if(!TextUtils.isEmpty(preSum)){
                PreSumAmount = Double.parseDouble(preSum);
                totalPreSum += PreSumAmount;
            }
            //治疗金额总数
            if(!TextUtils.isEmpty(treatAmount)){
                TreatAmount = Double.parseDouble(treatAmount);
                totalTreatAmount += TreatAmount;
            }
            //支付金额总数
            if(!TextUtils.isEmpty(payAmount)){
                PayAmount = Double.parseDouble(payAmount);
                totalPayAmount += PayAmount;
            }
        }

        if(!TextUtils.isEmpty(String.valueOf(totalNumber))){
            tvNumber.setText(BigDecimalUtil.fromat4S5R(totalNumber, 0) + "");//数量
        }
        if(!TextUtils.isEmpty(String.valueOf(totalMoney))){
            tvMoney.setText(BigDecimalUtil.fromat4S5R(totalMoney, 2) + "");//金额合计
        }
        if(!TextUtils.isEmpty(String.valueOf(totalPreSum))){
            tvPreSum.setText(BigDecimalUtil.fromat4S5R(totalPreSum, 2) + "");//处方金额合计
        }
        if(!TextUtils.isEmpty(String.valueOf(totalTreatAmount))){
            tvTreatAmount.setText(BigDecimalUtil.fromat4S5R(totalTreatAmount, 2) + "");//治疗金额合计
        }
        if(!TextUtils.isEmpty(String.valueOf(totalPayAmount))){
            tvPayAmount.setText(BigDecimalUtil.fromat4S5R(totalPayAmount, 2) + "");//支付金额合计
        }
    }


    private void runRequest(String StartDate, String EndDate) {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == loginBean) return;

        Map<String, Object> params = new HashMap<>();
        params.put("PatID", bean.getPatID());//住院号
        params.put("BillType", "");//费用类型
        params.put("OptionType", "4");//操作类型
        params.put("Fiter", "");//高级查询
        params.put("Token", loginBean.getToken());//token
        params.put("StartDate", StartDate);//开始执行时间
        params.put("EndDate", EndDate);//结束时间
        params.put("UserID", loginBean.getUserID());
        NetRequest.getInstens().requestDate(params, Api.GetPainbBillDetails_Ex, false, new NetCallBack<ArrayList<ExpenseListBean.ResultDataBean>>(mContext, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<ExpenseListBean.ResultDataBean> bean) {
                L.i("拦截请求与相应", params.toString());
                footer.setVisibility(View.VISIBLE);
                if (null != bean && bean.size() > 0) {
                    adapter.setData(bean);
                    adapter.notifyDataSetChanged();
                    totalNumber=0;
                    totalMoney=0;
                    totalPreSum=0;
                    totalTreatAmount=0;
                    totalPayAmount=0;
                    setTotalData(bean);
                }else {
                    footer.setVisibility(View.GONE);
                }
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rvDailyList.stopRefresh();
                    }
                },500);
            }

            @Override
            public void updataLayout(int flag) {
                if(flag== RootFrameLayout.REQUEST_FAIL&&adapter!=null){
                    L.i("拦截请求与相应", params.toString());
                    adapter.setData(new ArrayList<>());
                    adapter.notifyDataSetChanged();
                    footer.setVisibility(View.GONE);
                }
                rvDailyList.stopRefresh();
            }

            @Override
            protected void onFlaiCacheRequest() {
                rvDailyList.stopRefresh();
            }
        });
    }

    @Override
    public void onRefresh() {
        runRequest(currentTime,currentTime);
        tvDateSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy年MM月dd日"));
    }

    @Override
    public void onLoadMore() {

    }
}
