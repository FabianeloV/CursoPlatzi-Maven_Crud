package org.example.fabianelo.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataBaseConnection {
    public static final String databaseURL = "jdbc:mysql://localhost:3306/project";
    public static final String user = "fabianelo";
    public static final String password = "123456";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(databaseURL, user, password);
    }
}
