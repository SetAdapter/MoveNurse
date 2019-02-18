package com.example.hjy.movenurse.health;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.HealthGuidancesBean;
import com.fy.recyclerview.DividerParams;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 *  第一层adapter
 */

public class HealthGuidanceNurseAdapter extends RecyclerCommonAdapter<HealthGuidancesBean> {

    boolean isChecked;
    private SparseArray<HealthGuidanceListAdapter> mAdpters;

    public HealthGuidanceNurseAdapter(AppCompatActivity context, List<HealthGuidancesBean> datas) {
        super(context, R.layout.item_health_list, datas);
        mAdpters = new SparseArray<>();
    }

    @Override
    public void convert(ViewHolder holder, HealthGuidancesBean dictsBean, int position) {
        TextView tvtitle = holder.getView(R.id.tv_title);
        RecyclerView Lv_list = holder.getView(R.id.lv_list);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        Lv_list.setLayoutManager(gManager);
        Lv_list.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        HealthGuidanceListAdapter departAdapter = new HealthGuidanceListAdapter(mContext, new ArrayList<>());
        departAdapter.setmDatas(dictsBean.getDicts());

        if (isChecked){
            departAdapter.setIsAllSelect(isChecked);
        }

        Lv_list.setAdapter(departAdapter);
        tvtitle.setText(dictsBean.getDict_TypeName());// 数据对

        mAdpters.put(position, departAdapter);
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public SparseArray<HealthGuidanceListAdapter> getAdpters() {
        return mAdpters;
    }
}
