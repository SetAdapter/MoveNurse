package com.example.hjy.movenurse.doctor.manage;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.DoctorInfoBean;

import java.util.List;

/**
 * 当前医嘱（药物医嘱 one） adapter item adapter
 * Created by fangs on 2017/9/26.
 */
public class CurrentDoctorOneSunAdapter extends RecyclerCommonAdapter<DoctorInfoBean.OrderDetailsBean> {

    public CurrentDoctorOneSunAdapter(AppCompatActivity context, List<DoctorInfoBean.OrderDetailsBean> datas) {
        super(context, R.layout.item_medicine, datas);
    }

    @Override
    public void convert(ViewHolder holder, DoctorInfoBean.OrderDetailsBean detailBean, int position) {

        holder.setText(R.id.tvName1, detailBean.getOrderText());
        holder.setText(R.id.tvChannel1, detailBean.getAdministration());
        holder.setText(R.id.tvUsageDesc1, detailBean.getSpecialUseType());

        holder.setOnClickListener(R.id.tvUsageDesc1 ,v ->
                new WarningDialog.Builder()
                        .setTitle("特殊用法")
                        .setMessage(detailBean.getSpecialUseType())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );
    }
}
