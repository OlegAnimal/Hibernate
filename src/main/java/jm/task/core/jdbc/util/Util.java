package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String URL = "jdbc:mysql://localhost:3306/firstdb";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";

    private Util() {

    }

    public static Connection getConnectionJDBC() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
