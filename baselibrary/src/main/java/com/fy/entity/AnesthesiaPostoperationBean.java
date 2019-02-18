package com.fy.entity;

import java.io.Serializable;

/**
 * 术后
 * Created by Gab on 2017/8/31 0031.
 */
public class AnesthesiaPostoperationBean implements Serializable {

        /**
         * AnesthesiaAfter : {"BreathingStatus":"","Exceptional":"","GA":"","GA_BLReason":"","GA_EO":"","GA_JZL":"","GA_KP":"","MentalStatus":"","NB_HGY":"","NB_SS":"","OffGoingPerson":"","OnComingPerson":"","OrderOtherAf":"","OtherAf":"","Postoperative":"","RecConsciousness":"","SA":"","SA_NZL":"","SA_SXGY":"","SA_TT":"","SurgeryEndDate":"","VitalSign":"正常","VitalSign_HR":"","VitalSign_SPO2":""}
         * Consciousness :
         * DateKey : 1507490280000
         * OrderType : 2
         * PA_ID : 604201
         * Token : D9551AB0E3419AFCD0C78C838FE02374
         * VisitDoc :
         * VisitDocDate : 2017/10/9 11:18:24
         * VisitDocID :
         * VisitNurse : 林悦涛
         * VisitNurseDate : 2017/10/9 11:18:24
         * VisitNurseID : 596
         * VitalSign_BP : null/null
         * VitalSign_R :
         */

        private AnesthesiaAfterBean AnesthesiaAfter;
        private String Consciousness = "";
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

        public AnesthesiaAfterBean getAnesthesiaAfter() {
            return AnesthesiaAfter;
        }

        public void setAnesthesiaAfter(AnesthesiaAfterBean AnesthesiaAfter) {
            this.AnesthesiaAfter = AnesthesiaAfter;
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

        public static class AnesthesiaAfterBean implements Serializable{
            /**
             * BreathingStatus :
             * Exceptional :
             * GA :
             * GA_BLReason :
             * GA_EO :
             * GA_JZL :
             * GA_KP :
             * MentalStatus :
             * NB_HGY :
             * NB_SS :
             * OffGoingPerson :
             * OnComingPerson :
             * OrderOtherAf :
             * OtherAf :
             * Postoperative :
             * RecConsciousness :
             * SA :
             * SA_NZL :
             * SA_SXGY :
             * SA_TT :
             * SurgeryEndDate :
             * VitalSign : 正常
             * VitalSign_HR :
             * VitalSign_SPO2 :
             */

            private String BreathingStatus= "";
            private String Exceptional= "";
            private String GA= "";
            private String GA_BLReason= "";
            private String GA_EO= "";
            private String GA_JZL= "";
            private String GA_KP= "";
            private String MentalStatus= "";
            private String NB_HGY= "";
            private String NB_SS= "";
            private String OffGoingPerson= "";
            private String OnComingPerson= "";
            private String OrderOtherAf= "";
            private String OtherAf= "";
            private String Postoperative= "";
            private String RecConsciousness= "";
            private String SA= "";
            private String SA_NZL= "";
            private String SA_SXGY= "";
            private String SA_TT= "";
            private String SurgeryEndDate= "";
            private String VitalSign= "";
            private String VitalSign_HR= "";
            private String VitalSign_SPO2= "";

            public String getBreathingStatus() {
                return BreathingStatus;
            }

            public void setBreathingStatus(String BreathingStatus) {
                this.BreathingStatus = BreathingStatus;
            }

            public String getExceptional() {
                return Exceptional;
            }

            public void setExceptional(String Exceptional) {
                this.Exceptional = Exceptional;
            }

            public String getGA() {
                return GA;
            }

            public void setGA(String GA) {
                this.GA = GA;
            }

            public String getGA_BLReason() {
                return GA_BLReason;
            }

            public void setGA_BLReason(String GA_BLReason) {
                this.GA_BLReason = GA_BLReason;
            }

            public String getGA_EO() {
                return GA_EO;
            }

            public void setGA_EO(String GA_EO) {
                this.GA_EO = GA_EO;
            }

            public String getGA_JZL() {
                return GA_JZL;
            }

            public void setGA_JZL(String GA_JZL) {
                this.GA_JZL = GA_JZL;
            }

            public String getGA_KP() {
                return GA_KP;
            }

            public void setGA_KP(String GA_KP) {
                this.GA_KP = GA_KP;
            }

            public String getMentalStatus() {
                return MentalStatus;
            }

            public void setMentalStatus(String MentalStatus) {
                this.MentalStatus = MentalStatus;
            }

            public String getNB_HGY() {
                return NB_HGY;
            }

            public void setNB_HGY(String NB_HGY) {
                this.NB_HGY = NB_HGY;
            }

            public String getNB_SS() {
                return NB_SS;
            }

            public void setNB_SS(String NB_SS) {
                this.NB_SS = NB_SS;
            }

            public String getOffGoingPerson() {
                return OffGoingPerson;
            }

            public void setOffGoingPerson(String OffGoingPerson) {
                this.OffGoingPerson = OffGoingPerson;
            }

            public String getOnComingPerson() {
                return OnComingPerson;
            }

            public void setOnComingPerson(String OnComingPerson) {
                this.OnComingPerson = OnComingPerson;
            }

            public String getOrderOtherAf() {
                return OrderOtherAf;
            }

            public void setOrderOtherAf(String OrderOtherAf) {
                this.OrderOtherAf = OrderOtherAf;
            }

            public String getOtherAf() {
                return OtherAf;
            }

            public void setOtherAf(String OtherAf) {
                this.OtherAf = OtherAf;
            }

            public String getPostoperative() {
                return Postoperative;
            }

            public void setPostoperative(String Postoperative) {
                this.Postoperative = Postoperative;
            }

            public String getRecConsciousness() {
                return RecConsciousness;
            }

            public void setRecConsciousness(String RecConsciousness) {
                this.RecConsciousness = RecConsciousness;
            }

            public String getSA() {
                return SA;
            }

            public void setSA(String SA) {
                this.SA = SA;
            }

            public String getSA_NZL() {
                return SA_NZL;
            }

            public void setSA_NZL(String SA_NZL) {
                this.SA_NZL = SA_NZL;
            }

            public String getSA_SXGY() {
                return SA_SXGY;
            }

            public void setSA_SXGY(String SA_SXGY) {
                this.SA_SXGY = SA_SXGY;
            }

            public String getSA_TT() {
                return SA_TT;
            }

            public void setSA_TT(String SA_TT) {
                this.SA_TT = SA_TT;
            }

            public String getSurgeryEndDate() {
                return SurgeryEndDate;
            }

            public void setSurgeryEndDate(String SurgeryEndDate) {
                this.SurgeryEndDate = SurgeryEndDate;
            }

            public String getVitalSign() {
                return VitalSign;
            }

            public void setVitalSign(String VitalSign) {
                this.VitalSign = VitalSign;
            }

            public String getVitalSign_HR() {
                return VitalSign_HR;
            }

            public void setVitalSign_HR(String VitalSign_HR) {
                this.VitalSign_HR = VitalSign_HR;
            }

            public String getVitalSign_SPO2() {
                return VitalSign_SPO2;
            }

            public void setVitalSign_SPO2(String VitalSign_SPO2) {
                this.VitalSign_SPO2 = VitalSign_SPO2;
            }
        }
}
