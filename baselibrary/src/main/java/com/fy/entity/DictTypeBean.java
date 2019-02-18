package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/11/9.
 */

public class DictTypeBean implements Serializable{

    /**
     * Dict_TypeID : 10053
     * Dict_TypeName : 住院费用分类
     * Pid : 10053
     * Status : 0
     * DictOrder :
     * DictType : 10053
     * Dicts : [{"Dict_TypeID":"B","Dict_TypeName":"床位费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"C","Dict_TypeName":"诊查费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"D","Dict_TypeName":"检查费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"E","Dict_TypeName":"治疗费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"F","Dict_TypeName":"护理费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"G","Dict_TypeName":"手术费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"H","Dict_TypeName":"化验费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"I","Dict_TypeName":"特殊服务费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"CL","Dict_TypeName":"材料费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"XY","Dict_TypeName":"西药费","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"ZY","Dict_TypeName":"中成药","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null},{"Dict_TypeID":"CY","Dict_TypeName":"中草药","Pid":"10053","Status":"0","DictOrder":"","DictType":null,"Dicts":null}]
     */

    private String Dict_TypeID;
    private String Dict_TypeName;
    private String Pid;
    private String Status;
    private String DictOrder;
    private String DictType;
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

    public static class DictsBean implements Serializable{
        /**
         * Dict_TypeID : B
         * Dict_TypeName : 床位费
         * Pid : 10053
         * Status : 0
         * DictOrder :
         * DictType : null
         * Dicts : null
         */

        private String Dict_TypeID;
        private String Dict_TypeName;
        private String Pid;
        private String Status;
        private String DictOrder;
        private Object DictType;
        private Object Dicts;

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

        public Object getDictType() {
            return DictType;
        }

        public void setDictType(Object DictType) {
            this.DictType = DictType;
        }

        public Object getDicts() {
            return Dicts;
        }

        public void setDicts(Object Dicts) {
            this.Dicts = Dicts;
        }
    }

}
