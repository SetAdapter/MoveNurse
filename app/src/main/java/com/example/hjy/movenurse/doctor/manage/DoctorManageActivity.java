package com.example.hjy.movenurse.doctor.manage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.RadioButton;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.entity.DoctorInfoBean;
import com.fy.entity.LoginBean;
import com.fy.entity.NoDataBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.L;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 医嘱管理 Activity
 * Created by fangs on 2017/9/21.
 */
public class DoctorManageActivity extends BaseActivity {

    private FragmentManager fragmentManageer;
    private Fragment mCurrentFrgment;
    private int currentFrament = 0;
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEXDoctorManageActivity";

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] nursingLabel = {"CurrentDoctorOneFragment", "CurrentDoctorTwoFragment",
            "OldDoctorOneFragment", "OldDoctorTwoFragment"};

    private Map<String, Fragment> fmMap;

    @BindView(R.id.tvSelectLabel)
    TextView tvSelectLabel;
    @BindView(R.id.tvLabel1)
    TextView tvLabel1;
    @BindView(R.id.tvSave)
    TextView tvSave;

    PatientsBean bean;
    Bundle bundle;

    @BindView(R.id.tvMedicationOrders)
    RadioButton tvMedicationOrders;
    @BindView(R.id.tvImplementOrders)
    RadioButton tvImplementOrders;

    @Override
    protected int getContentView() {
        return R.layout.activity_doctor_manage;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment(savedInstanceState);

        tvTitle.setText("医嘱管理 ");
        llTitle.setOnClickListener(this);
        imgDropDown.setVisibility(View.VISIBLE);
        tvMedicationOrders.setChecked(true);
        setModuleTag("医嘱管理");

        bundle = getIntent().getExtras();
        bean = (PatientsBean) bundle.getSerializable("PatientsBean");
        setBean(bean);

        initInfoFragment();

        beginTransaction(nursingLabel[0]);

    }

    private void initInfoFragment() {
        fmMap = new HashMap<>();

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();

        DoctorPatientInfoFragment infoFragment = new DoctorPatientInfoFragment();
        infoFragment.setArguments(bundle);
        transaction.replace(R.id.fmPatientInfo, infoFragment);
        transaction.commit();
    }

    @OnClick({R.id.tvLabel1, R.id.tvMedicationOrders, R.id.tvImplementOrders, R.id.tvSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvLabel1://头部 标签切换 按钮
                String label = tvSelectLabel.getText().toString();
                String label1 = tvLabel1.getText().toString();
                tvLabel1.setText(label);
                tvSelectLabel.setText(label1);
                if (label1.equals("现行医嘱")) {
                    tvSave.setVisibility(View.VISIBLE);
                } else {
                    tvSave.setVisibility(View.GONE);//隐藏 确定按钮
                }

                String tag1 = showTagetFragment();
                beginTransaction(tag1);
                break;
            case R.id.tvMedicationOrders://药物医嘱
                String tag2 = showTagetFragment();
                beginTransaction(tag2);
                break;
            case R.id.tvImplementOrders://已执行医嘱
                String tag3 = showTagetFragment();
                beginTransaction(tag3);
                break;
            case R.id.tvSave://确定
                String tag4 = showTagetFragment();

                String data = "";
                if (tag4.equals("CurrentDoctorOneFragment")){
                    CurrentDoctorOneFragment showfragment = (CurrentDoctorOneFragment) getFragment(tag4);
                    SparseBooleanArray sparseArray = showfragment.adapter.getmSelectedPositions();
                    data = getSelectdata(showfragment.adapter.getmDatas(), sparseArray);
                } else if (tag4.equals("CurrentDoctorTwoFragment")){
                    CurrentDoctorTwoFragment showfragment = (CurrentDoctorTwoFragment) getFragment(tag4);
                    SparseBooleanArray sparseArray = showfragment.adapter.getmSelectedPositions();
                    data = getSelectdata(showfragment.adapter.getmDatas(), sparseArray);
                }

                if (!TextUtils.isEmpty(data))runRequest(data);
                break;
        }
    }

    /**
     * 获取选中的数据集合
     * @param dataSouse
     * @param booleanArray
     * @return
     */
    public static String getSelectdata(List<DoctorInfoBean> dataSouse, SparseBooleanArray booleanArray) {
        String data = "";
        for (int i = 0; i < booleanArray.size(); ++i) {
            if (booleanArray.valueAt(i)) {
                DoctorInfoBean item = dataSouse.get(booleanArray.keyAt(i));
                L.e(item.getOrderNo());
                data += item.getOrderNo() + ",";
            }
        }

        if (!TextUtils.isEmpty(data)){
            data = data.substring(0, data.length() - 1);
        }

        return data;
    }

    //获取对应的 fragment 的tag
    private String showTagetFragment() {
        String label = tvSelectLabel.getText().toString();

        String tag = "";
        if (label.equals("医嘱明细查询")) {
            if (tvMedicationOrders.isChecked()) {//药物医嘱
                tag = nursingLabel[2];
            } else if (tvImplementOrders.isChecked()) {//处置医嘱
                tag = nursingLabel[3];
            }
        } else if (label.equals("现行医嘱")) {
            if (tvMedicationOrders.isChecked()) {//药物医嘱
                tag = nursingLabel[0];
            } else if (tvImplementOrders.isChecked()) {//处置医嘱
                tag = nursingLabel[1];
            }
        }


        return tag;
    }

    //显示对应的fragment
    private void beginTransaction(String label) {
        if (null == fragmentManageer) {
            fragmentManageer = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManageer.beginTransaction();

        Fragment showfragment = getFragment(label);

        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFrgment) {
            fragmentTransaction.hide(mCurrentFrgment);
        }

        if (null == showfragment) return;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!showfragment.isAdded()) {
            fragmentTransaction.add(R.id.fmContent, showfragment, label);
        } else {
            fragmentTransaction.show(showfragment);
        }

        //保存当前显示的那个Fragment
        mCurrentFrgment = showfragment;
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(String label) {
        if (null != fmMap.get(label)) {
            return fmMap.get(label);
        }
        Fragment fm = null;
        switch (label) {
            case "CurrentDoctorOneFragment":
                fm = new CurrentDoctorOneFragment();
                break;
            case "CurrentDoctorTwoFragment":
                fm = new CurrentDoctorTwoFragment();
                break;
            case "OldDoctorOneFragment":
                fm = new OldDoctorOneFragment();
                break;
            case "OldDoctorTwoFragment":
                fm = new OldDoctorTwoFragment();
                break;
        }

        if (null != fm) {
            fm.setArguments(bundle);
            fmMap.put(label, fm);
        }

        return fm;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            //保存tab选中的状态
            outState.putInt(PRV_SELINDEX, currentFrament);
            L.e("currentFrament", "第" + currentFrament + "个Fragment");
        }
    }

    /**
     * 初始化时候 获取activity销毁时保存的Fragment，并全部隐藏
     * 解决Fragment重叠问题
     *
     * @param savedInstanceState
     */
    private void initFragment(Bundle savedInstanceState) {
        if (null != savedInstanceState) {
            fragmentManageer = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManageer.beginTransaction();

            //读取上一次界面Save的时候tab选中的状态
            currentFrament = savedInstanceState.getInt(PRV_SELINDEX, currentFrament);

            for (String label : nursingLabel) {
                Fragment fm = fragmentManageer.findFragmentByTag(label);
                if (null != fm) {
                    fragmentTransaction.hide(fm);
                }
            }

            fragmentTransaction.commit();
        }
    }

    private void runRequest(String OrderNo) {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        if (null == loginBean) return;

        Map<String, Object> params = new HashMap<>();
        params.put("PatID", bean.getPatID());//住院号
        String inTime = "1";
        if (null != bean.getZyDetail()){
            inTime = bean.getZyDetail().getInTime();
        }
        params.put("InTime", inTime);//入院次数
        params.put("UserID", loginBean.getUserID());
        params.put("OrderNo", OrderNo);//医嘱编号
        params.put("NurseID", loginBean.getUserID());//校对护士ID
        params.put("Nurse", loginBean.getUserName());//校对护士
        params.put("SureNurseID", "");//停止护士Id
        params.put("SureNurse", "");//停止护士
        params.put("Token", loginBean.getToken());//token
        NetRequest.getInstens().requestDate(params, Api.ExcutePatOrder, false, new NetCallBack<ArrayList<NoDataBean>>
                (mContext, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<NoDataBean> bean) {
                T.showLong("执行成功");
                RxBus.getInstance().send(new RxBean("DoctorManageActivity#", "true"));
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
