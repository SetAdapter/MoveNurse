package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2017/10/19 0019.
 * 动态获取数据
 */

public class AntenatalExpectant implements Serializable{

    /**
     * DictOrder : 11
     * DictType : 10045
     * Dict_TypeID : 10011
     * Dict_TypeName : 胎位
     * Dicts : [{"DictOrder":"1","DictType":"10045","Dict_TypeID":"1001101","Dict_TypeName":"左枕前(LOA)","Pid":"10011","Status":"0"},{"DictOrder":"2","DictType":"10045","Dict_TypeID":"1001102","Dict_TypeName":"右枕前(ROA)","Pid":"10011","Status":"0"},{"DictOrder":"3","DictType":"10045","Dict_TypeID":"1001103","Dict_TypeName":"左骶前(LSA)","Pid":"10011","Status":"0"},{"DictOrder":"4","DictType":"10045","Dict_TypeID":"1001104","Dict_TypeName":"右骶前(RSA)","Pid":"10011","Status":"0"},{"DictOrder":"5","DictType":"10045","Dict_TypeID":"1001105","Dict_TypeName":"左肩前(LSCA)","Pid":"10011","Status":"0"},{"DictOrder":"6","DictType":"10045","Dict_TypeID":"1001106","Dict_TypeName":"右肩前(RSCA)","Pid":"10011","Status":"0"},{"DictOrder":"7","DictType":"10045","Dict_TypeID":"1001107","Dict_TypeName":"左枕后(LOP)","Pid":"10011","Status":"0"},{"DictOrder":"8","DictType":"10045","Dict_TypeID":"1001108","Dict_TypeName":"右枕后(ROP)","Pid":"10011","Status":"0"},{"DictOrder":"9","DictType":"10045","Dict_TypeID":"1001109","Dict_TypeName":"左枕横(LOT)","Pid":"10011","Status":"0"},{"DictOrder":"10","DictType":"10045","Dict_TypeID":"1001110","Dict_TypeName":"右枕横(ROT)","Pid":"10011","Status":"0"}]
     * Pid : 0
     * Status : 0
     */

    private String DictOrder = "";
    private String DictType = "";
    private String Dict_TypeID = "";
    private String Dict_TypeName = "";
    private String Pid = "";
    private String Status = "";
    private List<DictsBean> Dicts;

    public String getDictOrder() {
        return DictOrder;
    }

    public void setDictOrder(String DictOrder) {
        this.DictOrder = DictOrder;
    }

    public String getDictType() {
        return DictType;
    }

    public void setDictType(String DictType) {
        this.DictType = DictType;
    }

    public String getDict_TypeID() {
        return Dict_TypeID;
    }

    public void setDict_TypeID(String Dict_TypeID) {
        this.Dict_TypeID = Dict_TypeID;
    }

    public String getDict_TypeName() {
        return Dict_TypeName;
    }

    public void setDict_TypeName(String Dict_TypeName) {
        this.Dict_TypeName = Dict_TypeName;
    }

    public String getPid() {
        return Pid;
    }

    public void setPid(String Pid) {
        this.Pid = Pid;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public List<DictsBean> getDicts() {
        return Dicts;
    }

    public void setDicts(List<DictsBean> Dicts) {
        this.Dicts = Dicts;
    }

    public static class DictsBean implements Serializable {
        /**
         * DictOrder : 1
         * DictType : 10045
         * Dict_TypeID : 1001101
         * Dict_TypeName : 左枕前(LOA)
         * Pid : 10011
         * Status : 0
         */

        private String DictOrder = "";
        private String DictType = "";
        private String Dict_TypeID = "";
        private String Dict_TypeName = "";
        private String Pid = "";
        private String Status = "";

        public String getDictOrder() {
            return DictOrder;
        }

        public void setDictOrder(String DictOrder) {
            this.DictOrder = DictOrder;
        }

        public String getDictType() {
            return DictType;
        }

        public void setDictType(String DictType) {
            this.DictType = DictType;
        }

        public String getDict_TypeID() {
            return Dict_TypeID;
        }

        public void setDict_TypeID(String Dict_TypeID) {
            this.Dict_TypeID = Dict_TypeID;
        }

        public String getDict_TypeName() {
            return Dict_TypeName;
        }

        public void setDict_TypeName(String Dict_TypeName) {
            this.Dict_TypeName = Dict_TypeName;
        }

        public String getPid() {
            return Pid;
        }

        public void setPid(String Pid) {
            this.Pid = Pid;
        }

        public String getStatus() {
            return Status;
        }

        public void setStatus(String Status) {
            this.Status = Status;
        }
    }
}
