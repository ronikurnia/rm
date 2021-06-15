package dba;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {

    public static Connection intradana() {
        Connection con = null;

        try {

            DriverManager.registerDriver(new com.microsoft.sqlserver.jdbc.SQLServerDriver());
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            String url = "jdbc:sqlserver://172.24.16.92:1433;databaseName=RiskMonitoring;username=userrm;password=D4n4RM2021#";
            con = DriverManager.getConnection(url);

        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static Connection mysqlroni() throws ClassNotFoundException, SQLException {
        Connection kon = null;

            Class.forName("com.mysql.cj.jdbc.Driver");
            kon = DriverManager.getConnection("jdbc:mysql://172.25.96.215/risk", "java", "Password@mysql");

        return kon;
    }
}


/**
 * public static Connection ronisql(){
 * Connection con = null;
 * <p>
 * try {
 * Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 * String url = "jdbc:sqlserver://172.25.96.215:1433;databaseName=risk;username=java;password=R0n1kurn14@sql";
 * con = DriverManager.getConnection(url) ;
 * <p>
 * } catch (ClassNotFoundException | SQLException e) {
 * e.printStackTrace();
 * }
 * return con;
 * }
 **/