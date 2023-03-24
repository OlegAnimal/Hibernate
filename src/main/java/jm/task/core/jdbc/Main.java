package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.sql.*;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        // реализуйте алгоритм здесь
        UserService service = new UserServiceImpl();

        service.createUsersTable();

        service.saveUser("Petya", "Petrov", (byte) 20);
        System.out.println("User с именем – Petya добавлен в базу данных");
        service.saveUser("Vasya", "Kukol", (byte) 66);
        System.out.println("User с именем – Vasya добавлен в базу данных");
        service.saveUser("Kolya", "Krylov", (byte) 10);
        System.out.println("User с именем – Kolya добавлен в базу данных");
        service.saveUser("Vera", "Nikitina", (byte) 33);
        System.out.println("User с именем – Vera добавлен в базу данных");

//        service.removeUserById(1L);
        List<User> list = service.getAllUsers();
        list.forEach(System.out::println);

        service.cleanUsersTable();
        service.dropUsersTable();

    }
}
