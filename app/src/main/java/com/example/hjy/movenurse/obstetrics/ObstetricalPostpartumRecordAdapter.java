package com.example.hjy.movenurse.obstetrics;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.ObstetricalPostpartum;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * 产科产后记录单 adapter
 * Created by Stefan on 2017/9/14.
 */
public class ObstetricalPostpartumRecordAdapter extends MultiItemCommonAdapter<ObstetricalPostpartum> {

    public ObstetricalPostpartumRecordAdapter(AppCompatActivity context, List <ObstetricalPostpartum> datas) {
        super(context, datas, new MultiItemTypeSupport<ObstetricalPostpartum>(){
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.obstetrical_record_content;
            }

            @Override
            public int getItemViewType(int position, ObstetricalPostpartum obstetricalPostpartum) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, ObstetricalPostpartum bean, int position) {
//        if (position == 0) return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tvTemperature, bean.getPostpartum().getVitalSign_T());//
        holder.setText(R.id.tvHR, bean.getVitalSign_PAndHR());//
        holder.setText(R.id.tvR, bean.getPostpartum().getVitalSign_R());//
        holder.setText(R.id.tvBP, bean.getVitalSign_BP());//
        holder.setText(R.id.tvSp, bean.getPostpartum().getVitalSign_SP02());//
        holder.setText(R.id.tvOC_UCS, bean.getOC_UCS());//
        holder.setText(R.id.tvFundusHeight, bean.getPostpartum().getFundusHeight());//
        holder.setText(R.id.LochiaNature,  bean.getPostpartum().getLochiaNature());//
        holder.setText(R.id.BreastCondition,  bean.getPostpartum().getBreastCondition());//
        holder.setText(R.id.Lactation,  bean.getPostpartum().getLactation());//
        holder.setText(R.id.In_Project,  bean.getIn_Project());//
        holder.setText(R.id.In_Amount,  bean.getIn_Amount());//
        holder.setText(R.id.Out_Project,  bean.getOut_Project());//
        holder.setText(R.id.Out_Amount,  bean.getOut_Amount());//
        holder.setText(R.id.AbdomenWound,  bean.getPostpartum().getAbdomenWound());//
        holder.setText(R.id.PerinealWound,  bean.getPostpartum().getPerinealWound());//
        holder.setText(R.id.Catheters,  bean.getPostpartum().getCatheters());//
        holder.setText(R.id.POGBA,  bean.getPostpartum().getPOGBA());//
        holder.setText(R.id.OtherSituation, bean.getOtherSituation());//
        holder.setText(R.id.ExecutiveNurse, bean.getExecutiveNurse());
        holder.setOnClickListener(R.id.OtherSituation, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new WarningDialog.Builder()
                        .setTitle("特殊情况记录")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog");
            }
        });
    }
}
