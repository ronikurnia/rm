package sample;


import java.util.Date;

public class PosisiAlert {
    Date tglDaoTC = new Date(2021,06,21) ;;
    Date tglDaoMKBD= new Date(2021,06,21) ;
    Date tglDaoTB = new Date(2021,06,21) ;;
    float roe=0;
    float rlECM=0;
    float mkbdBuffer=0;
    float bopo=0;
    float T3=0;
    float t4=0;
    float gagalSerah=0;
    float gagalTerima=0;
    float pnlEcmMin1=0;

    public float getPnlEcmMin1() {
        return pnlEcmMin1;
    }

    public void setPnlEcmMin1(float pnlEcmMin1) {
        this.pnlEcmMin1 = pnlEcmMin1;
    }
    public float getRoe() {
        return roe;
    }

    public void setRoe(float roe) {
        this.roe = roe;
    }

    public Date getTglDaoTC() {
        return tglDaoTC;
    }

    public void setTglDaoTC(Date tglDaoTC) {
        this.tglDaoTC = tglDaoTC;
    }

    public Date getTglDaoMKBD() {
        return tglDaoMKBD;
    }

    public void setTglDaoMKBD(Date tglDaoMKBD) {
        this.tglDaoMKBD = tglDaoMKBD;
    }

    public Date getTglDaoTB() {
        return tglDaoTB;
    }

    public void setTglDaoTB(Date tglDaoTB) {
        this.tglDaoTB = tglDaoTB;
    }

    public float getRlECM() {
        return rlECM;
    }

    public void setRlECM(float rlECM) {
        this.rlECM = rlECM;
    }

    public float getMkbdBuffer() {
        return mkbdBuffer;
    }

    public void setMkbdBuffer(float mkbdBuffer) {
        this.mkbdBuffer = mkbdBuffer;
    }

    public float getBopo() {
        return bopo;
    }

    public void setBopo(float bopo) {
        this.bopo = bopo;
    }

    public float getT3() {
        return T3;
    }

    public void setT3(float t3) {
        T3 = t3;
    }

    public float getT4() {
        return t4;
    }

    public void setT4(float t4) {
        this.t4 = t4;
    }

    public float getGagalSerah() {
        return gagalSerah;
    }

    public void setGagalSerah(float gagalSerah) {
        this.gagalSerah = gagalSerah;
    }

    public float getGagalTerima() {
        return gagalTerima;
    }

    public void setGagalTerima(float gagalTerima) {
        this.gagalTerima = gagalTerima;
    }
}
