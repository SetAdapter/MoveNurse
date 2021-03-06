package com.example.hjy.movenurse.restraint;

import android.graphics.Bitmap;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.MyUtils;
import com.example.hjy.movenurse.widget.LinePathView;
import com.example.hjy.movenurse.widget.SignDialog;
import com.fy.base.BaseFragment;
import com.fy.entity.ImagerBean;
import com.fy.entity.LoginBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.RestraintStrapBean;
import com.fy.entity.SignInformedBean;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.NetUtils;
import com.fy.utils.T;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/10/17 0017.
 * 约束带编辑
 */

public class RestraintStrapNurseFragment extends BaseFragment {

    @BindView(R.id.tv_mCreateDate)
    TextView tv_mCreateDate;
    @BindView(R.id.tv_conttel)
    TextView tv_conttel;
    @BindView(R.id.relation_iv)
    ImageView relation_iv;
    @BindView(R.id.cb_health)
    CheckBox cb_health;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.EXENurseName)
    TextView EXENurseName;
    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    RestraintStrapNurseAdapter departAdapter;
    LinePathView mLinePathView;
    String tv_FZ = "";
    String remark = "";
    String mPath = "";
    StringBuffer Nodes = new StringBuffer();
    private String mCreateDate;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_restraint_strap_nurse;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        initDepartRv();
        EventBus.getDefault().register(this);
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        EXENurseName.setText("宣教护士:" + loginBean.getUserName());
        tv_mCreateDate.setText("签字日期:" + mCreateDate);
        tv_conttel.setText("患者亲属电话:  " + patin.getContTel());
        cb_health.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                departAdapter.setChecked(isChecked);
                departAdapter.notifyDataSetChanged();
            }
        });
    }

    @OnClick({R.id.relation_iv, R.id.save_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                ArrayList<String> objects = new ArrayList<>();
                objects.add(path);
                upload(objects);
                break;
            case R.id.relation_iv:
                GetDialogOne();
                break;
        }
    }

    /**
     * dialog框
     */
    private void GetDialogOne() {
        final SignDialog signDialog = new SignDialog(getActivity());
        signDialog.setOnDoodleListener(new SignDialog.OnDoodleListener() {
            @Override
            public void OnConfrim(String path) {
                //做一个请求 将图片上传
                mLinePathView = new LinePathView(mContext);
                Bitmap bitmapFromPath = MyUtils.getBitmapFromPath(path);
                relation_iv.setImageBitmap(bitmapFromPath);
                mPath = path;
            }
        });
        signDialog.show();
        Window dialogWindow = signDialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        dialogWindow.setGravity(Gravity.BOTTOM);
        WindowManager windowManager = dialogWindow.getWindowManager();
        Display d = windowManager.getDefaultDisplay(); // 获取屏幕宽、高用
        lp.height = (int) (d.getHeight() * 0.6); // 高度设置为屏幕
        lp.width = d.getWidth(); // 宽度设置为屏幕的
        dialogWindow.setAttributes(lp);
    }


    private void initDepartRv() {
        departAdapter = new RestraintStrapNurseAdapter(mContext, new ArrayList<>());
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(departAdapter);
        getDepartList();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10004");
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
        NetRequest.getInstens().requestDate(params, Api.getDicts10004, true, new NetCallBack<ArrayList<RestraintStrapBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<RestraintStrapBean> data) {
                if (null != data) {
                    departAdapter.setmDatas(data);
                    departAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void updataLayout(int flag) {
            }

            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    /**
     * 图片上传
     *
     * @param lists
     */
    private void upload(List<String> lists) {
        Map<String, Object> params = new HashMap<>();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("fileList", NetUtils.filesToMultipartBodyParts(lists));
        params.put("Token", loginBean.getToken());
        params.put("PatID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("UploadType", "1");
        NetRequest.getInstens().requestDate(params, Api.uploadPostFile, false, new NetCallBack<ArrayList<ImagerBean>>(mContext, R.string.upLoading) {
            @Override
            public void onSuccess(ArrayList<ImagerBean> bean) {
                if (null == bean) {
                    getActivity().finish();
                }
                ImagerBean imagerBean = bean.get(0);
                mCache.put("imagerBean", imagerBean.getFileUrl());
                getDetails();
            }

            @Override
            public void updataLayout(int flag) {
            }

            @Override
            protected void onFlaiCacheRequest() {
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void getData(RestraintsEvent restraints) {
        remark = restraints.getRemark();
    }

    /**
     * 上传保存
     */
    private void getDetails() {
        if (!checkInput()){
            return;
        }
        List<RestraintStrapBean> Health = getSelectData();
        if (Nodes.toString().equals("")){
            T.showLong("选项不能为空!");
            return;
        }
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        params.put("Token", loginBean.getToken());
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "4");
        params.put("UserID", loginBean.getUserID());
        params.put("PA_ID", patin.getPatID());
        params.put("PatFamilyURL", mCache.getAsString("imagerBean"));//图片地址
        params.put("PatFamilyType", tv_FZ);//家属关系
        params.put("EXENurseID", loginBean.getUserID());//当前执行护士ID
        params.put("EXENurseName", loginBean.getUserName());//当前执行护士
        params.put("Nodes", Nodes.length() > 0 ? Nodes.toString().substring(0, Nodes.length()) : "");//子节点
        params.put("Remark", remark);//备注
        NetRequest.getInstens().requestDate(params, Api.SignInformedConsent, false, new NetCallBack<ArrayList<SignInformedBean>>() {
            @Override
            public void onSuccess(ArrayList<SignInformedBean> data) {
                if (null != data) {
                    EventBus.getDefault().post(new Restraint(Health));
                    getActivity().finish();
                    tv_mCreateDate.setText("签字日期:" + mCreateDate);
                    T.showShort("保存成功");
                }
            }

            @Override
            public void updataLayout(int flag) {
            }

            @Override
            protected void onFlaiCacheRequest() {

            }
        });
    }

    //遍历内层的适配器
    private List<RestraintStrapBean> getSelectData() {
        List<RestraintStrapBean> mDatas = new ArrayList<>();

        SparseArray<RestraintStrapListAdapter> data = departAdapter.getAdpters();//得到所有的内层 适配器对象
        for (int i = 0; i < data.size(); i++) {
            List<RestraintStrapBean.DictsBean> DictsList = new ArrayList<>();//保存 内层选中的数据

            List<RestraintStrapBean> dataOne = departAdapter.getmDatas();//外层数据源
            RestraintStrapBean RestraintStrapBeanOne = dataOne.get(i);

            RestraintStrapListAdapter adapter = data.valueAt(i);//遍历的 内层的 适配器对象
            SparseBooleanArray selected = adapter.getmSelectedPositions();//得到 当前循环的  内层适配器对象的 选中条目集合

            for (int j = 0; j < selected.size(); j++) {//遍历内层适配器的 多选数据 集合
                if (selected.valueAt(j)) {
                    List<RestraintStrapBean.DictsBean> dictsBeans = adapter.getmDatas();

                    String context = dictsBeans.get(selected.keyAt(j)).getDict_TypeName();
                    RestraintStrapBean.DictsBean dictsBean = new RestraintStrapBean.DictsBean();
                    dictsBean.setDict_TypeName(context);
                    DictsList.add(dictsBean);
                    Nodes.append(dictsBeans.get(selected.keyAt(j)).getDict_TypeID()).append(";");
                }
            }

            if (DictsList.size() > 0) {
                RestraintStrapBean RestraintStrapBean = new RestraintStrapBean();
                RestraintStrapBean.setDict_TypeName(RestraintStrapBeanOne.getDict_TypeName());
                RestraintStrapBean.setDicts(DictsList);
                mDatas.add(RestraintStrapBean);
            }
        }
        return mDatas;
    }
    private boolean checkInput() {
        if (TextUtils.isEmpty(mPath)){
            T.showLong("患者或者家属签名不能为空!");
            return false;
        }
        tv_FZ = tv_FZ_Nurse.getText().toString().trim();
        if (tv_FZ.isEmpty()){
            T.showLong("患者与家属关系不能为空!");
            return false;
        }
        return true;
    }
}
