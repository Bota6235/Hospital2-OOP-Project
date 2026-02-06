package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static final String URL =
            "jdbc:postgresql://localhost:5432/hospital_db";
    private static final String USER = "postgres";
    private static final String PASSWORD = "1204";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void close(Connection con) {
        try {
            if (con != null) con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}