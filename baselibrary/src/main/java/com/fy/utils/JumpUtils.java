package com.fy.utils;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fy.application.BaseApplication;

import java.io.File;

import fy.library.com.baselibrary.R;

/**
 * 界面跳转工具类
 * Created by fangs on 2017/5/9.
 */
public class JumpUtils {

    /**
     * 跳转到指定 Action 的activity
     * @param act
     * @param action
     * @param bundle
     */
    public static void jump(AppCompatActivity act, String action, Bundle bundle) {
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        intent.setAction(action);
        act.startActivity(intent);
        act.overridePendingTransition(R.anim.anim_slide_left_in, R.anim.anim_slide_left_out);
    }

    /**
     * 跳转到指定 Action 的activity 带回调结果的跳转
     *
     * @param action    要跳转到的Activity
     * @param bundle
     * @param requestCode 请求码
     */
    public static void jump(AppCompatActivity act, String action, Bundle bundle, int requestCode) {
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        intent.setAction(action);
        act.startActivityForResult(intent, requestCode);
        //第一个参数 下一界面进入效果；第二个参数 当前界面退出效果
        act.overridePendingTransition(R.anim.anim_slide_left_in, R.anim.anim_slide_left_out);
    }

    /**
     * 跳转到指定 activity
     *
     * @param actClass
     * @param bundle
     */
    public static void jump(AppCompatActivity act, Class actClass, Bundle bundle) {
        Intent intent = new Intent(act, actClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        act.startActivity(intent);
        act.overridePendingTransition(R.anim.anim_slide_left_in, R.anim.anim_slide_left_out);
    }

    /**
     * 带回调结果的跳转
     *
     * @param actClass    要跳转到的Activity
     * @param bundle
     * @param requestCode 请求码
     */
    public static void jump(AppCompatActivity act, Class actClass, Bundle bundle, int requestCode) {
        Intent intent = new Intent(act, actClass);
        if (null != bundle) {
            intent.putExtras(bundle);
        }

        act.startActivityForResult(intent, requestCode);
        //第一个参数 下一界面进入效果；第二个参数 当前界面退出效果
        act.overridePendingTransition(R.anim.anim_slide_left_in, R.anim.anim_slide_left_out);
    }

    /**
     * 退出当前activity 并带数据回到上一个Activity
     * @param act
     * @param bundle
     */
    public static void jumpResult(AppCompatActivity act, Bundle bundle){
        Intent intent = new Intent();
        if (null != bundle) {
            intent.putExtras(bundle);
        }
        act.setResult(Activity.RESULT_OK, intent);
        act.finish();
        act.overridePendingTransition(R.anim.anim_slide_right_in, R.anim.anim_slide_right_out);
    }

    /**
     * 退出当前activity
     */
    public static void exitActivity(AppCompatActivity act) {
        act.finish();
        act.overridePendingTransition(R.anim.anim_slide_right_in, R.anim.anim_slide_right_out);
    }

    /**
     * 退出整个应用
     * @param act
     */
    public static void exitApp(AppCompatActivity act, Class actClass){
        Intent intent = new Intent(act, actClass);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);  //注意
        act.startActivity(intent);

        exitActivity(act);
    }

    /**
     * 安装apk
     * @param file
     */
    public static void install(AppCompatActivity act, File file) {

        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_VIEW);
        intent.setDataAndType(Uri.fromFile(file), "application/vnd.android.package-archive");
        act.finish();
        act.startActivity(intent);
    }

    /**
     * 卸载软件
     *
     * @param context
     * @param packageName
     */
    public static void uninstallApk(Context context, String packageName) {
        if (AppUtils.isPackageExist(context, packageName)) {
            Uri packageURI = Uri.parse("package:" + packageName);
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            context.startActivity(uninstallIntent);
        }
    }

    /**
     * 跳转到浏览器 打开指定 URL链接
     * @param act
     * @param url
     */
    public static void jump(AppCompatActivity act, String url){
        Uri uri = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uri);
        act.startActivity(intent);
    }
}
