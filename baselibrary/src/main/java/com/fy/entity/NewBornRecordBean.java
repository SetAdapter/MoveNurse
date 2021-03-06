package com.fy.entity;

import java.io.Serializable;

/**
 * 一般新生儿 实体类
 * Created by fangs on 2017/9/12.
 */
public class NewBornRecordBean implements Serializable {

    /**
     * Token : 8F5742D1B9BCEAE0E76D7CC4C9C30589
     * DateKey : 1505689740000
     * PA_ID : 605625
     * OrderType : 1
     * In_Dis :
     * RecodeDate : 2017-09-18 15:09:50
     * VitalSign_T : 2
     * Reaction : 22
     * Cry : 22
     * SuckForce : 33
     * FeedingPatterns : 普食
     * OxygenInhalation : 2123
     * OxygenFlowRate : 3132
     * Umbilical : 脐茸
     * Urination : 2423
     * Stool : 314324
     * OtherSituation : 6546553
     * ExecutiveNurseID : 306
     * ExecutiveNurse : 林昱
     * QualityControlNurseID :
     * QualityControlNurse :
     * NCNursing : null
     * NCCommonly : {"BoxTemperature":null,"VitalSign_PAndHR":"3","VitalSign_R":"4","VitalSign_BP":"56/77","IVT":"123","MilkAmount":"123","OralMucosa":"完整","PerianalSkin":"33","VitalSign_SP02":"","MBS":"4243","Phototherapy":"534"}
     */

    private String Token = "";
    private String DateKey = "";
    private String PA_ID = "";
    private String OrderType = "";
    private String In_Dis = "";
    private String RecodeDate = "";
    private String VitalSign_T = "";
    private String Reaction = "";
    private String Cry = "";
    private String SuckForce = "";
    private String FeedingPatterns = "";
    private String OxygenInhalation = "";
    private String OxygenFlowRate = "";
    private String Umbilical = "";
    private String Urination = "";
    private String Stool = "";
    private String OtherSituation = "";
    private String ExecutiveNurseID = "";
    private String ExecutiveNurse = "";
    private String QualityControlNurseID = "";
    private String QualityControlNurse = "";
    private Object NCNursing = "";
    private NCCommonlyBean NCCommonly;

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getPA_ID() {
        return PA_ID;
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getIn_Dis() {
        return In_Dis;
    }

    public void setIn_Dis(String In_Dis) {
        this.In_Dis = In_Dis;
    }

    public String getRecodeDate() {
        return RecodeDate;
    }

    public void setRecodeDate(String RecodeDate) {
        this.RecodeDate = RecodeDate;
    }

    public String getVitalSign_T() {
        return VitalSign_T;
    }

    public void setVitalSign_T(String VitalSign_T) {
        this.VitalSign_T = VitalSign_T;
    }

    public String getReaction() {
        return Reaction;
    }

    public void setReaction(String Reaction) {
        this.Reaction = Reaction;
    }

    public String getCry() {
        return Cry;
    }

    public void setCry(String Cry) {
        this.Cry = Cry;
    }

    public String getSuckForce() {
        return SuckForce;
    }

    public void setSuckForce(String SuckForce) {
        this.SuckForce = SuckForce;
    }

    public String getFeedingPatterns() {
        return FeedingPatterns;
    }

    public void setFeedingPatterns(String FeedingPatterns) {
        this.FeedingPatterns = FeedingPatterns;
    }

    public String getOxygenInhalation() {
        return OxygenInhalation;
    }

    public void setOxygenInhalation(String OxygenInhalation) {
        this.OxygenInhalation = OxygenInhalation;
    }

    public String getOxygenFlowRate() {
        return OxygenFlowRate;
    }

    public void setOxygenFlowRate(String OxygenFlowRate) {
        this.OxygenFlowRate = OxygenFlowRate;
    }

    public String getUmbilical() {
        return Umbilical;
    }

    public void setUmbilical(String Umbilical) {
        this.Umbilical = Umbilical;
    }

    public String getUrination() {
        return Urination;
    }

    public void setUrination(String Urination) {
        this.Urination = Urination;
    }

    public String getStool() {
        return Stool;
    }

    public void setStool(String Stool) {
        this.Stool = Stool;
    }

    public String getOtherSituation() {
        return OtherSituation;
    }

    public void setOtherSituation(String OtherSituation) {
        this.OtherSituation = OtherSituation;
    }

    public String getExecutiveNurseID() {
        return ExecutiveNurseID;
    }

    public void setExecutiveNurseID(String ExecutiveNurseID) {
        this.ExecutiveNurseID = ExecutiveNurseID;
    }

    public String getExecutiveNurse() {
        return ExecutiveNurse;
    }

    public void setExecutiveNurse(String ExecutiveNurse) {
        this.ExecutiveNurse = ExecutiveNurse;
    }

    public String getQualityControlNurseID() {
        return QualityControlNurseID;
    }

    public void setQualityControlNurseID(String QualityControlNurseID) {
        this.QualityControlNurseID = QualityControlNurseID;
    }

    public String getQualityControlNurse() {
        return QualityControlNurse;
    }

    public void setQualityControlNurse(String QualityControlNurse) {
        this.QualityControlNurse = QualityControlNurse;
    }

    public Object getNCNursing() {
        return NCNursing;
    }

    public void setNCNursing(Object NCNursing) {
        this.NCNursing = NCNursing;
    }

    public NCCommonlyBean getNCCommonly() {
        return NCCommonly;
    }

    public void setNCCommonly(NCCommonlyBean NCCommonly) {
        this.NCCommonly = NCCommonly;
    }

    public static class NCCommonlyBean implements Serializable {
        /**
         * BoxTemperature : 2
         * VitalSign_PAndHR : 3
         * VitalSign_R : 4
         * VitalSign_BP : 56/77
         * IVT : 123
         * MilkAmount : 123
         * OralMucosa : 完整
         * PerianalSkin : 33
         * VitalSign_SP02 :
         * MBS : 4243
         * Phototherapy : 534
         */

        private String BoxTemperature = "";
        private String VitalSign_PAndHR = "";
        private String VitalSign_R = "";
        private String VitalSign_BP = "";
        private String IVT = "";
        private String MilkAmount = "";
        private String OralMucosa = "";
        private String PerianalSkin = "";
        private String VitalSign_SP02 = "";
        private String MBS = "";
        private String Phototherapy = "";

        public String getBoxTemperature() {
            return BoxTemperature;
        }

        public void setBoxTemperature(String BoxTemperature) {
            this.BoxTemperature = BoxTemperature;
        }

        public String getVitalSign_PAndHR() {
            return VitalSign_PAndHR;
        }

        public void setVitalSign_PAndHR(String VitalSign_PAndHR) {
            this.VitalSign_PAndHR = VitalSign_PAndHR;
        }

        public String getVitalSign_R() {
            return VitalSign_R;
        }

        public void setVitalSign_R(String VitalSign_R) {
            this.VitalSign_R = VitalSign_R;
        }

        public String getVitalSign_BP() {
            return VitalSign_BP;
        }

        public void setVitalSign_BP(String VitalSign_BP) {
            this.VitalSign_BP = VitalSign_BP;
        }

        public String getIVT() {
            return IVT;
        }

        public void setIVT(String IVT) {
            this.IVT = IVT;
        }

        public String getMilkAmount() {
            return MilkAmount;
        }

        public void setMilkAmount(String MilkAmount) {
            this.MilkAmount = MilkAmount;
        }

        public String getOralMucosa() {
            return OralMucosa;
        }

        public void setOralMucosa(String OralMucosa) {
            this.OralMucosa = OralMucosa;
        }

        public String getPerianalSkin() {
            return PerianalSkin;
        }

        public void setPerianalSkin(String PerianalSkin) {
            this.PerianalSkin = PerianalSkin;
        }

        public String getVitalSign_SP02() {
            return VitalSign_SP02;
        }

        public void setVitalSign_SP02(String VitalSign_SP02) {
            this.VitalSign_SP02 = VitalSign_SP02;
        }

        public String getMBS() {
            return MBS;
        }

        public void setMBS(String MBS) {
            this.MBS = MBS;
        }

        public String getPhototherapy() {
            return Phototherapy;
        }

        public void setPhototherapy(String Phototherapy) {
            this.Phototherapy = Phototherapy;
        }
    }
}
