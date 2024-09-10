package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    private final SessionFactory sessionFactory = Util.getSessionFactory();
    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {  session  = Util.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "CREATE TABLE IF NOT EXISTS Users " +
                "(id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY, " +
                "name VARCHAR(50) NOT NULL, lastName VARCHAR(50) NOT NULL, " +
                "age TINYINT NOT NULL)";
        Query query = session.createSQLQuery(sql);
        query.executeUpdate();
        transaction.commit();
        }
        catch (Exception e) {
            e.printStackTrace();
            System.err.println(e.getMessage());
        }
        finally {
            if (session != null) {
            session.close(); }
        }



    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS Users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица удалена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при удалении таблицы");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("User с именем " + name + " добавлен в базу данных.");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при добавлении пользователя таблицу");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            User user = (User) session.get(User.class, id);
            session.delete(user);
            transaction.commit();
            System.out.println("User с id " + id + "удален из базы данных");
        }
        catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при удалении пользователя из базы данных");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }

    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> users = null;
        session = Util.getSessionFactory().openSession();
        try {
            return  session.createCriteria(User.class).list();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при получении списка пользователей");
            return null;
        }
        finally {
            session.close();
        }
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.getSessionFactory().openSession();
            Transaction transaction = session.beginTransaction();
            String sql = "DELETE FROM Users";
            Query query = session.createSQLQuery(sql);
            query.executeUpdate();
            transaction.commit();
            System.out.println("Таблица очищена");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ошибка при очистке таблицы");
        }
        finally {
            if (session != null) {
                session.close();
            }
        }
    }
}
