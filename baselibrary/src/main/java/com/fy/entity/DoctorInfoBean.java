package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 患者医嘱信息 实体类
 * Created by fangs on 2017/9/26.
 */
public class DoctorInfoBean implements Serializable {

    /**
     * PatID : 606410
     * OrderNo : 15
     * OrderType :
     * FreqTime : ST
     * ExcuteTime :
     * OrderDetails : [{"OrderNo":"15","OrderText":"葡萄糖5%","Administration":"静滴","Dosage":"250/ml","SpecialUseType":""}]
     */

    private String PatID = "";
    private String OrderNo = "";
    private String OrderType = "";
    private String FreqTime = "";
    private String ExcuteTime = "";
    private List<OrderDetailsBean> OrderDetails;

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

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getFreqTime() {
        return FreqTime;
    }

    public void setFreqTime(String FreqTime) {
        this.FreqTime = FreqTime;
    }

    public String getExcuteTime() {
        return ExcuteTime;
    }

    public void setExcuteTime(String ExcuteTime) {
        this.ExcuteTime = ExcuteTime;
    }

    public List<OrderDetailsBean> getOrderDetails() {
        return OrderDetails;
    }

    public void setOrderDetails(List<OrderDetailsBean> OrderDetails) {
        this.OrderDetails = OrderDetails;
    }

    public static class OrderDetailsBean implements Serializable {
        /**
         * OrderNo : 15
         * OrderText : 葡萄糖5%
         * Administration : 静滴
         * Dosage : 250/ml
         * SpecialUseType :
         */

        private String OrderNo = "";
        private String OrderText = "";
        private String Administration = "";
        private String Dosage = "";
        private String SpecialUseType = "";

        public String getOrderNo() {
            return OrderNo;
        }

        public void setOrderNo(String OrderNo) {
            this.OrderNo = OrderNo;
        }

        public String getOrderText() {
            return OrderText;
        }

        public void setOrderText(String OrderText) {
            this.OrderText = OrderText;
        }

        public String getAdministration() {
            return Administration;
        }

        public void setAdministration(String Administration) {
            this.Administration = Administration;
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
    }
}
