package com.example.hjy.movenurse.adapter;

import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.example.hjy.movenurse.edit.nursing.AntenatalExpectantListAdapter;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.AntenatalExpectant;

import java.util.List;

/**
 * Created by Gab on 2017/12/28 0028.
 * 新生儿科呼吸机辅助呼吸记录单
 */

public class RespiratorAuxiliaryRecordAdapter extends RecyclerCommonAdapter<AntenatalExpectant> {

    private SparseArray<AntenatalExpectantListAdapter> mAdpters;

    public RespiratorAuxiliaryRecordAdapter(AppCompatActivity context, List<AntenatalExpectant> datas) {
        super(context, R.layout.item_antenatal_list, datas);
        mAdpters = new SparseArray<>();
    }

    @Override
    public void convert(ViewHolder holder, AntenatalExpectant dictsBean, int position) {
        TextView tv_title = holder.getView(R.id.tv_title);
        tv_title.setText(dictsBean.getDict_TypeName());// 数据对
        RecyclerView Lv_list = holder.getView(R.id.lv_list);
        GridLayoutManager gManager = new GridLayoutManager(mContext, 2);
        Lv_list.setLayoutManager(gManager);
        AntenatalExpectantListAdapter departAdapter = new AntenatalExpectantListAdapter(mContext, dictsBean.getDicts());
        departAdapter.setmRv(Lv_list);
        Lv_list.setAdapter(departAdapter);
        mAdpters.put(position, departAdapter);
    }

    public SparseArray<AntenatalExpectantListAdapter> getAdpters() {
        return mAdpters;
    }
}
