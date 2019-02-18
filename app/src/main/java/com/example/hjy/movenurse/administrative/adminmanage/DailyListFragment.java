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
import com.fy.entity.DailyListBean;
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
 * 退费退药——日清单
 * Created by Stefan on 2017/10/26.
 */

public class DailyListFragment extends BaseFragment implements XListView.IXListViewListener{
    @BindView(R.id.rvDailyList)
    XListView rvDailyList;
    @BindView(R.id.tvDateSelect)
    TextView tvDateSelect;
//    @BindView(R.id.swipyRefreshLayout)
//    SwipyRefreshLayout swipyRefreshLayout;

    TextView tvNumber,tvMoney;

    DailyListAdapter adapter;
    PatientsBean bean;
    private List data;
    private View footer;
    private String fNumber;//数量
    private String mount;//总价
    private int fnumber;
    private double amount;
    private int totalNumber;
    private double totalAmount;
    private String currentTime;

    @Override
    protected int getContentLayout() {
        return R.layout.daily_list;
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
        footer = LayoutInflater.from(getActivity()).inflate(R.layout.footer_daylist, null);
        rvDailyList.addFooterView(footer);
        tvNumber= (TextView) footer.findViewById(R.id.tvNumber);//数量
        tvMoney= (TextView) footer.findViewById(R.id.tvMoney);//金额
    }

    private void initRv() {
        //默认时间数据列表
        currentTime = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        runRequest(currentTime, currentTime);
        adapter = new DailyListAdapter(mContext, new ArrayList<>());
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
    private void setTotalData(ArrayList<DailyListBean.ResultDataBean> data) {
        for (int i = 0; i < data.size(); i++) {
            //数量
            fNumber = data.get(i).getFNumber();
            //金额
            mount = data.get(i).getAmount();

            if(!TextUtils.isEmpty(fNumber)){
                fnumber= Integer.parseInt(fNumber);
                totalNumber += fnumber;
            }
            if(!TextUtils.isEmpty(mount)){
                amount = Double.parseDouble(mount);
                totalAmount += amount;
            }
        }
        //类型转换
        if(!TextUtils.isEmpty(String.valueOf(totalNumber))){
            tvNumber.setText(BigDecimalUtil.fromat4S5R(totalNumber, 0) + "");//数量
        }
        if(!TextUtils.isEmpty(String.valueOf(totalAmount))){
            tvMoney.setText(BigDecimalUtil.fromat4S5R(totalAmount, 2) + "");//金额合计
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
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());//token
        params.put("StartDate", StartDate);//开始执行时间
        params.put("EndDate", EndDate);//结束时间
        NetRequest.getInstens().requestDate(params, Api.GetPainbBillDetails_Da, false, new NetCallBack<ArrayList<DailyListBean.ResultDataBean>>(mContext, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<DailyListBean.ResultDataBean> bean) {
                footer.setVisibility(View.VISIBLE);
                L.i("传参：",params.toString());
                if (null != bean && bean.size() > 0) {
                    adapter.setData(bean);
                    adapter.notifyDataSetChanged();
                    totalNumber=0;
                    totalAmount=0;
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
