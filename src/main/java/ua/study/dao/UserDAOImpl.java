package ua.study.dao;

import ua.study.entity.User;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAOImpl implements UserDAO {

    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public UserDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(User user) throws SQLException {
        String sql = "INSERT INTO usr(usr_id, full_name, login, password) value(?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, user.getUserId());
            preparedStatement.setString(2, user.getFullName());
            preparedStatement.setString(3, user.getLogin());
            preparedStatement.setString(4, user.getPassword());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<User> getAll() throws SQLException {
        String sql = "SELECT usr_id, full_name, login, password FROM usr";

        List<User> userList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                User user = setUser();

                userList.add(user);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return userList;
    }

    @Override
    public User getById(Long id) throws SQLException {
        String sql = "SELECT usr_id, full_name, login, password FROM usr WHERE usr_id = ?";

        User user = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            user = setUser();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return user;
    }

    private User setUser() throws SQLException {
        User user = new User();

        user.setUserId(resultSet.getLong("usr_id"));
        user.setFullName(resultSet.getString("full_name"));
        user.setLogin(resultSet.getString("login"));
        user.setPassword(resultSet.getString("password"));

        return user;
    }

    @Override
    public void update(User user) throws SQLException {
        String sql = "UPDATE usr SET full_name=?, login=?, password=? WHERE usr_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, user.getFullName());
            preparedStatement.setString(2, user.getLogin());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setLong(4, user.getUserId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(User user) throws SQLException {
        String sql = "DELETE FROM usr WHERE usr_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, user.getUserId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
