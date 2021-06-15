package dba;

import java.sql.*;

public class DaoTC {
    float gain, fee;
    Date tanggal;

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
            rs.close();
            con3.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}
