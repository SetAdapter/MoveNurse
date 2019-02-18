package com.example.hjy.movenurse.adapter;

import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;

import java.util.List;

/**
 * Created by Gab on 2017/10/25 0025.
 *
 */

public class PopupWindowAdapter extends RecyclerCommonAdapter<String> {

    private PopupWindowAdapter.OnItemClickListner listner;

    public PopupWindowAdapter(AppCompatActivity context, List<String> datas) {
        super(context, R.layout.item_health_listview, datas);
    }

    @Override
    public void convert(ViewHolder holder, String dictsBean, int position) {
        CheckBox mMCheck = holder.getView(R.id.tv_DepartId);
        mMCheck.setText(dictsBean);
        mMCheck.setChecked(isItemChecked(mSelectedPositions, position));
        mMCheck.setOnClickListener(v -> {
            if (isItemChecked(mSelectedPositions, position)) {
                setItemChecked(mSelectedPositions, position, false);
            } else {
                setItemChecked(mSelectedPositions, position, true);
            }
            if (null != listner) {
                listner.onClick(dictsBean);
            }
        });
    }

    public void setListner(PopupWindowAdapter.OnItemClickListner listner) {
        this.listner = listner;
    }

    public interface OnItemClickListner {
        void onClick(String dictsBean);
    }
}
