package com.example.hjy.movenurse.wardmanage.modifydepartment;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.BaseFragment;
import com.fy.entity.DepartBean;
import com.fy.entity.LoginBean;
import com.fy.entity.RxBean;
import com.fy.eventbus.RxBus;
import com.fy.recyclerview.DividerParams;
import com.fy.retrofit.Api;
import com.fy.retrofit.NetCallBack;
import com.fy.retrofit.NetRequest;
import com.fy.utils.ResourceUtils;
import com.fy.utils.SpfUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;

/**
 * 科室列表(详细列表)
 * Created by fangs on 2017/9/18.
 */
public class DepartInfoListFragment extends BaseFragment{

    @BindView(R.id.rvDepart)
    RecyclerView rvDepart;
    DepartListAdapter departAdapter;

    @BindView(R.id.rvDepartInfo)
    RecyclerView rvDepartInfo;
    DepartInfoListAdapter adapter;

    @Override
    protected int getContentLayout() {
        return R.layout.fragment_departinfo_list;
    }

    @Override
    protected void baseInitView() {

        initDepartRv();

        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepartInfo.setLayoutManager(gManager);

        adapter = new DepartInfoListAdapter(mContext, new ArrayList<>());
        adapter.setmRv(rvDepartInfo);
        adapter.setListner(() -> {
            int mSelectedPos = adapter.getmSelectedPos();
            if (mSelectedPos != -1){
                DepartBean.DictsBean dictsBean = adapter.getmDatas().get(mSelectedPos);

                TextView tvDepartment = (TextView) getActivity().findViewById(R.id.tvDepartment);
                ResourceUtils.setText(mContext, tvDepartment, R.string.department, dictsBean.getDict_TypeName());

                //修改科室后 保存按钮 不能点击，必须在修改对应的床位后才可以点击
                TextView tvSave = (TextView) getActivity().findViewById(R.id.tvSave);
                tvSave.setEnabled(false);

                SpfUtils.saveStrToSpf(mContext, "In_Dept_ID", dictsBean.getDict_TypeID());//保存选中的 科室id
                RxBus.getInstance().send(new RxBean("DeptID#", dictsBean.getDict_TypeID()));
            } else {
//                T.showLong("请选择床位!!!");
            }
        });
        rvDepartInfo.setAdapter(adapter);
    }

    private void initDepartRv(){
        departAdapter = new DepartListAdapter(mContext, new ArrayList<>());
        departAdapter.setmRv(rvDepart);
        departAdapter.setListener(departBean -> {
            adapter.setmDatas(departBean.getDicts());
            adapter.setmSelectedPos(-1);
            adapter.notifyDataSetChanged();
        });

        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        rvDepart.setLayoutManager(gManager);
        rvDepart.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        rvDepart.setAdapter(departAdapter);

        getDepartList();
    }

    private void getDepartList(){
        Map<String, Object> params = new HashMap<>();
        LoginBean loginBean = (LoginBean) mCache.getAsObject("UserName");
        params.put("DictType", "10009");
        params.put("UserID", loginBean.getUserID());
        params.put("Token", loginBean.getToken());
        NetRequest.getInstens().requestDate(params, Api.getDicts, false, new NetCallBack<ArrayList<DepartBean>>(mContext, R.string.data_loading) {
            @Override
            public void onSuccess(ArrayList<DepartBean> data) {
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
}
