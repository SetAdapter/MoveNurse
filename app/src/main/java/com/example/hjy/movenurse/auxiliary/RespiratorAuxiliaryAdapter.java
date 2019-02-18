package com.example.hjy.movenurse.auxiliary;

import android.support.v7.app.AppCompatActivity;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.RespiratorAuxiliary;
import com.fy.utils.TimeUtils;

import java.util.List;

/**
 * Created by Gab on 2017/9/18 0018.
 * 新生儿科呼吸机辅助呼吸记录单 adapter
 */

public class RespiratorAuxiliaryAdapter extends MultiItemCommonAdapter<RespiratorAuxiliary> {

    public RespiratorAuxiliaryAdapter(AppCompatActivity context, List<RespiratorAuxiliary> datas) {
        super(context, datas, new MultiItemTypeSupport<RespiratorAuxiliary>(){
            @Override
            public int getLayoutId(int itemType) {
                return R.layout.respirator_auxiliary_content;
            }

            @Override
            public int getItemViewType(int position, RespiratorAuxiliary RespiratorAuxiliary) {
                return position;
            }

        });
    }
    @Override
    public void convert(ViewHolder holder, RespiratorAuxiliary bean, int position) {
//        if (position == 0)return;
        long timeL = TimeUtils.timeString2long(bean.getRecodeDate(), "yyyy/MM/dd HH:mm:ss");
        holder.setText(R.id.tvDate, TimeUtils.Long2DataString(timeL, "yyyy/MM/dd"));
        holder.setText(R.id.tvTime, TimeUtils.Long2DataString(timeL, "HH:mm"));
        holder.setText(R.id.tv_av, bean.getVentilationMode());//av
        holder.setText(R.id.tv_FiO2, bean.getFiO2());//
        holder.setText(R.id.tv_rr, bean.getRR());//
        holder.setText(R.id.tv_pip, bean.getPIP());//
        holder.setText(R.id.tv_peep, bean.getPEEP());//

        holder.setText(R.id.tv_ie, bean.getIE());//
        holder.setText(R.id.tv_sa02, bean.getSaO2());
        holder.setText(R.id.tv_ph, bean.getPH());//
        holder.setText(R.id.tv_po2, bean.getPO2());//

        holder.setText(R.id.tv_pc02, bean.getPCO2());//
        holder.setText(R.id.tv_hc03, bean.getHCO2());//
        holder.setText(R.id.tv_be, bean.getBE());//
        holder.setText(R.id.tv_LacticAcid, bean.getLacticAcid());//

        holder.setText(R.id.tv_TCType, bean.getTCType());//
        holder.setText(R.id.tv_TCDepth, bean.getTCDepth());//
        holder.setText(R.id.tv_ExecutiveNurse, bean.getExecutiveNurse());//

    }

}
