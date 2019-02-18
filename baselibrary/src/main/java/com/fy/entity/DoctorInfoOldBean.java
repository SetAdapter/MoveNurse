package com.fy.entity;

import java.io.Serializable;

/**
 * 患者已执行医嘱信息 实体类
 * Created by fangs on 2017/9/27.
 */
public class DoctorInfoOldBean implements Serializable {
    /**
     * PatID : 610565
     * OrderNo : 74
     * OrderSubNo : 0
     * OrderType : 0
     * OrderClass : null
     * FreqTime : ST
     * StartTime : 2018/1/18 9:32:00
     * BottleSticker : 61056574MED1001506
     * OrderText : 碳酸氢钠注射液[基]
     * Dosage : 125/ml
     * SpecialUseType :
     * Administration : 静滴
     * Nurse : 张梦娇
     * Status : 2
     * ExecuteTime :
     * OrderDetails : null
     */

    private String PatID;
    private String OrderNo;
    private String OrderSubNo;
    private String OrderType;
    private Object OrderClass;
    private String FreqTime;
    private String StartTime;
    private String BottleSticker;
    private String OrderText;
    private String Dosage;
    private String SpecialUseType;
    private String Administration;
    private String Nurse;
    private String Status;
    private String ExecuteTime;
    private Object OrderDetails;

    public String getPatID() {
        return PatID;
    }

    public void setPatID(String PatID) {
        this.PatID = PatID;
    }

    public String getOrderNo() {
        return OrderNo;
    }

    public void setOrderNo(String OrderNo) {
        this.OrderNo = OrderNo;
    }

    public String getOrderSubNo() {
        return OrderSubNo;
    }

    public void setOrderSubNo(String OrderSubNo) {
        this.OrderSubNo = OrderSubNo;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public Object getOrderClass() {
        return OrderClass;
    }

    public void setOrderClass(Object OrderClass) {
        this.OrderClass = OrderClass;
    }

    public String getFreqTime() {
        return FreqTime;
    }

    public void setFreqTime(String FreqTime) {
        this.FreqTime = FreqTime;
    }

    public String getStartTime() {
        return StartTime;
    }

    public void setStartTime(String StartTime) {
        this.StartTime = StartTime;
    }

    public String getBottleSticker() {
        return BottleSticker;
    }

    public void setBottleSticker(String BottleSticker) {
        this.BottleSticker = BottleSticker;
    }

    public String getOrderText() {
        return OrderText;
    }

    public void setOrderText(String OrderText) {
        this.OrderText = OrderText;
    }

    public String getDosage() {
        return Dosage;
    }

    public void setDosage(String Dosage) {
        this.Dosage = Dosage;
    }

    public String getSpecialUseType() {
        return SpecialUseType;
    }

    public void setSpecialUseType(String SpecialUseType) {
        this.SpecialUseType = SpecialUseType;
    }

    public String getAdministration() {
        return Administration;
    }

    public void setAdministration(String Administration) {
        this.Administration = Administration;
    }

    public String getNurse() {
        return Nurse;
    }

    public void setNurse(String Nurse) {
        this.Nurse = Nurse;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public String getExecuteTime() {
        return ExecuteTime;
    }

    public void setExecuteTime(String ExecuteTime) {
        this.ExecuteTime = ExecuteTime;
    }

    public Object getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(Object OrderDetails) {
        this.OrderDetails = OrderDetails;
    }
}
