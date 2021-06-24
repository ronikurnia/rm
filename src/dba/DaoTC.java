package dba;

import java.sql.*;

public class DaoTC {
    float gain, fee, gainmin1,lossmin1, pnlmin1;
     Date tanggal;

    public String getPnlmin1_s(){
        String ek = String.format("%,.0f", (getPnlmin1() / 1000000));
        return ek;
    }
    public String getGainmin1_s(){
        String ek = String.format("%,.0f", (getGainmin1() / 1000000));
        return ek;
    }
    public String getLossmin1_s(){
        String ek = String.format("%,.0f", (getLossmin1() / 1000000));
        return ek;
    }

    public String getGain_s(){
        String ek = String.format("%,.0f", (getGain() / 1000000));
        return ek;
    }
    public String getFee_s(){
        String ek = String.format("%,.0f", (getFee() / 1000000));
        return ek;
    }

    public float getGain(){return gain;  }
    public Date getTanggal() {
        return tanggal;
    }
    public float getFee() {
        return fee;
    }
    public float getGainmin1() { return gainmin1; }
    public float getLossmin1() { return lossmin1; }
    public float getPnlmin1() { return gainmin1 + lossmin1;}

    public DaoTC(Connection con3) {
        ResultSet rs = null;

        PreparedStatement pst = null;
        try {
            pst = con3.prepareStatement("Select max(Date), sum(GainLoss), sum(fee) from TC");
            rs = pst.executeQuery();
            while (rs.next()) {
                this.fee = rs.getFloat(3);
                this.tanggal = rs.getDate(1);
                this.gain = rs.getFloat(2);
            }
            pst = con3.prepareStatement("Select sum(GainLoss) from TC where Date = (select max(Date) from TC) AND GainLoss > 0");
            rs = pst.executeQuery();
            while (rs.next()) {
                this.gainmin1 = rs.getFloat(1);
            }
            pst = con3.prepareStatement("Select sum(GainLoss) from TC where Date = (select max(Date) from TC) AND GainLoss < 0");
            rs = pst.executeQuery();
            while (rs.next()) {
                this.lossmin1 = rs.getFloat(1);
            }
            rs.close();
            con3.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
