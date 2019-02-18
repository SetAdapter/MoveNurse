package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Gab on 2017/10/16 0016.
 */

public class HealthGuidanceBean implements Serializable{

    /**
     * DictOrder : 1
     * DictType : 10007
     * Dict_TypeID : 1000701
     * Dict_TypeName : 头面部
     * Dicts : [{"DictOrder":"4","DictType":"10007","Dict_TypeID":"1000704","Dict_TypeName":"上肢","Dicts":[{"DictOrder":"6","DictType":"10007","Dict_TypeID":"100070401","Dict_TypeName":"右上臂","Pid":"1000704","Status":"0"},{"DictOrder":"7","DictType":"10007","Dict_TypeID":"100070402","Dict_TypeName":"右前臂","Pid":"1000704","Status":"0"},{"DictOrder":"8","DictType":"10007","Dict_TypeID":"100070403","Dict_TypeName":"右手掌侧","Pid":"1000704","Status":"0"},{"DictOrder":"9","DictType":"10007","Dict_TypeID":"100070404","Dict_TypeName":"左上臂","Pid":"1000704","Status":"0"},{"DictOrder":"10","DictType":"10007","Dict_TypeID":"100070405","Dict_TypeName":"左前臂","Pid":"1000704","Status":"0"},{"DictOrder":"11","DictType":"10007","Dict_TypeID":"100070406","Dict_TypeName":"左手掌侧","Pid":"1000704","Status":"0"}],"Pid":"10007","Status":"0"},{"DictOrder":"5","DictType":"10007","Dict_TypeID":"1000705","Dict_TypeName":"腰腹部","Dicts":[{"DictOrder":"12","DictType":"10007","Dict_TypeID":"100070501","Dict_TypeName":"胸腹部","Pid":"1000705","Status":"0"},{"DictOrder":"13","DictType":"10007","Dict_TypeID":"100070502","Dict_TypeName":"下腹部","Pid":"1000705","Status":"0"}],"Pid":"10007","Status":"0"},{"DictOrder":"6","DictType":"10007","Dict_TypeID":"1000706","Dict_TypeName":"下肢","Dicts":[{"DictOrder":"14","DictType":"10007","Dict_TypeID":"100070601","Dict_TypeName":"右大腿","Pid":"1000706","Status":"0"},{"DictOrder":"15","DictType":"10007","Dict_TypeID":"100070602","Dict_TypeName":"右小腿","Pid":"1000706","Status":"0"},{"DictOrder":"17","DictType":"10007","Dict_TypeID":"100070603","Dict_TypeName":"左大腿","Pid":"1000706","Status":"0"},{"DictOrder":"18","DictType":"10007","Dict_TypeID":"100070604","Dict_TypeName":"左小腿","Pid":"1000706","Status":"0"}],"Pid":"10007","Status":"0"}]
     * Pid : 10007
     * Status : 0
     */

    private String DictOrder = "";
    private String DictType = "";
    private String Dict_TypeID = "";
    private String Dict_TypeName = "";
    private String Pid = "";
    private String Status = "";
    private List<DictsBeanX> Dicts;

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

    public List<DictsBeanX> getDicts() {
        return Dicts;
    }

    public void setDicts(List<DictsBeanX> Dicts) {
        this.Dicts = Dicts;
    }

    public static class DictsBeanX implements Serializable {
        /**
         * DictOrder : 4
         * DictType : 10007
         * Dict_TypeID : 1000704
         * Dict_TypeName : 上肢
         * Dicts : [{"DictOrder":"6","DictType":"10007","Dict_TypeID":"100070401","Dict_TypeName":"右上臂","Pid":"1000704","Status":"0"},{"DictOrder":"7","DictType":"10007","Dict_TypeID":"100070402","Dict_TypeName":"右前臂","Pid":"1000704","Status":"0"},{"DictOrder":"8","DictType":"10007","Dict_TypeID":"100070403","Dict_TypeName":"右手掌侧","Pid":"1000704","Status":"0"},{"DictOrder":"9","DictType":"10007","Dict_TypeID":"100070404","Dict_TypeName":"左上臂","Pid":"1000704","Status":"0"},{"DictOrder":"10","DictType":"10007","Dict_TypeID":"100070405","Dict_TypeName":"左前臂","Pid":"1000704","Status":"0"},{"DictOrder":"11","DictType":"10007","Dict_TypeID":"100070406","Dict_TypeName":"左手掌侧","Pid":"1000704","Status":"0"}]
         * Pid : 10007
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
             * DictOrder : 6
             * DictType : 10007
             * Dict_TypeID : 100070401
             * Dict_TypeName : 右上臂
             * Pid : 1000704
             * Status : 0
             */

            private String DictOrder;
            private String DictType;
            private String Dict_TypeID;
            private String Dict_TypeName;
            private String Pid;
            private String Status;

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
}
