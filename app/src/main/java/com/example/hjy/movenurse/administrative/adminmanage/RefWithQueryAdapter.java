package com.example.hjy.movenurse.administrative.adminmanage;

import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.AdapterBase;
import com.fy.entity.PainbBillDetailsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政管理 病区退药 数据列表 adapter
 * <p/>Created by fangs on 2017/9/30.
 */
public class RefWithQueryAdapter extends AdapterBase<PainbBillDetailsBean.ResultDataBean> implements Filterable {

    public RefWithQueryAdapter(AppCompatActivity context, List data) {
        super(context, data);
    }



    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            final PainbBillDetailsBean.ResultDataBean detailBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.query_child, null);
            TextView tvDate = (TextView) itemView.findViewById(R.id.tvDate);//退费日期
            TextView projectName = (TextView) itemView.findViewById(R.id.projectName);//项目名称
            TextView tvTemperature = (TextView) itemView.findViewById(R.id.tvTemperature);//单位
            TextView tvNumber = (TextView) itemView.findViewById(R.id.tvNumber);//数量
            TextView tvPrice = (TextView) itemView.findViewById(R.id.tvPrice);//标准价格
            TextView tvSpecifications = (TextView) itemView.findViewById(R.id.tvSpecifications);//规格
            TextView tvValuationTime = (TextView) itemView.findViewById(R.id.tvValuationTime);//计价时间
            TextView tvMainSequence = (TextView) itemView.findViewById(R.id.tvMainSequence);//主序
            TextView tvSubSequence = (TextView) itemView.findViewById(R.id.tvSubSequence);//子序号
            TextView tvBatchNumber = (TextView) itemView.findViewById(R.id.tvBatchNumber);//批号
            TextView tvRetailPrice = (TextView) itemView.findViewById(R.id.tvRetailPrice);//零售价
//            TextView tvPhoneticCode = (TextView) itemView.findViewById(R.id.tvPhoneticCode);//拼音码
//            TextView tvFiveStrokeCode = (TextView) itemView.findViewById(R.id.tvFiveStrokeCode);//五笔码
            TextView tvBillingDoctor = (TextView) itemView.findViewById(R.id.tvBillingDoctor);//开单医生
            TextView tvPrescriptionTypes = (TextView) itemView.findViewById(R.id.tvPrescriptionTypes);//处方类型
            TextView tvPrescriptionNumber = (TextView) itemView.findViewById(R.id.tvPrescriptionNumber);//处方号
            TextView tvVender = (TextView) itemView.findViewById(R.id.tvVender);//厂家
            TextView fNumber = (TextView) itemView.findViewById(R.id.fNumber);//金额
            TextView feeAmount = (TextView) itemView.findViewById(R.id.feeAmount);//自费金额
            TextView orderDepName = (TextView) itemView.findViewById(R.id.orderDepName);//开单科室
            TextView executiveDepName = (TextView) itemView.findViewById(R.id.executiveDepName);//执行科室
            TextView patID = (TextView) itemView.findViewById(R.id.patID);//住院号
            TextView costName = (TextView) itemView.findViewById(R.id.costName);//项目分类


                tvDate.setText(data.get(position).getTFDate().trim()+"");//退费日期
            projectName.setText(data.get(position).getProjectName().trim() + "");//项目名称
            tvTemperature.setText(data.get(position).getMeasure().trim()+"");
            tvNumber.setText(data.get(position).getFNumber().trim()+"");//数量
            tvPrice.setText(data.get(position).getPrice().trim()+"");//标准价格/单价
            tvSpecifications.setText(data.get(position).getItemSpec().trim()+"");//规格
            tvValuationTime.setText(data.get(position).getPSDate().trim()+"");//计价日期
            tvMainSequence.setText(data.get(position).getOrderNo().trim()+"");//主序
            tvSubSequence.setText(data.get(position).getOrderSubNo().trim()+"");//子序号
            tvBatchNumber.setText(data.get(position).getBatchNo().trim()+"");//批号
            tvRetailPrice.setText(data.get(position).getRoutineRetail().trim()+"");//零售价
            tvBillingDoctor.setText(data.get(position).getDoctor().trim()+"");//开单医生
            tvPrescriptionTypes.setText(data.get(position).getIOWHType().trim()+"");//处方类型
            tvPrescriptionNumber.setText(data.get(position).getIOWHNo().trim()+"");//处方号
            tvVender.setText(data.get(position).getMFT().trim()+"");//厂家
            fNumber.setText(data.get(position).getAmount().trim()+"");//金额/总价
            feeAmount.setText(data.get(position).getFeeAmount().trim()+"");//自费金额
            orderDepName.setText(data.get(position).getOrderDepName().trim()+"");//开单科室
            executiveDepName.setText(data.get(position).getExcuteDepName().trim()+"");//执行科室
            patID.setText(data.get(position).getPatID().trim()+"");//住院号
            costName.setText(data.get(position).getCostName().trim()+"");//项目分类/费用类型


            itemView.setTag(detailBean);
            getViewCache().put(position, itemView);
        }

        return itemView;
    }

    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<PainbBillDetailsBean.ResultDataBean> mOriginalValues;
    private ArrayFilter mFilter;

    @Override
    public Filter getFilter() {
        if (mFilter == null) {
            mFilter = new ArrayFilter();
        }
        return mFilter;
    }

    public class ArrayFilter extends Filter {
        //执行刷选
        @Override
        protected FilterResults performFiltering(CharSequence prefix) {
            FilterResults results = new FilterResults();//过滤的结果
            //原始数据备份为空时，上锁，同步复制原始数据
            if (mOriginalValues == null) {
                synchronized (mLock) {
                    mOriginalValues = new ArrayList<>(getData());
                }
            }
            //当首字母为空时
            if (prefix == null || prefix.length() == 0) {
                ArrayList<PainbBillDetailsBean.ResultDataBean> list;
                synchronized (mLock) {//同步复制一个原始备份数据
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            } else {
                String prefixString = prefix.toString().toLowerCase();//转化为小写

                ArrayList<PainbBillDetailsBean.ResultDataBean> values;
                synchronized (mLock) {//同步复制一个原始备份数据
                    values = new ArrayList<>(mOriginalValues);
                }
                final int count = values.size();
                final ArrayList<PainbBillDetailsBean.ResultDataBean> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final PainbBillDetailsBean.ResultDataBean value = values.get(i);//从List<PatientsBean>中拿到PatientsBean对象
                    String valueText =value.getProjectName()+value.getOrderNo();//PatientsBean对象的name属性作为过滤的参数
//                     PatientsBean.ZyDetailBean detailBean1 = value.getZyDetail();
//                    if (null != detailBean1){
//                        valueText += detailBean1.getIn_Bed().toLowerCase() + detailBean1.getIn_Room().toLowerCase();
//                    }
                    // First match against the whole, non-splitted value
                    if (valueText.contains(prefixString) || valueText.indexOf(prefixString.toString()) != -1) {//第一个字符是否匹配
                        newValues.add(value);//将这个item加入到数组对象中
                    } else {//处理首字符是空格
                        final String[] words = valueText.split(" ");
                        final int wordCount = words.length;

                        // Start at index 0, in case valueText starts with space(s)
                        for (int k = 0; k < wordCount; k++) {
                            if (words[k].contains(prefixString)) {//一旦找到匹配的就break，跳出for循环
                                newValues.add(value);
                                break;
                            }
                        }
                    }
                }

                results.values = newValues;//此时的results就是过滤后的List<PatientsBean>数组
                results.count = newValues.size();
            }
            return results;
        }

        //刷选结果
        @Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            //noinspection unchecked
            setData((List<PainbBillDetailsBean.ResultDataBean>) results.values);//此时，Adapter数据源就是过滤后的Results
            if (results.count > 0) {
                //clearCache();//这个相当于从mDatas中删除了一些数据，只是数据的变化，故使用notifyDataSetChanged()
                notifyDataSetChanged();
            } else {
                /**
                 * 数据容器变化 ----> notifyDataSetInValidated

                 容器中的数据变化  ---->  notifyDataSetChanged
                 */
                //notifyDataSetInvalidated();//当results.count<=0时，此时数据源就是重新new出来的，说明原始的数据源已经失效了
                notifyDataSetChanged();
            }
        }
    }

}
