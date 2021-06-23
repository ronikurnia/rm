package dba;

import data.MKBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class DaoMKBD {
    List<MKBD> mkbdList = new ArrayList<>();
    HashMap<String, String> mkbd2 = new HashMap<>();
    HashMap<String, String> mkbd4 = new HashMap<>();
    Date tanggal ;
    String nilaiMKBD = "";
    String minimumMKBD = "";
    String  bufferMKBD = " hard code";
 String piutangT3, piutangT4, gagalSerah, gagalTerima,pitangNasabahInsti, rangkingLiabilities, piutangDebt;


    public Date getTanggal() {
        return tanggal;
    }

    public float getPiutangT3() {
        float n = Float.parseFloat(mkbd2.get("VD51.103"));
        return n;
    }


    public String getPiutangT3_s() {
        float n = Float.parseFloat(mkbd2.get("VD51.103"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }
    public float getPiutangT4() {
        float n = Float.parseFloat(mkbd2.get("VD51.104"));
        return n;
    }

    public String getPiutangT4_s() {
        float n = Float.parseFloat(mkbd2.get("VD51.104"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }
    public float getGagalSerah() {
        float n = Float.parseFloat(mkbd2.get("VD51.44"));
        float m = Float.parseFloat(mkbd2.get("VD51.39"));
        return n+m;
    }
    public String getGagalSerah_s() {
        float n = Float.parseFloat(mkbd2.get("VD51.44"));
        float m = Float.parseFloat(mkbd2.get("VD51.39"));
        float nm = n + m;
        String r = String.format("%,.0f", nm/ 1000000);
        return r;
    }

    public float getGagalTerima() {
        float n = Float.parseFloat(mkbd2.get("VD52.137"));
        float m = Float.parseFloat(mkbd2.get("VD52.142"));
        return n+m;
    }
    public String getGagalTerima_s() {
        float n = Float.parseFloat(mkbd2.get("VD52.137"));
        float m = Float.parseFloat(mkbd2.get("VD52.142"));
        float nm = n + m;
        String r = String.format("%,.0f", nm/ 1000000);
        return r;
    }
    public String getPitangNasabahInsti_s() {
        float n = Float.parseFloat(mkbd2.get("VD51.38"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }
    public String getKonsentrasi_s() {
        float n = Float.parseFloat(mkbd2.get("VD53.28"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }
    public String getNilaiMKBD() {
        float n = Float.parseFloat(mkbd4.get("VD59.102"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }

    public void setNilaiMKBD(String nilaiMKBD) {
        this.nilaiMKBD = nilaiMKBD;
    }

    public String getMinimumMKBD() {
        return minimumMKBD;
    }

    public void setMinimumMKBD(String minimumMKBD) {
        this.minimumMKBD = minimumMKBD;
    }

    public String getBufferMKBD_s() {
        float n = Float.parseFloat(mkbd4.get("VD59.104"));
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }

    public void setBufferMKBD(String bufferMKBD) {
        this.bufferMKBD = bufferMKBD;
    }


    public Float getBufferMKBD(){ return Float.parseFloat(mkbd4.get("VD59.104"));}

    public DaoMKBD(Connection con2) {
        ResultSet rs = null;

        PreparedStatement pst = null;
        try {
            pst = con2.prepareStatement("Select * from MKBD");
            rs = pst.executeQuery();
            while (rs.next()) {
                /*
                mkbdList.add(new MKBD(rs.getString("baris"), rs.getString("kolom2"), rs.getString("kolom3")
                        , rs.getString("kolom4"), rs.getString("kolom5"), rs.getFloat("kolom6"),
                        rs.getFloat("kolom7"), rs.getFloat("kolom8"), rs.getFloat("kolom9"), rs.getFloat("kolom10")));
                */
                mkbd4.put(rs.getString("baris"), rs.getString("kolom7"));
                mkbd2.put(rs.getString("baris"), rs.getString("kolom2"));
                this.tanggal = rs.getDate("tanggal");

            }
            rs.close();
            con2.close();
/*
            setNilaiMKBD(mkbd4.get("VD59.102"));
            setMinimumMKBD(mkbd4.get("VD59.103"));
            setBufferMKBD(mkbd4.get("VD59.104"));
*/
//            nilaiMKBD = Float.parseFloat(t1);
//            minimumMKBD= Float.parseFloat(t2);
//            bufferMKBD = Float.parseFloat(t3);
       } catch (SQLException throwables) {throwables.printStackTrace();
        }

    }
}
