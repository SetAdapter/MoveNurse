package com.example.hjy.movenurse.upload;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseActivity;
import com.fy.base.BaseBean;
import com.fy.custom.dialog.DialogLoad;
import com.fy.entity.LoginBean;
import com.fy.entity.RxBean;
import com.fy.entity.UploadBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.statuslayout.manager.RootFrameLayout;
import com.fy.utils.L;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;

/**
 * 上传列表 Activity
 * <p/>Created by fangs on 2017/10/11.
 */
public class UpLoadListActivity extends BaseActivity {

    @BindView(R.id.rvUploadList)
    RecyclerView rvUploadList;
    UpLoadListAdapter adapter;

    int count = 1;
    String token = "";
    DialogLoad dialog;

    @Override
    protected int getContentView() {
        return R.layout.activity_upload_list;
    }

    @Override
    protected void init(Bundle savedInstanceState) {
        tvTitle.setText("上传列表");
        initRv();
    }

    private void initRv() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        String deptID = loginBean.getDeptID();
        token = loginBean.getToken();

        adapter = new UpLoadListAdapter(mContext, UpLoadUtils.getData(mCache));
        count = adapter.getItemCount();
        adapter.setListner(uploadBean -> {
            Map<String, Object> param = uploadBean.getRequest();
            param.remove("Token");
            param.remove("DateKey");
            param.put("Token", token);
            param.put("DateKey", System.currentTimeMillis() + "");

            NetRequest.getInstens()
                    .requestDate(param, uploadBean.getApi(), false,
                            new NetCallBack<ArrayList<BaseBean>>(mContext, R.string.upLoading) {
                                @Override
                                protected void onSuccess(ArrayList<BaseBean> baseBeen) {
                                    String key = null;
                                    if (uploadBean.getApi().equals("PSEvaluation")) {
                                        key = loginBean.getUserID() + uploadBean.getPatID() +
                                                uploadBean.getTime() + "&" + uploadBean.getApi();
                                    } else {
                                        key = loginBean.getUserID() + uploadBean.getPatID() + "&" + uploadBean.getApi();
                                    }
                                    mCache.remove(key);
                                    adapter.setmDatas(UpLoadUtils.getData(mCache));
                                    adapter.notifyDataSetChanged();
                                    T.showLong("上传成功");
                                }

                                @Override
                                protected void updataLayout(int flag) {
                                }

                                @Override
                                protected void onFlaiCacheRequest() {
                                }
                            });
        });

        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvUploadList.setLayoutManager(gManager);
        rvUploadList.setAdapter(adapter);
    }

    @OnClick({R.id.tvUpload})
    @Override
    public void onClick(View view) {
        super.onClick(view);
        switch (view.getId()) {
            case R.id.tvUpload://一键上传
                List<UploadBean> dataList = adapter.getmDatas();
                if (dataList.size() == 0) {
                    T.showLong("没有未上传的内容");
                    return;
                }
                int msg = adapter.getItemCount() / count;
                dialog = new DialogLoad.Builder()
                        .setMsg("正在上传 " + msg + " %")
                        .create();
                dialog.show(mContext.getSupportFragmentManager(), "DialogLoad");

                for (UploadBean bean : dataList) {
                    try {
                        Thread.sleep(200);
                        saveCont(bean);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                break;
        }
    }

    String DateKey;

    public void setKey(String key) {
        this.DateKey = key;
    }

    private void saveCont(UploadBean bean) {

        Map<String, Object> param = bean.getRequest();
        param.remove("Token");
        param.remove("DateKey");
        param.put("Token", token);
        param.put("DateKey", System.currentTimeMillis() + "");

        NetRequest.getInstens().requestDate(param, bean.getApi(), false,
                new NetCallBack<ArrayList<BaseBean>>() {
                    @Override
                    protected void onSuccess(ArrayList<BaseBean> baseBeen) {

                        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
                        String key = null;
                        if (bean.getApi().equals("PSEvaluation")) {
                            key = loginBean.getUserID() + bean.getPatID() + bean.getTime() + "&" + bean.getApi();
                        } else {
                            key = loginBean.getUserID() + bean.getPatID() + "&" + bean.getApi();
                        }

                        mCache.remove(key);
                        List<UploadBean> data = UpLoadUtils.getData(mCache);
                        adapter.setmDatas(data);
                        adapter.notifyDataSetChanged();

                        if (data.size() == 0 && null != dialog) {
                            mCache.remove(loginBean.getUserID() + "UP_LOAD_LIST_KEY");
                            RxBus.getInstance().send(new RxBean("DialogLoad#", "上传成功"));
                            dialog.dismiss();
                            T.showLong("上传成功");
                        } else {
                            int msg = adapter.getItemCount() / count;
                            RxBus.getInstance().send(new RxBean("DialogLoad#", "正在上传 " + msg + " %"));
                        }
                    }

                    @Override
                    protected void updataLayout(int flag) {
                        switch (flag) {
                            case RootFrameLayout.REQUEST_FAIL:
                                if (null != dialog) dialog.dismiss();
                                break;
                            case RootFrameLayout.LAYOUT_NETWORK_ERROR_ID:
                                if (null != dialog) dialog.dismiss();
                                break;
                        }
                    }

                    @Override
                    protected void onFlaiCacheRequest() {
                    }
                });
    }
}
