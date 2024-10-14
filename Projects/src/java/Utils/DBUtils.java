package Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtils {
    public static final Connection getConnection() throws ClassNotFoundException, SQLException{
        Connection conn= null;
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String url= "jdbc:sqlserver://localhost:1433;databaseName=Office";
        conn= DriverManager.getConnection(url, "sa", "12345");
        return conn;
    }
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            if (conn != null) {
                System.out.println("Connected successfully!!!");
                conn.close();
            } else {
                System.out.println("Failed to connect!!!");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("Cannot found driver!!!");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Error!!!");
            e.printStackTrace();
        }
    }
}
