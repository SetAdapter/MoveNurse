package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 * 约束带
 */

public class RestraintStrapBean implements Serializable{

    /**
     * DictOrder : 1
     * DictType : 10004
     * Dict_TypeID : 1000401
     * Dict_TypeName : 使用目的
     * Dicts : [{"DictOrder":"1","DictType":"10004","Dict_TypeID":"100040101","Dict_TypeName":"限制不合作患者身体或肢体的活动，防止自伤或伤人。","Pid":"1000401","Status":"1"},{"DictOrder":"2","DictType":"10004","Dict_TypeID":"100040102","Dict_TypeName":"保护病人安全，防止躁动病人坠床。","Pid":"1000401","Status":"1"},{"DictOrder":"3","DictType":"10004","Dict_TypeID":"100040103","Dict_TypeName":"防止病人自行拔出各种重要管道(如气管插管、引流管、输液管等)，延误治疗甚至危","Pid":"1000401","Status":"1"},{"DictOrder":"4","DictType":"10004","Dict_TypeID":"100040104","Dict_TypeName":"其他","Pid":"1000401","Status":"1"}]
     * Pid : 10004
     * Status : 0
     */

    private String DictOrder ="";
    private String DictType="";
    private String Dict_TypeID="";
    private String Dict_TypeName="";
    private String Pid="";
    private String Status="";
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
         * DictType : 10004
         * Dict_TypeID : 100040101
         * Dict_TypeName : 限制不合作患者身体或肢体的活动，防止自伤或伤人。
         * Pid : 1000401
         * Status : 1
         */

        private String DictOrder="";
        private String DictType="";
        private String Dict_TypeID="";
        private String Dict_TypeName="";
        private String Pid="";
        private String Status="";

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

    @Override
    public String toString() {
        return "RestraintStrapBean{" +
                "DictOrder='" + DictOrder + '\'' +
                ", DictType='" + DictType + '\'' +
                ", Dict_TypeID='" + Dict_TypeID + '\'' +
                ", Dict_TypeName='" + Dict_TypeName + '\'' +
                ", Pid='" + Pid + '\'' +
                ", Status='" + Status + '\'' +
                ", Dicts=" + Dicts +
                '}';
    }
}
