package com.fy.base.recyclerv;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView 通用的Adapter
 * Created by fangs on 2017/7/31.
 */
public abstract class RecyclerCommonAdapter<T> extends RecyclerView.Adapter<ViewHolder> {

    protected AppCompatActivity mContext;
    protected int mLayoutId;
    protected List<T> mDatas;
    protected LayoutInflater mInflater;

    protected SparseBooleanArray mSelectedPositions;//保存多选 数据

    public RecyclerCommonAdapter(AppCompatActivity context, int layoutId, List<T> datas) {
        mContext = context;
        mInflater = LayoutInflater.from(context);
        mLayoutId = layoutId;
        mDatas = datas;

        mSelectedPositions = new SparseBooleanArray();
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        ViewHolder viewHolder = ViewHolder.get(mContext, parent, mLayoutId);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        convert(holder, mDatas.get(position), position);
    }

    /**
     * 渲染数据到View中
     * @param holder
     * @param t
     */
    public abstract void convert(ViewHolder holder, T t, int position);

    @Override
    public int getItemCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    public List<T> getmDatas() {
        return mDatas;
    }

    public void setmDatas(List<T> mDatas) {
        this.mDatas = mDatas;
    }


    /**
     * 添加data，从指定location中加入
     * @param location
     * @param item
     */
    public void addData(int location, T item){
        mDatas.add(location, item);
    }

    /**
     * 删除指定 Location 位置的data
     * @param location
     */
    public void removeData(int location){
        mDatas.remove(location);
    }

    /**
     * 清理 多选状态
     */
    public void cleanChecked(){
        mSelectedPositions.clear();
    }
    /**
     * 设置给定位置条目的选择状态
     * @param array
     * @param position
     * @param isChecked
     */
    protected void setItemChecked(SparseBooleanArray array, int position, boolean isChecked){
        array.put(position, isChecked);
    }

    /**
     * 根据位置判断条目是否选中
     * @param position
     * @return
     */
    protected boolean isItemChecked(SparseBooleanArray array, int position) {
        return array.get(position);
    }

    /**
     * 设置全选 or 反选
     * @param isAllSelect
     */
    public void setIsAllSelect(boolean isAllSelect){
        for (int i = 0; i < getItemCount(); i++){
            setItemChecked(mSelectedPositions, i, isAllSelect);
        }

        notifyDataSetChanged();
    }

    public SparseBooleanArray getmSelectedPositions() {
        return mSelectedPositions;
    }
}
