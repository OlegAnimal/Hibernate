package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() {
        try (Connection connection = Util.getConnectionJDBC()) {
            Statement stm = connection.createStatement();
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, PRIMARY KEY ( id ))";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void dropUsersTable() {
        try (Connection connection = Util.getConnectionJDBC()) {
            Statement stm = connection.createStatement();
            String sql = "DROP TABLE IF EXISTS USERS";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void saveUser(String name, String lastName, byte age) {
        try (Connection connection = Util.getConnectionJDBC()) {
            String sql = "INSERT INTO USERS (name, lastName, age) VALUES (?, ?, ?)";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, name);
            ps.setString(2, lastName);
            ps.setInt(3, age);
            ps.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void removeUserById(long id) {
        try (Connection connection = Util.getConnectionJDBC()) {
            String sql = "DELETE FROM USERS WHERE id = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setLong(1, id);
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public List<User> getAllUsers() {
        List<User> listUser = new ArrayList<>();
        try (Connection connection = Util.getConnectionJDBC()) {
            String sql = "SELECT * FROM USERS";
            PreparedStatement ps = connection.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();

            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String name = resultSet.getString("name");
                String lastName = resultSet.getString("lastname");
                Byte age = resultSet.getByte("age");

                User user = new User(name, lastName, age);
                user.setId(id);

                listUser.add(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return listUser;
    }

    public void cleanUsersTable() {
        try (Connection connection = Util.getConnectionJDBC()) {
            Statement stm = connection.createStatement();
            String sql = "TRUNCATE TABLE USERS";
            stm.executeUpdate(sql);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }
}
