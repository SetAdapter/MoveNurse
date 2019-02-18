package com.example.hjy.movenurse.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.io.File;
import java.io.FileInputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Created by Administrator on 2016/8/12.
 */
public class MyUtils {

    static SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
    static SimpleDateFormat dateFormatOut1 = new SimpleDateFormat("MM-dd");
    static SimpleDateFormat dateFormatOut2 = new SimpleDateFormat("yyyy-MM-dd");


    /**
     * 获取本周 星期一的日期
     * 参数由 yyyy年MM月dd日 HH:mm:ss 组成，可以自定义格式
     */
    public static String getCurrentDate(String format) {
        SimpleDateFormat formatter = new SimpleDateFormat(format);
        Calendar c = Calendar.getInstance();
        int day_of_week = c.get(Calendar.DAY_OF_WEEK) - 1;
        if (day_of_week == 0)
            day_of_week = 7;
        c.add(Calendar.DATE, -day_of_week + 1);
        return formatter.format(c.getTime());
    }

    /**
     * 返回年月  如 201701
     */
    public static String getCurrentYearMonth() {
        String time = "" + Calendar.getInstance().get(Calendar.YEAR);
        if (((Calendar.getInstance().get(Calendar.MONTH) + 1)) < 10) {
            time = time + "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1);
        } else {
            time = time + (Calendar.getInstance().get(Calendar.MONTH) + 1);
        }
        return time;
    }


//    /**
//     * 获取月份
//     * 参数由 yyyy年MM月dd日 HH:mm:ss 组成，可以自定义格式
//     */
//    public void String getMonth(String format) {
//
//    }

    /**
     * 获取系统时间 后面第几天的日期,或往前几天
     * date = 2016-01-01
     * format = yyyy-MM-dd 想要的格式
     * dayAfter = 正数为往后几天，负数为往前几天
     */
    public static String getFutureDate(String dateStr, String format, int dayAfter) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;

        try {
            date = sdf.parse(dateStr);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat(format);

        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(Calendar.DATE, dayAfter);//把日期往后增加N天.整数往后推,负数往前移动
        Date futureDate = calendar.getTime();   //这个时间就是日期往后推的结果
        return formatter.format(futureDate);
    }


    public static boolean isTwoWeekLate(int year, int month, int day) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");

        try {
            Calendar calendar = Calendar.getInstance();
            calendar.add(Calendar.DAY_OF_MONTH, 14);
            Date CurrentTime = sdf.parse("" + calendar.get(Calendar.YEAR) +
                    ((calendar.get(Calendar.MONTH) + 1) < 10 ? "0" + (calendar.get(Calendar.MONTH) + 1) : (calendar.get(Calendar.MONTH) + 1)) +
                    (calendar.get(Calendar.DAY_OF_MONTH) < 10 ? "0" + (calendar.get(Calendar.DAY_OF_MONTH)) : (calendar.get(Calendar.DAY_OF_MONTH))));
            Date InputTime = sdf.parse("" + year + ((month + 1) < 10 ? "0" + (month + 1) : (month + 1)) + (day < 10 ? "0" + day : day));
            if (CurrentTime.compareTo(InputTime) > 0) {
                return false;
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return true;
    }

    /*检测网络是否连接*/
    public static boolean isNetAvailable(Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cm != null) {
            NetworkInfo networkInfo = cm.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                    return true;
                }
            }
        }
        return false;
    }

    /*    比较时间大小*/
    public static boolean beginTOend(Context context, String beginTime, String endTime) {

        boolean large = false;

        if (beginTime == null || beginTime.equals("")) {
            return large;
        }
        if (endTime == null || endTime.equals("")) {
            return large;
        }

        DateFormat dateFormat = new SimpleDateFormat("HH:mm");

        try {
            Date begin = dateFormat.parse(beginTime);
            Date end = dateFormat.parse(endTime);

            large = begin.getTime() < end.getTime();


        } catch (ParseException e) {
            e.printStackTrace();
        }

        return large;
    }

    //性别
    public static String getSex(String Sex) {
        switch (Sex) {
            case "M":
                return "男";
            case "F":
                return "女";
            case "0":
                return "未知";
            default:
                return Sex;
        }
    }

    //婚姻
    public static String getMarried(String Married) {
        switch (Married) {
            case "0":
                return "已婚";
            case "1":
                return "未婚";
            case "3":
                return "未知";
            default:
                return Married;
        }
    }

    //护理级别
    public static String getNurse(String Nurse) {
        switch (Nurse) {
            case "0":
                return "特级护理";
            case "1":
                return "一级护理";
            case "2":
                return "二级护理";
            case "3":
                return "三级护理";
            default:
                return Nurse;
        }
    }

    public static Bitmap getBitmapFromPath(String path) {
        if (!new File(path).exists()) {
            System.err.println("getBitmapFromPath: file not exists");
            return null;
        }
        // Bitmap bitmap = Bitmap.createBitmap(1366, 768, Config.ARGB_8888);
        // Canvas canvas = new Canvas(bitmap);
        // Movie movie = Movie.decodeFile(path);
        // movie.draw(canvas, 0, 0);
        //
        // return bitmap;
        byte[] buf = new byte[1024 * 1024];// 1M
        Bitmap bitmap = null;
        try {
            FileInputStream fis = new FileInputStream(path);
            int len = fis.read(buf, 0, buf.length);
            bitmap = BitmapFactory.decodeByteArray(buf, 0, len);
            if (bitmap == null) {
                System.out.println("len= " + len);
                System.err.println("path: " + path + "  could not be decode!!!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return bitmap;
    }
}