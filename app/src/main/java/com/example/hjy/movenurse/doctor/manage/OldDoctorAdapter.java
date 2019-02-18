package com.example.hjy.movenurse.doctor.manage;

import android.support.v7.app.AppCompatActivity;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.MultiItemCommonAdapter;
import com.fy.base.recyclerv.MultiItemTypeSupport;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.DoctorInfoOldBean;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 已执行医嘱 adapter
 * Created by fangs on 2017/9/27.
 */
public class OldDoctorAdapter extends MultiItemCommonAdapter<DoctorInfoOldBean> implements Filterable {

    int fiag;//0:已校对已执行 1:已校对未执行 2:未校对未执行

    public int getFiag() {
        return fiag;
    }

    public void setFiag(int fiag) {
        this.fiag = fiag;
    }

    public OldDoctorAdapter(AppCompatActivity context, List<DoctorInfoOldBean> datas) {
        super(context, datas, new MultiItemTypeSupport<DoctorInfoOldBean>() {
            @Override
            public int getLayoutId(int itemType) {
                    return R.layout.item_old_doctor_content;
            }

            @Override
            public int getItemViewType(int position, DoctorInfoOldBean doctorInfoBean) {
                return position;
            }
        });
    }

    @Override
    public void convert(ViewHolder holder, DoctorInfoOldBean doctorInfoBean, int position) {

//        if (position == 0)return;
        holder.setText(R.id.tvItemName, doctorInfoBean.getOrderText());
        holder.setText(R.id.tvDosage, doctorInfoBean.getDosage());//剂量
        holder.setText(R.id.tvFrequency, doctorInfoBean.getFreqTime());//执行频率
        holder.setText(R.id.tvChannel, doctorInfoBean.getAdministration());//给药途径
        holder.setText(R.id.tvGroupNumber, doctorInfoBean.getOrderNo());//组号

//        long runTieml = TimeUtils.timeString2long(doctorInfoBean.getStartTime(), "yyyy/MM/dd HH:mm:ss");

        if(doctorInfoBean.getStartTime().equals("")){
            holder.setText(R.id.tvRunTime, "");//执行时间
        }else {
            holder.setText(R.id.tvRunTime, doctorInfoBean.getStartTime().toString());//执行时间
        }

        holder.setText(R.id.tvRunName, doctorInfoBean.getNurse());//执行签名

    }


    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<DoctorInfoOldBean> mOriginalValues;
    private ArrayFilter mFilter;


    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    private class ArrayFilter extends Filter {
        //执行刷选
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();//过滤的结果
            //原始数据备份为空时，上锁，同步复制原始数据
            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<>(mDatas);
                }
            }
            //当首字母为空时
            if (prefix == null || prefix.length() == 0) {
                ArrayList<DoctorInfoOldBean> list;
                synchronized (mLock) {//同步复制一个原始备份数据
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            } else {
                String prefixString = prefix.toString().toLowerCase();//转化为小写
                int indexOf = prefixString.indexOf("#");
                String startTime = prefixString.substring(0, indexOf);
                String endTime   = prefixString.substring(indexOf + 1, prefixString.length());
                long startLong = TimeUtils.timeString2long(startTime, "yyyy年MM月dd日");
                long endLong   = TimeUtils.timeString2long(endTime, "yyyy年MM月dd日");


                ArrayList<DoctorInfoOldBean> values;
                synchronized (mLock) {//同步复制一个原始备份数据
                    values = new ArrayList<>(mOriginalValues);
                }
                final int count = values.size();
                final ArrayList<DoctorInfoOldBean> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final DoctorInfoOldBean value = values.get(i);//从List<DoctorInfoOldBean>中拿到DoctorInfoOldBean对象
                    long runTieml = TimeUtils.timeString2long(value.getStartTime(), "yyyy/MM/dd");

                    if (runTieml >= startLong && runTieml <= endLong) {
                        newValues.add(value);//将这个item加入到数组对象中
                    }
                }

                results.values = newValues;//此时的results就是过滤后的List<DoctorInfoOldBean>数组
                results.count = newValues.size();
            }
            return results;
        }

        //刷选结果
        @Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            //noinspection unchecked
            setmDatas((List<DoctorInfoOldBean>) results.values);//此时，Adapter数据源就是过滤后的Results
            if (results.count > 0) {
                notifyDataSetChanged();//这个相当于从mDatas中删除了一些数据，只是数据的变化，故使用notifyDataSetChanged()
            } else {
                /**
                 * 数据容器变化 ----> notifyDataSetInValidated

                 容器中的数据变化  ---->  notifyDataSetChanged
                 */
                notifyDataSetChanged();//当results.count<=0时，此时数据源就是重新new出来的，说明原始的数据源已经失效了
            }
        }
    }
}
