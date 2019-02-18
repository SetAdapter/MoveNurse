package com.example.hjy.movenurse.adapter;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.restraint.AntenatalEvent;
import com.example.hjy.movenurse.restraint.AntenatalsEvent;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.AntenatalExpectant;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 获取动态数据 按钮 第一层adapter 产前待产
 */

public class AntenatalSoleAdapter extends RecyclerCommonAdapter<AntenatalExpectant> {

    private SparseArray<AntenatalExpectantSoleListAdapter> mAdpters;

    public AntenatalSoleAdapter(AppCompatActivity context, List<AntenatalExpectant> datas) {
        super(context, R.layout.item_antenatal_sole_list, datas);
        mAdpters = new SparseArray<>();
    }

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public void convert(ViewHolder holder, AntenatalExpectant dictsBean, int position) {
        TextView tv_title = holder.getView(R.id.tv_title);
        tv_title.setText(dictsBean.getDict_TypeName());// 数据对
        RecyclerView Lv_list = holder.getView(R.id.lv_list);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 4);
        Lv_list.setLayoutManager(gManager);

        AntenatalExpectantSoleListAdapter departAdapter = new AntenatalExpectantSoleListAdapter(mContext, dictsBean.getDicts());
        departAdapter.setmRv(Lv_list);
        Lv_list.setAdapter(departAdapter);
        mAdpters.put(position, departAdapter);
    }

    public SparseArray<AntenatalExpectantSoleListAdapter> getAdpters() {
        return mAdpters;
    }
}
