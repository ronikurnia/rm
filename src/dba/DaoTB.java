package dba;

import java.sql.*;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class DaoTB {
    HashMap<String, Float> tbList = new HashMap<>();

    float kas, bank, piutang, piutangibcm, piutangibas,
            portofolio, afs, bungayad, pendapatanyad, biayaymd, pajakymd,
            penyertaan, aktivatetap, asetsgu, penyaktivatetap,
            piutangafiliasi, piutangpegawai, piutanglainnya, jaminan, bebanditangguhkan,
            debiturumum, uangmuka, pajaktangguhan;
    float pendapatan, biaya, ekuitas, kewajiban, aset, aktivalancar, asetlainnya, gedung;
    Date tanggal;
    LocalDate hariini = LocalDate.now();
    float pendEqBro, pendIBCM, pendIBAS, pendDCM;

    public String getDebiturumum_s(){ return String.format("%,.0f", (getDebiturumum()) * 1 / 1000000); }
    public String getKasbank_s(){ return String.format("%,.0f", (getKasbank()) * 1 / 1000000); }
    public String getBiaya_s(){ return String.format("%,.0f", (getBiaya()) * 1 / 1000000);}
    public String getPendapatan_s(){ return String.format("%,.0f", (getPendapatan()) * -1 / 1000000);}

public String getBopo_s(){
    float bopo = (getBiaya()*-100) / getPendapatan();
    return (String.format("%.0f%%",bopo));
}
    public String getRoe_s() {
        float percent = (getLaba()*100) / getEkuitas();
        float p = percent * 365/getJumlahhari();
        return (String.format("%.1f%%",p));
    }
    public String getEkuitasS() {return String.format("%,.0f", (getEkuitas()) * -1 / 1000000);}
    public String getLaba_s() {return String.format("%,.0f", (this.pendapatan + this.biaya) * -1 / 1000000);}
    public String getAset_s() {
        float n = aset / 1000000;
        String r = String.format("%,.0f", n / 1000000);
        return r;
    }
    public int getJumlahhari() {return hariini.getDayOfYear();}

    // untuk getter setter standar:

    public Float getKasbank(){ return kas + bank;}
    public Float getRoe() {
        float percent = (getLaba()) / getEkuitas();
        float p = percent * 365/getJumlahhari();
        return p;
    }

    public LocalDate getHariini() { return hariini; }
    public float getLaba(){ return getPendapatan() + getBiaya();}
    public Date getTanggal() { return tanggal;   }
    public float getKewajiban() { return kewajiban;  }
    public void setKewajiban(float kewajiban) {this.kewajiban = kewajiban;  }
    public float getEkuitas() {return ekuitas; }
    public void setEkuitas(float ekuitas) { this.ekuitas = ekuitas; }
    public float getPendapatan() {return pendapatan; }
    public void setPendapatan(float pendapatan) {this.pendapatan = pendapatan; }
    public float getBiaya() { return biaya; }
    public void setBiaya(float biaya) { this.biaya = biaya;  }
    public float getAktivatetap() { return aktivatetap;  }
    public void setAktivatetap(float aktivatetap) { this.aktivatetap = aktivatetap;   }
    public float getDebiturumum() { return debiturumum;  }
    public DaoTB(Connection con) throws SQLException {
        ResultSet rs = null;

        PreparedStatement pst = null;
        pst = con.prepareStatement("Select AccountID, AccountName, CurrentBalance, Tanggal from TB order by AccountID");
        rs = pst.executeQuery();
        while (rs.next()) {
//                System.out.println(rs.getString("AccountID")+" , "+rs.getString("AccountName")+" ,"+rs.getFloat("CurrentBalance"));
            tbList.put(rs.getString(1), rs.getFloat(3));
            this.tanggal = rs.getDate("Tanggal");
        }
        rs.close();
        con.close();

        // buat iterasi pengambilan data
        for (Map.Entry<String, Float> entry : tbList.entrySet()) {
            if (entry.getKey().startsWith("100")) {
                kas += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("110")) {
                bank += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("118")) {
                bank += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("131")) {
                piutang += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("133")) {
                piutangibcm += +entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("135")) {
                piutangibas += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("141")) {
                portofolio += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("142")) {
                afs += entry.getValue();
                aktivalancar += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("150")) {
                bungayad += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("151")) {
                bungayad += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("169")) {
                pendapatanyad += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("189")) {
                biayaymd += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("190")) {
                pajakymd += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("210")) {
                penyertaan += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("222")) {
                penyertaan += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("223")) {
                gedung += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("225")) {
                asetsgu += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("232")) {
                penyaktivatetap += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("233")) {
                penyaktivatetap += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("235")) {
                penyaktivatetap += entry.getValue();
                aktivatetap += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("242")) {
                piutangafiliasi += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("243")) {
                piutangpegawai += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("245")) {
                piutanglainnya += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("250")) {
                jaminan += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("255")) {
                bebanditangguhkan += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("263")) {
                debiturumum += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("264")) {
                uangmuka += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("287")) {
                pajaktangguhan += entry.getValue();
                asetlainnya += entry.getValue();
                aset += entry.getValue();
            } else if (entry.getKey().startsWith("400")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("430")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("445")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("450")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("460")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("490")) {
                ekuitas += entry.getValue();
            } else if (entry.getKey().startsWith("7")) {
                biaya += entry.getValue();
            } else if (entry.getKey().startsWith("8")) {
                pendapatan += entry.getValue();
            } else if (entry.getKey().startsWith("952")) {
                biaya += entry.getValue();
            }
        }
    }
}
