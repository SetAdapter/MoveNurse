package com.example.hjy.movenurse.wardmanage.modifydepartment;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.SparseBooleanArray;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.DepartBean;

import java.util.List;

/**
 * 科室列表
 * Created by fangs on 2017/9/19.
 */
public class DepartListAdapter extends RecyclerCommonAdapter<DepartBean> {

    private int mSelectedPos = -1;//实现单选, 保存上次选中的位置
    private SparseBooleanArray mSinglePositions;  //保存单选 数据
    private RecyclerView mRv;

    private OnItemClickListener listener;

    public DepartListAdapter(AppCompatActivity context, List<DepartBean> datas) {
        super(context, R.layout.item_depart_list, datas);
        mSinglePositions = new SparseBooleanArray();
    }

    @Override
    public void convert(ViewHolder holder, DepartBean departBean, int position) {

        CheckBox tvBedId = holder.getView(R.id.tvDepartId);
        tvBedId.setText(departBean.getDict_TypeName());

        tvBedId.setChecked(isItemChecked(mSinglePositions, position));
        tvBedId.setOnClickListener(v -> {
            if (null != mRv){
                //每次点击选择时，把看见的，看不见的的项目设置为非选择状态
                ViewHolder holder1 = (ViewHolder) mRv.findViewHolderForLayoutPosition(mSelectedPos);
                if (null != holder1) {
                    CheckBox tvBedId1 =  holder1.getView(R.id.tvDepartId);
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

            listener.onItemClick(departBean);
        });
    }

    interface OnItemClickListener{
        void onItemClick(DepartBean departBean);
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

    public void setmRv(RecyclerView mRv) {
        this.mRv = mRv;
    }

    public int getmSelectedPos() {
        return mSelectedPos;
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }
}
