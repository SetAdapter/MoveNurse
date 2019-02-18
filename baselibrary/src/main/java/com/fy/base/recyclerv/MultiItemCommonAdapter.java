package com.fy.base.recyclerv;

import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;

import java.util.List;

/**
 * RecyclerView多种ItemViewType 的adapter
 * Created by 下载 on 2017/7/31.
 */
public abstract class MultiItemCommonAdapter<T> extends RecyclerCommonAdapter<T> {

    protected MultiItemTypeSupport<T> mMultiItemTypeSupport;

    public MultiItemCommonAdapter(AppCompatActivity context, List<T> datas,
                                  MultiItemTypeSupport<T> multiItemTypeSupport) {
        super(context, -1, datas);
        mMultiItemTypeSupport = multiItemTypeSupport;
    }

    @Override
    public int getItemViewType(int position) {
        return mMultiItemTypeSupport.getItemViewType(position, mDatas.get(position));
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        int layoutId = mMultiItemTypeSupport.getLayoutId(viewType);
        ViewHolder holder = ViewHolder.get(mContext, parent, layoutId);
        return holder;
    }


}
