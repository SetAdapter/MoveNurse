package com.fy.utils;

import android.content.Context;
import android.widget.TextView;

/**
 * 通过 getResources() 为控件 设置内容
 * Created by fangs on 2017/9/13.
 */
public class ResourceUtils {

    private ResourceUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

//    %d   （表示整数）
//    %f   （表示浮点数）
//    %s   （表示字符串）
    /**
     * %1$s Android string （java & Android 格式化字符串）
     * @param ctx
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$s”）
     * @param replaceStr 将要替换的内容
     */
    public static void setText(Context ctx, TextView tv, int id, String replaceStr){
        String format = ctx.getResources().getString(id);
        String text = String.format(format, replaceStr);
        tv.setText(text);
    }

    /**
     * %1$d Android string （java & Android 格式化字符串）
     * @param ctx
     * @param tv
     * @param id 资源ID（如：ID内容为 “病人ID：%1$d”）
     * @param replaceStr 将要替换的内容
     */
    public static void setText(Context ctx, TextView tv, int id, int replaceStr){
        String format = ctx.getResources().getString(id);
        String text = String.format(format, replaceStr);
        tv.setText(text);
    }
}
