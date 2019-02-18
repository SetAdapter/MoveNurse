package com.example.hjy.movenurse.fragment;

import android.annotation.SuppressLint;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.PatientsBean;
import com.fy.entity.PressureSoreBean;
import com.fy.utils.TimeUtils;
import com.fy.utils.cache.ACache;

import java.util.List;

/**
 * Created by yclm on 2017/10/28 0028.
 * 压疮adapter list
 */

public class PressureSoresAdapter extends RecyclerCommonAdapter<PressureSoreBean> {

    ACache mCache;
//    public LinearLayout ll_pressuer;

    public PressureSoresAdapter(AppCompatActivity context, List<PressureSoreBean> datas) {
        super(context, R.layout.item_pressure_list, datas);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void convert(ViewHolder holder, PressureSoreBean dictsBean, int position) {
        CheckBox cb_title = holder.getView(R.id.cb_title);
        TextView tv_In_Dis = holder.getView(R.id.tv_In_Dis);
        TextView OP_Name = holder.getView(R.id.OP_Name);
        TextView SomatoType = holder.getView(R.id.SomatoType);
        TextView SkinType = holder.getView(R.id.SkinType);
        TextView Sex = holder.getView(R.id.Sex);
        TextView Age = holder.getView(R.id.Age);
        TextView OtherHazard = holder.getView(R.id.OtherHazard);
        TextView ContinenceAbility = holder.getView(R.id.ContinenceAbility);
        TextView Activities = holder.getView(R.id.Activities);
        TextView Appetite = holder.getView(R.id.Appetite);

        TextView NeuroticDisor = holder.getView(R.id.NeuroticDisor);
        TextView Medication = holder.getView(R.id.Medication);
        TextView Major_OP = holder.getView(R.id.Major_OP);
        TextView AssessmentNurse = holder.getView(R.id.AssessmentNurse);
        TextView AssessmentDate = holder.getView(R.id.AssessmentDate);
        TextView overallScore = holder.getView(R.id.overallScore);
        TextView tvPrevention_PM1 = holder.getView(R.id.tvPrevention_PM1);
        TextView tvPrevention_PM2 = holder.getView(R.id.tvPrevention_PM2);
        TextView tvPrevention_PM3 = holder.getView(R.id.tvPrevention_PM3);
        TextView tvPrevention_PM4 = holder.getView(R.id.tvPrevention_PM4);
        TextView tvPrevention_PM5 = holder.getView(R.id.tvPrevention_PM5);
        TextView tvPrevention_PM6 = holder.getView(R.id.tvPrevention_PM6);
        LinearLayout ll_pressuer = holder.getView(R.id.ll_pressuer);

        mCache = ACache.get(mContext);
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        //术前诊断
        tv_In_Dis.setText(patin.getZyDetail().getICD_Name());
        OP_Name.setText(dictsBean.getOP_Name());//手术名称

        /**
         * 下拉刷新 判断是否展开
         */
        if (mSelectedPositions.valueAt(position)) {
            ll_pressuer.setVisibility(View.VISIBLE);
            cb_title.setChecked(true);
        } else {
            ll_pressuer.setVisibility(View.GONE);
            cb_title.setChecked(false);
        }

        cb_title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (cb_title.isChecked()) {
                    ll_pressuer.setVisibility(View.VISIBLE);
                } else {
                    ll_pressuer.setVisibility(View.GONE);
                }
            }
        });

//        long string2long = TimeUtils.timeString2long(dictsBean.getAssessmentDate(), "yyyy-MM-dd HH:mm:ss");
//        String data = TimeUtils.Long2DataString(string2long, "yyyy年MM月dd日 HH:mm:ss");
        cb_title.setText("   " + dictsBean.getAssessmentDate() + "   " + dictsBean.getOP_Name());
        if (dictsBean.getOverallScore().equals("0")) {
            overallScore.setText("评估：" + 0 + " 分");
        } else {
            overallScore.setText("评估：" + dictsBean.getOverallScore() + " 分");//评估总分
        }
        SomatoType.setText(dictsBean.getSomatoType());
        SkinType.setText(dictsBean.getSkinType());
        Sex.setText(dictsBean.getSex());
        Age.setText(dictsBean.getAge());
        if (!TextUtils.isEmpty(dictsBean.getOtherHazard())) {
            OtherHazard.setText(dictsBean.getOtherHazard());
        }
        ContinenceAbility.setText(dictsBean.getContinenceAbility());
        Activities.setText(dictsBean.getActivities());
        Appetite.setText(dictsBean.getAppetite());

        if (!TextUtils.isEmpty(dictsBean.getNDScore())) {
            NeuroticDisor.setText("类型：" + dictsBean.getNeuroticDisor() + "       分数：" + dictsBean.getNDScore());
        } else {
            NeuroticDisor.setVisibility(View.GONE);
        }

//        if (!TextUtils.isEmpty(dictsBean.getNDScore())) {
//            String ndScore = dictsBean.getNDScore();
//            String substring = "糖尿病：" + ndScore.substring(0, 1);
//            String substring1 = "多发性硬化：" + ndScore.substring(1, 2);
//            String substring2 = "脑血管意外：" + ndScore.substring(2, 3);
//            NeuroticDisor.setText(substring + " " + substring1 + " " + substring2);
//        } else {
//            NeuroticDisor.setVisibility(View.GONE);
//        }
        Medication.setText(dictsBean.getMedication());
        Major_OP.setText(dictsBean.getMajor_OP());
        AssessmentDate.setText("时间:" + dictsBean.getAssessmentDate());
        AssessmentNurse.setText("评估护士签名:" + dictsBean.getAssessmentNurse());


        if (!TextUtils.isEmpty(dictsBean.getPM1())) {
            tvPrevention_PM1.setText("全面评估患者发生压疮的危险因素");
        } else {
            tvPrevention_PM1.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(dictsBean.getPM2())) {
            tvPrevention_PM2.setText("合理安置手术体位,选择合适体位垫");
        } else {
            tvPrevention_PM2.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(dictsBean.getPM3())) {
            tvPrevention_PM3.setText("保持床单平整,干燥,保护受压部位皮肤,合理使用抗压软垫");
        } else {
            tvPrevention_PM3.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(dictsBean.getPM4())) {
            tvPrevention_PM4.setText("保障组织有效灌注,注意观察出血量与血压变化");
        } else {
            tvPrevention_PM4.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(dictsBean.getPM5())) {
            tvPrevention_PM5.setText("注意保暖,维持患者正常体温");
        } else {
            tvPrevention_PM5.setVisibility(View.GONE);
        }

        if (!TextUtils.isEmpty(dictsBean.getPM6())) {
            tvPrevention_PM6.setText(dictsBean.getPM6());
        } else {
            tvPrevention_PM6.setVisibility(View.GONE);
        }

    }
}
