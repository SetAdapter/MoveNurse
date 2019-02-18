package com.example.hjy.movenurse.restraint;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.RestraintStrapBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 约束带 预览 第二层adapter
 */

public class RestraintListAdapter extends RecyclerCommonAdapter<RestraintStrapBean.DictsBean> {

    public RestraintListAdapter(AppCompatActivity context, List<RestraintStrapBean.DictsBean> datas) {
        super(context, R.layout.item_list_health, datas);
    }

    @Override
    public void convert(ViewHolder holder, RestraintStrapBean.DictsBean dictsBean, int position) {
        TextView tv_name = holder.getView(R.id.tv_content);
        tv_name.setText(dictsBean.getDict_TypeName());
    }
}
