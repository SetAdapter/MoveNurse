package com.example.hjy.movenurse.administrative.adminmanage;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
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
 * 退费退药 Activity
 * Created by Stefan on 2017/9/11 11:36.
 */
public class AdministrativeManagementActivity extends BaseActivity {
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
    private static final String[] nursingLabel = {"病区退药", "病区退费", "退费退药查询","日清单","费用清单"};

    private Map<String, Fragment> fmMap;

    @BindView(R.id.tvSelectLabel)
    TextView tvSelectLabel;
    @BindView(R.id.rvNursingLabel)
    RecyclerView rvNursingLabel;
    @BindView(R.id.beg)
    LinearLayout beg;
    @BindView(R.id.cost_type)
    Spinner cost_type;

    private String BillType_S;
    private Bundle bundle;

    @Override
    protected int getContentView() {
        return R.layout.activity_administrative_management;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        initFragment(savedInstanceState);
        tvTitle.setText("退费退药 ");
        llTitle.setOnClickListener(this);
        imgDropDown.setVisibility(View.VISIBLE);
        setModuleTag("退药退费");

       // BillType_S=cost_type.getSelectedItem().toString();
        Bundle bundle_s = new Bundle();
        bundle = getIntent().getExtras();

        PatientsBean bean = (PatientsBean) bundle.getSerializable("PatientsBean");
        setBean(bean);

        initInfoFragment();
        initRv();
        beginTransaction(nursingLabel[0]);

    }
//    public String getBillType_S(){
//        return BillType_S;
//    }

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
            switch (label) {
                case "病区退药":
                    beg.setVisibility(View.GONE);
                    break;
                case "病区退费":
                    beg.setVisibility(View.VISIBLE);
                    break;
                case "退费退药查询":
                    beg.setVisibility(View.VISIBLE);
                    break;
                case "日清单":
                    beg.setVisibility(View.GONE);
                    break;
                case "费用清单":
                    beg.setVisibility(View.GONE);
                    break;
            }

            return fmMap.get(label);
        }
        Fragment fm = null;
        switch (label) {
            case "病区退药":
                fm = new WardDrugFragment();
                beg.setVisibility(View.GONE);
                break;
            case "病区退费":
                fm = new WardRefundFragment();
                beg.setVisibility(View.VISIBLE);
                break;
            case "退费退药查询":
                fm = new RefWithQueryFragment();
                //fm.setArguments(bundle_s);
                beg.setVisibility(View.VISIBLE);
                break;
            case "日清单":
                fm = new DailyListFragment();
                beg.setVisibility(View.GONE);
                break;
            case "费用清单":
                fm = new ExpenseListFragment();
                beg.setVisibility(View.GONE);
                break;

        }

        if (null != fm) {
            fm.setArguments(bundle);
            fmMap.put(label, fm);
        }
        return fm;
    }

    private void initInfoFragment() {
        fmMap = new HashMap<>();

        AdministrativeManagementFragment infoFragment = new AdministrativeManagementFragment();
        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        infoFragment.setArguments(bundle);
        transaction.replace(R.id.fmPatientInfo, infoFragment);
        transaction.commit();
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
