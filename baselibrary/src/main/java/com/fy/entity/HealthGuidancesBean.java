package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 *
 */

public class HealthGuidancesBean implements Serializable{

    /**
     * Dict_TypeID : 1000101
     * Dict_TypeName : 饮食、营养
     * Pid : 10001
     * Status : 0
     * DictOrder : 1
     * DictType : 10001
     * Dicts : [{"Dict_TypeID":"100010101","Dict_TypeName":"倡导母乳喂养","Pid":"1000101","Status":"1","DictOrder":"1","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010102","Dict_TypeName":"掌握喂养时间: 患儿未满2个月，提倡按需喂养; 2个月后，可采取按时喂养，2 -3小时喂\n一次\n","Pid":"1000101","Status":"1","DictOrder":"2","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010103","Dict_TypeName":"母乳不足时添加奶粉喂养，配奶时注意奶瓶的消毒、奶的浓度及温度，喂养后拍背及头偏向一侧","Pid":"1000101","Status":"1","DictOrder":"3","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010104","Dict_TypeName":"随着患儿成长，可适当添加辅食。","Pid":"1000101","Status":"1","DictOrder":"4","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010105","Dict_TypeName":"按期添加辅食","Pid":"1000101","Status":"1","DictOrder":"5","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010106","Dict_TypeName":"忌食","Pid":"1000101","Status":"1","DictOrder":"6","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010107","Dict_TypeName":"特殊饮食","Pid":"1000101","Status":"1","DictOrder":"7","DictType":"10001","Dicts":null},{"Dict_TypeID":"100010108","Dict_TypeName":"无","Pid":"1000101","Status":"1","DictOrder":"8","DictType":"10001","Dicts":null}]
     */

    private String Dict_TypeID = "";
    private String Dict_TypeName = "";
    private String Pid = "";
    private String Status = "";
    private String DictOrder = "";
    private String DictType = "";
    private List<DictsBean> Dicts;

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

    public List<DictsBean> getDicts() {
        return Dicts;
    }

    public void setDicts(List<DictsBean> Dicts) {
        this.Dicts = Dicts;
    }

    public static class DictsBean implements Serializable {
        /**
         * Dict_TypeID : 100010101
         * Dict_TypeName : 倡导母乳喂养
         * Pid : 1000101
         * Status : 1
         * DictOrder : 1
         * DictType : 10001
         * Dicts : null
         */

        private String Dict_TypeID = "";
        private String Dict_TypeName = "";
        private String Pid = "";
        private String Status = "";
        private String DictOrder = "";
        private String DictType = "";
        private Object Dicts = "";

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

        public Object getDicts() {
            return Dicts;
        }

        public void setDicts(Object Dicts) {
            this.Dicts = Dicts;
        }

        @Override
        public String toString() {
            return "DictsBean{" +
                    "Dict_TypeID='" + Dict_TypeID + '\'' +
                    ", Dict_TypeName='" + Dict_TypeName + '\'' +
                    ", Pid='" + Pid + '\'' +
                    ", Status='" + Status + '\'' +
                    ", DictOrder='" + DictOrder + '\'' +
                    ", DictType='" + DictType + '\'' +
                    ", Dicts=" + Dicts +
                    '}';
        }
    }
}
