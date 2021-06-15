package data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Register {


    private String riskid;
    private String parameter;
    private String type;
    private Float bobot;
    private String owner;
    private Float satu;
    private Float dua;
    private Float tiga;
    private Float empat;
    private Float lima;

    public String getRiskid() {
        return riskid;
    }

    public void setRiskid(String riskid) {
        this.riskid = riskid;
    }

    public String getParameter() {
        return parameter;
    }

    public void setParameter(String parameter) {
        this.parameter = parameter;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Float getBobot() {
        return bobot;
    }

    public void setBobot(Float bobot) {
        this.bobot = bobot;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public Float getSatu() {
        return satu;
    }

    public void setSatu(Float satu) {
        this.satu = satu;
    }

    public Float getDua() {
        return dua;
    }

    public void setDua(Float dua) {
        this.dua = dua;
    }

    public Float getTiga() {
        return tiga;
    }

    public void setTiga(Float tiga) {
        this.tiga = tiga;
    }

    public Float getEmpat() {
        return empat;
    }

    public void setEmpat(Float empat) {
        this.empat = empat;
    }

    public Float getLima() {
        return lima;
    }

    public void setLima(Float lima) {
        this.lima = lima;
    }
}
