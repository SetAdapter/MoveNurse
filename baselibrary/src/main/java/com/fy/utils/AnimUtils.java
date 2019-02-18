package com.fy.utils;

import android.animation.ObjectAnimator;
import android.widget.ImageView;

/**
 * 属性动画 工具类
 * Created by fangs on 2017/10/30.
 */
public class AnimUtils {

    /**
     * 箭头的动画
     */
    public static void doArrowAnim(ImageView iv_arrow, boolean isExpand) {
        if (isExpand) {
            // 当前是收起，箭头由上变为下
            ObjectAnimator.ofFloat(iv_arrow, "rotation", -180, 0).start();
        } else {
            // 当前是展开，箭头由下变为上
            ObjectAnimator.ofFloat(iv_arrow, "rotation", 0, 180).start();
        }
    }


}
