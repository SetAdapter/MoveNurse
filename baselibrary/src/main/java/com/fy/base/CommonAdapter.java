package com.fy.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

import java.util.List;

/**
 * 万能 ListView GridView 适配器
 * <p/>
 * Created by fangs on 2017/4/5.
 */
public abstract class CommonAdapter<Data> extends BaseAdapter {

    protected LayoutInflater mInflater;
    protected Context mContext;
    protected List<Data> mDatas;
    protected final int mItemLayoutId;

    public CommonAdapter(Context context, List<Data> mDatas, int itemLayoutId) {
        mInflater = LayoutInflater.from(context);
        this.mContext = context;
        this.mDatas = mDatas;
        this.mItemLayoutId = itemLayoutId;
    }

    public Context getContext() {
        return mContext;
    }

    @Override
    public int getCount() {
        return null == mDatas ? 0 : mDatas.size();
    }

    @Override
    public Data getItem(int position) {
        return mDatas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHolder viewHolder = getViewHolder(position, convertView, parent);
        convert(viewHolder, getItem(position));

        return viewHolder.getConvertView();
    }

    /**
     * 只需要重写本抽象方法，通过ViewHolder.getView()方法获得控件（如：button），然后开始玩耍了....
     * @param helper
     * @param item
     */
    public abstract void convert(ViewHolder helper, Data item);

    private ViewHolder getViewHolder(int position, View convertView, ViewGroup parent) {

        return ViewHolder.get(mContext, convertView, parent, mItemLayoutId, position);
    }

    /**
     * 局部更新数据，调用一次getView()方法；Google推荐的做法
     *
     * @param listView 要更新的listview
     * @param position 要更新的位置
     */
    public void notifyDataSetChanged(ListView listView, int position) {
        /**第一个可见的位置**/
        int firstVisiblePosition = listView.getFirstVisiblePosition();
        /**最后一个可见的位置**/
        int lastVisiblePosition = listView.getLastVisiblePosition();

        /**在看见范围内才更新，不可见的滑动后自动会调用getView方法更新**/
        if (position >= firstVisiblePosition && position <= lastVisiblePosition) {
            /**获取指定位置view对象**/
            View view = listView.getChildAt(position - firstVisiblePosition);
            getView(position, view, listView);
        }
    }

    public List<Data> getmDatas() {
        return mDatas;
    }

    /**
     * 设置data,覆盖原有数据
     * @param data
     */
    public void setData(List<Data> data){
        this.mDatas = data;
    }

    /**
     * 添加 data,添加一组data
     * @param data
     */
    public void addData(List<Data> data){
        this.mDatas.addAll(data);
    }

    /**
     * 添加 data,添加一个data
     * @param data
     */
    public void addData(Data data) {
        this.mDatas.add(data);
    }

    /**
     * 刷新
     */
    public void clearCache(){
        this.notifyDataSetChanged();
    }


}
