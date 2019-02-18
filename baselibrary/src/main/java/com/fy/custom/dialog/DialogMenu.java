package com.fy.custom.dialog;

import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.fy.base.AdapterBase;
import com.fy.base.DialogBase;
import com.fy.entity.DialogEntity;

import java.util.List;

import fy.library.com.baselibrary.R;

/**
 * 菜单dialog
 * Created by fangs on 2017/3/13.
 */
public class DialogMenu extends DialogBase {

    private TextView tvCancel;
    private ListView lvMenu;

    private List<DialogEntity> data;
    private OnItemOnClickListener listener;

    @Override
    protected void clickOutside() {
        setHide(true);
    }

    @Override
    protected int getContentLayout() {
        return R.layout.dialog_menu;
    }

    @Override
    protected void baseInit() {
        lvMenu = (ListView) getView().findViewById(R.id.lvMenu);
        lvMenu.setAdapter(new MenuDialogAdapter((AppCompatActivity) getActivity(), data));
        lvMenu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                dismiss();
                if (null != listener) {
                    DialogEntity entity = (DialogEntity) view.getTag();
                    listener.onItemClick(entity, position);
                }
            }
        });

        tvCancel = (TextView) getView().findViewById(R.id.tvCancel);
        tvCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });

        setGravity(Gravity.BOTTOM);
        setAnim(R.style.AnimUp);
        setTransparent(true);

        super.baseInit();
    }

    public List<DialogEntity> getData() {
        return data;
    }

    public void setData(List<DialogEntity> data) {
        this.data = data;
    }

    public OnItemOnClickListener getListener() {
        return listener;
    }

    public void setListener(OnItemOnClickListener listener) {
        this.listener = listener;
    }

    public static class Builder {
        List<DialogEntity> data;
        OnItemOnClickListener listener;
        DialogMenu dialog;
        public Builder() {
            this.dialog = new DialogMenu();
        }

        public Builder setData(List<DialogEntity> data) {
            this.data = data;
            this.dialog.setData(data);
            return this;
        }

        public Builder setListener(OnItemOnClickListener listener) {
            this.listener = listener;
            this.dialog.setListener(listener);
            return this;
        }

        public DialogMenu create(){
            return dialog;
        }
    }

    /**
     * 功能描述：弹窗子类项按钮监听事件
     */
    public interface OnItemOnClickListener {
        /** 弹出子类项点击回调方法 */
        void onItemClick(DialogEntity item, int position);
    }


    /**
     * 菜单列表adapter
     */
    class MenuDialogAdapter extends AdapterBase<DialogEntity> {

        public MenuDialogAdapter(AppCompatActivity context, List<DialogEntity> data) {
            super(context, data);
        }

        @Override
        public View getView(int position, View arg1, ViewGroup arg2) {
            View itemView = getViewCache().get(position);

            if (null == itemView) {
                final DialogEntity item = getData().get(position);
                itemView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_menu_item, null);

                TextView textMenu = (TextView) itemView.findViewById(R.id.textMenu);
                textMenu.setText(item.getMenuName());

                if (1 == getCount()) {
                    textMenu.setBackgroundResource(R.drawable.select_dialog_item_bg_only);
                } else if (position == 0) {
                    textMenu.setBackgroundResource(R.drawable.select_dialog_item_bg_top);
                } else if (position == getCount() - 1) {
                    textMenu.setBackgroundResource(R.drawable.select_dialog_item_bg_buttom);
                } else {
                    textMenu.setBackgroundResource(R.drawable.select_dialog_item_bg_center);
                }
                
                itemView.setTag(item);
                getViewCache().put(position, itemView);
            }

            return itemView;
        }
    }
}
