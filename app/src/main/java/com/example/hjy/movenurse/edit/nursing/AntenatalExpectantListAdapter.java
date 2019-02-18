package com.example.hjy.movenurse.edit.nursing;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.AntenatalExpectant;

import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 获取动态数据 按钮 第二层adapter
 */

public class AntenatalExpectantListAdapter extends RecyclerCommonAdapter<AntenatalExpectant.DictsBean> {

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置
    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;
    private AntenatalExpectantListAdapter.OnItemClickListner listner;

    public AntenatalExpectantListAdapter(AppCompatActivity context, List<AntenatalExpectant.DictsBean> datas) {
        super(context, R.layout.item_antenatal_listview, datas);
        mSinglePositions = new SparseBooleanArray();
    }

    @Override
    public void convert(ViewHolder holder, AntenatalExpectant.DictsBean dictsBean, int position) {
        CheckBox tv_DepartId = holder.getView(R.id.tv_DepartId);
        tv_DepartId.setText(dictsBean.getDict_TypeName());
        tv_DepartId.setChecked(isItemChecked(mSinglePositions, position));
        tv_DepartId.setOnClickListener(v -> {
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
                    tv_DepartId.setChecked(false);
                    mSelectedPos = -1;
                } else {
                    tv_DepartId.setChecked(true);

                    mSelectedPos = position;
                }
            }

            if (isItemChecked(mSinglePositions, position)) {
                setItemChecked(position, false);
            } else {
                setItemChecked(position, true);
            }

            if (null != listner) {
                listner.onClick(dictsBean.getDict_TypeName());
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

    public void setListner(AntenatalExpectantListAdapter.OnItemClickListner listner) {
        this.listner = listner;
    }

    public interface OnItemClickListner {
        void onClick(String data);
    }
}
