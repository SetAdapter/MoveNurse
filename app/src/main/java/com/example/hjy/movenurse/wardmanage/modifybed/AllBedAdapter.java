package com.example.hjy.movenurse.wardmanage.modifybed;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.widget.CheckBox;
import android.widget.Filter;
import android.widget.Filterable;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.BedBean;

import java.util.ArrayList;
import java.util.List;

/**
 * 病房管理 空床列表 adapter
 * Created by fangs on 2017/9/18.
 */
public class AllBedAdapter extends RecyclerCommonAdapter<BedBean> implements Filterable {

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置

    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;

    private OnItemClickListner listner;

    public AllBedAdapter(AppCompatActivity context, List<BedBean> datas) {
        super(context, R.layout.item_all_bed, datas);

        mSinglePositions = new SparseBooleanArray();
    }

    @Override
    public void convert(ViewHolder holder, BedBean bedBean, int position) {
        BedBean.ZyDetailBean zyDetailBean = bedBean.getZyDetail();

        CheckBox tvBedId =  holder.getView(R.id.tvBedId);
        if (null != zyDetailBean){
            tvBedId.setText(zyDetailBean.getIn_Bed());
        }

        tvBedId.setChecked(isItemChecked(mSinglePositions, position));

        holder.setOnClickListener(R.id.tvBedId, v ->{
            if (null != mRv){
                //每次点击选择时，把看见的，看不见的的项目设置为非选择状态
                ViewHolder holder1 = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                if (null != holder1) {
                    CheckBox tvBedId1 =  holder1.getView(R.id.tvBedId);
                    tvBedId1.setChecked(isItemChecked(mSinglePositions, position));
                } else {
                    notifyItemChanged(mSelectedPos);
                }

                if (mSelectedPos == position){
                    tvBedId.setChecked(false);
                    mSelectedPos = -1;
                } else {
                    tvBedId.setChecked(true);

                    mSelectedPos = position;
                }
            }

            if (isItemChecked(mSinglePositions, position)) {
                setItemChecked(position, false);
            } else {
                setItemChecked(position, true);
            }

            if (null != listner){
                listner.onClick();
            }
        });
    }

    public void setListner(OnItemClickListner listner) {
        this.listner = listner;
    }

    /**
     * 单选 设置条目选中状态
     * @param position
     * @param isChecked
     */
    private void setItemChecked(int position, boolean isChecked) {
        mSinglePositions.clear();
        setItemChecked(mSinglePositions, position, isChecked);
    }

    public void chear(){
        mSinglePositions.clear();
        mSelectedPos = -1;
    }

    public void setmRv(RecyclerView mRv) {
        this.mRv = mRv;
    }

    public int getmSelectedPos() {
        return mSelectedPos;
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
                    mOriginalValues = new ArrayList<>(getmDatas());
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
                    BedBean.ZyDetailBean zyDetailBean = value.getZyDetail();
                    if (null == zyDetailBean) continue;
                    final String valueText = zyDetailBean.getIn_Bed().toLowerCase();//BedBean对象的name属性作为过滤的参数
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
            setmDatas((List<BedBean>) results.values);//此时，Adapter数据源就是过滤后的Results
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

    public interface OnItemClickListner{
        void onClick();
    }
}
