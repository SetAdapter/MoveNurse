package com.fy.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 上传列表 缓存实体类
 * <p/>Created by fangs on 2017/10/11.
 */
public class UploadBean implements Serializable {

    /**
     * 缓存时间
     * TimeUtils.Long2DataString(System.currentTimeMillis(), "yyyy年MM月dd日 HH:mm")
     */
    private String time = "";
    /**
     * 病人 ID
     */
    private String patID = "";

    /**
     * 病人 名称
     */
    private String patinName = "";

    /**
     * 请求API
     */
    private String api = "";

    /**
     * 请求参数 map 集合
     */
    private Map<String, Object> request = new HashMap<>();

    /**
     * key 列表
     */
    private List<String> keyList = new ArrayList<>();

    public UploadBean(List<String> keyList) {
        this.keyList = keyList;
    }

    public UploadBean(String time, String patID, String patinName, String api, Map<String, Object> request) {
        this.time = time;
        this.patID = patID;
        this.patinName = patinName;
        this.api = api;
        this.request = request;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getPatID() {
        return patID;
    }

    public void setPatID(String patID) {
        this.patID = patID;
    }

    public String getPatinName() {
        return patinName;
    }

    public void setPatinName(String patinName) {
        this.patinName = patinName;
    }

    public String getApi() {
        return api;
    }

    public void setApi(String api) {
        this.api = api;
    }

    public Map<String, Object> getRequest() {
        return request;
    }

    public void setRequest(Map<String, Object> request) {
        this.request = request;
    }

    public List<String> getKeyList() {
        return keyList;
    }

    public void setKeyList(List<String> keyList) {
        this.keyList = keyList;
    }
}
