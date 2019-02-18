package com.fy.entity;

import java.io.Serializable;

/**
 * Created by Gab on 2017/8/31 0031.
 */

public class SignInformedBean implements Serializable {

    /**
     * DateKey : 1509091437101
     * EXENurseID : 286
     * EXENurseName : 林海彬
     * Nodes :
     * Opinion :
     * OrderType : 1
     * PA_ID : 606981
     * PatFamilyType :
     * PatFamilyURL : http://192.168.100.30:8099/qm_file/20171027040357_4dcc158a-3e22-41c8-98b2-25b37d9a8f72.png
     * Remark :
     * SignDate : 2017年10月27日
     * Token : BE13A3D075C9EFAFEDA3F3E58336E465
     */

    private String DateKey = "";
    private String EXENurseID = "";
    private String EXENurseName = "";
    private String Nodes = "";
    private String Opinion = "";
    private String OrderType = "";
    private String PA_ID = "";
    private String PatFamilyType = "";
    private String PatFamilyURL = "";
    private String Remark = "";
    private String SignDate = "";
    private String Token = "";

    public String getDateKey() {
        return DateKey;
    }

    public void setDateKey(String DateKey) {
        this.DateKey = DateKey;
    }

    public String getEXENurseID() {
        return EXENurseID;
    }

    public void setEXENurseID(String EXENurseID) {
        this.EXENurseID = EXENurseID;
    }

    public String getEXENurseName() {
        return EXENurseName;
    }

    public void setEXENurseName(String EXENurseName) {
        this.EXENurseName = EXENurseName;
    }

    public String getNodes() {
        return Nodes;
    }

    public void setNodes(String Nodes) {
        this.Nodes = Nodes;
    }

    public String getOpinion() {
        return Opinion;
    }

    public void setOpinion(String Opinion) {
        this.Opinion = Opinion;
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

    public String getPatFamilyType() {
        return PatFamilyType;
    }

    public void setPatFamilyType(String PatFamilyType) {
        this.PatFamilyType = PatFamilyType;
    }

    public String getPatFamilyURL() {
        return PatFamilyURL;
    }

    public void setPatFamilyURL(String PatFamilyURL) {
        this.PatFamilyURL = PatFamilyURL;
    }

    public String getRemark() {
        return Remark;
    }

    public void setRemark(String Remark) {
        this.Remark = Remark;
    }

    public String getSignDate() {
        return SignDate;
    }

    public void setSignDate(String SignDate) {
        this.SignDate = SignDate;
    }

    public String getToken() {
        return Token;
    }

    public void setToken(String Token) {
        this.Token = Token;
    }
}
