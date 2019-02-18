package com.example.hjy.movenurse.salvage;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.EmergencyRecordBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gab on 2017/9/18 0018.
 * 急诊抢救记录单 adapter
 */

public class EmergencySalvageAdapter extends MultiItemCommonAdapter<EmergencyRecordBean> {

    public EmergencySalvageAdapter(AppCompatActivity context, List<EmergencyRecordBean> datas) {
        super(context, datas, new MultiItemTypeSupport<EmergencyRecordBean>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.emergency_salvage_content;
            }

            @Override
            public int getItemViewType(int position, EmergencyRecordBean emergencyRecordBean) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, EmergencyRecordBean bean, int position) {
//        if (position == 0) return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tv_VitalSign, bean.getVitalSign_T());//
        holder.setText(R.id.tv_VitalSign_PAndHR, bean.getVitalSign_PAndHR());//
        holder.setText(R.id.tv_VitalSign_R, bean.getVitalSign_R());//
        holder.setText(R.id.tv_VitalSign_BP, bean.getVitalSign_BP());//
        holder.setText(R.id.tv_Consciousness, bean.getConsciousness());//

        holder.setText(R.id.tv_In_Content, bean.getIn_Content());//
        holder.setText(R.id.tv_In_Amount, bean.getIn_Amount());
        holder.setText(R.id.tv_Out_Content, bean.getOut_Content());//
        holder.setText(R.id.tv_Out_Amount, bean.getOut_Amount());//

        holder.setText(R.id.tv_LeftPD, bean.getLeftPD());//
        holder.setText(R.id.tv_LeftIrisCR, bean.getLeftIrisCR());//
        holder.setText(R.id.tv_RightPD, bean.getRightPD());//
        holder.setText(R.id.tv_RightIrisCR, bean.getRightIrisCR());//

        holder.setText(R.id.tv_OtherSituation, bean.getOtherSituation());//
        holder.setText(R.id.tv_QualityControlNurse, bean.getExecutiveNurse());//
        holder.setOnClickListener(R.id.tv_OtherSituation, v ->
                new WarningDialog.Builder()
                        .setTitle("病情观察及措施")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );

    }
}
