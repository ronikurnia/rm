package data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class User {
    @SerializedName("iduser")
    @Expose
    private String iduser;
    @SerializedName("namo")
    @Expose
    private String namo;
    @SerializedName("psw")
    @Expose
    private String psw;
    @SerializedName("peran")
    @Expose
    private String peran;

    public String getIduser() {
        return iduser;
    }

    public void setIduser(String iduser) {
        this.iduser = iduser;
    }

    public String getNamo() {
        return namo;
    }

    public void setNamo(String namo) {
        this.namo = namo;
    }

    public String getPsw() {
        return psw;
    }

    public void setPsw(String psw) {
        this.psw = psw;
    }

    public String getPeran() {
        return peran;
    }

    public void setPeran(String peran) {
        this.peran = peran;
    }
}
