package com.example.hjy.movenurse.postpartum;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.PostpartumBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gab on 2017/9/18 0018.
 * 产后观察记录单_adapter
 */

public class PostpartumObserveRecordAdapter extends MultiItemCommonAdapter<PostpartumBean> {

    public PostpartumObserveRecordAdapter(AppCompatActivity context, List<PostpartumBean> datas) {
        super(context, datas, new MultiItemTypeSupport<PostpartumBean>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.postpartum_observe_content;
            }

            @Override
            public int getItemViewType(int position, PostpartumBean PostpartumBean) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, PostpartumBean bean, int position) {
//        if (position == 0) return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tv_VitalSign_PAndHR, bean.getVitalSign_PAndHR());//
        holder.setText(R.id.tv_VitalSign_R, bean.getPostPartumLook().getVitalSign_R());//
        holder.setText(R.id.tv_VitalSign_BP, bean.getVitalSign_BP());//
        holder.setText(R.id.tv_OC_UCS, bean.getOC_UCS());//
        holder.setText(R.id.tv_FundusHeight, bean.getPostPartumLook().getFundusHeight());//
        holder.setText(R.id.tv_BladderCondition, bean.getPostPartumLook().getBladderCondition());//

        holder.setText(R.id.tv_In_Project, bean.getIn_Project());//
        holder.setText(R.id.tv_In_Amount, bean.getIn_Amount());
        holder.setText(R.id.tv_Out_Project, bean.getOut_Project());//
        holder.setText(R.id.tv_Out_Amount, bean.getOut_Amount());//

        holder.setText(R.id.tv_OtherSituation, bean.getOtherSituation());//
        holder.setText(R.id.tv_QualityControlNurse, bean.getExecutiveNurse());//
        holder.setOnClickListener(R.id.tv_OtherSituation, v ->
                new WarningDialog.Builder()
                        .setTitle("特殊情况记录")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );
    }
}
