package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        userService.createUsersTable();
        userService.saveUser("Ivan", "Ivanov", (byte) 23);
        userService.saveUser("Alexey", "Voron", (byte) 31);
        userService.saveUser("xdd", "dxx", (byte) 28);
        userService.saveUser("Dmitry", "Komarov", (byte) 35);
        List<User> ds = userService.getAllUsers();
        System.out.println(ds);
        userService.cleanUsersTable();
        userService.dropUsersTable();
    }
}
