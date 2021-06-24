package sample;

import dba.*;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;


import java.io.FileNotFoundException;
import java.net.URL;
import java.sql.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import static javafx.application.Platform.exit;


public class Controller implements Initializable {

    private boolean logout = false;
    private String message ="";
    private int errorlogin, hit2, hit3 =0;

    private static List<DayOfWeek> workdays =  Arrays.asList(DayOfWeek.MONDAY, DayOfWeek.TUESDAY, DayOfWeek.WEDNESDAY, DayOfWeek.THURSDAY, DayOfWeek.FRIDAY);
    private static List<Integer> jammon = Arrays.asList(11, 12, 8, 9);

    DaoTB tbData = new DaoTB(DbConnection.intradana());
    DaoMKBD mkbdData = new DaoMKBD(DbConnection.intradana());
    DaoTC tcData = new DaoTC(DbConnection.intradana());
    DaoRisk risk = new DaoRisk();
    private PosisiAlert pa = new PosisiAlert();

    ScheduledExecutorService ses = Executors.newScheduledThreadPool(2);

    static long START_TIME = System.currentTimeMillis();

    @FXML    private TextField inpNama;
    @FXML    private PasswordField inpPassword;
    @FXML    private Button btnLogin;
    @FXML    private TabPane tabpane;
    @FXML    private Label lblUser;
    @FXML    private Text  a1, a2, a3, a4, a5, a8, a9,a10, a11,
            b1, b2, b3, b4, b5, b6, b8, b9, b10,
            c1, c2,c3, c4, c5, c6, c7, c8;

    @FXML    private RadioButton rb1, rb2;

    public Controller() throws SQLException, ClassNotFoundException {
    }

    @FXML
    private void log() throws FileNotFoundException {

        if (!logout) {
            String nama = inpNama.getText();
            String psw = inpPassword.getText();
            String un = null;
            String pss = null;
            if (nama.isEmpty() || psw.isEmpty()) {
                System.out.println("psw kosong");
                exit();
            } else {
                LoginUser loginUser = new LoginUser();
                if(loginUser.LoginUser(nama,psw)){tes2();}
                     else {
                       if(errorlogin<2){
                        inpNama.setText("");
                        inpPassword.setText("");
                        errorlogin ++;
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Error");
              //      alert.setHeaderText("Login Error.");
                    alert.setContentText("Periksa kembali koneksi internet, VPN,User Name dan Password.");
                    alert.showAndWait();
                    }else{
                           Stage st = (Stage) btnLogin.getScene().getWindow();
                           st.close();
                       }
                    }
                }

        } else {
            Stage st = (Stage) btnLogin.getScene().getWindow();
            st.close();
            Platform.exit();
            System.exit(0);
        }
     //   masuk();

    }

    @Override

    public void initialize(URL url, ResourceBundle rb) {
        tabpane.setVisible(false);
    }

    Runnable task1 = tradingmonitor();

    @FXML
    private void tes2() {
            masuk();
            posisiMKBD();
            posisikeuangan();
            ecm();
            cekrisk();
            message="";
        ses.scheduleAtFixedRate(eodmonRunable, 1, 30, TimeUnit.MINUTES);
      //  ses.scheduleAtFixedRate(task1, 1, 2, TimeUnit.MINUTES);

    }


    final Runnable eodmonRunable = () -> {
        try {
            if(workdays.contains(LocalDate.now().getDayOfWeek())/*&& jammon.contains( LocalTime.now().getHour())*/);
            {eodmonitoring();}
        } catch ( Throwable t ) {  // Catch Throwable rather than Exception (a subclass).
            System.out.println( "Caught exception in ScheduledExecutorService. StackTrace:\n" + t.getStackTrace() );
        }
    };

    final Runnable tradmonRunable = () -> {
        try {
            tradmon();
        } catch ( Throwable t ) {  // Catch Throwable rather than Exception (a subclass).
            System.out.println( "Caught exception in ScheduledExecutorService. StackTrace:\n" + t.getStackTrace() );
        }
    };
    private void tradingmon(){
        if(rb1.isSelected()){System.out.println("pakai run");}}

   @FXML Runnable tradingmonitor(){
        { return()->Mail.sendMail(message);}
    }

    private void tradmon(){if(rb1.isSelected()){System.out.println("Pakai tradmon runale cek rb2");}}

    private void eodmonitoring()  {

            this.risk = new DaoRisk();
            this.mkbdData = new DaoMKBD(DbConnection.intradana());
            this.tcData = new DaoTC(DbConnection.intradana());
        try {
            this.tbData = new DaoTB(DbConnection.intradana());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
            posisikeuangan();
            posisiMKBD();
            ecm();
            cekrisk();
         //  sendnotif();
        System.out.println("Task2 ok " + (LocalTime.now().toString()));
        if(!pa.getTglDaoMKBD().equals(mkbdData.getTanggal())){Mail.sendMail(message);};
        setPosisiAkhir();
        message="";
    }

@FXML    private void sendnotif(){

        if(message!=""){tradingmonitor();}
        setPosisiAkhir();
        message="";

      //  Mail.sendMail("Tes");
    }
    private void posisikeuangan(){

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

    private void posisiMKBD(){
        /*pasang di tab xml*/
        b1.setText(mkbdData.getNilaiMKBD());
        b2.setText(mkbdData.getBufferMKBD_s());
        b3.setText(mkbdData.getPiutangT3_s());
        b4.setText(mkbdData.getPiutangT4_s());
        b5.setText(mkbdData.getGagalSerah_s());
        b6.setText(mkbdData.getGagalTerima_s());
        b8.setText(mkbdData.getKonsentrasi_s());
        b10.setText(mkbdData.getTanggal().toString());
    }


    private void ecm(){

        /*pasang di tab xml*/
        c1.setText(tcData.getGain_s());
        c2.setText(tcData.getFee_s());
        c3.setText(tcData.getTanggal().toString());
        c4.setText(tcData.getGainmin1_s());
        c5.setText(tcData.getLossmin1_s());
        c6.setText(tcData.getPnlmin1_s());
    }

    private void setPosisiAkhir(){
        pa.setTglDaoTC(tcData.getTanggal());
        pa.setTglDaoMKBD(mkbdData.getTanggal());
        pa.setTglDaoTB(tbData.getTanggal());
        pa.setRoe(tbData.getRoe());
        pa.setRlECM(tcData.getGain());
        pa.setMkbdBuffer(mkbdData.getBufferMKBD());
        pa.setBopo(tbData.getBopo());
        pa.setT3(mkbdData.getPiutangT3());
        pa.setT4(mkbdData.getPiutangT4());
        pa.setGagalSerah(mkbdData.getGagalSerah());
        pa.setGagalTerima(mkbdData.getGagalTerima());
        pa.setPnlEcmMin1(tcData.getPnlmin1());
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
        lblUser.setText("Welcome Bp/Ibu, " + nama);
    }

private void cekrisk(){
    if(tbData.getRoe() < risk.getRoe2() && tbData.getRoe()>= risk.getRoe3()) { alert(a9); }
    else if (tbData.getRoe() < risk.getRoe3()){warning(a9, "\nROE Risk Indicator: "+pa.getRoe()+"=> "+tbData.getRoe());}

    if(mkbdData.getBufferMKBD() < risk.getMKBDbuffer2()  && mkbdData.getBufferMKBD() >= risk.getMKBDbuffer3())
    { alert(a9); } else if(mkbdData.getBufferMKBD() <  risk.getMKBDbuffer3())
    { warning(b2,"MKBD Buffer Indicator: "+pa.getMkbdBuffer()+" => "+mkbdData.getBufferMKBD()); }

    if(mkbdData.getGagalSerah()>0){warning(b5,"Gagal serah"+pa.getGagalSerah()+"=> "+mkbdData.getGagalSerah());}
    if(mkbdData.getGagalTerima()>0){warning(b6,"Gagal serah"+pa.getGagalTerima()+"=> "+mkbdData.getGagalTerima());}
    if(tcData.getPnlmin1()<0){warning(c6,"PNL ECM T-1:"+pa.getPnlEcmMin1()+"=> "+tcData.getPnlmin1());}
    }

private void alert(Text t){
        t.setFill(Color.ORANGERED);
        }

private void warning(Text t, String s){
        t.setFill(Color.RED);
         message += s;
    }
}


