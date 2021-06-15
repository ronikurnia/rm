package sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Mysqlcon {
    public static Connection mysql(){
        Connection con2 = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con2 = DriverManager.getConnection("jdbc:mysql://172.25.96.37:3306/risk","root","");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
        return con2;
    }

}
