package jm.task.core.jdbc.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.*;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/firstdb";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";

    private Util() {

    }


    public static Connection getConnectionJDBC() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
