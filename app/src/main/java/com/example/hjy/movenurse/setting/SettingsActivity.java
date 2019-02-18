package com.example.hjy.movenurse.setting;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.entity.LoginBean;
import com.fy.img.picker.ImagePicker;
import com.fy.img.picker.bean.ImageFolder;
import com.fy.img.picker.bean.ImageItem;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statusbar.MdStatusBarCompat;
import com.fy.utils.ConstantUtils;
import com.fy.utils.JumpUtils;
import com.fy.utils.L;
import com.fy.utils.NetUtils;
import com.fy.utils.SpfUtils;
import com.fy.utils.T;
import com.fy.utils.Validator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * 设置 服务器地址
 * <p/>Created by fangs on 2017/10/12.
 */
public class SettingsActivity extends BaseActivity {

    @BindView(R.id.edtServiceAddress)
    EditText edtServiceAddress;

    @BindView(R.id.edtPortNum)
    EditText edtPortNum;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        setContentView(R.layout.activity_settings);
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
        String ipAddress = SpfUtils.getSpfSaveStr(mContext, "ipAddress");
        String PortNum = SpfUtils.getSpfSaveStr(mContext, "PortNum");

        if (!TextUtils.isEmpty(PortNum)){
            edtPortNum.setText(PortNum);
        } else {
            edtPortNum.setText("8099");
        }

        if (!TextUtils.isEmpty(ipAddress)){
            edtServiceAddress.setText(ipAddress);
        } else {
            edtServiceAddress.setText("192.168.100.251");
        }
    }

    @OnClick({R.id.btnSave})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.btnSave://保存按钮
                String serviceAddress = edtServiceAddress.getText().toString().trim();
                String portNum = edtPortNum.getText().toString().trim();
                if (!Validator.isIPAddr(serviceAddress)) {
                    T.showLong("请输入正确的服务器地址!!!");
                    return;
                }

//                "http://192.168.100.251:8099/"
                ConstantUtils.custom_Url = "http://" + serviceAddress + ":" + portNum + "/";
                SpfUtils.saveStrToSpf(mContext, "ServiceAddress", "http://" + serviceAddress + ":" + portNum + "/");

                SpfUtils.saveStrToSpf(mContext, "ipAddress", serviceAddress);
                SpfUtils.saveStrToSpf(mContext, "PortNum", portNum);

                JumpUtils.exitActivity(mContext);
//                JumpUtils.jump(this, "com.fy.img.picker.ImgPickerActivity", null, ImagePicker.Picture_Selection);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK && requestCode == ImagePicker.Picture_Selection){
            Bundle bundle = data.getExtras();
            ImageFolder imgFolder = (ImageFolder) bundle.getSerializable("ImageFolder");
            List<ImageItem> imgList = imgFolder.images;

            List<String> lists = new ArrayList<>();
            for (ImageItem img : imgList){
                L.e("aaa", img.getPath());
                lists.add(img.getPath());
            }

            upload(lists);
        }
    }

    private void upload(List<String> lists){
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("fileList", NetUtils.filesToMultipartBodyParts(lists));
        params.put("Token", "123456789");
        params.put("UserID", loginBean.getUserID());
        NetRequest.getInstens().requestDate(params, Api.uploadPostFile, false,
                new NetCallBack<ArrayList<LoginBean>>(this, R.string.user_login) {
            @Override
            public void onSuccess(ArrayList<LoginBean> bean) {

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
