package com.fy.entity;

import java.io.Serializable;

/**
 * 新生儿 实体类
 * Created by fangs on 2017/9/12.
 */
public class NewBornNurseRecordBean implements Serializable {

    /**
     * Token : D8E6F21C856C0256406240224D2FE926
     * DateKey : 1509562560000
     * PA_ID : 609452
     * OrderType : 2
     * In_Dis : 轻度妊娠剧吐
     * RecodeDate : 2017-11-02 10:56:49
     * VitalSign_T : 123
     * Reaction : 好
     * Cry : 大
     * SuckForce : 一般
     * FeedingPatterns : 自吮
     * OxygenInhalation : 鼻导管
     * OxygenFlowRate : 123
     * Umbilical : 干燥
     * Urination : 黄色
     * Stool : 正常
     * OtherSituation : 131232131231
     * ExecutiveNurseID : 664
     * ExecutiveNurse : 黄超君
     * QualityControlNurseID :
     * QualityControlNurse :
     * NCNursing : {"BabySex":null,"BirthDate":null,"FeedType":"12312","SkinColor":"红润","Vomit":"喷射","UrinaryProperty":null}
     * NCCommonly : null
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
    private NCNursingBean NCNursing;
    private Object NCCommonly = "";

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

    public NCNursingBean getNCNursing() {
        return NCNursing;
    }

    public void setNCNursing(NCNursingBean NCNursing) {
        this.NCNursing = NCNursing;
    }

    public Object getNCCommonly() {
        return NCCommonly;
    }

    public void setNCCommonly(Object NCCommonly) {
        this.NCCommonly = NCCommonly;
    }

    public static class NCNursingBean implements Serializable {
        /**
         * BabySex : null
         * BirthDate : null
         * FeedType : 12312
         * SkinColor : 红润
         * Vomit : 喷射
         * UrinaryProperty : null
         */

        private Object BabySex = "";
        private String BirthDate = "";
        private String FeedType = "";
        private String SkinColor = "";
        private String Vomit = "";
        private Object UrinaryProperty = "";

        public Object getBabySex() {
            return BabySex;
        }

        public void setBabySex(Object BabySex) {
            this.BabySex = BabySex;
        }

        public String getBirthDate() {
            return BirthDate;
        }

        public void setBirthDate(String BirthDate) {
            this.BirthDate = BirthDate;
        }

        public String getFeedType() {
            return FeedType;
        }

        public void setFeedType(String FeedType) {
            this.FeedType = FeedType;
        }

        public String getSkinColor() {
            return SkinColor;
        }

        public void setSkinColor(String SkinColor) {
            this.SkinColor = SkinColor;
        }

        public String getVomit() {
            return Vomit;
        }

        public void setVomit(String Vomit) {
            this.Vomit = Vomit;
        }

        public Object getUrinaryProperty() {
            return UrinaryProperty;
        }

        public void setUrinaryProperty(Object UrinaryProperty) {
            this.UrinaryProperty = UrinaryProperty;
        }
    }
}
