package com.fy.utils;

/**
 * 常量
 * Created by fangs on 2017/5/8.
 */
public class ConstantUtils {

    /** 动态服务器地址 */
    public static String custom_Url = "";

    /** 区分不同用户的缓存 key */
    public static String userId = "userid";

    /** 用户是否登录 */
    public static final String isLogin = "userIsLogin";

    /** APP 当前模式 （日间/夜间） */
    public static final String appMode = "appModeSwitch";

    /** 用户是否 第一次打开APP */
    public static final String isfirstOpenApp = "userIsFirstOpenApp";

    /** 普通 状态栏 */
    public static final int OrdinaryStatus = 1;
    /** 全屏透明 状态栏 */
    public static final int ImageTransparent = 2;
    /** 全屏半透明 状态栏 */
    public static final int ImageTranslucent = 3;

    /** ToolBar+TabLayout可伸缩 状态栏 */
    public static final int TelescopicStatus = 4;
    /** DrawerLayout+ToolBar+TabLayout 可伸缩 状态栏 */
    public static final int DrawerToolbarTabLayout = 5;
    /** CollapsingToolbarLayout状态栏(可折叠图片) 状态栏 */
    public static final int CollapsingToolbar = 6;
    /** CollapsingToolbar折叠时statusBar显示和隐藏 状态栏 */
    public static final int CollapsingToolbarStatus = 7;

    /** DrawerLayout+ToolBar 状态栏 */
    public static final int DrawerToolbar = 8;

}
