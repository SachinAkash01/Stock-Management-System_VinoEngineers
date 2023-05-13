package DBLayer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DBConnection {

    private static final String url = "jdbc:mysql://localhost:3306/vinoengineersdb?autoReconnect=true&useSSL=false";
    private static final String uname = "root";
    private static final String password = "";
    static Connection con;

    public static Connection getConnection() throws ClassNotFoundException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(url, uname, password);
            System.out.println("Connection Success");
        } catch (SQLException ex) {
            System.out.println("Database connection error "+ ex.getMessage());
        }catch (ClassNotFoundException ex){
            System.out.println("Driver class error "+ ex.getMessage());
        }
        return con;
    }

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
