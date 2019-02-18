package com.example.hjy.movenurse.nursing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.auxiliary.RespiratorAuxiliaryFragment;
import com.example.hjy.movenurse.fragment.AnesthesiaPostoperationFragment;
import com.example.hjy.movenurse.fragment.AnesthesiaPreoperativeFragment;
import com.example.hjy.movenurse.fragment.ChildFirstNurseOrderFragment;
import com.example.hjy.movenurse.fragment.FirstNurseOrderFragment;
import com.example.hjy.movenurse.fragment.PressureSoresFragment;
import com.example.hjy.movenurse.health.HealthGuidanceFragment;
import com.example.hjy.movenurse.hotbag.UserHotBagFragment;
import com.example.hjy.movenurse.newborngeneral.NewbornGeneralFragment;
import com.example.hjy.movenurse.newbornnurse.NewbornNurseFragment;
import com.example.hjy.movenurse.nursing.antenatalrecord.AntenatalRecordFragment;
import com.example.hjy.movenurse.nursing.recordsheet.NursingRecordFragment;
import com.example.hjy.movenurse.obstetrics.ObstetricalPostpartumRecordFragment;
import com.example.hjy.movenurse.patient.reject.PatientRejectFragment;
import com.example.hjy.movenurse.postpartum.PostpartumObserveRecordFragment;
import com.example.hjy.movenurse.prevent.PreventTumbleFragment;
import com.example.hjy.movenurse.restraint.RestraintStrapFragment;
import com.example.hjy.movenurse.salvage.EmergencySalvageFragment;
import com.fy.base.BaseActivity;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.PatientsBean;
import com.fy.utils.L;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 护理评估
 * Created by fangs on 2017/9/11.
 */
public class FirstNurseOrderActivity extends BaseActivity {

    private FragmentManager fragmentManager;
    private Fragment mCurrentFragment;
    private int currentFragment = 0;
    /**
     * 上一次界面 onSaveInstanceState 之前的tab被选中的状态 key 和 value
     */
    private static final String PRV_SELINDEX = "PREV_SELINDEX";

    /**
     * Fragment的TAG 用于解决app内存被回收之后导致的fragment重叠问题
     */
    private static final String[] nursingLabel = {"首次护理记录", "儿童首次护理", "压疮风险评估", "麻醉术前访视",
            "麻醉术后访视", "护理记录单", "产前待产记录", "产科产后记录", "产后观察记录", "急诊抢救记录",
            "一般新生儿护理", "新生儿护理记录", "新生儿辅助呼吸", "患者拒绝翻身", "使用热水袋注意", "出院健康指导",
            "约束带使用", "防跌倒/坠床"};

    private Map<String, Fragment> fmMap;

    @BindView(R.id.tvSelectLabel)
    TextView tvSelectLabel;
    @BindView(R.id.rvNursingLabel)
    RecyclerView rvNursingLabel;

    @Override
    protected int getContentView() {
        return R.layout.first_nurse_order_activity;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment(savedInstanceState);

        tvTitle.setText("护理评估 ");
        llTitle.setOnClickListener(this);
        imgDropDown.setVisibility(View.VISIBLE);
        setModuleTag("护理评估");

        PatientsBean bean = (PatientsBean) mCache.getAsObject("PatName");
        setBean(bean);

        initInfoFragment();
        initRv();

        beginTransaction(nursingLabel[0]);
    }

    private void initRv() {
        List<String> label = new ArrayList<>();
        tvSelectLabel.setText(nursingLabel[0]);
        for (int i = 1; i < nursingLabel.length; i++) {
            label.add(nursingLabel[i]);
        }

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置为水平布局，默认为垂直布局
        layoutManager.setOrientation(OrientationHelper.HORIZONTAL);
        //设置布局管理器
        rvNursingLabel.setLayoutManager(layoutManager);
        rvNursingLabel.setAdapter(new RecyclerCommonAdapter<String>(mContext, R.layout.item_nursing_label, label) {
            @Override
            public void convert(ViewHolder holder, String s, int position) {
                holder.setText(R.id.tvNursingLabel, s);
                holder.setOnClickListener(R.id.tvNursingLabel, v -> {
                    String LastTimeLabel = tvSelectLabel.getText().toString().trim();
                    tvSelectLabel.setText(s);

                    removeData(position);
                    addData(position, LastTimeLabel);
                    notifyItemChanged(position);

                    beginTransaction(s);
                });
            }
        });
    }

    private void initInfoFragment() {
        fmMap = new HashMap<>();

        PatientInfoFragment infoFragment = new PatientInfoFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fmPatientInfo, infoFragment);
        transaction.commit();
    }

    private void beginTransaction(String label) {
        if (null == fragmentManager) {
            fragmentManager = getSupportFragmentManager();
        }
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment showfragment = getFragment(label);
        //判断当前的Fragment是否为空，不为空则隐藏
        if (null != mCurrentFragment) {
            fragmentTransaction.hide(mCurrentFragment);
        }

        if (null == showfragment) return;
        //判断此Fragment是否已经添加到FragmentTransaction事物中
        if (!showfragment.isAdded()) {
            fragmentTransaction.add(R.id.fmContent, showfragment, label);
        } else {
            fragmentTransaction.show(showfragment);
        }

        //保存当前显示的那个Fragment
        mCurrentFragment = showfragment;
        fragmentTransaction.commitAllowingStateLoss();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    private Fragment getFragment(String label) {
        if (null != fmMap.get(label)) {
            return fmMap.get(label);
        }
        Fragment fm = null;
        switch (label) {
            case "首次护理记录":
                fm = new FirstNurseOrderFragment();
                break;
            case "儿童首次护理":
                fm = new ChildFirstNurseOrderFragment();
                break;
            case "压疮风险评估":
                fm = new PressureSoresFragment();
                break;
            case "麻醉术前访视":
                fm = new AnesthesiaPreoperativeFragment();
                break;
            case "麻醉术后访视":
                fm = new AnesthesiaPostoperationFragment();
                break;
            case "护理记录单":
                fm = new NursingRecordFragment();
                break;
            case "产前待产记录":
                fm = new AntenatalRecordFragment();
                break;
            case "产科产后记录":
                fm = new ObstetricalPostpartumRecordFragment();
                break;
            case "产后观察记录":
                fm = new PostpartumObserveRecordFragment();
                break;
            case "急诊抢救记录":
                fm = new EmergencySalvageFragment();
                break;
            case "一般新生儿护理":
                fm = new NewbornGeneralFragment();
                break;
            case "新生儿护理记录":
                fm = new NewbornNurseFragment();
                break;
            case "新生儿辅助呼吸":
                fm = new RespiratorAuxiliaryFragment();
                break;
            case "患者拒绝翻身":
                fm = new PatientRejectFragment();
                break;
            case "使用热水袋注意":
                fm = new UserHotBagFragment();
                break;
            case "出院健康指导":
                fm = new HealthGuidanceFragment();
                break;
            case "约束带使用":
                fm = new RestraintStrapFragment();
                break;
            case "防跌倒/坠床":
                fm = new PreventTumbleFragment();
                break;
        }

        fmMap.put(label, fm);
        return fm;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        if (outState != null) {
            //保存tab选中的状态
            outState.putInt(PRV_SELINDEX, currentFragment);
            L.e("currentFrament", "第" + currentFragment + "个Fragment");
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
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            //读取上一次界面Save的时候tab选中的状态
            currentFragment = savedInstanceState.getInt(PRV_SELINDEX, currentFragment);

            for (String label : nursingLabel) {
                Fragment fm = fragmentManager.findFragmentByTag(label);
                if (null != fm) {
                    fragmentTransaction.hide(fm);
                }
            }

            fragmentTransaction.commit();
        }
    }
}
