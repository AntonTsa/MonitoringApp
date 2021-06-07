package ua.study.dao;

import ua.study.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDAO {
    //Create
    void add(User user) throws SQLException;
    //Read
    List<User> getAll() throws SQLException;
    User getById(Long id) throws SQLException;
    //Update
    void update(User user) throws SQLException;
    //Delete
    void delete(User user) throws SQLException;
}
