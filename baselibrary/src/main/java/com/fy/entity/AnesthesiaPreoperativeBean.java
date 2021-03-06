package com.fy.entity;

import com.fy.base.BaseBean;

import java.io.Serializable;

/**
 * 术前
 * Created by Gab on 2017/8/31 0031.
 */
public class AnesthesiaPreoperativeBean extends BaseBean {

    /**
     * AnesthesiaBefore : {"ASA":"","AnesthesiaIndication":"","AnesthesiaOther":"","AnesthesiaType":"","CBC":"","CXR":"","Coagulation":"","ECG":"","GLU":"","HasAnesthesia":"","HasHXSick":"","HeartGrade":"","In_Dis":"","IsHealthy":"","LFT":"","Lung":"","MallampatiGrade":"","ModusOperandi":"","OrderOther":"","OtherEx":"","Renal":"","SurgeryDate":"","ToothStatus":"","VertebraStatus":"","VitalSign_P":"","VitalSign_T":"","XFFStatus":""}
     * Consciousness :
     * DateKey : 1507503840000
     * OrderType : 1
     * PA_ID : 604201
     * Token : 582303BE120143F14CBB27BDDA72E7F2
     * VisitDoc :
     * VisitDocDate : 2017/10/9 15:04:25
     * VisitDocID :
     * VisitNurse : 林悦涛
     * VisitNurseDate : 2017/10/9 15:04:25
     * VisitNurseID : 596
     * VitalSign_BP : null/null
     * VitalSign_R :
     */

    private AnesthesiaBeforeBean AnesthesiaBefore;
    private String Consciousness= "";
    private String DateKey= "";
    private String OrderType= "";
    private String PA_ID= "";
    private String Token= "";
    private String VisitDoc= "";
    private String VisitDocDate= "";
    private String VisitDocID= "";
    private String VisitNurse= "";
    private String VisitNurseDate= "";
    private String VisitNurseID= "";
    private String VitalSign_BP= "";
    private String VitalSign_R= "";

    public AnesthesiaBeforeBean getAnesthesiaBefore() {
        return AnesthesiaBefore;
    }

    public void setAnesthesiaBefore(AnesthesiaBeforeBean AnesthesiaBefore) {
        this.AnesthesiaBefore = AnesthesiaBefore;
    }

    public String getConsciousness() {
        return Consciousness;
    }

    public void setConsciousness(String Consciousness) {
        this.Consciousness = Consciousness;
    }

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getOrderType() {
        return OrderType;
    }

    public void setOrderType(String OrderType) {
        this.OrderType = OrderType;
    }

    public String getPA_ID() {
        return PA_ID;
    }

    public void setPA_ID(String PA_ID) {
        this.PA_ID = PA_ID;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }

    public String getVisitDoc() {
        return VisitDoc;
    }

    public void setVisitDoc(String VisitDoc) {
        this.VisitDoc = VisitDoc;
    }

    public String getVisitDocDate() {
        return VisitDocDate;
    }

    public void setVisitDocDate(String VisitDocDate) {
        this.VisitDocDate = VisitDocDate;
    }

    public String getVisitDocID() {
        return VisitDocID;
    }

    public void setVisitDocID(String VisitDocID) {
        this.VisitDocID = VisitDocID;
    }

    public String getVisitNurse() {
        return VisitNurse;
    }

    public void setVisitNurse(String VisitNurse) {
        this.VisitNurse = VisitNurse;
    }

    public String getVisitNurseDate() {
        return VisitNurseDate;
    }

    public void setVisitNurseDate(String VisitNurseDate) {
        this.VisitNurseDate = VisitNurseDate;
    }

    public String getVisitNurseID() {
        return VisitNurseID;
    }

    public void setVisitNurseID(String VisitNurseID) {
        this.VisitNurseID = VisitNurseID;
    }

    public String getVitalSign_BP() {
        return VitalSign_BP;
    }

    public void setVitalSign_BP(String VitalSign_BP) {
        this.VitalSign_BP = VitalSign_BP;
    }

    public String getVitalSign_R() {
        return VitalSign_R;
    }

    public void setVitalSign_R(String VitalSign_R) {
        this.VitalSign_R = VitalSign_R;
    }

    public static class AnesthesiaBeforeBean implements Serializable{
        /**
         * ASA :
         * AnesthesiaIndication :
         * AnesthesiaOther :
         * AnesthesiaType :
         * CBC :
         * CXR :
         * Coagulation :
         * ECG :
         * GLU :
         * HasAnesthesia :
         * HasHXSick :
         * HeartGrade :
         * In_Dis :
         * IsHealthy :
         * LFT :
         * Lung :
         * MallampatiGrade :
         * ModusOperandi :
         * OrderOther :
         * OtherEx :
         * Renal :
         * SurgeryDate :
         * ToothStatus :
         * VertebraStatus :
         * VitalSign_P :
         * VitalSign_T :
         * XFFStatus :
         */

        private String ASA= "";
        private String AnesthesiaIndication= "";
        private String AnesthesiaOther= "";
        private String AnesthesiaType= "";
        private String CBC= "";
        private String CXR= "";
        private String Coagulation= "";
        private String ECG= "";
        private String GLU= "";
        private String HasAnesthesia= "";
        private String HasHXSick= "";
        private String HeartGrade= "";
        private String In_Dis= "";
        private String IsHealthy= "";
        private String LFT= "";
        private String Lung= "";
        private String MallampatiGrade= "";
        private String ModusOperandi= "";
        private String OrderOther= "";
        private String OtherEx= "";
        private String Renal= "";
        private String SurgeryDate= "";
        private String ToothStatus= "";
        private String VertebraStatus= "";
        private String VitalSign_P= "";
        private String VitalSign_T= "";
        private String XFFStatus= "";

        public String getASA() {
            return ASA;
        }

        public void setASA(String ASA) {
            this.ASA = ASA;
        }

        public String getAnesthesiaIndication() {
            return AnesthesiaIndication;
        }

        public void setAnesthesiaIndication(String AnesthesiaIndication) {
            this.AnesthesiaIndication = AnesthesiaIndication;
        }

        public String getAnesthesiaOther() {
            return AnesthesiaOther;
        }

        public void setAnesthesiaOther(String AnesthesiaOther) {
            this.AnesthesiaOther = AnesthesiaOther;
        }

        public String getAnesthesiaType() {
            return AnesthesiaType;
        }

        public void setAnesthesiaType(String AnesthesiaType) {
            this.AnesthesiaType = AnesthesiaType;
        }

        public String getCBC() {
            return CBC;
        }

        public void setCBC(String CBC) {
            this.CBC = CBC;
        }

        public String getCXR() {
            return CXR;
        }

        public void setCXR(String CXR) {
            this.CXR = CXR;
        }

        public String getCoagulation() {
            return Coagulation;
        }

        public void setCoagulation(String Coagulation) {
            this.Coagulation = Coagulation;
        }

        public String getECG() {
            return ECG;
        }

        public void setECG(String ECG) {
            this.ECG = ECG;
        }

        public String getGLU() {
            return GLU;
        }

        public void setGLU(String GLU) {
            this.GLU = GLU;
        }

        public String getHasAnesthesia() {
            return HasAnesthesia;
        }

        public void setHasAnesthesia(String HasAnesthesia) {
            this.HasAnesthesia = HasAnesthesia;
        }

        public String getHasHXSick() {
            return HasHXSick;
        }

        public void setHasHXSick(String HasHXSick) {
            this.HasHXSick = HasHXSick;
        }

        public String getHeartGrade() {
            return HeartGrade;
        }

        public void setHeartGrade(String HeartGrade) {
            this.HeartGrade = HeartGrade;
        }

        public String getIn_Dis() {
            return In_Dis;
        }

        public void setIn_Dis(String In_Dis) {
            this.In_Dis = In_Dis;
        }

        public String getIsHealthy() {
            return IsHealthy;
        }

        public void setIsHealthy(String IsHealthy) {
            this.IsHealthy = IsHealthy;
        }

        public String getLFT() {
            return LFT;
        }

        public void setLFT(String LFT) {
            this.LFT = LFT;
        }

        public String getLung() {
            return Lung;
        }

        public void setLung(String Lung) {
            this.Lung = Lung;
        }

        public String getMallampatiGrade() {
            return MallampatiGrade;
        }

        public void setMallampatiGrade(String MallampatiGrade) {
            this.MallampatiGrade = MallampatiGrade;
        }

        public String getModusOperandi() {
            return ModusOperandi;
        }

        public void setModusOperandi(String ModusOperandi) {
            this.ModusOperandi = ModusOperandi;
        }

        public String getOrderOther() {
            return OrderOther;
        }

        public void setOrderOther(String OrderOther) {
            this.OrderOther = OrderOther;
        }

        public String getOtherEx() {
            return OtherEx;
        }

        public void setOtherEx(String OtherEx) {
            this.OtherEx = OtherEx;
        }

        public String getRenal() {
            return Renal;
        }

        public void setRenal(String Renal) {
            this.Renal = Renal;
        }

        public String getSurgeryDate() {
            return SurgeryDate;
        }

        public void setSurgeryDate(String SurgeryDate) {
            this.SurgeryDate = SurgeryDate;
        }

        public String getToothStatus() {
            return ToothStatus;
        }

        public void setToothStatus(String ToothStatus) {
            this.ToothStatus = ToothStatus;
        }

        public String getVertebraStatus() {
            return VertebraStatus;
        }

        public void setVertebraStatus(String VertebraStatus) {
            this.VertebraStatus = VertebraStatus;
        }

        public String getVitalSign_P() {
            return VitalSign_P;
        }

        public void setVitalSign_P(String VitalSign_P) {
            this.VitalSign_P = VitalSign_P;
        }

        public String getVitalSign_T() {
            return VitalSign_T;
        }

        public void setVitalSign_T(String VitalSign_T) {
            this.VitalSign_T = VitalSign_T;
        }

        public String getXFFStatus() {
            return XFFStatus;
        }

        public void setXFFStatus(String XFFStatus) {
            this.XFFStatus = XFFStatus;
        }
    }

    @Override
    public String toString() {
        return "AnesthesiaPreoperativeBean{" +
                "AnesthesiaBefore=" + AnesthesiaBefore +
                ", Consciousness='" + Consciousness + '\'' +
                ", DateKey='" + DateKey + '\'' +
                ", OrderType='" + OrderType + '\'' +
                ", PA_ID='" + PA_ID + '\'' +
                ", Token='" + Token + '\'' +
                ", VisitDoc='" + VisitDoc + '\'' +
                ", VisitDocDate='" + VisitDocDate + '\'' +
                ", VisitDocID='" + VisitDocID + '\'' +
                ", VisitNurse='" + VisitNurse + '\'' +
                ", VisitNurseDate='" + VisitNurseDate + '\'' +
                ", VisitNurseID='" + VisitNurseID + '\'' +
                ", VitalSign_BP='" + VitalSign_BP + '\'' +
                ", VitalSign_R='" + VitalSign_R + '\'' +
                '}';
    }
}
