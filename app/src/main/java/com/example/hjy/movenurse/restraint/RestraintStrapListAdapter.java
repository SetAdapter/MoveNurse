package com.example.hjy.movenurse.restraint;

import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.PatientsBean;
import com.fy.entity.RestraintStrapBean;
import com.fy.utils.cache.ACache;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Created by Gab on 2017/10/17 0017.
 * checkbox 选择 adapter
 */

public class RestraintStrapListAdapter extends RecyclerCommonAdapter<RestraintStrapBean.DictsBean> {

    private boolean isShowEdit;
    protected ACache mCache;

    public RestraintStrapListAdapter(AppCompatActivity context, List<RestraintStrapBean.DictsBean> datas) {
        super(context, R.layout.item_restraint_listview, datas);
        mCache = ACache.get(context);
    }

    @Override
    public void convert(ViewHolder holder, RestraintStrapBean.DictsBean dictsBean, int position) {
        CheckBox mCheck = holder.getView(R.id.tv_DepartId);
        TextView tv_context = holder.getView(R.id.tv_context);
        EditText other_matter = holder.getView(R.id.other_matter);
        mCheck.setText(dictsBean.getDict_TypeName());
        mCheck.setChecked(isItemChecked(mSelectedPositions, position));
        mCheck.setOnClickListener(v -> {
            if (isItemChecked(mSelectedPositions, position)) {
                setItemChecked(mSelectedPositions, position, false);
            } else {
                setItemChecked(mSelectedPositions, position, true);
            }
        });

        if (isShowEdit){
            other_matter.setVisibility(View.VISIBLE);
            other_matter.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                }

                @Override
                public void onTextChanged(CharSequence s, int start, int before, int count) {

                }

                @Override
                public void afterTextChanged(Editable s) {
                    EventBus.getDefault().post(new RestraintsEvent(s.toString()));
                }
            });
        }
        PatientsBean patin = (PatientsBean) mCache.getAsObject("PatName");
        if (dictsBean.getDict_TypeID().equals("100040301")){//意见
            tv_context.setText(dictsBean.getDict_TypeName().replace("AA",patin.getPatName()));
            mCheck.setVisibility(View.GONE);
            tv_context.setVisibility(View.VISIBLE);
        }
        if (dictsBean.getDict_TypeID().equals("100040401")){//备注
            tv_context.setText(dictsBean.getDict_TypeName());
            mCheck.setVisibility(View.GONE);
            tv_context.setVisibility(View.VISIBLE);
        }
        if (dictsBean.getDict_TypeID().equals("100040201")){
            tv_context.setText(dictsBean.getDict_TypeName());
            tv_context.setVisibility(View.VISIBLE);
            mCheck.setVisibility(View.GONE);
        }

    }

    public void setShowEdit(boolean showEdit) {
        isShowEdit = showEdit;
    }

}
