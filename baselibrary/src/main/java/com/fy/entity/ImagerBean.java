package com.fy.entity;

import com.fy.base.BaseBean;

/**
 * Created by Gab on 2017/8/31 0031.
 *
 */

public class ImagerBean extends BaseBean {

    /**
     * FileNo : 1
     * FileName : 20171026051834_9f901dee-ec1e-406e-a8ce-b23638c071e3.png
     * FilePath : D:\System\NurseThrowing\qm_file\20171026051834_9f901dee-ec1e-406e-a8ce-b23638c071e3.png
     * FileSize : 16.00
     * FileUrl : http://192.168.100.30:8099/qm_file/20171026051834_9f901dee-ec1e-406e-a8ce-b23638c071e3.png
     */

    private String FileNo = "";
    private String FileName = "";
    private String FilePath = "";
    private String FileSize = "";
    private String FileUrl = "";

    public String getFileNo() {
        return FileNo;
    }

    public void setFileNo(String FileNo) {
        this.FileNo = FileNo;
    }

    public String getFileName() {
        return FileName;
    }

    public void setFileName(String FileName) {
        this.FileName = FileName;
    }

    public String getFilePath() {
        return FilePath;
    }

    public void setFilePath(String FilePath) {
        this.FilePath = FilePath;
    }

    public String getFileSize() {
        return FileSize;
    }

    public void setFileSize(String FileSize) {
        this.FileSize = FileSize;
    }

    public String getFileUrl() {
        return FileUrl;
    }

    public void setFileUrl(String FileUrl) {
        this.FileUrl = FileUrl;
    }
}
