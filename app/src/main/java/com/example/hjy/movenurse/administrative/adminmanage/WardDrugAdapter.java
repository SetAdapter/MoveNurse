package com.example.hjy.movenurse.administrative.adminmanage;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.PainbBillDetailsBean;
import com.fy.utils.T;

import java.util.ArrayList;
import java.util.List;

/**
 * 行政管理 病区退药 数据列表 adapter
 * <p/>Created by fangs on 2017/9/30.
 */
public class WardDrugAdapter extends RecyclerCommonAdapter<PainbBillDetailsBean.ResultDataBean> implements Filterable{

    SparseArray editArray;

    public WardDrugAdapter(AppCompatActivity context, List<PainbBillDetailsBean.ResultDataBean> datas) {
        super(context, R.layout.item_drug_grid, datas);
        editArray = new SparseArray();
    }

    @Override
    public void convert(ViewHolder holder, PainbBillDetailsBean.ResultDataBean resultDataBean, int position) {
        holder.setIsRecyclable(false);

        holder.setText(R.id.billNo, resultDataBean.getBillNo());
        holder.setText(R.id.tv_name, "药物名称:  " + resultDataBean.getProjectName()); //项目名称
        //holder.setText(R.id.tv_count, "退药数量:  " + resultDataBean.getAmount()); //单位
        holder.setText(R.id.tv_standard, "规格:  "+resultDataBean.getItemSpec()); //规格
        holder.setText(R.id.tv_company, "单位: "+resultDataBean.getMeasure()); //单位
        holder.setText(R.id.tv_time, "计费时间: "+resultDataBean.getPSDate()); //计费时间
        //填写退药数量
        EditText et_count = (EditText) holder.getView(R.id.et_count);
        et_count.setHint(resultDataBean.getAmount()+"");

        //1、为了避免TextWatcher在第2步被调用，提前将他移除。
        if (et_count.getTag() instanceof TextWatcher) {
            et_count.removeTextChangedListener((TextWatcher) et_count.getTag());
        }

        // 第2步：移除TextWatcher之后，设置EditText的Text。
        if (editArray.size() > 0) {
            String editConent = (String) editArray.get(position);
            if (!TextUtils.isEmpty(editConent)){
                et_count.setText(editConent);
            }
        }

        TextWatcher watcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) { }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if(s.length()>0){
                    double counts = Double.parseDouble(s.toString());
                    if(counts<0||counts>=Double.parseDouble(resultDataBean.getAmount())){
                        T.showShort("退药数量超过限制");
                    }
                }

                editArray.put(position, s.toString());
            }
            @Override
            public void afterTextChanged(Editable editable) { }
        };
        et_count.addTextChangedListener(watcher);
        et_count.setTag(watcher);

        CheckBox select = holder.getView(R.id.drug_select);
        select.setChecked(isItemChecked(mSelectedPositions, position));
        select.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isItemChecked(mSelectedPositions, position)) {
                    setItemChecked(mSelectedPositions, position, false);
                } else {
                    setItemChecked(mSelectedPositions, position, true);
                }
            }
        });
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
                    mOriginalValues = new ArrayList<>(getmDatas());
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
            setmDatas((List<PainbBillDetailsBean.ResultDataBean>) results.values);//此时，Adapter数据源就是过滤后的Results
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
