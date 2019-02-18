package com.example.hjy.movenurse.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.setting.SettingsActivity;
import com.fy.base.BaseActivity;
import com.fy.entity.LoginBean;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.start.activity.StartActivity;
import com.fy.statusbar.MdStatusBarCompat;
import com.fy.utils.ConstantUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.SpfUtils;
import com.fy.utils.T;
import com.fy.utils.TransfmtUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 *  登录
 * Created by Gab on 2017/8/29 0029.
 */
public class LoginActivity extends BaseActivity {

    @BindView(R.id.user_name_edt)
    EditText mUserNameEdt;
    @BindView(R.id.password)
    EditText mPassword;
    @BindView(R.id.cBoxIsSavePass)
    CheckBox cBoxIsSavePass;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.login_activity);
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getContentView() {
        return 0;
    }

    @Override
    protected void setStatusBarType() {
        MdStatusBarCompat.setImageTransparent(this);
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        String UserID = SpfUtils.getSpfSaveStr(mContext, "UserID");
        if (!TextUtils.isEmpty(UserID)){
            mUserNameEdt.setText(UserID);
            mUserNameEdt.setSelection(UserID.length());
            cBoxIsSavePass.setChecked(true);
        }
    }

    /**
     * 登陆请求
     * @param UserID
     * @param PassWd
     */
    private void runLogin(String UserID, String PassWd) {
        String key = System.currentTimeMillis() + "";
        String passMd5 = TransfmtUtils.getMD5(PassWd);
        String sign = TransfmtUtils.getMD5(UserID + key + passMd5);

        Map<String, Object> params = new HashMap<>();
        params.put("UserID", UserID);
        params.put("PassWd", passMd5);
        params.put("sign", sign);
        params.put("key", key);
        NetRequest.getInstens().requestDate(params, Api.login, false, new NetCallBack<ArrayList<LoginBean>>(this, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<LoginBean> bean) {
                if (bean != null && bean.size() > 0) {
                    if (cBoxIsSavePass.isChecked()){//是否保存账号
                        SpfUtils.saveStrToSpf(mContext, "UserID", UserID);
                    } else {
                        SpfUtils.saveStrToSpf(mContext, "UserID", "");
                    }
                    LoginBean loginBean = bean.get(0);
                    //判断是否是 医生登录
                    if (!loginBean.getIsDoc().equals("0")){
                        T.showLong("请使用正确的账号登录系统");
                        return;
                    }


                    ConstantUtils.userId += loginBean.getUserID();
                    mCache.put("UserName", loginBean);
                    mCache.put("token", loginBean.getToken());
                    JumpUtils.exitActivity(mContext);
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

    @OnClick({R.id.login_btn, R.id.btnModifyRequestAddress})
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.login_btn:
                String user_name = mUserNameEdt.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                if (!TextUtils.isEmpty(user_name) && !TextUtils.isEmpty(password)) {
                    runLogin(user_name, password);
                }else {
                    T.showShort("用户名和密码不能为空");
                }
                break;
            case R.id.btnModifyRequestAddress://修改 服务器地址
                JumpUtils.jump(mContext, SettingsActivity.class, null);
                break;
        }
    }

    //保存点击的时间
    private long exitTime = 0;

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if ((System.currentTimeMillis() - exitTime) > 2000) {

                T.showLong(R.string.exit_app);
                exitTime = System.currentTimeMillis();
            } else {
                JumpUtils.exitApp(mContext, StartActivity.class);
            }
            return false;
        }
        return super.onKeyDown(keyCode, event);
    }

    /**
     * 点击空白位置 隐藏软键盘
     */
    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (null != this.getCurrentFocus()) {
            InputMethodManager mInputMethodManager = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
            return mInputMethodManager.hideSoftInputFromWindow(this.getCurrentFocus().getWindowToken(), 0);
        }
        return super.onTouchEvent(event);
    }
}
