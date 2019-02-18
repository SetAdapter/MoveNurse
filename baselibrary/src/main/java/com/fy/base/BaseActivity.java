package com.fy.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewStub;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fy.custom.popupwindow.CommonPopupWindow;
import com.fy.entity.PatientsBean;
import com.fy.statusbar.MdStatusBarCompat;
import com.fy.statuslayout.manager.OnRetryListener;
import com.fy.statuslayout.manager.OnShowHideViewListener;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.statuslayout.manager.StatusLayoutManager;
import com.fy.utils.AnimUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.T;
import com.fy.utils.cache.ACache;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import fy.library.com.baselibrary.R;
import io.reactivex.disposables.Disposable;

/**
 * Activity 基类，统一处理activity界面样式，多状态视图切换
 * <p/>
 * Created by fangs on 2017/4/1.
 */
public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener,
        AdapterView.OnItemClickListener {

    protected AppCompatActivity mContext;
    protected Unbinder unbinder;
    protected Disposable disposable;

    /**
     * 使用默认头部布局
     */
    protected static final int USE_SON_LAYOUT = 0;
    protected ACache mCache;
    protected StatusLayoutManager slManager;
    protected LinearLayout llTitle;
    protected TextView tvTitle;
    protected ImageView imgDropDown;
    protected TextView tvBack;
    protected TextView tvClose;
    protected TextView tvMenu;
    protected TextView tvComplete;
    protected TextView tv_huanzhe;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        if (getContentView() != 0) {
            setContentView(R.layout.activity_base_top_bar);

            RootFrameLayout viewContent = (RootFrameLayout) findViewById(R.id.viewContent);
            //将继承 TopBarBaseActivity 的布局解析到 FrameLayout 里面
            initSLManager(viewContent);

            ViewStub vStubTitleBar = (ViewStub) findViewById(R.id.vStubTitleBar);
            if (getHeadView() == USE_SON_LAYOUT) {
                vStubTitleBar.inflate();
                initTitleBar();
            } else if (getHeadView() > USE_SON_LAYOUT) {
                vStubTitleBar.setLayoutResource(getHeadView());
                vStubTitleBar.inflate();
            }
        }
        mCache = ACache.get(this);

        super.onCreate(savedInstanceState);

        unbinder = ButterKnife.bind(this, this);

        mContext = this;

        init(savedInstanceState);

        setStatusBarType();
    }

    /**
     * 获取自定义 ContentView
     *
     * @return
     */
    protected abstract int getContentView();

    /**
     * 初始化
     *
     * @param savedInstanceState
     */
    protected abstract void init(Bundle savedInstanceState);

    /**
     * 头部布局
     *
     * @return
     */
    protected int getHeadView() {
        return USE_SON_LAYOUT;
    }

    /**
     * 设置状态栏类型
     *
     * @return
     */
    protected void setStatusBarType() {
        MdStatusBarCompat.setOrdinaryToolBar(this, R.color.bule);
    }

    /**
     * 重试
     */
    protected void reTry() {
    }

    /**
     * 设置 不同情况下 界面显示内容
     *
     * @param flag
     */
    protected void setStatusLayout(int flag) {
        switch (flag) {
            case RootFrameLayout.LAYOUT_LOADING_ID:
                slManager.showLoading();
                break;
            case RootFrameLayout.LAYOUT_CONTENT_ID:
                slManager.showContent();
                break;
            case RootFrameLayout.LAYOUT_ERROR_ID:
                slManager.showError();
                break;
            case RootFrameLayout.LAYOUT_NETWORK_ERROR_ID:
                slManager.showNetWorkError();
                break;
            case RootFrameLayout.LAYOUT_EMPTYDATA_ID:
                slManager.showEmptyData();
                break;
        }
    }

    /**
     * 设置 多状态视图 管理器
     *
     * @param viewContent
     */
    protected void initSLManager(RootFrameLayout viewContent) {
        slManager = StatusLayoutManager.newBuilder(this)
                .setRootLayout(viewContent)
                .contentView(getContentView())
                .loadingView(R.layout.activity_loading)
                .errorView(R.layout.activity_error)
                .netWorkErrorView(R.layout.activity_networkerror)
                .emptyDataView(R.layout.activity_emptydata)
                .retryViewId(R.id.tvTry)
                .onShowHideViewListener(new OnShowHideViewListener() {
                    @Override
                    public void onShowView(View view, int id) {
                    }

                    @Override
                    public void onHideView(View view, int id) {
                    }
                }).onRetryListener(new OnRetryListener() {
                    @Override
                    public void onRetry() {
                        reTry();
                    }
                }).build();

        setStatusLayout(RootFrameLayout.LAYOUT_CONTENT_ID);
    }

    /**
     * 初始化标题栏
     */
    protected void initTitleBar() {
        llTitle = (LinearLayout) findViewById(R.id.llTitle);
        tvTitle = (TextView) findViewById(R.id.tvTitle);
        imgDropDown = (ImageView) findViewById(R.id.imgDropDown);
        tvBack = (TextView) findViewById(R.id.tvBack);
        tvClose = (TextView) findViewById(R.id.tvClose);
        tvMenu = (TextView) findViewById(R.id.tvMenu);
        tvComplete = (TextView) findViewById(R.id.tvComplete);
        tv_huanzhe = (TextView) findViewById(R.id.tv_huanzhe);
        tvBack.setOnClickListener(this);
        tvClose.setOnClickListener(this);
        tvMenu.setOnClickListener(this);
        tvComplete.setOnClickListener(this);
        tv_huanzhe.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Bundle bundle = new Bundle();
        int i = view.getId();
        if (i == R.id.tvBack) {
           setJumpValue(bundle);//点击返回跳转
        } else if (i == R.id.tvMenu) {//二维码
            T.showLong(R.string.No_Open);
//            JumpUtils.jump(mContext, "com.example.hjy.movenurse.scan.ScanCaptureActivity", null, 55667);
        } else if (i == R.id.tvClose) {
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.MainActivity", null);
        } else if (i == R.id.tv_huanzhe) {
            setJumpValue(bundle);//点击小人 跳转
        } else if (i == R.id.llTitle) {//显示 头部标题 弹窗
            showMenuPopup(view);
        } else if (i == R.id.tv1) {
            popupWindow.dismiss();
            if (null != bean) bundle.putSerializable("PatientsBean", bean);
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.activity.PatientDataItemActivity", bundle);//患者资料详情
        } else if (i == R.id.tv2) {
            popupWindow.dismiss();
            if (null != bean) bundle.putSerializable("PatientsBean", bean);
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.doctor.manage.DoctorManageActivity", bundle);//医嘱管理
        } else if (i == R.id.tv3) {
            popupWindow.dismiss();
            if (null != bean) mCache.put("PatName", bean);//TODO 缓存前 需要 更新这个实体类
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.nursing.FirstNurseOrderActivity", bundle);//护理评估
        } else if (i == R.id.tv5) {
            popupWindow.dismiss();
            //退药退费
            if (null != bean) bundle.putSerializable("PatientsBean", bean);
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.administrative.adminmanage.AdministrativeManagementActivity", bundle);
        }
    }

    private void setJumpValue(Bundle bundle){
        String s = tvTitle.getText().toString();
        if (s.equals("患者详情信息")) {
            bundle.putString("DataActivity", "activity.PatientDataItemActivity");
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.activity.PatientDataActivity", bundle);//患者列表
        } else if (s.equals("医嘱管理 ")) {
            bundle.putString("DataActivity","doctor.manage.DoctorManageActivity");
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.activity.PatientDataActivity", bundle);//医嘱管理
        } else if (s.equals("护理评估 ")) {
            bundle.putString("DataActivity", "nursing.FirstNurseOrderActivity");
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.activity.PatientDataActivity", bundle);//护理评估
        } else if (s.equals("退费退药 ")){
            bundle.putString("DataActivity", "adminmanage.AdministrativeManagementActivity");
            JumpUtils.jump(mContext, "com.example.hjy.movenurse.activity.PatientDataActivity", bundle);//退费退药
        }else {
            JumpUtils.exitActivity(this);
        }
    }

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (null != unbinder) {
            unbinder.unbind();
        }

        if (null != disposable) {
            disposable.dispose();
        }
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            JumpUtils.exitActivity(this);
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 返回对象本身的context
     *
     * @return
     */
    public Context ctx() {
        return this;
    }

    /**
     * 返回对象本身 Activity
     *
     * @return
     */
    public BaseActivity act() {
        return this;
    }

    /**
     * 设置activity标题
     *
     * @param resouseId
     */
    protected void setActTitle(int resouseId) {
        if (null != tvTitle) {
            tvTitle.setText(resouseId);
        }
    }

    /**
     * 设置activity 标题栏 左边按钮 文本
     *
     * @param resouseId
     */
    protected void setActBack(int resouseId) {
        if (null != tvBack) {
            tvBack.setText(resouseId);
        }
    }

    /**
     * 隐藏activity 标题栏 左边按钮
     */
    protected void hideBack() {
        if (null != tvBack) {
            tvBack.setVisibility(View.INVISIBLE);
        }
    }

    /**
     * 设置activity 标题栏 右边按钮 文本
     *
     * @param resouseId
     */
    protected void setActMenu(int resouseId) {
        if (null != tvMenu) {
            tvMenu.setText(resouseId);
        }
    }

    /**
     * 隐藏activity 标题栏 右边按钮
     */
    protected void hideMenu() {
        if (null != tvMenu) {
            tvMenu.setVisibility(View.INVISIBLE);
        }
    }


    private PatientsBean bean;
    private CommonPopupWindow popupWindow;
    private String moduleTag = "";

    public void setBean(PatientsBean bean) {
        this.bean = bean;
    }

    public void setModuleTag(String moduleTag) {
        this.moduleTag = moduleTag;
    }

    //头部标题点击事件 显示弹窗
    protected void showMenuPopup(View view) {
        AnimUtils.doArrowAnim(imgDropDown, false);
        popupWindow = new CommonPopupWindow
                .Builder(this)
                .setView(R.layout.popup_fun_menu)
                .setWidthAndHeight(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
                .setAnimationStyle(R.style.AnimTop)
                .setViewOnclickListener((view12, layoutResId) -> {
                    TextView tv1 = (TextView) view12.findViewById(R.id.tv1);
                    TextView tv2 = (TextView) view12.findViewById(R.id.tv2);
                    TextView tv3 = (TextView) view12.findViewById(R.id.tv3);
                    TextView tv5 = (TextView) view12.findViewById(R.id.tv5);

                    switch (moduleTag) {
                        case "患者资料":
                            tv1.setVisibility(View.GONE);
                            break;
                        case "医嘱管理":
                            tv2.setVisibility(View.GONE);
                            break;
                        case "护理评估":
                            tv3.setVisibility(View.GONE);
                            break;
                        case "退药退费":
                            tv5.setVisibility(View.GONE);
                            break;
                    }
                    tv1.setOnClickListener(act());
                    tv2.setOnClickListener(act());
                    tv3.setOnClickListener(act());
                    tv5.setOnClickListener(act());
                }).setListener(() -> AnimUtils.doArrowAnim(imgDropDown, true))
                .create();

        showPopu(view);
    }

    private void showPopu(View view) {
        int[] positions = new int[2];
        //得到button的左上角坐标
        imgDropDown.getLocationOnScreen(positions);
        int xDeviation = positions[0] - (popupWindow.getWidth() - imgDropDown.getWidth()) + 25;

        popupWindow.showAtLocation(findViewById(android.R.id.content), Gravity.NO_GRAVITY,
                xDeviation,
                positions[1] + view.getHeight() - 5);
    }
}
