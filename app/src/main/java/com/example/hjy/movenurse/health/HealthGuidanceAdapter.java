package com.example.hjy.movenurse.health;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.HealthGuidancesBean;
import com.fy.recyclerview.DividerParams;

import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 出院健康指导 预览第一层adapter
 */

public class HealthGuidanceAdapter extends RecyclerCommonAdapter<HealthGuidancesBean> {

    public HealthGuidanceAdapter(AppCompatActivity context, List<HealthGuidancesBean> datas) {
        super(context, R.layout.item_health_list, datas);
    }

    @Override
    public void convert(ViewHolder holder, HealthGuidancesBean dictsBean, int position) {
        TextView tvtitle = holder.getView(R.id.tv_title);
        RecyclerView Lv_list = holder.getView(R.id.lv_list);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 1);
        Lv_list.setLayoutManager(gManager);
        Lv_list.addItemDecoration(new DividerParams().setLayoutManager(1).create(mContext));
        HealthListAdapter departAdapter = new HealthListAdapter(mContext, dictsBean.getDicts());
        tvtitle.setText(dictsBean.getDict_TypeName());// 数据对
        Lv_list.setAdapter(departAdapter);
    }
}
