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
import com.fy.entity.DailyListBean;
import com.fy.entity.PatientsBean;

import java.util.ArrayList;
import java.util.List;

import me.grantland.widget.AutofitHelper;

/**
 * 日清单 adapter
 * Created by Stefan on 2017/10/26.
 */
public class DailyListAdapter extends AdapterBase<DailyListBean.ResultDataBean> implements Filterable {
    PatientsBean bean;

    public void setBean(PatientsBean bean) {
        this.bean = bean;
    }

    public DailyListAdapter(AppCompatActivity context, List<DailyListBean.ResultDataBean> data) {
        super(context, data);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            DailyListBean.ResultDataBean listBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.adapter_daylist, null);
            TextView tvHosTimes= (TextView) itemView.findViewById(R.id.tvHosTimes);//入院次数
            TextView tvCharDate= (TextView) itemView.findViewById(R.id.tvCharDate);//收费日期
            TextView tvProCary= (TextView) itemView.findViewById(R.id.tvProCary);//项目类别
            TextView tvEntryName= (TextView) itemView.findViewById(R.id.tvEntryName);//项目名称
            TextView tvPrice= (TextView) itemView.findViewById(R.id.tvPrice);//价格
            TextView tvNumber= (TextView) itemView.findViewById(R.id.tvNumber);//数量
            TextView tvMoney= (TextView) itemView.findViewById(R.id.tvMoney);//金额
            TextView tvCompany= (TextView) itemView.findViewById(R.id.tvCompany);//单位

            //设置数据
            tvHosTimes.setText(bean.getZyDetail().getInTime().trim());//入院次数
            tvCharDate.setText(listBean.getTFDate());//收费日期
            tvProCary.setText(listBean.getCostName());//项目类别
            AutofitHelper.create(tvEntryName);
            tvEntryName.setText(listBean.getProjectName());//项目名称
            tvPrice.setText(listBean.getPrice());//价格
            tvNumber.setText(listBean.getFNumber());//数量
            tvMoney.setText(listBean.getAmount());//金额
            tvCompany.setText(listBean.getMeasure());//单位

            itemView.setTag(listBean);
            getViewCache().put(position, itemView);
        }
        return itemView;
    }

    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<DailyListBean.ResultDataBean> mOriginalValues;
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
                ArrayList<DailyListBean.ResultDataBean> list;
                synchronized (mLock) {//同步复制一个原始备份数据
                    list = new ArrayList(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            } else {
                String prefixString = prefix.toString().toLowerCase();//转化为小写

                ArrayList<DailyListBean.ResultDataBean> values;
                synchronized (mLock) {//同步复制一个原始备份数据
                    values = new ArrayList(mOriginalValues);
                }
                final int count = values.size();
                final ArrayList<DailyListBean.ResultDataBean> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final DailyListBean.ResultDataBean value = values.get(i);//从List<PatientsBean>中拿到PatientsBean对象
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
            setData((List<DailyListBean.ResultDataBean>) results.values);//此时，Adapter数据源就是过滤后的Results
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
