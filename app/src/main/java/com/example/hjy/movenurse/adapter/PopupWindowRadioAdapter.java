package com.example.hjy.movenurse.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;

import java.util.List;

/**
 * Created by Gab on 2017/10/25 0025.
 * 单选
 */

public class PopupWindowRadioAdapter extends RecyclerCommonAdapter<String> {

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置
    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;
    private PopupWindowRadioAdapter.OnItemClickListner listner;

    public PopupWindowRadioAdapter(AppCompatActivity context, List<String> datas) {
        super(context, R.layout.item_health_listview, datas);
        mSinglePositions = new SparseBooleanArray();
    }

    @Override
    public void convert(ViewHolder holder, String dictsBean, int position) {
        CheckBox mMCheck = holder.getView(R.id.tv_DepartId);
        mMCheck.setText(dictsBean);
        mMCheck.setChecked(isItemChecked(mSelectedPositions, position));
        mMCheck.setOnClickListener(v -> {
            if (null != mRv) {
                //每次点击选择时，把看见的，看不见的的项目设置为非选择状态
                ViewHolder holder1 = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                if (null != holder1) {
                    CheckBox tvBedId1 = holder1.getView(R.id.tv_DepartId);
                    tvBedId1.setChecked(isItemChecked(mSinglePositions, position));
                } else {
                    notifyItemChanged(mSelectedPos);
                }

                if (mSelectedPos == position) {
                    mMCheck.setChecked(false);
                    mSelectedPos = -1;
                } else {
                    mMCheck.setChecked(true);

                    mSelectedPos = position;
                }
            }

            if (isItemChecked(mSinglePositions, position)) {
                setItemChecked(position, false);
            } else {
                setItemChecked(position, true);
            }
            if (null != listner) {
                listner.onClick(dictsBean);
            }
        });
    }

    /**
     * 单选 设置条目选中状态
     *
     * @param position
     * @param isChecked
     */
    private void setItemChecked(int position, boolean isChecked) {
        mSinglePositions.clear();
        setItemChecked(mSinglePositions, position, isChecked);
    }

    public void setmRv(RecyclerView mRv) {
        this.mRv = mRv;
    }

    public int getmSelectedPos() {
        return mSelectedPos;
    }


    public void setListner(PopupWindowRadioAdapter.OnItemClickListner listner) {
        this.listner = listner;
    }

    public interface OnItemClickListner {
        void onClick(String dictsBean);
    }
}
