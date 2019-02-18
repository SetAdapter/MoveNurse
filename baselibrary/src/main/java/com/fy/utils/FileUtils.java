package com.fy.utils;

import android.os.Environment;
import android.os.StatFs;

import com.fy.application.BaseApplication;

import java.io.File;
import java.io.FileWriter;

/**
 * 文件工具类
 * <p/>
 * Created by fangs on 2017/3/22.
 */
public class FileUtils {

    /** 应用 所有 文件 根目录 */
    public static String SAVE_FOLDER = "/A_fy_Cache/";

    private FileUtils() {
        /* cannot be instantiated */
        throw new UnsupportedOperationException("cannot be instantiated");
    }

    /**
     * 判断SDCard是否可用
     *
     * @return
     */
    public static boolean isSDCardEnable() {
        return Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED);
    }

    /**
     * 获取SD卡路径
     *
     * @return
     */
    public static String getSDCardPath() {
        return Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator;
    }

    /**
     * 获取SD卡的剩余容量 单位byte
     *
     * @return
     */
    public static long getSDCardAllSize() {
        if (isSDCardEnable()) {
            StatFs stat = new StatFs(getSDCardPath());
            // 获取空闲的数据块的数量
            long availableBlocks = (long) stat.getAvailableBlocks() - 4;
            // 获取单个数据块的大小（byte）
            long freeBlocks = stat.getAvailableBlocks();
            return freeBlocks * availableBlocks;
        }
        return 0;
    }

    /**
     * 获取指定路径所在空间的剩余可用容量字节数，单位byte
     *
     * @param filePath
     * @return 容量字节 SDCard可用空间，内部存储可用空间
     */
    public static long getFreeBytes(String filePath) {
        // 如果是sd卡的下的路径，则获取sd卡可用容量
        if (filePath.startsWith(getSDCardPath())) {
            filePath = getSDCardPath();
        } else {// 如果是内部存储的路径，则获取内存存储的可用容量
            filePath = Environment.getDataDirectory().getAbsolutePath();
        }
        StatFs stat = new StatFs(filePath);
        long availableBlocks = (long) stat.getAvailableBlocks() - 4;
        return stat.getBlockSize() * availableBlocks;
    }

    /**
     * 获取系统存储路径
     *
     * @return
     */
    public static String getRootDirectoryPath() {
        return Environment.getRootDirectory().getAbsolutePath();
    }

    /**
     * 到得文件的放置路径
     * <p/> 如果文件目录不存在 会创建
     * @param aModuleName 模块名字
     * @return
     * @author fangs
     */
    public static String getPath(String aModuleName) {
//        String sp = File.separator;
//        String modulePath = aModuleName.replace(".", sp);
//        String fDirStr = Environment.getExternalStorageDirectory() + sp  + SAVE_FOLDER + sp + modulePath + sp;

        File dirpath = new File(BaseApplication.getApplication().getExternalFilesDir("HJY"), aModuleName);
        if (!dirpath.exists()) {
            dirpath.mkdirs();
        }
        return dirpath.getPath();
    }

    /**
     * 判断指定路径的文件是否存在
     *
     * @param filePath
     * @return
     */
    public static boolean fileIsExists(String filePath) {
        try {
            File f = new File(filePath);
            if (!f.exists()) {
                return false;
            }

        } catch (Exception e) {
            return false;
        }
        return true;
    }

    /**
     * 递归删除文件和文件夹
     * @param file    要删除的根目录
     */
    public void recursionDeleteFile(File file){
        if(file.isFile()){
            file.delete();
            return;
        }
        if(file.isDirectory()){
            File[] childFile = file.listFiles();
            if(childFile == null || childFile.length == 0){
                file.delete();
                return;
            }
            for(File f : childFile){
                recursionDeleteFile(f);
            }
            file.delete();
        }
    }

    /**
     * 向指定文件写内容  (追加形式写文件)
     * @param path          文件目录(如：fy.com.base)
     * @param inputfileName 文件名（如：log.txt）
     * @param content       准备写入的内容
     */
    public static void fileToInputContent(String path, String inputfileName, String content) {
        StringBuffer sb = new StringBuffer();
        sb.append("\n").append(content).append("\n");

        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            File dir = new File(getPath(path));
            if (!dir.exists()) {
                dir.mkdir();
            }

            try {
                // 文件目录 + 文件名 String
                String fileName = dir.toString() + File.separator + inputfileName;
                // 打开一个写文件器，构造函数中的第二个参数true表示以追加形式写文件
                FileWriter writer = new FileWriter(fileName, true);
                writer.write(sb.toString());
                writer.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
