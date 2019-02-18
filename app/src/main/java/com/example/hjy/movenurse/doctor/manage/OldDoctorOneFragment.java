package com.example.hjy.movenurse.doctor.manage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.DateSelectUtils;
import com.fy.base.BaseFragment;
import com.fy.custom.popupwindow.CommonPopupWindow;
import com.fy.custom.swiperefreshlayout.SwipyRefreshLayout;
import com.fy.entity.DoctorInfoOldBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.AnimUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 已执行医嘱(药物医嘱 one) fragment
 * Created by fangs on 2017/9/21.
 */
public class OldDoctorOneFragment extends BaseFragment{

    PatientsBean bean;

    @BindView(R.id.tvDateSelect)
    TextView tvDateSelect;
    @BindView(R.id.tvTimeSelect)
    TextView tvTimeSelect;
    @BindView(R.id.tv_time)
    TextView tv_time;//执行时间

    @BindView(R.id.tvEndDateSelect)
    TextView tvEndDateSelect;
    @BindView(R.id.tvEndTimeSelect)
    TextView tvEndTimeSelect;

    @BindView(R.id.tvClassification)
    TextView tvClassification;
    @BindView(R.id.imgDropDown)
    ImageView imgDropDown;

    @BindView(R.id.rvOldDoctor)
    RecyclerView rvOldDoctor;
    @BindView(R.id.swipyRefreshLayout)
    SwipyRefreshLayout swipyRefreshLayout;
    OldDoctorAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_old_doctor;
    }

    @Override
    protected void baseInitView() {
        swipyRefreshLayout.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                tvEndDateSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy年MM月dd日"));
                tvEndTimeSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "HH:mm"));
                swipyRefreshLayout.setRefreshing(true);
                runRequest();
                swipyRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onLoad(int index) {

            }
        });
        Bundle bundle = getArguments();
        bean = (PatientsBean) bundle.getSerializable("PatientsBean");

        PatientsBean.ZyDetailBean detailBean = bean.getZyDetail();
        if (null != detailBean){
            String startTime = detailBean.getIn_Date();
            long startLong = TimeUtils.timeString2long(startTime, "yyyy-MM-dd");
            tvDateSelect.setText(TimeUtils.Long2DataString(startLong, "yyyy年MM月dd日"));
            tvTimeSelect.setText("00:00");
        }

        tvEndDateSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy年MM月dd日"));
        tvEndTimeSelect.setText(TimeUtils.Long2DataString(System.currentTimeMillis(), "HH:mm"));

        initRv();

    }
    @Override
    public void onResume() {
        super.onResume();
        runRequest();
    }

    @OnClick({R.id.tvDateSelect, R.id.tvTimeSelect, R.id.tvEndDateSelect, R.id.tvEndTimeSelect, R.id.llDoctorDetailed})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()){
            case R.id.tvDateSelect:
                DateSelectUtils.getDatePicker(mContext, date -> {
                    String time = TimeUtils.Data2String(date, "yyyy年MM月dd日");
                    String endTime = tvEndDateSelect.getText().toString().trim();
                    tvDateSelect.setText(time);
                    long endLong = TimeUtils.timeString2long(endTime, "yyyy年MM月dd日");
                    if (date.getTime() - endLong > 0){
                        tvEndDateSelect.setText(time);
                    }
                    adapter.getFilter().filter(time + "#" + endTime);
                }).show();
                break;
            case R.id.tvTimeSelect:
                DateSelectUtils.getTimePicker(mContext, date -> {
                    String time = TimeUtils.Data2String(date, "HH:mm");
                    tvTimeSelect.setText(time);
                }).show();
                break;
            case R.id.tvEndDateSelect:
                DateSelectUtils.getDatePicker(mContext, date -> {
                    String startTime = tvDateSelect.getText().toString().trim();
                    long startLong = TimeUtils.timeString2long(startTime, "yyyy年MM月dd日");

                    if (date.getTime() - startLong < 0){
                        T.showLong("请选择合适的日期!!!");
                        return;
                    }
                    String time = TimeUtils.Data2String(date, "yyyy年MM月dd日");
                    tvEndDateSelect.setText(time);

                    tvEndTimeSelect.setText("23:59");
                    adapter.getFilter().filter(startTime + "#" + time);
                }).show();
                break;
            case R.id.tvEndTimeSelect:
                DateSelectUtils.getTimePicker(mContext, date -> {
                    String time = TimeUtils.Data2String(date, "HH:mm");
                    tvEndTimeSelect.setText(time);
                }).show();
                break;
            case R.id.llDoctorDetailed://医嘱分类显示 按钮
                showMenuPopup(view);
                break;
            case R.id.tvDoctor1:
                popupWindow.dismiss();
                tvClassification.setText("已校对已执行");
                tv_time.setText("执行日期");
                runRequest();
                break;
            case R.id.tvDoctor2:
                popupWindow.dismiss();
                tvClassification.setText("未校对未执行");
                tv_time.setText("制表日期");
                runRequest();
                break;
            case R.id.tvDoctor3:
                popupWindow.dismiss();
                tvClassification.setText("已校对未执行");
                tv_time.setText("校对日期");
                runRequest();
                break;
        }
    }

    private void initRv(){
        List<DoctorInfoOldBean> data = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvOldDoctor.setLayoutManager(layoutManager);
        rvOldDoctor.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));

        adapter = new OldDoctorAdapter(mContext, data);
        rvOldDoctor.setAdapter(adapter);
    }

    private void runRequest(){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == loginBean)return;

        String endTime = tvEndDateSelect.getText().toString().trim() + " " + tvEndTimeSelect.getText().toString().trim();
        long current2 = TimeUtils.timeString2long(endTime, "yyyy年MM月dd日 HH:mm");

        PatientsBean.ZyDetailBean detailBean = bean.getZyDetail();
        String startTime = "1970-01-01 00:00:00";
        if (null != detailBean){
            startTime = detailBean.getIn_Date() + " 00:00:00";
        }

        Map<String, Object> params = new HashMap<>();
        params.put("PatID", bean.getPatID());//住院号
        params.put("OrderType", "1005206");//医嘱类型
        params.put("UserID", loginBean.getUserID());
        String doctorStatus = tvClassification.getText().toString().trim();
        String status = doctorStatus.equals("已校对已执行") ? "2" : (doctorStatus.equals("未校对未执行") ? "3" : "4");
        params.put("OrderStatus", status);//医嘱执行状态
        params.put("StartTime", startTime);//医嘱开始执行时间
        params.put("EndTime", TimeUtils.Long2DataString(current2, "yyyy-MM-dd HH:mm:ss"));//医嘱结束执行时间
        params.put("Token", loginBean.getToken());//token
        NetRequest.getInstens().requestDate(params, Api.GetPatOrders2, false, new NetCallBack<ArrayList<DoctorInfoOldBean>>(mContext, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<DoctorInfoOldBean> bean) {
                if (null != bean && bean.size() > 0){
                    List<DoctorInfoOldBean> data = new ArrayList<>();
                    data.addAll(bean);

                    adapter.setmDatas(data);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void updataLayout(int flag) {
                if (flag == RootFrameLayout.REQUEST_FAIL){
                    List<DoctorInfoOldBean> data = new ArrayList<>();

                    adapter.setmDatas(data);
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            protected void onFlaiCacheRequest() {
                swipyRefreshLayout.setRefreshing(false);
            }
        });
    }

    private CommonPopupWindow popupWindow;
    //头部标题点击事件 显示弹窗
    protected void showMenuPopup(View view){
        AnimUtils.doArrowAnim(imgDropDown, false);
        popupWindow = new CommonPopupWindow
                .Builder(getActivity())
                .setView(R.layout.pop_doctor_classification)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(fy.library.com.baselibrary.R.style.AnimTop)
                .setViewOnclickListener((view12, layoutResId) -> {
                    TextView tv1 = (TextView) view12.findViewById(R.id.tvDoctor1);
                    TextView tv2 = (TextView) view12.findViewById(R.id.tvDoctor2);
                    TextView tv3 = (TextView) view12.findViewById(R.id.tvDoctor3);
                    tv1.setText("已校对已执行");
                    tv2.setText("未校对未执行");
                    tv3.setText("已校对未执行");


                    tv1.setOnClickListener(OldDoctorOneFragment.this);
                    tv2.setOnClickListener(OldDoctorOneFragment.this);
                    tv3.setOnClickListener(OldDoctorOneFragment.this);
                }).setListener(() -> AnimUtils.doArrowAnim(imgDropDown, true))
                .create();

        showPopu(view);
    }

    private void showPopu(View view){
        int[] positions = new int[2];
        //得到button的左上角坐标
        imgDropDown.getLocationOnScreen(positions);
        int xDeviation = positions[0] - (popupWindow.getWidth() - imgDropDown.getWidth()) + 25;

        popupWindow.showAtLocation(getActivity().findViewById(android.R.id.content), Gravity.NO_GRAVITY,
                xDeviation,
                positions[1] + view.getHeight() - 5);
    }
}
