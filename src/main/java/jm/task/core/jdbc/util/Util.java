package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.MySQL5Dialect;
import org.hibernate.service.ServiceRegistry;

import java.sql.*;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/firstdb";
    private static final String USER = "root";
    private static final String PASSWORD = "rootroot";
    private static SessionFactory sessionFactory;

    private Util() {

    }

    public static SessionFactory getSessionFactory() throws HibernateException {
        if (sessionFactory == null) {
            Properties properties = new Properties();
            properties.put(Environment.URL, URL);
            properties.put(Environment.USER, USER);
            properties.put(Environment.PASS, PASSWORD);
            properties.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

            sessionFactory = new Configuration().addProperties(properties)
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        }
        return sessionFactory;
    }

    public static Connection getConnectionJDBC() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

}
