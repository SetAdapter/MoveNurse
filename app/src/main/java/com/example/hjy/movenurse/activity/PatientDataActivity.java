package com.example.hjy.movenurse.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.adapter.AntenatalExpectantSoleListAdapter;
import com.example.hjy.movenurse.adapter.PatientDataAdapter;
import com.example.hjy.movenurse.adapter.PopupWindowAdapter;
import com.example.hjy.movenurse.adapter.PopupWindowRadioAdapter;
import com.example.hjy.movenurse.administrative.adminmanage.AdministrativeManagementActivity;
import com.example.hjy.movenurse.doctor.manage.DoctorManageActivity;
import com.example.hjy.movenurse.nursing.FirstNurseOrderActivity;
import com.fy.base.BaseActivity;
import com.fy.custom.popupwindow.CommonPopupWindow;
import com.fy.custom.swiperefreshlayout.SwipyRefreshLayout;
import com.fy.entity.AntenatalExpectant;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.AnimUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 患者资料
 * Created by Gab on 2017/8/30 0030.
 */
public class PatientDataActivity extends BaseActivity implements AdapterView.OnItemClickListener {

    @BindView(R.id.edit_search)
    EditText search;
    @BindView(R.id.grid_patient)
    GridView mGridView;
    @BindView(R.id.clear)
    Button clear;
    @BindView(R.id.imgPopDropDown)
    ImageView imgPopDropDown;
    @BindView(R.id.tv_screen)
    TextView tv_screen;
    @BindView(R.id.shop_scrollview_list)
    SwipyRefreshLayout shop_scrollview_list;
    PatientDataAdapter mPatientDataAdapter;

    String target = "";//获取Activity跳转 传递的 目标Activity

    @Override
    protected int getContentView() {
        return R.layout.patientdata_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initRxEvent();
        target = getIntent().getExtras().getString("PatientDataActivity");
        tv_huanzhe.setVisibility(View.INVISIBLE);
        switch (target) {
            case "activity.PatientDataItemActivity"://患者资料详情
                tvTitle.setText("患者资料");
                break;
            case "doctor.manage.DoctorManageActivity"://医嘱管理
                tvTitle.setText("医嘱管理");
                break;
            case "adminmanage.AdministrativeManagementActivity"://退费退药
                tvTitle.setText("退费退药");
                break;
            case "nursing.FirstNurseOrderActivity"://护理评估
                tvTitle.setText("护理评估");
                break;

        }

        initListner();
        mPatientDataAdapter = new PatientDataAdapter(mContext, new ArrayList());
        mGridView.setAdapter(mPatientDataAdapter);
        shop_scrollview_list.setOnRefreshListener(new SwipyRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh(int index) {
                LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
                patientData(loginBean.getDeptID(), loginBean.getToken(), loginBean.getUserID());
                shop_scrollview_list.setRefreshing(false);
            }

            @Override
            public void onLoad(int index) {

            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        patientData(loginBean.getDeptID(), loginBean.getToken(), loginBean.getUserID());

        String target1 = getIntent().getExtras().getString("DataActivity");
        if(target1!=null){
            switch (target1){
                case "activity.PatientDataItemActivity"://患者资料详情
                    tvTitle.setText("患者资料");
                    break;
                case "doctor.manage.DoctorManageActivity"://医嘱管理
                    tvTitle.setText("医嘱管理");
                    break;
                case "adminmanage.AdministrativeManagementActivity"://退费退药
                    tvTitle.setText("退费退药");
                    break;
                case "nursing.FirstNurseOrderActivity"://护理评估
                    tvTitle.setText("护理评估");
                    break;

            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @OnClick({R.id.Ll_patient})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.Ll_patient:
                GetScreen();
                break;
        }
    }

    private void initListner() {
        mGridView.setOnItemClickListener(this);
        search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                clear.setVisibility(View.VISIBLE);
                mPatientDataAdapter.getFilter().filter("单" + s);

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

    /**
     * 患者资料请求数据
     */
    private void patientData(String DeptID, String Token, String UserID) {
        Map<String, Object> params = new HashMap<>();
        params.put("DeptID", DeptID);
        params.put("Token", Token);
        params.put("UserID", UserID);
        NetRequest.getInstens()
                .requestDate(params, Api.getZyPatient, true,
                        new NetCallBack<ArrayList<PatientsBean>>(this, R.string.data_loading) {
                            @Override
                            public void onSuccess(ArrayList<PatientsBean> bean) {
                                if (null != bean) {
                                    mPatientDataAdapter.setData(bean);
                                    mPatientDataAdapter.clearCache();
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

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        PatientsBean bean = (PatientsBean) view.getTag();
        Bundle bundle = new Bundle();
        bundle.putSerializable("PatientsBean", bean);

        switch (target) {
            case "activity.PatientDataItemActivity"://患者资料详情
                JumpUtils.jump(mContext, PatientDataItemActivity.class, bundle);
                break;
            case "doctor.manage.DoctorManageActivity"://医嘱管理
                JumpUtils.jump(mContext, DoctorManageActivity.class, bundle);
                break;
            case "adminmanage.AdministrativeManagementActivity"://退费退药
                JumpUtils.jump(mContext, AdministrativeManagementActivity.class, bundle);
                break;
            case "nursing.FirstNurseOrderActivity"://护理评估
                mCache.put("PatName", bean);
                JumpUtils.jump(mContext, FirstNurseOrderActivity.class, null);
                break;
        }
    }

    protected void initRxEvent() {
        Flowable<RxBean> f1 = RxBus.getInstance().register(RxBean.class);
        disposable = f1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {

                    if (event.getSendAction().equals("SCAN_RESULT#")) {
                        search.setText(event.getContent());
                    }
                });
    }


    PopupWindow popupWindow;

    private void GetScreen() {
        AnimUtils.doArrowAnim(imgPopDropDown, false);

        popupWindow = new CommonPopupWindow.Builder(mContext)
                .setView(R.layout.popupwindow_list)
                .setWidthAndHeight(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTop)
                .setViewOnclickListener((view, layoutResId) -> {
                    //患者类别
                    RecyclerView recyclerView1 = view.findViewById(R.id.rvData);
                    PopupWindowAdapter mAdapter1 = new PopupWindowAdapter(mContext, initDepartRv());
                    GridLayoutManager gManager1 = new GridLayoutManager(mContext, 4);
                    recyclerView1.setLayoutManager(gManager1);
                    recyclerView1.setAdapter(mAdapter1);
                    //护理级别
                    RecyclerView recyclerView2 = view.findViewById(R.id.rvData_list);
                    PopupWindowRadioAdapter mAdapters2 = new PopupWindowRadioAdapter(mContext, initrvData());
                    GridLayoutManager gManager2 = new GridLayoutManager(mContext, 4);
                    mAdapters2.setmRv(recyclerView2);
                    recyclerView2.setLayoutManager(gManager2);
                    recyclerView2.setAdapter(mAdapters2);
                    //性别
                    RecyclerView recyclerView3 = view.findViewById(R.id.rvSexData_list);
                    PopupWindowRadioAdapter mAdapters3 = new PopupWindowRadioAdapter(mContext, initsex());
                    mAdapters3.setmRv(recyclerView3);
                    GridLayoutManager gManager3 = new GridLayoutManager(mContext, 4);
                    recyclerView3.setLayoutManager(gManager3);
                    recyclerView3.setAdapter(mAdapters3);

                    Button save_btn = view.findViewById(R.id.save_btn);
                    save_btn.setOnClickListener(v -> {
                        popupWindow.dismiss();
                        String arr1 = getData(mAdapter1);

                        StringBuilder sb = new StringBuilder();

                        if (mAdapters2.getmSelectedPos() > -1) {
                            sb.append(mAdapters2.getmDatas().get(mAdapters2.getmSelectedPos()))
                                    .append("&");
                        }

                        if (mAdapters3.getmSelectedPos() > -1) {
                            sb.append(mAdapters3.getmDatas().get(mAdapters3.getmSelectedPos()))
                                    .append("&");
                        }

                        mPatientDataAdapter.getFilter().filter("多" + arr1 + sb.toString() );
                    });
                }).setListener(() -> AnimUtils.doArrowAnim(imgPopDropDown, true))
                .create();

        //得到button的左上角坐标
        int[] positions = new int[2];
        tv_screen.getLocationOnScreen(positions);
        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY, 0, positions[1] + tv_screen.getHeight());
    }

    private String getData(PopupWindowAdapter adapter) {

        List<String> datas = adapter.getmDatas();
        SparseBooleanArray selected = adapter.getmSelectedPositions();
        String da = "";
        for (int i = 0; i < datas.size(); i++) {
            if (selected.valueAt(i)) {
                da += datas.get(selected.keyAt(i)) + "&";
            }
        }

        return da;
    }

    private List<String> initDepartRv() {
        List<String> list = new ArrayList<>();
        list.add("肿瘤病人");
        list.add("住院超过30天病人");
        list.add("有医嘱");
        list.add("欠费");
        return list;
    }

    private List<String> initrvData() {
        List<String> list = new ArrayList<>();
        list.add("特级护理");
        list.add("一级护理");
        list.add("二级护理");
        list.add("三级护理");
        list.add("无护理级别");
        return list;
    }

    private List<String> initsex() {
        List<String> list = new ArrayList<>();
        list.add("男");
        list.add("女");
        return list;
    }
}
