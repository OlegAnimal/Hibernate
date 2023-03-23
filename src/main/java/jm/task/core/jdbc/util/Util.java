package jm.task.core.jdbc.util;

import java.sql.*;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/firstdb";
    private static final String user = "root";
    private static final String password = "rootroot";

    public Util() {

    }

    public Connection getConnectionJDBC() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

}
