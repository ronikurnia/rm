package sample;

import dba.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import javax.mail.internet.AddressException;
import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.util.HashMap;
import java.util.ResourceBundle;

import static javafx.application.Platform.exit;


public class Controller implements Initializable {

    private boolean logout = false;
    private String message ="";
    private int hit2, hit3 =0;

    DaoTB tbData = new DaoTB(DbConnection.intradana());
    DaoMKBD mkbdData = new DaoMKBD(DbConnection.intradana());
    DaoTC tcData = new DaoTC(DbConnection.intradana());
    DaoRisk risk = new DaoRisk();

    @FXML    private TextField inpNama;
    @FXML    private PasswordField inpPassword;
    @FXML    private Button btnLogin;
    @FXML    private TabPane tabpane;
  //  @FXML    private Label lblUser;
    @FXML    private Text  a1, a2, a3, a4, a5, a8, a9,a10, a11,
            b1, b2, b3, b4, b5, b6, b8, b9, b10,
            c1, c2,c3;


    public Controller() throws SQLException, ClassNotFoundException {
    }

    @FXML
    private void printHelloWorld(ActionEvent event) {
        event.consume();
        System.out.println("Hello, World!");
        //  loaddata();
    }

    @FXML
    private void login() throws FileNotFoundException {

        if (!logout) {
            String nama = inpNama.getText();
            String psw = inpPassword.getText();
            String un = null;
            String pss = null;
            if (nama.isEmpty() || psw.isEmpty()) {
                System.out.println("psw kosong");
                exit();
            } else {
                try {
                    Class.forName("com.mysql.cj.jdbc.Driver");
                    Connection kon = DriverManager.getConnection("jdbc:mysql://172.25.96.215/risk", "java", "Password@mysql");
                    String sql = "Select * from user Where namo=? AND psw=?";
                    PreparedStatement ps = kon.prepareStatement(sql);
                    ps.setString(1, nama);
                    ps.setString(2, psw);
                    ResultSet rs = ps.executeQuery();

                    while (rs.next()) {
                        un = rs.getString(1);
                        pss = rs.getString(2);
                        System.out.println(un);
                        System.out.println(pss);
                    }
                    if (un.equals(nama) & pss.equals(psw)) {
              //          masuk();
                    } else {
 //                       lblUser.setText("Unser name/password salah");
                        inpNama.setText("");
                        inpPassword.setText("");
                    }
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                } finally {

                }

                //     loaddata();

            }
        } else {
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();
        }
     //   masuk();

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tabpane.setVisible(false);
    }

    @FXML
    private void tes2() {

            masuk();
            posisiMKBD();
            posisikeuangan();
            operasi();
            cekrisk();


   //         Mail.sendMail(message);

    }

    private void posisikeuangan() {
        a1.setText(tbData.getEkuitasS());
        a2.setText(tbData.getPendapatan_s());
        a3.setText(tbData.getBiaya_s());
        a4.setText(tbData.getKasbank_s());
        a5.setText(tbData.getDebiturumum_s());
        a8.setText(tbData.getLaba_s());
        a9.setText(tbData.getRoe_s());
        a10.setText(tbData.getTanggal().toString());
        a11.setText(tbData.getBopo_s());
    }

    private void posisiMKBD() {
        b1.setText(mkbdData.getNilaiMKBD());
        b2.setText(mkbdData.getBufferMKBD_s());
        b3.setText(mkbdData.getPiutangT3_s());
        b4.setText(mkbdData.getPiutangT4_s());
        b5.setText(mkbdData.getGagalSerah_s());
        b6.setText(mkbdData.getGagalTerima_s());
        b8.setText(mkbdData.getKonsentrasi_s());
        b10.setText(mkbdData.getTanggal().toString());
    }

    private void operasi(){
        c1.setText(tcData.getGain_s());
        c2.setText(tcData.getFee_s());
        c3.setText(tcData.getTanggal().toString());
    }
    private void masuk() {
        String nama = inpNama.getText();
        inpNama.setText("");
        inpPassword.setText("");
        inpNama.setVisible(false);
        btnLogin.setText("Log out");
        inpPassword.setVisible(false);
        tabpane.setVisible(true);
        logout = true;
 //       lblUser.setText("Welcome Bp/Ibu, " + nama);
    }

private void cekrisk(){
    if(tbData.getRoe() < risk.getRoe2() && tbData.getRoe()>= risk.getRoe3()) { alert(a9, "ROE Dibawah target\n"); }
    else if (tbData.getRoe() < risk.getRoe3()){warning(a9, "ROE di bawah appetite");}

    if(mkbdData.getBufferMKBD()/1000000000 < risk.getMKBDbuffer()) { warning(b2,"Buffer MKBD Tipis\n"); }

}

private void alert(Text t, String s){
        t.setFill(Color.ORANGERED);
        hit2 +=1;
        }

        private void warning(Text t, String s){
        t.setFill(Color.RED);
        hit2 +=1;
        message += s;
    }
}


