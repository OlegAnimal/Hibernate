package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS USERS " +
                    "(id INTEGER NOT NULL AUTO_INCREMENT, " +
                    " name VARCHAR(255), " +
                    " lastname VARCHAR(255), " +
                    " age INTEGER, PRIMARY KEY ( id ))";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void dropUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS USERS";
            session.createSQLQuery(sql).executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
//            User usr = new User(name, lastName, age);
            session.save(new User(name, lastName, age));
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }

    }

    @Override
    public void removeUserById(long id) {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            User usr = session.get(User.class, id);
            session.delete(usr);
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public List<User> getAllUsers() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            List<User> usr = session.createQuery("from USERS", User.class).getResultList();
            session.getTransaction().commit();
            return usr;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public void cleanUsersTable() {
        try (Session session = Util.getSessionFactory().getCurrentSession()) {
            session.beginTransaction();
            session.createQuery("DELETE from USERS").executeUpdate();
            session.getTransaction().commit();
        } catch (Exception e) {
            throw e;
        }

    }
}
