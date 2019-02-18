package com.example.hjy.movenurse.wardmanage;

import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.utils.MyUtils;
import com.fy.base.AdapterBase;
import com.fy.entity.BedBean;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gab on 2017/9/15 0015.
 *  病房管理adapter
 */

public class WardManageAdapter extends AdapterBase<BedBean> implements Filterable {

    public WardManageAdapter(AppCompatActivity context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            final BedBean detailBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_wardmanagedata, null);

            TextView tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            TextView tv_sex = (TextView) itemView.findViewById(R.id.tv_sex);
            TextView tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            TextView tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            TextView tv_main = (TextView) itemView.findViewById(R.id.tv_main);
            TextView tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            tv_name.setText("姓名:" + detailBean.getPatName());//姓名
            tv_sex.setText("性别:" + MyUtils.getSex(detailBean.getSex()));//性别

            int age = TimeUtils.calculationAge(detailBean.getBirthDay(), "yyyyMMdd");
            tv_age.setText("年龄:" + age + "岁");//年龄
            tv_number.setText("编号:" + detailBean.getPatID());//编号
            tv_main.setText("主治:" + detailBean.getZyDetail().getAttend_PSName());//主治
            tv_status.setText(detailBean.getZyDetail().getIn_Room() + detailBean.getZyDetail().getIn_Bed());

            switch (detailBean.getZyDetail().getDay_Type()) {
                case "0":
                    tv_status.setBackgroundResource(R.drawable.shape_pink_top_bg);
                    break;
                case "1":
                    tv_status.setBackgroundResource(R.drawable.shape_orange_top_bg);
                    break;
                case "2":
                    tv_status.setBackgroundResource(R.drawable.shape_green_top_bg);
                    break;
                case "3":
                    tv_status.setBackgroundResource(R.drawable.shape_blue_top_bg);
                    break;
            }

            getViewCache().put(position, itemView);
            itemView.setTag(detailBean);
        }

        return itemView;
    }

    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<BedBean> mOriginalValues;
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
                    mOriginalValues = new ArrayList<>(getData());
                }
            }
            //当首字母为空时
            if (prefix == null || prefix.length() == 0) {
                ArrayList<BedBean> list;
                synchronized (mLock) {//同步复制一个原始备份数据
                    list = new ArrayList<>(mOriginalValues);
                }
                results.values = list;
                results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
            } else {
                String prefixString = prefix.toString().toLowerCase();//转化为小写

                ArrayList<BedBean> values;
                synchronized (mLock) {//同步复制一个原始备份数据
                    values = new ArrayList<>(mOriginalValues);
                }
                final int count = values.size();
                final ArrayList<BedBean> newValues = new ArrayList<>();

                for (int i = 0; i < count; i++) {
                    final BedBean value = values.get(i);//从List<BedBean>中拿到BedBean对象
//                    final String valueText = value.toString().toLowerCase();
                    final String valueText = value.getPatName().toLowerCase()  + value.getPatID().toLowerCase();//BedBean对象的name属性作为过滤的参数
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

                results.values = newValues;//此时的results就是过滤后的List<BedBean>数组
                results.count = newValues.size();
            }
            return results;
        }

        //刷选结果
        @Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            //noinspection unchecked
            setData((List<BedBean>) results.values);//此时，Adapter数据源就是过滤后的Results
            if (results.count > 0) {
                clearCache();//这个相当于从mDatas中删除了一些数据，只是数据的变化，故使用notifyDataSetChanged()
            } else {
                /**
                 * 数据容器变化 ----> notifyDataSetInValidated

                 容器中的数据变化  ---->  notifyDataSetChanged
                 */
                notifyDataSetInvalidated();//当results.count<=0时，此时数据源就是重新new出来的，说明原始的数据源已经失效了
            }
        }
    }
}
