package com.example.hjy.movenurse.prevent;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.LoginBean;
import com.fy.entity.HealthGuidanceBean;
import com.fy.entity.PatientsBean;
import com.fy.entity.SignInformedBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.JumpUtils;
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
 * 坠床知情同意书_编辑
 */

public class PreventTumbleFragment extends BaseFragment {

    @BindView(R.id.tv_Dict_TypeName)
    TextView tv_Dict_TypeName;
    @BindView(R.id.tv_mCreateDate)
    TextView tv_mCreateDate;
    @BindView(R.id.tv_FZ_Nurse)
    TextView tv_FZ_Nurse;
    @BindView(R.id.tv_EXENurseName)
    TextView tv_EXENurseName;
    @BindView(R.id.relation_iv)
    ImageView relation_iv;
    String mCreateDate;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_prevent_tumble;
    }

    @Override
    protected void baseInitView() {
        super.baseInitView();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
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
                JumpUtils.jump(mContext, PreventTumbleActivity.class, null);
                break;
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        getDepartList();
        getDetails();
    }

    private void getDepartList() {
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10003");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts1, true, new NetCallBack<ArrayList<HealthGuidanceBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<HealthGuidanceBean> data) {
                if (null != data) {
                    tv_Dict_TypeName.setText(data.get(0).getDict_TypeName());
                    tv_mCreateDate.setText("签字日期:" + mCreateDate);
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
        params.put("OrderType", "5");
        params.put("UserID", loginBean.getUserID());
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
        if (null!=signInformedBean){
            ImgLoadUtils.loadImage(mContext, signInformedBean.getPatFamilyURL(), relation_iv);
            tv_mCreateDate.setText("签字日期:" + mCreateDate);
            tv_FZ_Nurse.setText("患者与家属关系:" + signInformedBean.getPatFamilyType());
            tv_EXENurseName.setText("宣教护士：" + signInformedBean.getEXENurseName());
        }
    }
}
