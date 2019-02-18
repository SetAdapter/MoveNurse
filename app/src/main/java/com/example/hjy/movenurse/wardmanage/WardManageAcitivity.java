package com.example.hjy.movenurse.wardmanage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.wardmanage.modifybed.AllBedFragment;
import com.example.hjy.movenurse.wardmanage.modifydepartment.DepartInfoListFragment;
import com.fy.base.BaseActivity;
import com.fy.entity.BedBean;
import com.fy.entity.ChangeBedBean;
import com.fy.entity.LoginBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.L;
import com.fy.utils.ResourceUtils;
import com.fy.utils.SpfUtils;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 病房管理 患者在院 操作 界面
 * <p/>Created by fangs on 2017/10/13.
 */
public class WardManageAcitivity extends BaseActivity{

    private FragmentManager fragmentManageer;
    private Fragment mCurrentFrgment;
    private int currentFrament = 0;
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEX";

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] nursingLabel = {"AllBedFragment", "DepartInfoListFragment"};

    private Map<String, Fragment> fmMap;

    @BindView(R.id.tv_patient_id)
    TextView tv_patient_id;
    @BindView(R.id.tvDepartment)
    TextView tvDepartment;
    @BindView(R.id.tvBed)
    TextView tvBed;
    @BindView(R.id.tvSelectPackBed)
    TextView tvSelectPackBed;

    @BindView(R.id.tvModifyDepartment)
    TextView tvModifyDepartment;
    @BindView(R.id.tvModifyBed)
    TextView tvModifyBed;
    @BindView(R.id.tvPackBed)
    TextView tvPackBed;
    @BindView(R.id.tvSave)
    TextView tvSave;

    @BindView(R.id.tvName)
    TextView tvName;
    @BindView(R.id.tvSex)
    TextView tvSex;

    @BindView(R.id.rgStatus)
    RadioGroup rgStatus;
    @BindView(R.id.rbtnDisease)
    RadioButton rbtnDisease;
    @BindView(R.id.rbtnLeave)
    RadioButton rbtnLeave;
    @BindView(R.id.rbtnPackBed)
    RadioButton rbtnPackBed;

    BedBean bedBean;
    String Depart = "";//科室
    String Bed = "";//患者床位

    @Override
    protected int getContentView() {
        return R.layout.activity_ward_manage;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        tvTitle.setText("病房管理");

        initPatinInfo();

        fmMap = new HashMap<>();
        beginTransaction(nursingLabel[0]);
    }

    private void initPatinInfo(){
        bedBean = (BedBean) getIntent().getExtras().getSerializable("#BedBean#");

        if (null != bedBean) {
            ResourceUtils.setText(mContext, tv_patient_id, R.string.hospitalizationID, bedBean.getPatID());
            ResourceUtils.setText(mContext, tvName, R.string.name, bedBean.getPatName());
            String sexlabel = bedBean.getSex();
            String sex = sexlabel.equals("F") ? "女" : (sexlabel.equals("M") ? "男" : "未知");
            ResourceUtils.setText(mContext, tvSex, R.string.sex, sex);

            BedBean.ZyDetailBean zyDetail = bedBean.getZyDetail();
            if (null != zyDetail) {
                Depart = zyDetail.getIn_Dept_Name();
                Bed = zyDetail.getIn_Bed();
                ResourceUtils.setText(mContext, tvDepartment, R.string.department, Depart);//科室
                ResourceUtils.setText(mContext, tvBed, R.string.bed, Bed);//床位
                SpfUtils.saveStrToSpf(mContext, "In_Bed", zyDetail.getIn_Bed());
                ResourceUtils.setText(mContext, tvSelectPackBed, R.string.packBed, "");

                SpfUtils.saveStrToSpf(mContext, "BedStatus", zyDetail.getBedStatus());//病床状态
                //设置患者状态
                switch (zyDetail.getBedStatus()) {
                    case "2"://患者在院
                        rbtnDisease.setChecked(true);
                        break;
                    case "4"://请假
                        rbtnLeave.setChecked(true);
                        break;
                    case "3"://包床
                        rbtnPackBed.setChecked(true);
                        break;
                }

                SpfUtils.saveStrToSpf(mContext, "OldBedID", zyDetail.getIn_Bed());//保存旧床位
                SpfUtils.saveStrToSpf(mContext, "oldIn_AreaID", zyDetail.getIn_AreaID());//保存旧病区
                SpfUtils.saveStrToSpf(mContext, "In_Dept_ID", zyDetail.getIn_Dept_ID());//保存科室
            }
        }

        rgStatus.setOnCheckedChangeListener((radioGroup, checkedId) -> {
            String stutes = "";
            switch (checkedId) {
                case R.id.rbtnDisease://患者在院
                    stutes = "2";
                    initFragment(new Bundle());
                    tvModifyDepartment.setVisibility(View.VISIBLE);
                    tvModifyBed.setVisibility(View.VISIBLE);
                    tvPackBed.setVisibility(View.INVISIBLE);
                    ResourceUtils.setText(mContext, tvSelectPackBed, R.string.packBed, "");
                    break;
                case R.id.rbtnLeave://请假
                    stutes = "4";
                    initFragment(new Bundle());
                    tvModifyDepartment.setVisibility(View.VISIBLE);
                    tvModifyBed.setVisibility(View.VISIBLE);
                    tvPackBed.setVisibility(View.INVISIBLE);

                    ResourceUtils.setText(mContext, tvSelectPackBed, R.string.packBed, "");
                    break;
                case R.id.rbtnPackBed://包床
                    stutes = "3";
                    RxBus.getInstance().send(new RxBean("DeptID#", ""));
                    initFragment(new Bundle());

                    tvModifyDepartment.setVisibility(View.INVISIBLE);
                    tvModifyBed.setVisibility(View.INVISIBLE);
                    tvPackBed.setVisibility(View.VISIBLE);

                    tvSave.setEnabled(true);//选择床位后 保存按钮 可以点击
                    ResourceUtils.setText(mContext, tvDepartment, R.string.department, Depart);//科室
                    ResourceUtils.setText(mContext, tvBed, R.string.bed, Bed);//床位

                    SpfUtils.saveStrToSpf(mContext, "In_Dept_ID", "");//保存科室
                    break;
            }
            SpfUtils.saveStrToSpf(mContext, "BedStatus", stutes);//病床状态
        });
    }

    @OnClick({R.id.tvModifyDepartment, R.id.tvModifyBed, R.id.tvSave, R.id.tvPackBed})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvModifyDepartment://修改科室
                beginTransaction(nursingLabel[1]);
                break;
            case R.id.tvModifyBed://修改床位
                beginTransaction(nursingLabel[0]);
                break;
            case R.id.tvPackBed://修改包床 床位
                beginTransaction(nursingLabel[0]);
                break;
            case R.id.tvSave://保存
                ChangeBed();
                break;
        }
    }

    //2.13.	修改患者科室/床位信息
    private void ChangeBed(){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("PatID", bedBean.getPatID());//住院ID
        params.put("UserID", loginBean.getUserID());
        params.put("DeptID", SpfUtils.getSpfSaveStr(mContext, "In_Dept_ID"));//科室id
        params.put("OldAreaID", SpfUtils.getSpfSaveStr(mContext, "oldIn_AreaID"));//旧病区
        String AreaID = SpfUtils.getSpfSaveStr(mContext, "In_AreaID");//新病区
        if (TextUtils.isEmpty(AreaID)){
            AreaID = SpfUtils.getSpfSaveStr(mContext, "oldIn_AreaID");
        }
        params.put("AreaID", AreaID);//病区
        params.put("BedID", SpfUtils.getSpfSaveStr(mContext, "In_Bed"));//病床号
        params.put("Status", SpfUtils.getSpfSaveStr(mContext, "BedStatus"));//床位状态
        params.put("OldBedID", SpfUtils.getSpfSaveStr(mContext, "OldBedID"));//旧床号
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.ChangeBed, false, new NetCallBack<ArrayList<ChangeBedBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<ChangeBedBean> data) {
                T.showLong("保存成功!!!");
                JumpUtils.jumpResult(mContext, null);
            }

            @Override
            public void updataLayout(int flag) {
            }
            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    public void beginTransaction(String label) {
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
            fragmentTransaction.add(R.id.fmWardManage, showfragment, label);
        } else {
            fragmentTransaction.show(showfragment);
        }

        //保存当前显示的那个Fragment
        mCurrentFrgment = showfragment;
        fragmentTransaction.commitAllowingStateLoss();
    }

    private Fragment getFragment(String label) {
        if (null != fmMap.get(label)) return fmMap.get(label);

        Fragment fm = null;
        switch (label) {
            case "AllBedFragment":
                fm = new AllBedFragment();
                Bundle bundle = new Bundle();
                bundle.putSerializable("BedBean", bedBean);
                fm.setArguments(bundle);
                break;
            case "DepartInfoListFragment":
                fm = new DepartInfoListFragment();
                break;
        }

        if (null != fm) {
//            fm.setArguments(bundle);
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
}
