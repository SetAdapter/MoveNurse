package com.fy.entity;

import java.io.Serializable;

/**
 * 产前待产记录 实体类
 * Created by fangs on 2017/9/12.
 */
public class AntenatalRecordBean implements Serializable {

    /**
     * Antenatal : {"Aerophose":"","GestationalWeeks":"","Gravidity":"","KneeJerk":"","OC_AFI":"","OC_CD":"","OC_CS":"","OC_Caul":"","OC_FHt":"","OC_FM":"","OC_POTF":"","OC_SO":"","OC_UCI":"/","Parity":"","VitalSign_R":"","VitalSign_SP02":"","VitalSign_T":""}
     * DateKey : 1505438475211
     * ExecutiveNurse : 林昱
     * ExecutiveNurseID : 306
     * In_Amount :
     * In_Dis :
     * In_Project :
     * OC_UCS :
     * OrderType : 1
     * OtherSituation :
     * Out_Amount :
     * Out_Project :
     * PA_ID : 605625
     * QualityControlNurse :
     * QualityControlNurseID :
     * RecodeDate : 2017/9/15 9:21:30
     * Token : CAE0FE375421296DCB9FB2E3AB3629CB
     * VitalSign_BP : /
     * VitalSign_PAndHR :
     */

    private AntenatalBean Antenatal;
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
    private String QualityControlNurse = "";
    private String QualityControlNurseID = "";
    private String RecodeDate = "";
    private String Token = "";
    private String VitalSign_BP = "";
    private String VitalSign_PAndHR = "";

    public AntenatalBean getAntenatal() {
        return Antenatal;
    }

    public void setAntenatal(AntenatalBean Antenatal) {
        this.Antenatal = Antenatal;
    }

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

    public static class AntenatalBean implements Serializable {
        /**
         * Aerophose :
         * GestationalWeeks :
         * Gravidity :
         * KneeJerk :
         * OC_AFI :
         * OC_CD :
         * OC_CS :
         * OC_Caul :
         * OC_FHt :
         * OC_FM :
         * OC_POTF :
         * OC_SO :
         * OC_UCI : /
         * Parity :
         * VitalSign_R :
         * VitalSign_SP02 :
         * VitalSign_T :
         */

        private String Aerophose = "";
        private String GestationalWeeks = "";
        private String Gravidity = "";
        private String KneeJerk = "";
        private String OC_AFI = "";
        private String OC_CD = "";
        private String OC_CS = "";
        private String OC_Caul = "";
        private String OC_FHt = "";
        private String OC_FM = "";
        private String OC_POTF = "";
        private String OC_SO = "";
        private String OC_UCI = "";
        private String Parity = "";
        private String VitalSign_R = "";
        private String VitalSign_SP02 = "";
        private String VitalSign_T = "";

        public String getAerophose() {
            return Aerophose;
        }

        public void setAerophose(String Aerophose) {
            this.Aerophose = Aerophose;
        }

        public String getGestationalWeeks() {
            return GestationalWeeks;
        }

        public void setGestationalWeeks(String GestationalWeeks) {
            this.GestationalWeeks = GestationalWeeks;
        }

        public String getGravidity() {
            return Gravidity;
        }

        public void setGravidity(String Gravidity) {
            this.Gravidity = Gravidity;
        }

        public String getKneeJerk() {
            return KneeJerk;
        }

        public void setKneeJerk(String KneeJerk) {
            this.KneeJerk = KneeJerk;
        }

        public String getOC_AFI() {
            return OC_AFI;
        }

        public void setOC_AFI(String OC_AFI) {
            this.OC_AFI = OC_AFI;
        }

        public String getOC_CD() {
            return OC_CD;
        }

        public void setOC_CD(String OC_CD) {
            this.OC_CD = OC_CD;
        }

        public String getOC_CS() {
            return OC_CS;
        }

        public void setOC_CS(String OC_CS) {
            this.OC_CS = OC_CS;
        }

        public String getOC_Caul() {
            return OC_Caul;
        }

        public void setOC_Caul(String OC_Caul) {
            this.OC_Caul = OC_Caul;
        }

        public String getOC_FHt() {
            return OC_FHt;
        }

        public void setOC_FHt(String OC_FHt) {
            this.OC_FHt = OC_FHt;
        }

        public String getOC_FM() {
            return OC_FM;
        }

        public void setOC_FM(String OC_FM) {
            this.OC_FM = OC_FM;
        }

        public String getOC_POTF() {
            return OC_POTF;
        }

        public void setOC_POTF(String OC_POTF) {
            this.OC_POTF = OC_POTF;
        }

        public String getOC_SO() {
            return OC_SO;
        }

        public void setOC_SO(String OC_SO) {
            this.OC_SO = OC_SO;
        }

        public String getOC_UCI() {
            return OC_UCI;
        }

        public void setOC_UCI(String OC_UCI) {
            this.OC_UCI = OC_UCI;
        }

        public String getParity() {
            return Parity;
        }

        public void setParity(String Parity) {
            this.Parity = Parity;
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

    @Override
    public String toString() {
        return "AntenatalRecordBean{" +
                "Antenatal=" + Antenatal +
                ", DateKey='" + DateKey + '\'' +
                ", ExecutiveNurse='" + ExecutiveNurse + '\'' +
                ", ExecutiveNurseID='" + ExecutiveNurseID + '\'' +
                ", In_Amount='" + In_Amount + '\'' +
                ", In_Dis='" + In_Dis + '\'' +
                ", In_Project='" + In_Project + '\'' +
                ", OC_UCS='" + OC_UCS + '\'' +
                ", OrderType='" + OrderType + '\'' +
                ", OtherSituation='" + OtherSituation + '\'' +
                ", Out_Amount='" + Out_Amount + '\'' +
                ", Out_Project='" + Out_Project + '\'' +
                ", PA_ID='" + PA_ID + '\'' +
                ", QualityControlNurse='" + QualityControlNurse + '\'' +
                ", QualityControlNurseID='" + QualityControlNurseID + '\'' +
                ", RecodeDate='" + RecodeDate + '\'' +
                ", Token='" + Token + '\'' +
                ", VitalSign_BP='" + VitalSign_BP + '\'' +
                ", VitalSign_PAndHR='" + VitalSign_PAndHR + '\'' +
                '}';
    }
}
