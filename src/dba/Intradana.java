package dba;


import data.MKBD;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Intradana {


    public HashMap<String, Float> Tb() { //List<TrialBalance>
        ResultSet rs = null;
        HashMap<String, Float> trialBalanceList = new HashMap<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://172.24.16.92:1433;databaseName=RiskMonitoring;username=userrm;password=D4n4RM2021#";
            Connection con = DriverManager.getConnection(url);
            PreparedStatement pst = null;
            pst = con.prepareStatement("Select AccountID, AccountName, CurrentBalance from TB order by AccountID");
            rs = pst.executeQuery();
            while (rs.next()) {
//                System.out.println(rs.getString("AccountID")+" , "+rs.getString("AccountName")+" ,"+rs.getFloat("CurrentBalance"));
                trialBalanceList.put(rs.getString(1), rs.getFloat(3));
            }
            rs.close();
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return trialBalanceList;
    }


    public List<MKBD> mkbdsql() {
        ResultSet rs = null;
        List<MKBD> mkbds = new ArrayList<>();
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://172.24.16.92:1433;databaseName=RiskMonitoring;username=userrm;password=D4n4RM2021#";
            Connection con = DriverManager.getConnection(url);
            PreparedStatement pst = null;
            pst = con.prepareStatement("Select * MKBD");
            rs = pst.executeQuery();
            while (rs.next()) {
                 mkbds.add(new MKBD(rs.getString(1),rs.getString(2),rs.getString(3)
                ,rs.getString(4),rs.getString(5), rs.getFloat(6), rs.getFloat(7)
                ,rs.getFloat(8),rs.getFloat(9), rs.getFloat(10)));
            }
            rs.close();
            con.close();
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return mkbds ;
    }

}


