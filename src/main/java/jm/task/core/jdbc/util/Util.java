package jm.task.core.jdbc.util;


import org.hibernate.SessionFactory;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import jm.task.core.jdbc.model.User;
import org.hibernate.HibernateException;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;




public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/katatestdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123123";
    public static SessionFactory sessionFactory;

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено.");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к базе данных", e);
        }
    }
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try  {
                Configuration configuration = new Configuration();
                Properties settings = new Properties();

                settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
                settings.put(Environment.URL, "jdbc:mysql://localhost:3306/katatestdb");
                settings.put(Environment.USER, "root");
                settings.put(Environment.PASS, "root123123");
                settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQLDialect");
                settings.put(Environment.HBM2DDL_AUTO, "create-drop");
                settings.put(Environment.SHOW_SQL, "true");

                configuration.addAnnotatedClass(User.class);

                ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(settings).build();


                sessionFactory = configuration.buildSessionFactory(serviceRegistry);

            } catch (HibernateException e) {
                throw new RuntimeException("Ошибка подключения к БД", e);
            }
        }
        return sessionFactory;
    }
}
