package com.example.hjy.movenurse.nursing.recordsheet;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.NursingRecordBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * 护理记录单 adapter
 * Created by fangs on 2017/9/12.
 */
public class NursingRecordAdapter extends MultiItemCommonAdapter<NursingRecordBean> {

    public NursingRecordAdapter(AppCompatActivity context, List<NursingRecordBean> datas) {
        super(context, datas, new MultiItemTypeSupport<NursingRecordBean>(){
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_record_content;
            }

            @Override
            public int getItemViewType(int position, NursingRecordBean integer) {
                return position;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, NursingRecordBean bean, int position) {
//        if (position == 0)return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tvTemperature, bean.getVitalSign_T());//温度
        holder.setText(R.id.tvHR, bean.getVitalSign_PAndHR());//HR
        holder.setText(R.id.tvR, bean.getVitalSign_R());//R
        holder.setText(R.id.tvBP, bean.getVitalSign_BP());//BP
        holder.setText(R.id.tvConsciousness, bean.getConsciousness());//意识

        holder.setText(R.id.tvInContent, bean.getIn_Content());//入  内容
        holder.setText(R.id.tvInNum, bean.getIn_Amount());//入  量
        holder.setText(R.id.tvOutContent, bean.getOut_Content());//出  内容
        holder.setText(R.id.tvOutNum, bean.getOut_Amount());//出  量

        holder.setText(R.id.tvLeftDiameter, bean.getLeftPD());//瞳孔左侧 直径
        holder.setText(R.id.tvLeftReflex, bean.getLeftIrisCR());//瞳孔左侧   反射
        holder.setText(R.id.tvRightDiameter, bean.getRightPD());//瞳孔右侧   直径
        holder.setText(R.id.tvRightReflex, bean.getRightIrisCR());//瞳孔右侧   反射
//        holder.setOnClickListener(R.id.tvLeftReflex, v -> new WarningDialog.Builder()
//                .setTitle("瞳孔左侧反射")
//                .setMessage(bean.getLeftIrisCR())
//                .create()
//                .show(mContext.getSupportFragmentManager(), "WarningDialog"));
//        holder.setOnClickListener(R.id.tvRightReflex, v -> new WarningDialog.Builder()
//                .setTitle("瞳孔右侧反射")
//                .setMessage(bean.getRightIrisCR())
//                .create()
//                .show(mContext.getSupportFragmentManager(), "WarningDialog"));
        holder.setText(R.id.tvAutograph, bean.getExecutiveNurse());//护士签名
        holder.setText(R.id.tvMeasures, bean.getOtherSituation());//观察 和 措施
        holder.setOnClickListener(R.id.tvMeasures, v ->
                new WarningDialog.Builder()
                        .setTitle("病情观察及措施")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );

    }

}
