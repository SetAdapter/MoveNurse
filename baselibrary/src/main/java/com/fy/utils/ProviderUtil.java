package com.fy.utils;

import android.content.Context;

/**
 * 解决provider冲突的util
 */
public class ProviderUtil {

    public static String getFileProviderName(Context context){
        return context.getPackageName()+".provider";
    }
}
