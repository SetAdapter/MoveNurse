package com.example.hjy.movenurse.wardmanage.modifybed;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.BedBean;
import com.fy.entity.LoginBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.ResourceUtils;
import com.fy.utils.SpfUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import io.reactivex.Flowable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * 所有的 空床 展示
 * Created by fangs on 2017/9/18.
 */
public class AllBedFragment extends BaseFragment{

    EditText edit_search;

    @BindView(R.id.rvAllBed)
    RecyclerView rvAllBed;
    AllBedAdapter adapter;

    String diptid = "";

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_all_bed;
    }

    @Override
    protected void baseInitView() {
        initRxEvent();
        initView();

    }

    @Override
    public void onResume() {
        super.onResume();
        if (TextUtils.isEmpty(diptid)){
            BedBean bedBean = (BedBean) getArguments().getSerializable("BedBean");

            if (null != bedBean) {
                BedBean.ZyDetailBean zyDetailBean = bedBean.getZyDetail();
                if (null != zyDetailBean)diptid = zyDetailBean.getIn_Dept_ID();
            }
        }

        adapter.chear();
        getBeds();
    }

    private void initView(){
        GridLayoutManager gManager = new GridLayoutManager(mContext, 6);
        adapter = new AllBedAdapter(mContext, new ArrayList<>());
        adapter.setmRv(rvAllBed);
        adapter.setListner(() -> {
            int mSelectedPos = adapter.getmSelectedPos();
            String stutes = SpfUtils.getSpfSaveStr(mContext, "BedStatus");//病床状态

            TextView tvSave = (TextView) getActivity().findViewById(R.id.tvSave);
            if (mSelectedPos != -1) {
                BedBean bedBean = adapter.getmDatas().get(mSelectedPos);
                BedBean.ZyDetailBean zyDetailBean = bedBean.getZyDetail();
                if (null != zyDetailBean) {

                    if (stutes.equals("3")) {
                        TextView tvBed = (TextView) getActivity().findViewById(R.id.tvSelectPackBed);
                        ResourceUtils.setText(mContext, tvBed, R.string.packBed, zyDetailBean.getIn_Bed());
                        tvSave.setEnabled(true);
                        SpfUtils.saveStrToSpf(mContext, "In_Bed", zyDetailBean.getIn_Bed());//床位
                    } else {
                        TextView tvBed = (TextView) getActivity().findViewById(R.id.tvBed);
                        ResourceUtils.setText(mContext, tvBed, R.string.bed, zyDetailBean.getIn_Bed());
                        //选择床位后 保存按钮 可以点击
                        tvSave.setEnabled(true);
                    }

                    SpfUtils.saveStrToSpf(mContext, "In_Bed", zyDetailBean.getIn_Bed());//床位
                    SpfUtils.saveStrToSpf(mContext, "In_AreaID", zyDetailBean.getIn_AreaID());//新病区
                }
            } else {
                if (stutes.equals("3")) {
                    TextView tvBed = (TextView) getActivity().findViewById(R.id.tvSelectPackBed);
                    ResourceUtils.setText(mContext, tvBed, R.string.packBed, "");

                    SpfUtils.saveStrToSpf(mContext, "In_Bed", "");//床位
                    tvSave.setEnabled(false);
                }
            }
        });
        rvAllBed.setLayoutManager(gManager);
        rvAllBed.setAdapter(adapter);


//        edit_search = (EditText) getActivity().findViewById(R.id.edit_search);
//        edit_search.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after){}
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//                adapter.chear();
//                adapter.getFilter().filter(s);
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {}
//        });
    }

    /**  2.11.	获取病房管理信息 */
    private void getBeds() {
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        Map<String, Object> params = new HashMap<>();
        params.put("UserID", loginBean.getUserID());
        params.put("DeptID", diptid);
        params.put("Fiter", "");
        params.put("Token", loginBean.getToken());
        params.put("OptionType", "2");
        NetRequest.getInstens().requestDate(params, Api.GetBeds, false,
                new NetCallBack<ArrayList<BedBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<BedBean> bean) {
                if (null != bean) {
                    adapter.setmDatas(bean);
                    adapter.notifyDataSetChanged();
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

    protected void initRxEvent() {
        Flowable<RxBean> f1 = RxBus.getInstance().register(RxBean.class);
        disposable = f1.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(event -> {
                    if (event.getSendAction().equals("DeptID#")) {
                        diptid = event.getContent();
                    }
                });
    }
}
