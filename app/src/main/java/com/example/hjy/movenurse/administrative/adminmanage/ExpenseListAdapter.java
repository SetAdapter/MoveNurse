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
import com.fy.entity.ExpenseListBean;
import com.fy.entity.PatientsBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 费用清单 adapter
 * Created by Stefan on 2017/10/26.
 */
public class ExpenseListAdapter extends AdapterBase<ExpenseListBean.ResultDataBean> implements Filterable {
    PatientsBean bean;

    public void setBean(PatientsBean bean) {
        this.bean = bean;
    }

    public ExpenseListAdapter(AppCompatActivity context, List<ExpenseListBean.ResultDataBean> data) {
        super(context, data);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            ExpenseListBean.ResultDataBean dataBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_exlist, null);
            TextView tvAdmissionTimes= (TextView) itemView.findViewById(R.id.tvAdmissionTimes);//入院次数
            TextView tvSerialNumber= (TextView) itemView.findViewById(R.id.tvSerialNumber);//序号
            TextView tvCostType= (TextView) itemView.findViewById(R.id.tvCostType);//费用类型
           // TextView tvProjectCode= (TextView) itemView.findViewById(R.id.tvProjectCode);//项目编码
            TextView tvStandard= (TextView) itemView.findViewById(R.id.tvStandard);//规格
            TextView tvCompany= (TextView) itemView.findViewById(R.id.tvCompany);//单位
            TextView tvNumber= (TextView) itemView.findViewById(R.id.tvNumber);//数量
            TextView tvUnitPrice= (TextView) itemView.findViewById(R.id.tvUnitPrice);//单价
            TextView tvMoney= (TextView) itemView.findViewById(R.id.tvMoney);//金额
            TextView tvExDepartment= (TextView) itemView.findViewById(R.id.tvExDepartment);//执行科室
            TextView tvDiscount= (TextView) itemView.findViewById(R.id.tvDiscount);//折扣
            TextView tvMedicalInsurance= (TextView) itemView.findViewById(R.id.tvMedicalInsurance);//医保
            TextView tvPreSum= (TextView) itemView.findViewById(R.id.tvPreSum);//处方金额
            TextView tvTreatAmount= (TextView) itemView.findViewById(R.id.tvTreatAmount);//治疗金额
            TextView tvPayAmount= (TextView) itemView.findViewById(R.id.tvPayAmount);//支付金额
            TextView tvMark= (TextView) itemView.findViewById(R.id.tvMark);//农合标志

            //设置数据
            tvAdmissionTimes.setText(bean.getZyDetail().getInTime().trim());//入院次数
            tvSerialNumber.setText(dataBean.getBillNo().trim());//序号
            tvCostType.setText(dataBean.getCostName().trim());//费用类型
            tvStandard.setText(dataBean.getItemSpec().trim());//规格
            tvCompany.setText(dataBean.getMeasure().trim());//单位
            tvNumber.setText(dataBean.getFNumber().trim());//数量
            tvUnitPrice.setText(dataBean.getPrice().trim());//单价
            tvMoney.setText(dataBean.getAmount().trim());//金额
            tvExDepartment.setText(dataBean.getExcuteDepName().trim());//执行科室
            tvDiscount.setText(dataBean.getDisCount()+"");//折扣
            tvMedicalInsurance.setText(bean.getMedicareType().trim());//医保类别
            tvPreSum.setText(dataBean.getAmount().trim());//处方金额
            tvTreatAmount.setText(dataBean.getAmount().trim());//治疗金额
            tvPayAmount.setText(dataBean.getAmount().trim());//支付金额
            tvMark.setText(bean.getMedicareType().trim());//农合标志

            itemView.setTag(dataBean);
            getViewCache().put(position, itemView);
        }
        return itemView;
    }

    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<ExpenseListBean.ResultDataBean> mOriginalValues;
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
                ArrayList<ExpenseListBean.ResultDataBean> list;
                synchronized (mLock) {//同步复制一个原始备份数据
                    list = new ArrayList(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            } else {
                String prefixString = prefix.toString().toLowerCase();//转化为小写

                ArrayList<ExpenseListBean.ResultDataBean> values;
                synchronized (mLock) {//同步复制一个原始备份数据
                    values = new ArrayList(mOriginalValues);
                }
                final int count = values.size();
                final ArrayList<ExpenseListBean.ResultDataBean> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final ExpenseListBean.ResultDataBean value = values.get(i);//从List<PatientsBean>中拿到PatientsBean对象
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
            setData((List<ExpenseListBean.ResultDataBean>) results.values);//此时，Adapter数据源就是过滤后的Results
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
