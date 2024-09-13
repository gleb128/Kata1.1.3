package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void createUsersTable() {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS Users (" + "id BIGINT PRIMARY KEY AUTO_INCREMENT, " + "name VARCHAR(50) NOT NULL, " + "lastName VARCHAR(50) NOT NULL, " + "age TINYINT NOT NULL)";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(createTableSQL);
            System.out.println("Таблица создана или уже существует.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при создании таблицы", e);
        }
    }

    public void dropUsersTable() {
        String dropTableSQL = "DROP TABLE IF EXISTS Users";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(dropTableSQL);
            System.out.println("Таблица удалена.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении таблицы", e);
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        String insertSQL = "INSERT INTO Users (name, lastName, age) VALUES (?, ?, ?)";

        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {

            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);

            preparedStatement.executeUpdate();
            System.out.println("User с именем " + name + " добавлен в базу данных.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при добавлении пользователя", e);
        }
    }

    public void removeUserById(long id) {
        String deleteSQL = "DELETE FROM Users WHERE id = ?";

        try (Connection connection = Util.getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(deleteSQL)) {

            preparedStatement.setLong(1, id);
            preparedStatement.executeUpdate();
            System.out.println("User с id " + id + " удалён из базы данных.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при удалении пользователя", e);
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = new ArrayList<>();

        try (Connection connection = Util.getConnection(); PreparedStatement statement = connection.prepareStatement("SELECT * FROM Users")) {

            ResultSet resultSet = statement.executeQuery();

            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName(resultSet.getString("name"));
                user.setLastName(resultSet.getString("lastName"));
                user.setAge(resultSet.getByte("age"));

                userList.add(user);
            }

            resultSet.close();
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при получении всех пользователей", e);
        }


        return userList;

    }

    public void cleanUsersTable() {
        String cleanSQL = "DELETE FROM Users";

        try (Connection connection = Util.getConnection(); Statement statement = connection.createStatement()) {
            statement.execute(cleanSQL);
            System.out.println("Таблица очищена.");
        } catch (SQLException e) {
            throw new RuntimeException("Ошибка при очистке таблицы", e);
        }
    }
}
