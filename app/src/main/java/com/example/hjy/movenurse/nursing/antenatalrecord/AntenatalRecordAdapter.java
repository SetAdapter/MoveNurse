package com.example.hjy.movenurse.nursing.antenatalrecord;

import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.custom.dialog.WarningDialog;
import com.fy.entity.AntenatalRecordBean;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * 产前待产记录 列表 adapter
 * Created by fangs on 2017/9/14.
 */
public class AntenatalRecordAdapter extends MultiItemCommonAdapter<AntenatalRecordBean> {

    public AntenatalRecordAdapter(AppCompatActivity context, List<AntenatalRecordBean> datas) {
        super(context, datas, new MultiItemTypeSupport<AntenatalRecordBean>() {
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.item_antenatal_record_content;
            }

            @Override
            public int getItemViewType(int position, AntenatalRecordBean antenatalBean) {
                return position;
            }

        });
    }

    @Override
    public void convert(ViewHolder holder, AntenatalRecordBean bean, int position) {
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tvTemperature, bean.getAntenatal().getVitalSign_T());//温度
        holder.setText(R.id.tvHR, bean.getVitalSign_PAndHR());//HR
        holder.setText(R.id.tvR, bean.getAntenatal().getVitalSign_R());//R
        holder.setText(R.id.tvBP, bean.getVitalSign_BP());//BP
        holder.setText(R.id.tvSpo2, bean.getAntenatal().getVitalSign_SP02());
        holder.setText(R.id.tvOC_FHt, bean.getAntenatal().getOC_FHt());
        holder.setText(R.id.tvOC_FM, bean.getAntenatal().getOC_FM());
        if (!TextUtils.isEmpty( bean.getAntenatal().getOC_POTF())){
//            holder.setText(R.id.tvOC_POTF, bean.getAntenatal().getOC_POTF().substring(4,bean.getAntenatal().getOC_POTF().lastIndexOf(")")));//胎位
//            holder.setText(R.id.tvOC_POTF, bean.getAntenatal().getOC_POTF().substring(4).replace(")",""));//胎位
            StringBuilder sb = new StringBuilder();
            String potf = bean.getAntenatal().getOC_POTF();
            boolean is = false;
            int len = potf.length();
            for (int i = 0; i < len; i++){
                String chart = String.valueOf(potf.charAt(i));
                if (chart.equals("(")){
                    is = true;
                    continue;
                } else if (chart.equals(")")){
                    break;
                }
                if (is){
                    sb.append(chart);
                }
            }
            holder.setText(R.id.tvOC_POTF, sb.toString());//胎位
        }else {
            holder.setText(R.id.tvOC_POTF,"");//胎位
        }
        holder.setText(R.id.tvOC_UCS, bean.getOC_UCS());
        holder.setText(R.id.tvOC_UCI, bean.getAntenatal().getOC_UCI());
        holder.setText(R.id.tv_OC_CD, bean.getAntenatal().getOC_CD());
        holder.setText(R.id.tvOC_CS, bean.getAntenatal().getOC_CS());
        holder.setText(R.id.tvOC_SO, bean.getAntenatal().getOC_SO());
        holder.setText(R.id.OC_Caul, bean.getAntenatal().getOC_Caul());
        holder.setText(R.id.OC_AFI, bean.getAntenatal().getOC_AFI());
        holder.setText(R.id.In_Project, bean.getIn_Project());
        holder.setText(R.id.In_Amount, bean.getIn_Amount());//
        holder.setText(R.id.Out_Project, bean.getOut_Project());
        holder.setText(R.id.Out_Amount, bean.getOut_Amount());
        holder.setText(R.id.Aerophose, bean.getAntenatal().getAerophose());
        holder.setText(R.id.KneeJerk, bean.getAntenatal().getKneeJerk());
        holder.setText(R.id.ExecutiveNurse, bean.getExecutiveNurse());
        holder.setText(R.id.OtherSituation, bean.getOtherSituation());
        holder.setOnClickListener(R.id.OtherSituation, v ->
                new WarningDialog.Builder()
                        .setTitle("特殊情况记录")
                        .setMessage(bean.getOtherSituation())
                        .create()
                        .show(mContext.getSupportFragmentManager(), "WarningDialog")
        );
    }
}
