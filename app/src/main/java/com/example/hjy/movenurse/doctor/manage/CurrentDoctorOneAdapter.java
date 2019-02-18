package com.example.hjy.movenurse.doctor.manage;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.DoctorInfoBean;
import com.fy.recyclerview.DividerParams;

import java.util.List;

/**
 * 现行医嘱 adapter
 * Created by fangs on 2017/9/26.
 */
public class CurrentDoctorOneAdapter extends RecyclerCommonAdapter<DoctorInfoBean> {

    private String tag = "";//区别药物医嘱 和 处置医嘱

    public CurrentDoctorOneAdapter(AppCompatActivity context, List<DoctorInfoBean> datas, String tag) {
        super(context, R.layout.item_current_doctor_one, datas);
        this.tag = tag;
    }

    @Override
    public void convert(ViewHolder holder, DoctorInfoBean bean, int position) {

        holder.setText(R.id.tvItemName, tag);

        holder.setText(R.id.tvItemId, bean.getOrderNo());//医嘱编号

        CheckBox cBoxStatus = holder.getView(R.id.cBoxStatus);
        cBoxStatus.setChecked(isItemChecked(mSelectedPositions, position));
        if (isItemChecked(mSelectedPositions, position)){
            cBoxStatus.setText("完成");
        } else {
            cBoxStatus.setText("待完成");
        }
        cBoxStatus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(mSelectedPositions, position)) {
                    setItemChecked(position, false);
                } else {
                    setItemChecked(position, true);
                }

                if (isItemChecked(mSelectedPositions, position)){
                    cBoxStatus.setText("完成");
                } else {
                    cBoxStatus.setText("待完成");
                }
            }
        });

        List<DoctorInfoBean.OrderDetailsBean> CurrentDoctorOneSunAdapter = bean.getOrderDetails();
        RecyclerView lvMedicine = holder.getView(R.id.lvMedicine);

        GridLayoutManager layoutManager = new GridLayoutManager(mContext, 1, OrientationHelper.VERTICAL, false);
        //设置布局管理器
        lvMedicine.setLayoutManager(layoutManager);
        lvMedicine.addItemDecoration(new DividerParams().setLayoutManager(0).create(mContext));

        CurrentDoctorOneSunAdapter adapter = new CurrentDoctorOneSunAdapter(mContext, CurrentDoctorOneSunAdapter);
        lvMedicine.setAdapter(adapter);
    }

    /**
     * 区别单选 多选 设置条目选中状态
     * @param position
     * @param isChecked
     */
    private void setItemChecked(int position, boolean isChecked) {
        setItemChecked(mSelectedPositions, position, isChecked);
    }

//    /**
//     * 设置给定位置条目的选择状态
//     * @param array
//     * @param position
//     * @param isChecked
//     */
//    private void setItemChecked(SparseBooleanArray array, int position, boolean isChecked){
//        array.put(position, isChecked);
//    }
//
//    /**
//     * 根据位置判断条目是否选中
//     * @param position
//     * @return
//     */
//    private boolean isItemChecked(SparseBooleanArray array, int position) {
//        return array.get(position);
//    }
}
