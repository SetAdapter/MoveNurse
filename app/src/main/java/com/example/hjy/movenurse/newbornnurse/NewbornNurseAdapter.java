package com.example.hjy.movenurse.newbornnurse;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.NewBornNurseRecordBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gab on 2017/9/18 0018.
 * 新生儿护理记录单 adapter
 */

public class NewbornNurseAdapter extends MultiItemCommonAdapter<NewBornNurseRecordBean> {

    public NewbornNurseAdapter(AppCompatActivity context, List<NewBornNurseRecordBean> datas) {
        super(context, datas, new MultiItemTypeSupport<NewBornNurseRecordBean>() {
            @Override
            public int getLayoutId(int itemType) {
//                if (itemType == 0) {
//                    return R.layout.newborn_nurse_record;
//                } else {
//                }
                    return R.layout.newborn_nurse_content;
            }

            @Override
            public int getItemViewType(int position, NewBornNurseRecordBean newBornNurseRecordBean) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, NewBornNurseRecordBean bean, int position) {
//        if (position == 0) return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tvTemperature, bean.getVitalSign_T());//温度
        holder.setText(R.id.tv_Reaction, bean.getReaction());//av
        holder.setText(R.id.tv_Cry, bean.getCry());//
        holder.setText(R.id.tv_SuckForce, bean.getSuckForce());//
        holder.setText(R.id.tv_FeedingPatterns, bean.getFeedingPatterns());//
        holder.setText(R.id.tv_FeedType, bean.getNCNursing().getFeedType());//

        holder.setText(R.id.tv_OxygenInhalation, bean.getOxygenInhalation());//
        holder.setText(R.id.tv_OxygenFlowRate, bean.getOxygenFlowRate());
        holder.setText(R.id.tv_SkinColor, bean.getNCNursing().getSkinColor());//
        holder.setText(R.id.tv_Umbilical, bean.getUmbilical());//

        holder.setText(R.id.tv_Vomit, bean.getNCNursing().getVomit());//
        holder.setText(R.id.tv_Stool, bean.getStool());//
        holder.setText(R.id.tv_Urination, bean.getUrination());//
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
