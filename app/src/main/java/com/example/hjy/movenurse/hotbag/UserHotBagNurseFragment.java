package com.example.hjy.movenurse.hotbag;

import android.graphics.Bitmap;
import android.text.TextUtils;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.MyUtils;
import com.example.hjy.movenurse.widget.LinePathView;
import com.example.hjy.movenurse.widget.SignDialog;
import com.fy.base.BaseFragment;
import com.fy.entity.ImagerBean;
import com.fy.entity.LoginBean;
import com.fy.entity.HealthGuidanceBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.SignInformedBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.NetUtils;
import com.fy.utils.T;
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
 * Created by Gab on 2017/10/16 0016.
 * 使用热水袋注意 编辑页面
 */

public class UserHotBagNurseFragment extends BaseFragment {

    private String mCreateDate;
    @BindView(R.id.tv_Dict_TypeName)
    TextView tv_Dict_TypeName;
    @BindView(R.id.tv_mCreateDate)
    TextView tv_mCreateDate;
    @BindView(R.id.save_btn)
    Button save_btn;
    @BindView(R.id.EXENurseName)
    TextView EXENurseName;
    @BindView(R.id.tv_FZ_Nurse)
    EditText tv_FZ_Nurse;
    @BindView(R.id.relation_et)
    ImageView relation_et;
    @BindView(R.id.tv_context)
    TextView tv_context;
    LinePathView mLinePathView;
    String tv_FZ = "";
    String mPath = "";

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_user_hotbag_nurse;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (!TextUtils.isEmpty(patin.getZyDetail().getIn_Date())) {
            long string2long = TimeUtils.timeString2long(patin.getZyDetail().getIn_Date(), "yyyy-MM-dd");
            String data = TimeUtils.Long2DataString(string2long, "yyyy年MM月dd日");
            tv_context.setText("  " + "病人姓名：" + patin.getPatName() + "，" + "性别：" + MyUtils.getSex(patin.getSex()) + "，" +
                    "年龄：" + patin.getAge() + "岁" + "，" + "住院号：" + patin.getPatID() + "，" + "于" + data + "，" +
                    "住科室：" + patin.getZyDetail().getIn_Dept_Name() + "，" + "诊断为：" + patin.getZyDetail().getICD_Name() + "。");
        }
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date curDate = new Date(System.currentTimeMillis());//获取当前时间
        mCreateDate = formatter.format(curDate);
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        EXENurseName.setText("宣教护士:" + loginBean.getUserName());
        tv_mCreateDate.setText("签字日期:" + mCreateDate);

    }

    @OnClick({R.id.relation_et, R.id.save_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                ArrayList<String> objects = new ArrayList<>();
                objects.add(path);
                upload(objects);
                break;
            case R.id.relation_et:
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
                relation_et.setImageBitmap(bitmapFromPath);
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


    @Override
    public void onResume() {
        super.onResume();
        getDepartList();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10002");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts10002, true, new NetCallBack<ArrayList<HealthGuidanceBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<HealthGuidanceBean> data) {
                if (null != data) {
                    tv_Dict_TypeName.setText(data.get(0).getDict_TypeName().replace("\n", " "));
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

    /**
     * 上传保存
     */
    private void getDetails() {
        if (!checkInput()) {
            return;
        }
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        params.put("Token", loginBean.getToken());
        params.put("DateKey", System.currentTimeMillis() + "");
        params.put("OrderType", "2");
        params.put("PA_ID", patin.getPatID());
        params.put("UserID", loginBean.getUserID());
        params.put("PatFamilyURL", mCache.getAsString("imagerBean"));//图片地址
        params.put("PatFamilyType", tv_FZ);//家属关系
        params.put("EXENurseID", loginBean.getUserID());//当前执行护士ID
        params.put("EXENurseName", loginBean.getUserName());//当前执行护士

        NetRequest.getInstens().requestDate(params, Api.SignInformedConsent, false, new NetCallBack<ArrayList<SignInformedBean>>() {
            @Override
            public void onSuccess(ArrayList<SignInformedBean> data) {
                if (null != data) {
                    JumpUtils.exitActivity(mContext);
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

    private boolean checkInput() {
        if (TextUtils.isEmpty(mPath)) {
            T.showLong("患者或者家属签名不能为空!");
            return false;
        }
        tv_FZ = tv_FZ_Nurse.getText().toString().trim();
        if (tv_FZ.isEmpty()) {
            T.showLong("患者与家属关系不能为空!");
            return false;
        }
        return true;
    }
}
