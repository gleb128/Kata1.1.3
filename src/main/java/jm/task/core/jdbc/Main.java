package jm.task.core.jdbc;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import jm.task.core.jdbc.util.Util;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;


public class Main {
    public static void main(String[] args) {
        SessionFactory sessionFactory = Util.getSessionFactory();
        Session session = null;

        try {
            session = sessionFactory.openSession();
            System.out.println("Подключение к базе данных установлено успешно!");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Не удалось подключиться к базе данных.");
        }
    }
}
