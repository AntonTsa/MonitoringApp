package ua.study.bl.users;

import ua.study.bl.service.adminService.UserFactory;
import ua.study.bl.service.adminService.UserFactoryImpl;
import ua.study.dao.UserDAO;
import ua.study.dao.UserDAOImpl;
import ua.study.entity.User;
import ua.study.entity.enums.Role;
import ua.study.util.Util;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

public class Admin extends User {
    private UserDAO userDAO;

    public void setUserDAO(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public Admin() {
        this.userDAO = new UserDAOImpl(new Util());
    }

    public User createUser(String fullName, String login, String password, Role role) {
        UserFactory userFactory = new UserFactoryImpl();
        User user = userFactory.createUser(role);

        user.setFullName(fullName);
        user.setLogin(login);
        user.setPassword(password);
        user.setRole(role);

        addUserToDB(user);
        return user;
    }

    private void addUserToDB(User user) {
        try {
            userDAO.add(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User updateUserFullName(Long id, String fullName) {
        User user = getUserById(id);
        Objects.requireNonNull(user).setFullName(fullName);
        updateUser(user);
        return user;
    }

    public User getUserById(Long id) {
        User user = null;
        try {
             user = userDAO.getById(id);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }

    private void updateUser(User user) {
        try {
            userDAO.update(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public User updateUserLogin(Long id, String login) {
        User user = getUserById(id);
        Objects.requireNonNull(user).setLogin(login);
        updateUser(user);
        return user;
    }

    public User updateUserPassword(Long id, String password) {
        User user = getUserById(id);
        Objects.requireNonNull(user).setPassword(password);
        updateUser(user);
        return user;
    }

    public User updateUserRole(Long id, Role role) {
        User user = getUserById(id);
        Objects.requireNonNull(user).setRole(role);
        updateUser(user);
        return user;
    }

    public void deleteUser(Long id) {
        try {
            User user = getUserById(id);
            userDAO.delete(Objects.requireNonNull(user));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<User> getAllUsers() {
        List<User> userList = null;
        try{
            userList = userDAO.getAll();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return userList;
    }
}
