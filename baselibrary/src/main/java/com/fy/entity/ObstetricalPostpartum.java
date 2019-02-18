package com.fy.entity;

import java.io.Serializable;

/**
 * Created by Gab on 2017/9/20 0020.
 *  产科产后记录单 实体类
 */

public class ObstetricalPostpartum implements Serializable {

    /**
     * DateKey : 1505878162302
     * ExecutiveNurse : 林昱
     * ExecutiveNurseID : 306
     * In_Amount :
     * In_Dis :
     * In_Project :
     * OC_UCS :
     * OrderType : 3
     * OtherSituation :
     * Out_Amount :
     * Out_Project :
     * PA_ID : 605625
     * Postpartum : {"AbdomenWound":"","BreastCondition":"","Catheters":"","FundusHeight":"","Lactation":"","LochiaNature":"","POGBA":"","PerinealWound":"","VitalSign_R":"","VitalSign_SP02":"","VitalSign_T":""}
     * QualityControlNurse :
     * QualityControlNurseID :
     * RecodeDate : 2017-09-20 11:29:13
     * Token : 9AB439AECBAA03A448BD7892FFDA7034
     * VitalSign_BP : /
     * VitalSign_PAndHR :
     */

    private String DateKey = "";
    private String ExecutiveNurse = "";
    private String ExecutiveNurseID = "";
    private String In_Amount = "";
    private String In_Dis = "";
    private String In_Project = "";
    private String OC_UCS = "";
    private String OrderType = "";
    private String OtherSituation = "";
    private String Out_Amount = "";
    private String Out_Project = "";
    private String PA_ID = "";
    private PostpartumBean Postpartum;
    private String QualityControlNurse = "";
    private String QualityControlNurseID = "";
    private String RecodeDate = "";
    private String Token = "";
    private String VitalSign_BP = "";
    private String VitalSign_PAndHR = "";

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getExecutiveNurse() {
        return ExecutiveNurse;
    }

    public void setExecutiveNurse(String ExecutiveNurse) {
        this.ExecutiveNurse = ExecutiveNurse;
    }

    public String getExecutiveNurseID() {
        return ExecutiveNurseID;
    }

    public void setExecutiveNurseID(String ExecutiveNurseID) {
        this.ExecutiveNurseID = ExecutiveNurseID;
    }

    public String getIn_Amount() {
        return In_Amount;
    }

    public void setIn_Amount(String In_Amount) {
        this.In_Amount = In_Amount;
    }

    public String getIn_Dis() {
        return In_Dis;
    }

    public void setIn_Dis(String In_Dis) {
        this.In_Dis = In_Dis;
    }

    public String getIn_Project() {
        return In_Project;
    }

    public void setIn_Project(String In_Project) {
        this.In_Project = In_Project;
    }

    public String getOC_UCS() {
        return OC_UCS;
    }

    public void setOC_UCS(String OC_UCS) {
        this.OC_UCS = OC_UCS;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getOtherSituation() {
        return OtherSituation;
    }

    public void setOtherSituation(String OtherSituation) {
        this.OtherSituation = OtherSituation;
    }

    public String getOut_Amount() {
        return Out_Amount;
    }

    public void setOut_Amount(String Out_Amount) {
        this.Out_Amount = Out_Amount;
    }

    public String getOut_Project() {
        return Out_Project;
    }

    public void setOut_Project(String Out_Project) {
        this.Out_Project = Out_Project;
    }

    public String getPA_ID() {
        return PA_ID;
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

    public PostpartumBean getPostpartum() {
        return Postpartum;
    }

    public void setPostpartum(PostpartumBean Postpartum) {
        this.Postpartum = Postpartum;
    }

    public String getQualityControlNurse() {
        return QualityControlNurse;
    }

    public void setQualityControlNurse(String QualityControlNurse) {
        this.QualityControlNurse = QualityControlNurse;
    }

    public String getQualityControlNurseID() {
        return QualityControlNurseID;
    }

    public void setQualityControlNurseID(String QualityControlNurseID) {
        this.QualityControlNurseID = QualityControlNurseID;
    }

    public String getRecodeDate() {
        return RecodeDate;
    }

    public void setRecodeDate(String RecodeDate) {
        this.RecodeDate = RecodeDate;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getVitalSign_BP() {
        return VitalSign_BP;
    }

    public void setVitalSign_BP(String VitalSign_BP) {
        this.VitalSign_BP = VitalSign_BP;
    }

    public String getVitalSign_PAndHR() {
        return VitalSign_PAndHR;
    }

    public void setVitalSign_PAndHR(String VitalSign_PAndHR) {
        this.VitalSign_PAndHR = VitalSign_PAndHR;
    }

    public static class PostpartumBean implements Serializable {
        /**
         * AbdomenWound :
         * BreastCondition :
         * Catheters :
         * FundusHeight :
         * Lactation :
         * LochiaNature :
         * POGBA :
         * PerinealWound :
         * VitalSign_R :
         * VitalSign_SP02 :
         * VitalSign_T :
         */

        private String AbdomenWound = "";
        private String BreastCondition = "";
        private String Catheters = "";
        private String FundusHeight = "";
        private String Lactation = "";
        private String LochiaNature = "";
        private String POGBA = "";
        private String PerinealWound = "";
        private String VitalSign_R = "";
        private String VitalSign_SP02 = "";
        private String VitalSign_T = "";

        public String getAbdomenWound() {
            return AbdomenWound;
        }

        public void setAbdomenWound(String AbdomenWound) {
            this.AbdomenWound = AbdomenWound;
        }

        public String getBreastCondition() {
            return BreastCondition;
        }

        public void setBreastCondition(String BreastCondition) {
            this.BreastCondition = BreastCondition;
        }

        public String getCatheters() {
            return Catheters;
        }

        public void setCatheters(String Catheters) {
            this.Catheters = Catheters;
        }

        public String getFundusHeight() {
            return FundusHeight;
        }

        public void setFundusHeight(String FundusHeight) {
            this.FundusHeight = FundusHeight;
        }

        public String getLactation() {
            return Lactation;
        }

        public void setLactation(String Lactation) {
            this.Lactation = Lactation;
        }

        public String getLochiaNature() {
            return LochiaNature;
        }

        public void setLochiaNature(String LochiaNature) {
            this.LochiaNature = LochiaNature;
        }

        public String getPOGBA() {
            return POGBA;
        }

        public void setPOGBA(String POGBA) {
            this.POGBA = POGBA;
        }

        public String getPerinealWound() {
            return PerinealWound;
        }

        public void setPerinealWound(String PerinealWound) {
            this.PerinealWound = PerinealWound;
        }

        public String getVitalSign_R() {
            return VitalSign_R;
        }

        public void setVitalSign_R(String VitalSign_R) {
            this.VitalSign_R = VitalSign_R;
        }

        public String getVitalSign_SP02() {
            return VitalSign_SP02;
        }

        public void setVitalSign_SP02(String VitalSign_SP02) {
            this.VitalSign_SP02 = VitalSign_SP02;
        }

        public String getVitalSign_T() {
            return VitalSign_T;
        }

        public void setVitalSign_T(String VitalSign_T) {
            this.VitalSign_T = VitalSign_T;
        }
    }
}
