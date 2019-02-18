package com.example.hjy.movenurse.adapter;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.os.Build;
import android.support.annotation.RequiresApi;
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
import com.fy.entity.PatientsBean;
import com.fy.utils.ResourceUtils;
import com.fy.utils.T;
import com.fy.utils.TimeUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 患者资料 列表adapter
 * Created by Gab on 2017/8/30 0030.
 */
public class PatientDataAdapter extends AdapterBase<PatientsBean> implements Filterable {

    public PatientDataAdapter(AppCompatActivity context, List data) {
        super(context, data);
    }

    @SuppressLint("StringFormatMatches")
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View view, ViewGroup parent) {
        View itemView = getViewCache().get(position);
        if (null == itemView) {
            final PatientsBean detailBean = getData().get(position);
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.item_patientdata, null);
            TextView tv_name = (TextView) itemView.findViewById(R.id.tv_name);
            TextView tv_age = (TextView) itemView.findViewById(R.id.tv_age);
            TextView tv_number = (TextView) itemView.findViewById(R.id.tv_number);
            TextView tv_main = (TextView) itemView.findViewById(R.id.tv_main);
            TextView tv_status = (TextView) itemView.findViewById(R.id.tv_status);
            TextView tv_total_fee = (TextView) itemView.findViewById(R.id.tv_total_fee);
            TextView tv_MedicareType = (TextView) itemView.findViewById(R.id.tv_MedicareType);
            TextView tv_in_Date = (TextView) itemView.findViewById(R.id.tv_in_Date);
            TextView tv_ICD_Name = (TextView) itemView.findViewById(R.id.tv_ICD_Name);
            ImageView iv_sex = (ImageView) itemView.findViewById(R.id.iv_sex);
            ImageView iv_HaveOrders = (ImageView) itemView.findViewById(R.id.iv_HaveOrders);
            RelativeLayout RL_status = (RelativeLayout) itemView.findViewById(R.id.RL_status);
            ResourceUtils.setText(getContext(), tv_name, R.string.name, detailBean.getPatName());
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

            if (detailBean.getZyDetail().getHaveOrders().equals("0")) { //肿瘤患者
                iv_HaveOrders.setVisibility(View.GONE);
            }else {
                iv_HaveOrders.setVisibility(View.VISIBLE);
            }

            PatientsBean.ZyDetailBean detailBean1 = detailBean.getZyDetail();
            int age = TimeUtils.calculationAge(detailBean.getBirthDay(), "yyyyMMdd");
            ResourceUtils.setText(getContext(), tv_age, R.string.age,detailBean.getAge());//年龄
            tv_number.setText("编号：" + detailBean.getPatID());//编号
            if (detailBean.getZyDetail().getArrears().startsWith("-")) { //金额
                tv_total_fee.setText("金额：" + detailBean.getZyDetail().getArrears());
                tv_total_fee.setTextColor(ContextCompat.getColor(getContext(), R.color.red));
            } else {
                tv_total_fee.setText("金额：" + detailBean.getZyDetail().getArrears());
            }

            if (null != detailBean1) {
                tv_main.setText("主治：" + detailBean1.getAttend_PSName());//主治
                tv_in_Date.setText("入院日期：" + detailBean.getZyDetail().getIn_Date());//入院日期
                tv_MedicareType.setText("医保：" + detailBean.getMedicareType());//医保
                tv_ICD_Name.setText("入院诊断：" + detailBean.getZyDetail().getICD_Name());//入院判断

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
            itemView.setTag(detailBean);
            getViewCache().put(position, itemView);
        }

        return itemView;
    }


    //过滤器上的锁可以同步复制原始数据。
    private final Object mLock = new Object();

    //对象数组的备份，当调用ArrayFilter的时候初始化和使用。此时，对象数组只包含已经过滤的数据。
    private ArrayList<PatientsBean> mOriginalValues;
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

            String filterCondition = prefix.toString().substring(0, 1);
            prefix = prefix.toString().substring(1, prefix.length());
            if ("单".equals(filterCondition)){
                //当首字母为空时
                if (prefix == null || prefix.length() == 0) {
                    ArrayList<PatientsBean> list;
                    synchronized (mLock) {//同步复制一个原始备份数据
                        list = new ArrayList<>(mOriginalValues);
                    }
                    results.values = list;
                    results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
                } else {
                    String prefixString = prefix.toString().toLowerCase();//转化为小写

                    ArrayList<PatientsBean> values;
                    synchronized (mLock) {//同步复制一个原始备份数据
                        values = new ArrayList<>(mOriginalValues);
                    }
                    final int count = values.size();
                    final ArrayList<PatientsBean> newValues = new ArrayList<>();

                    for (int i = 0; i < count; i++) {
                        final PatientsBean value = values.get(i);//从List<PatientsBean>中拿到PatientsBean对象
                        PatientsBean.ZyDetailBean detailBean1 = value.getZyDetail();

                        String valueText = value.getPatName().toLowerCase() + value.getPatID().toLowerCase();//PatientsBean对象的name属性作为过滤的参数
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

                    results.values = newValues;//此时的results就是过滤后的List<PatientsBean>数组
                    results.count = newValues.size();
                }
            } else {
                if (prefix == null || prefix.length() == 0) {
                    ArrayList<PatientsBean> list;
                    synchronized (mLock) {//同步复制一个原始备份数据
                        list = new ArrayList<>(mOriginalValues);
                    }
                    results.values = list;
                    results.count = list.size();//此时返回的results就是原始的数据，不进行过滤
                } else {

                    List<PatientsBean> newValues =  getFilterList(prefix);

                    results.values = newValues;//此时的results就是过滤后的List<PatientsBean>数组
                    results.count = newValues.size();
                }
            }

            return results;
        }

        //刷选结果
        @Override
        protected void publishResults(CharSequence prefix, FilterResults results) {
            //noinspection unchecked
            setData((List<PatientsBean>) results.values);//此时，Adapter数据源就是过滤后的Results
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

        private List<PatientsBean> getFilterList(CharSequence prefix){

            ArrayList<PatientsBean> values;
            synchronized (mLock) {//同步复制一个原始备份数据
                values = new ArrayList<>(mOriginalValues);
            }
            final int count = values.size();
            final List<PatientsBean> newValues = new ArrayList<>();

            for (int i = 0; i < count; i++) {
                final PatientsBean value = values.get(i);//从List<PatientsBean>中拿到PatientsBean对象
                PatientsBean.ZyDetailBean detailBean1 = value.getZyDetail();

                boolean isAddList = true;
                String[] array = prefix.toString().split("\\&");
                for (String str : array) {
                    switch (str){
                        case "肿瘤病人":
                            if (null == detailBean1 || !"1".equals(detailBean1.getIsTumour())) isAddList = false;
                            break;
                        case "住院超过30天病人":
                            if (null == detailBean1) {
                                isAddList = false;
                                break;
                            }
                            String in_Date = detailBean1.getIn_Date();
                            long timeLong = TimeUtils.timeString2long(in_Date, "yyyy-MM-dd");
                            if (System.currentTimeMillis() - timeLong < 30L * 24L * 3600L * 1000L) isAddList = false;
                            break;
                        case "有医嘱":
                            if (null == detailBean1) {
                                isAddList = false;
                                break;
                            }

                            String strHaveOrders = detailBean1.getHaveOrders();
                            int haveOrders = Integer.parseInt(strHaveOrders);
                            if (haveOrders <= 0) isAddList = false;
                            break;
                        case "男":
                            if (!"M".equals(value.getSex())) isAddList = false;
                            break;
                        case "女":
                            if (!"F".equals(value.getSex())) isAddList = false;
                            break;
                        case "欠费":
                            if (null == detailBean1) {
                                isAddList = false;
                                break;
                            }
                            String arrears = detailBean1.getArrears();
                            double arrearsDoub = Double.parseDouble(arrears);
                            if (arrearsDoub >= 0) isAddList = false;
                            break;
                        case "特级护理":
                            if (null == detailBean1 || !"0".equals(detailBean1.getDay_Type())) isAddList = false;
                            break;
                        case "一级护理":
                            if (null == detailBean1 || !"1".equals(detailBean1.getDay_Type())) isAddList = false;
                            break;
                        case "二级护理":
                            if (null == detailBean1 || !"2".equals(detailBean1.getDay_Type())) isAddList = false;
                            break;
                        case "三级护理":
                            if (null == detailBean1 || !"3".equals(detailBean1.getDay_Type())) isAddList = false;
                            break;
                        case "无护理级别":
                            if (null == detailBean1 || !"".equals(detailBean1.getDay_Type())) isAddList = false;
                            break;
                    }
                }

                if (isAddList) {
                    newValues.add(value);//将这个item加入到数组对象中
                }
            }

            return newValues;
        }
    }
}
