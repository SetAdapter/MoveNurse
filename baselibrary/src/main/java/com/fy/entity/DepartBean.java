package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * 科室 实体类
 * Created by fangs on 2017/9/19.
 */
public class DepartBean implements Serializable {

    /**
     * Dict_TypeID : 0400
     * Dict_TypeName : 外科
     * Pid : 10009
     * Status : 0
     * DictOrder :
     * Dicts : [{"Dict_TypeID":"925","Dict_TypeName":"外七科室","Pid":"0400","Status":"0","DictOrder":"","Dicts":null}]
     */

    private String Dict_TypeID = "";
    private String Dict_TypeName = "";
    private String Pid = "";
    private String Status = "";
    private String DictOrder = "";
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

    public List<DictsBean> getDicts() {
        return Dicts;
    }

    public void setDicts(List<DictsBean> Dicts) {
        this.Dicts = Dicts;
    }

    public static class DictsBean implements Serializable {
        /**
         * Dict_TypeID : 925
         * Dict_TypeName : 外七科室
         * Pid : 0400
         * Status : 0
         * DictOrder :
         * Dicts : null
         */

        private String Dict_TypeID = "";
        private String Dict_TypeName = "";
        private String Pid = "";
        private String Status = "";
        private String DictOrder = "";
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

        public Object getDicts() {
            return Dicts;
        }

        public void setDicts(Object Dicts) {
            this.Dicts = Dicts;
        }
    }
}
