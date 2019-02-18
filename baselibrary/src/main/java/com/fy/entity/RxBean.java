package com.fy.entity;

import java.io.Serializable;

/**
 * Rx事件通讯 实体类
 * <p/>Created by fangs on 2017/10/11.
 */
public class RxBean implements Serializable{

    /**
     * 发送事件的action
     */
    private String sendAction = "";

    private String content = "";

    public RxBean(String sendAction, String content) {
        this.sendAction = sendAction;
        this.content = content;
    }

    public String getSendAction() {
        return sendAction;
    }

    public void setSendAction(String sendAction) {
        this.sendAction = sendAction;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
