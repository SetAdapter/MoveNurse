package com.example.hjy.movenurse.wardmanage;

import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.AdapterBase;
import com.fy.entity.BedBean;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Gab on 2017/9/15 0015.
 * 病房管理adapter
 */
public class WardManageListAdapter extends AdapterBase<BedBean> implements Filterable {

    public WardManageListAdapter(AppCompatActivity context, List data) {
        super(context, data);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            final BedBean detailBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_wardmanage_list, null);
            TextView tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            TextView tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            TextView tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            TextView tv_main = (TextView) itemView.findViewById(R.id.tv_main);
            TextView tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            TextView tv_total_fee = (TextView) itemView.findViewById(R.id.tv_total_fee);
            TextView tv_MedicareType = (TextView) itemView.findViewById(R.id.tv_MedicareType);
            TextView tv_in_Date = (TextView) itemView.findViewById(R.id.tv_in_Date);
            TextView tv_ICD_Name = (TextView) itemView.findViewById(R.id.tv_ICD_Name);
            ImageView iv_PackBed = (ImageView) itemView.findViewById(R.id.iv_PackBed);
            ImageView iv_leave = (ImageView) itemView.findViewById(R.id.iv_leave);
            ImageView iv_sex = (ImageView) itemView.findViewById(R.id.iv_sex);
            ImageView iv_HaveOrders = (ImageView) itemView.findViewById(R.id.iv_HaveOrders);

            RelativeLayout RL_status = (RelativeLayout) itemView.findViewById(R.id.RL_status);

            tv_name.setText("姓名:" + detailBean.getPatName());//姓名
            switch (detailBean.getSex()) {//性别
                case "M":
                    iv_sex.setImageResource(R.mipmap.man);
                    break;
                case "F":
                    iv_sex.setImageResource(R.mipmap.woman);
                    break;
                case "0":
                    break;

            }
            switch (detailBean.getBedStatus()) {//
                case "3":
                    iv_PackBed.setImageResource(R.mipmap.icon_bcxxx);
                    break;
                case "4":
                    iv_leave.setImageResource(R.mipmap.icon_qjxxx);
                    break;
                case "0":
                    break;

            }

            if (detailBean.getZyDetail().getHaveOrders().equals("0")) { //肿瘤患者
                iv_HaveOrders.setVisibility(View.GONE);
            } else {
                iv_HaveOrders.setVisibility(View.VISIBLE);
            }

            BedBean.ZyDetailBean detailBean1 = detailBean.getZyDetail();
            int age = TimeUtils.calculationAge(detailBean.getBirthDay(), "yyyyMMdd");
            tv_age.setText("年龄:" + age + "岁");//年龄
            tv_number.setText("编号:" + detailBean.getPatID());//编号
            if (detailBean.getZyDetail().getArrears().startsWith("-")) { //金额
                tv_total_fee.setText("金额:" + detailBean.getZyDetail().getArrears());
                tv_total_fee.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            } else {
                tv_total_fee.setText("金额:" + detailBean.getZyDetail().getArrears());
            }

            if (null != detailBean1) {
                tv_main.setText("主治:" + detailBean1.getAttend_PSName());//主治
                tv_in_Date.setText("入院日期:" + detailBean.getZyDetail().getIn_Date());//入院日期
                tv_MedicareType.setText("医保:" + detailBean.getMedicareType());//医保
                tv_ICD_Name.setText("判断:" + detailBean.getZyDetail().getICD_Name());//入院判断

                String roomAndBed = "";
                if (!TextUtils.isEmpty(detailBean1.getIn_Room()) && !TextUtils.isEmpty(detailBean1.getIn_Bed())) {
                    roomAndBed = detailBean1.getIn_Room() + "-" + detailBean1.getIn_Bed();
                } else {
                    if (!TextUtils.isEmpty(detailBean1.getIn_Room()))
                        roomAndBed = detailBean1.getIn_Room();
                    if (!TextUtils.isEmpty(detailBean1.getIn_Bed()))
                        roomAndBed = detailBean1.getIn_Bed();
                }

                tv_status.setText(roomAndBed);
                switch (detailBean1.getDay_Type()) {
                    case "0":
                        RL_status.setBackgroundResource(R.drawable.shape_pink_top_bg);
                        break;
                    case "1":
                        RL_status.setBackgroundResource(R.drawable.shape_orange_top_bg);
                        break;
                    case "2":
                        RL_status.setBackgroundResource(R.drawable.shape_green_top_bg);
                        break;
                    case "3":
                        RL_status.setBackgroundResource(R.drawable.shape_blue_top_bg);
                        break;
                }
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
                    String valueText = value.getPatName().toLowerCase() + value.getPatID().toLowerCase();//BedBean对象的name属性作为过滤的参数
                    BedBean.ZyDetailBean detailBean1 = value.getZyDetail();
                    if (null != detailBean1) {
                        valueText += detailBean1.getIn_Bed().toLowerCase() + detailBean1.getIn_Room().toLowerCase();
                    }
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
            if (getCount() == 0) T.showLong("没有与之匹配的患者!!!");
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
