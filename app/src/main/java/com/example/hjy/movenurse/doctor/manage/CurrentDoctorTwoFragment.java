package com.example.hjy.movenurse.doctor.manage;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.DoctorInfoBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 当前医嘱（处置医嘱 two） Fragment
 * Created by fangs on 2017/9/21.
 */
public class CurrentDoctorTwoFragment extends BaseFragment{

    PatientsBean bean;

    @BindView(R.id.rvCurrentDoctor)
    RecyclerView rvCurrentDoctor;
    CurrentDoctorOneAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_current_doctor_one;
    }

    @Override
    protected void baseInitView() {
        initRxEvent();

        Bundle bundle = getArguments();
        bean = (PatientsBean) bundle.getSerializable("PatientsBean");

        initRv();
    }

    @Override
    public void onResume() {
        super.onResume();
        runRequest();
    }

    private void initRv(){
        List<DoctorInfoBean> data = new ArrayList<>();

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        rvCurrentDoctor.setLayoutManager(layoutManager);

        adapter = new CurrentDoctorOneAdapter(mContext, data, "项目名称");
        rvCurrentDoctor.setAdapter(adapter);
    }

    private void runRequest(){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == loginBean)return;

        Map<String, Object> params = new HashMap<>();
        params.put("PatID", bean.getPatID());//住院号
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "1005201");//医嘱类型 处置医嘱
        params.put("OrderStatus", "1");//医嘱执行状态
        params.put("StartTime", "");//医嘱开始执行时间
        params.put("EndTime", "");//医嘱结束执行时间
        params.put("Token", loginBean.getToken());//token
        NetRequest.getInstens().requestDate(params, Api.GetPatOrders, false, new NetCallBack<ArrayList<DoctorInfoBean>>() {
            @Override
            public void onSuccess(ArrayList<DoctorInfoBean> bean) {
                if (null != bean && bean.size() > 0){
                    adapter.cleanChecked();
                    adapter.setmDatas(bean);
                    adapter.notifyDataSetChanged();
                }
            }

            @Override
            public void updataLayout(int flag) {
                if (flag == RootFrameLayout.REQUEST_FAIL){
                    adapter.setmDatas(new ArrayList<>());
                    adapter.notifyDataSetChanged();
                }
            }
            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    protected void initRxEvent() {
        Flowable<RxBean> f1 = RxBus.getInstance().register(RxBean.class);
        disposable = f1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {

                    if (event.getSendAction().equals("DoctorManageActivity#") && !mHidden) {
                        runRequest();
                    }
                });
    }
}
