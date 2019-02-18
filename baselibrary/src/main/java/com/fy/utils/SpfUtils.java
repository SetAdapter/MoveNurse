package com.fy.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * SharedPreferences 管理工具类
 * Created by fangs on 2017/5/18.
 */
public class SpfUtils {

//    创建的Preferences文件存放位置可以在Eclipse中查看：
//	  DDMS->File Explorer /<package name>/shared_prefs/setting.xml

    private static String spfFileName = "fySpf";

    private static SharedPreferences getSpf(Context ctx){
        SharedPreferences mSpf = ctx.getSharedPreferences(spfFileName, Context.MODE_PRIVATE);

        return mSpf;
    }

    /**
     * 向默认的SharedPreferences文件保存String内容
     *
     * @param ctx
     * @param key   保存的键
     * @param value 保存的内容
     */
    public static void saveStrToSpf(Context ctx, String key, String value) {
        SharedPreferences.Editor editor = getSpf(ctx).edit();

        editor.putString(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 取String数据
     *
     * @param ctx
     * @param key
     * @return   没有对应的key 默认返回的""
     */
    public static String getSpfSaveStr(Context ctx, String key) {

        return getSpf(ctx).getString(key, "");
    }

    /**
     * 向默认的SharedPreferences文件保存int内容
     *
     * @param ctx
     * @param key   保存的键
     * @param value 保存的内容
     */
    public static void saveIntToSpf(Context ctx, String key, int value) {
        SharedPreferences.Editor editor = getSpf(ctx).edit();

        editor.putInt(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 取int数据
     *
     * @param ctx
     * @param key
     * @return   没有对应的key  默认返回-1值
     */
    public static int getSpfSaveInt(Context ctx, String key) {

        return getSpf(ctx).getInt(key, -1);
    }

    /**
     * 向默认的SharedPreferences文件保存boolean内容
     * @param ctx
     * @param key
     * @param value
     */
    public static void saveBooleanToSpf(Context ctx, String key, boolean value){
        SharedPreferences.Editor editor = getSpf(ctx).edit();

        editor.putBoolean(key, value);
        editor.commit();
    }

    /**
     * 从默认的SharedPreferences文件 boolean内容
     *
     * @param ctx
     * @param key
     * @return      没有对应的key 默认返回false
     */
    public static boolean getSpfSaveBoolean(Context ctx, String key) {

        return getSpf(ctx).getBoolean(key, false);
    }

}
