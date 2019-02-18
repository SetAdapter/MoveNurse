package com.fy.entity;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/9/27.
 */

public class PainbBillDetailsBean implements Serializable{


    /**
     * ResultCode : 1
     * ResultMes : 成功
     * ResultData : [{"PatID":"609135","BillNo":"1259","ProjectName":"血细胞分析(五分类)","FNumber":"-1","Price":"19","Measure":"次","Amount":"-19","FeeAmount":"-19","ItemSpec":"","PSDate":"2017/10/25 9:42:58","ExcuteDepID":"302","ExcuteDepName":"检验科","OrderDepID":"917","OrderDepName":"外六科室","BottleSticker":"609135125915561","CostName":"化验费","OrderNo":"172","OrderSubNo":"0","BatchNo":"","RoutineRetail":"","PYM":"","WBM":"","Doctor":"赵让加","IOWHType":"","IOWHNo":"","MFT":""}]
     */

    private int ResultCode;
    private String ResultMes;
    private List<ResultDataBean> ResultData;

    public int getResultCode() {
        return ResultCode;
    }

    public void setResultCode(int ResultCode) {
        this.ResultCode = ResultCode;
    }

    public String getResultMes() {
        return ResultMes;
    }

    public void setResultMes(String ResultMes) {
        this.ResultMes = ResultMes;
    }

    public List<ResultDataBean> getResultData() {
        return ResultData;
    }

    public void setResultData(List<ResultDataBean> ResultData) {
        this.ResultData = ResultData;
    }

    public static class ResultDataBean implements Serializable{
        /**
         * PatID : 609135
         * BillNo : 1259
         * ProjectName : 血细胞分析(五分类)
         * FNumber : -1
         * Price : 19
         * Measure : 次
         * Amount : -19
         * FeeAmount : -19
         * ItemSpec :
         * PSDate : 2017/10/25 9:42:58
         * ExcuteDepID : 302
         * ExcuteDepName : 检验科
         * OrderDepID : 917
         * OrderDepName : 外六科室
         * BottleSticker : 609135125915561
         * CostName : 化验费
         * OrderNo : 172
         * OrderSubNo : 0
         * BatchNo :
         * RoutineRetail :
         * PYM :
         * WBM :
         * Doctor : 赵让加
         * IOWHType :
         * IOWHNo :
         * MFT :
         */

        private String PatID;
        private String BillNo;
        private String ProjectName;
        private String FNumber;
        private String Price;
        private String Measure;
        private String Amount;
        private String FeeAmount;
        private String ItemSpec;
        private String PSDate;
        private String ExcuteDepID;
        private String ExcuteDepName;
        private String OrderDepID;
        private String OrderDepName;
        private String BottleSticker;
        private String CostName;
        private String OrderNo;
        private String OrderSubNo;
        private String BatchNo;
        private String RoutineRetail;
        private String PYM;
        private String WBM;
        private String Doctor;
        private String IOWHType;
        private String IOWHNo;
        private String MFT;
        private String TFDate;

        public String getTFDate() {
            return TFDate;
        }

        public void setTFDate(String TFDate) {
            this.TFDate = TFDate;
        }

        public String getPatID() {
            return PatID;
        }

        public void setPatID(String PatID) {
            this.PatID = PatID;
        }

        public String getBillNo() {
            return BillNo;
        }

        public void setBillNo(String BillNo) {
            this.BillNo = BillNo;
        }

        public String getProjectName() {
            return ProjectName;
        }

        public void setProjectName(String ProjectName) {
            this.ProjectName = ProjectName;
        }

        public String getFNumber() {
            return FNumber;
        }

        public void setFNumber(String FNumber) {
            this.FNumber = FNumber;
        }

        public String getPrice() {
            return Price;
        }

        public void setPrice(String Price) {
            this.Price = Price;
        }

        public String getMeasure() {
            return Measure;
        }

        public void setMeasure(String Measure) {
            this.Measure = Measure;
        }

        public String getAmount() {
            return Amount;
        }

        public void setAmount(String Amount) {
            this.Amount = Amount;
        }

        public String getFeeAmount() {
            return FeeAmount;
        }

        public void setFeeAmount(String FeeAmount) {
            this.FeeAmount = FeeAmount;
        }

        public String getItemSpec() {
            return ItemSpec;
        }

        public void setItemSpec(String ItemSpec) {
            this.ItemSpec = ItemSpec;
        }

        public String getPSDate() {
            return PSDate;
        }

        public void setPSDate(String PSDate) {
            this.PSDate = PSDate;
        }

        public String getExcuteDepID() {
            return ExcuteDepID;
        }

        public void setExcuteDepID(String ExcuteDepID) {
            this.ExcuteDepID = ExcuteDepID;
        }

        public String getExcuteDepName() {
            return ExcuteDepName;
        }

        public void setExcuteDepName(String ExcuteDepName) {
            this.ExcuteDepName = ExcuteDepName;
        }

        public String getOrderDepID() {
            return OrderDepID;
        }

        public void setOrderDepID(String OrderDepID) {
            this.OrderDepID = OrderDepID;
        }

        public String getOrderDepName() {
            return OrderDepName;
        }

        public void setOrderDepName(String OrderDepName) {
            this.OrderDepName = OrderDepName;
        }

        public String getBottleSticker() {
            return BottleSticker;
        }

        public void setBottleSticker(String BottleSticker) {
            this.BottleSticker = BottleSticker;
        }

        public String getCostName() {
            return CostName;
        }

        public void setCostName(String CostName) {
            this.CostName = CostName;
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

        public String getBatchNo() {
            return BatchNo;
        }

        public void setBatchNo(String BatchNo) {
            this.BatchNo = BatchNo;
        }

        public String getRoutineRetail() {
            return RoutineRetail;
        }

        public void setRoutineRetail(String RoutineRetail) {
            this.RoutineRetail = RoutineRetail;
        }

        public String getPYM() {
            return PYM;
        }

        public void setPYM(String PYM) {
            this.PYM = PYM;
        }

        public String getWBM() {
            return WBM;
        }

        public void setWBM(String WBM) {
            this.WBM = WBM;
        }

        public String getDoctor() {
            return Doctor;
        }

        public void setDoctor(String Doctor) {
            this.Doctor = Doctor;
        }

        public String getIOWHType() {
            return IOWHType;
        }

        public void setIOWHType(String IOWHType) {
            this.IOWHType = IOWHType;
        }

        public String getIOWHNo() {
            return IOWHNo;
        }

        public void setIOWHNo(String IOWHNo) {
            this.IOWHNo = IOWHNo;
        }

        public String getMFT() {
            return MFT;
        }

        public void setMFT(String MFT) {
            this.MFT = MFT;
        }
    }
}
