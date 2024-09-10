package jm.task.core.jdbc;
import jm.task.core.jdbc.dao.UserDaoHibernateImpl;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.Util;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import java.util.List;


public class Main {
    public static void main(String[] args) {
        UserDaoHibernateImpl userDao = new UserDaoHibernateImpl();
        userDao.createUsersTable();
        userDao.saveUser("Ivan", "Ivanov", (byte) 23);
        userDao.saveUser("Alexey", "Voron", (byte) 31);
        userDao.saveUser("xdd", "dxx", (byte) 28);
        userDao.saveUser("Dmitry", "Komarov", (byte) 35);
        List<User> ds = userDao.getAllUsers();
        System.out.println(ds);
        userDao.cleanUsersTable();
        userDao.dropUsersTable();
    }
}
