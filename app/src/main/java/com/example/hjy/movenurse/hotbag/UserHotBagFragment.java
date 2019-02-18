package com.example.hjy.movenurse.hotbag;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.MyUtils;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.HealthGuidanceBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.SignInformedBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
import com.fy.utils.TimeUtils;
import com.fy.utils.imgload.ImgLoadUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by Gab on 2017/10/16 0016.
 * 使用热水袋注意 预览
 */

public class UserHotBagFragment extends BaseFragment {

    @BindView(R.id.tv_Dict_TypeName)
    TextView tv_Dict_TypeName;
    @BindView(R.id.tv_mCreateDate)
    TextView tv_mCreateDate;
    @BindView(R.id.tv_context)
    TextView tv_context;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.tv_EXENurseName)
    TextView tv_EXENurseName;
    @BindView(R.id.relation_iv)
    ImageView relation_iv;
    String mCreateDate;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_user_hotbag;
    }

    @SuppressLint("SetTextI18n")
    @Override
    protected void baseInitView() {
        super.baseInitView();
        getDetails();
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
    }

    @OnClick({R.id.save_btn})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.save_btn:
                JumpUtils.jump(mContext, UserHotBagActivity.class, null);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDepartList();
        getDetails();
    }

    /**
     * 字典查询
     */
    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10002");
        params.put("Token", loginBean.getToken());
        params.put("UserID", loginBean.getUserID());
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
     * 预览
     */
    private void getDetails() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        params.put("Token", loginBean.getToken());
        params.put("DateKey", "");
        params.put("UserID", loginBean.getUserID());
        params.put("OrderType", "2");
        params.put("PA_ID", patin.getPatID());

        NetRequest.getInstens().requestDate(params, Api.SignInformedConsent, false, new NetCallBack<ArrayList<SignInformedBean>>() {
            @Override
            public void onSuccess(ArrayList<SignInformedBean> data) {
                if (null != data) {
                    SignInformedBean signInformedBean = data.get(0);
                    setContent(signInformedBean);
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

    private void setContent(SignInformedBean signInformedBean) {
        if (null != signInformedBean) {
            ImgLoadUtils.loadImage(mContext, signInformedBean.getPatFamilyURL(), relation_iv);
            tv_mCreateDate.setText("签字日期:" + mCreateDate);
            tv_FZ_Nurse.setText("患者与家属关系:" + signInformedBean.getPatFamilyType());
            tv_EXENurseName.setText("宣教护士：" + signInformedBean.getEXENurseName());
        }
    }
}
