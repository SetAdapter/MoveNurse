package com.example.hjy.movenurse.upload;

import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.hjy.movenurse.R;
import com.fy.base.recyclerv.RecyclerCommonAdapter;
import com.fy.base.recyclerv.ViewHolder;
import com.fy.entity.UploadBean;

import java.util.List;

/**
 * 上传列表 adapter
 * <p/>Created by fangs on 2017/10/11.
 */
public class UpLoadListAdapter extends RecyclerCommonAdapter<UploadBean> {

    OnViewClickListner listner;

    public UpLoadListAdapter(AppCompatActivity context, List<UploadBean> datas) {
        super(context, R.layout.item_upload_list, datas);
    }

    @Override
    public void convert(ViewHolder holder, UploadBean uploadBean, int position) {
        holder.setText(R.id.tvPatinID, uploadBean.getPatID());
        holder.setText(R.id.tvPatinName, uploadBean.getPatinName());
        holder.setText(R.id.tvCacheTime, uploadBean.getTime());
        holder.setText(R.id.tvDesc, UpLoadUtils.getFunDesc(uploadBean.getApi()));

        View llParent = holder.getView(R.id.llParent);
        int bgColor = position % 2 == 0 ? R.color.white : R.color.divider;
        llParent.setBackgroundResource(bgColor);

        View view = holder.getView(R.id.tvUpload);
        view.setOnClickListener(v -> {
            if (null != listner) {
                listner.onClick(uploadBean);
            }
        });
    }

    public void setListner(OnViewClickListner listner) {
        this.listner = listner;
    }

    public interface OnViewClickListner {
        void onClick(UploadBean uploadBean);
    }
}
