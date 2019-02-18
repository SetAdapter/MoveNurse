package com.example.hjy.movenurse.health;

import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.HealthGuidancesBean;

import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 * checkbox 选择 adapter
 */

public class HealthGuidanceListAdapter extends RecyclerCommonAdapter<HealthGuidancesBean.DictsBean> {

    private OnItemClickListner listner;

    public HealthGuidanceListAdapter(AppCompatActivity context, List<HealthGuidancesBean.DictsBean> datas) {
        super(context, R.layout.item_guidance_listview, datas);
    }

    @Override
    public void convert(ViewHolder holder, HealthGuidancesBean.DictsBean dictsBean, int position) {
        CheckBox mMCheck = holder.getView(R.id.tv_DepartId);
        mMCheck.setText(dictsBean.getDict_TypeName());

        mMCheck.setChecked(isItemChecked(mSelectedPositions, position));
        mMCheck.setOnClickListener(v -> {
            if (isItemChecked(mSelectedPositions, position)) {
                setItemChecked(mSelectedPositions, position, false);
            } else {
                setItemChecked(mSelectedPositions, position, true);
            }
        });
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public void setListner(OnItemClickListner listner) {
        this.listner = listner;
    }


    public interface OnItemClickListner {
        void onClick(HealthGuidancesBean dictsBean);
    }
}
