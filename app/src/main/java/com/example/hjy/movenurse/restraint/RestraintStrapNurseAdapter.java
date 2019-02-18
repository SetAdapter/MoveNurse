package com.example.hjy.movenurse.restraint;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.RestraintStrapBean;
import com.fy.recyclerview.DividerParams;

import java.util.List;

/**
 * Created by Gab on 2017/10/17 0017.
 * 约束带adapter
 */

public class RestraintStrapNurseAdapter extends RecyclerCommonAdapter<RestraintStrapBean> {

    boolean isChecked;
    private SparseArray<RestraintStrapListAdapter> mAdpters;

    public RestraintStrapNurseAdapter(AppCompatActivity context, List<RestraintStrapBean> datas) {
        super(context, R.layout.item_strap_list, datas);
        mAdpters = new SparseArray<>();
    }

    @Override
    public void convert(ViewHolder holder, RestraintStrapBean dictsBean, int position) {
        TextView tvtitle = holder.getView(R.id.tv_title);
        RecyclerView Lv_list = holder.getView(R.id.lv_list);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        Lv_list.setLayoutManager(gManager);
        Lv_list.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        RestraintStrapListAdapter departAdapter = new RestraintStrapListAdapter(mContext, dictsBean.getDicts());

        if (position == 3){
            departAdapter.setShowEdit(true);
        }

        if (isChecked) {
            departAdapter.setIsAllSelect(isChecked);
        }

        Lv_list.setAdapter(departAdapter);
        tvtitle.setText(dictsBean.getDict_TypeName());// 数据对
        mAdpters.put(position, departAdapter);
    }

    public void setChecked(Boolean checked) {
        isChecked = checked;
    }

    public SparseArray<RestraintStrapListAdapter> getAdpters() {
        return mAdpters;
    }
}
