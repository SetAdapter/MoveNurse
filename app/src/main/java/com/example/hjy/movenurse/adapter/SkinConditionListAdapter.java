package com.example.hjy.movenurse.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.HealthGuidanceBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 *  选择部位压疮
 */

public class SkinConditionListAdapter extends RecyclerCommonAdapter<HealthGuidanceBean> {

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置
    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;
    private OnItemClickListner listner;

    public SkinConditionListAdapter(AppCompatActivity context, List<HealthGuidanceBean> datas) {
        super(context, R.layout.item_depart_list, datas);
        mSinglePositions = new SparseBooleanArray();
    }

    @Override
    public void convert(ViewHolder holder, HealthGuidanceBean dictsBean, int position) {
        CheckBox tvBedId = holder.getView(R.id.tvDepartId);
        tvBedId.setText(dictsBean.getDict_TypeName());

        tvBedId.setChecked(isItemChecked(mSinglePositions, position));
        tvBedId.setOnClickListener(v -> {
            if (null != mRv) {
                //每次点击选择时，把看见的，看不见的的项目设置为非选择状态
                ViewHolder holder1 = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                if (null != holder1) {
                    CheckBox tvBedId1 = holder1.getView(R.id.tvDepartId);
                    tvBedId1.setChecked(isItemChecked(mSinglePositions, position));
                } else {
                    notifyItemChanged(mSelectedPos);
                }

                if (mSelectedPos == position) {
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

    public void setmSelectedPos(int mSelectedPos) {
        this.mSelectedPos = mSelectedPos;
        mSinglePositions.clear();
    }

    public void setListner(OnItemClickListner listner) {
        this.listner = listner;
    }



    public interface OnItemClickListner{
        void onClick(HealthGuidanceBean dictsBean);
    }
}
