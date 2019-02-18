package com.example.hjy.movenurse.newborngeneral;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.NewBornRecordBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gab on 2017/9/18 0018.
 * 新生儿一般护理记录单_预览 adapter
 */

public class NewbornGeneralAdapter extends MultiItemCommonAdapter<NewBornRecordBean> {

    public NewbornGeneralAdapter(AppCompatActivity context, List<NewBornRecordBean> datas) {
        super(context, datas, new MultiItemTypeSupport<NewBornRecordBean>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.newborn_general_content;
            }

            @Override
            public int getItemViewType(int position, NewBornRecordBean NewBornRecordBean) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, NewBornRecordBean bean, int position) {
//        if (position == 0) return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tv_BoxTemperature, bean.getNCCommonly().getBoxTemperature());//温箱
        holder.setText(R.id.VitalSign_T, bean.getVitalSign_T());//
        holder.setText(R.id.tv_VitalSign_PAndHR, bean.getNCCommonly().getVitalSign_PAndHR());//
        holder.setText(R.id.tv_VitalSign_R, bean.getNCCommonly().getVitalSign_R());//
        holder.setText(R.id.tv_VitalSign_BP, bean.getNCCommonly().getVitalSign_BP());//
        holder.setText(R.id.tv_VitalSign_SP02, bean.getNCCommonly().getVitalSign_SP02());//

        holder.setText(R.id.tv_Reaction, bean.getReaction());//
        holder.setText(R.id.tv_Cry, bean.getCry());
        holder.setText(R.id.tv_SuckForce, bean.getSuckForce());//
        holder.setText(R.id.tv_FeedingPatterns, bean.getFeedingPatterns());//

        holder.setText(R.id.tv_OralMucosa, bean.getNCCommonly().getOralMucosa());//
        holder.setText(R.id.tv_Umbilical, bean.getUmbilical());//
        holder.setText(R.id.tv_PerianalSkin, bean.getNCCommonly().getPerianalSkin());//
        holder.setText(R.id.tv_OxygenInhalation, bean.getOxygenInhalation());//

        holder.setText(R.id.tv_OxygenFlowRate, bean.getOxygenFlowRate());//
        holder.setText(R.id.tv_IVT, bean.getNCCommonly().getIVT());//
        holder.setText(R.id.tv_MilkAmount, bean.getNCCommonly().getMilkAmount());//
        holder.setText(R.id.tv_Urination, bean.getUrination());//
        holder.setText(R.id.tv_Stool, bean.getStool());//
        holder.setText(R.id.tv_MBS, bean.getNCCommonly().getMBS());//
        holder.setText(R.id.tv_Phototherapy, bean.getNCCommonly().getPhototherapy());//
        holder.setText(R.id.tv_OtherSituation, bean.getOtherSituation());//
        holder.setText(R.id.tv_ExecutiveNurse, bean.getExecutiveNurse());//
        holder.setOnClickListener(R.id.tv_OtherSituation, v ->
                new WarningDialog.Builder()
                        .setTitle("特殊情况记录")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );

    }
}
