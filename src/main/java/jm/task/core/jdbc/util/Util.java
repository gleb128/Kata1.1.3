package jm.task.core.jdbc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/katatestdb";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root123123";

    public static Connection getConnection() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Соединение установлено.");
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при подключении к базе данных", e);
        }
    }
}
