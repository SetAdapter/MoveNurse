package com.example.hjy.movenurse.health;

import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.HealthGuidancesBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 出院健康指导 预览 第二次adapter
 */

public class HealthListAdapter extends RecyclerCommonAdapter<HealthGuidancesBean.DictsBean> {

    public HealthListAdapter(AppCompatActivity context, List<HealthGuidancesBean.DictsBean> adatas) {
        super(context, R.layout.item_list_health, adatas);
    }

    @Override
    public void convert(ViewHolder holder, HealthGuidancesBean.DictsBean dictsBean, int position) {
        TextView tv_name = holder.getView(R.id.tv_content);
        tv_name.setText(dictsBean.getDict_TypeName());
    }
}
