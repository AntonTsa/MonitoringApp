package ua.study.dao;

import ua.study.entity.Emergency;

import java.sql.SQLException;
import java.util.List;

public interface EmergencyDAO {
    //Create
    void add(Emergency emergency) throws SQLException;
    //Read
    List<Emergency> getAll() throws SQLException;
    Emergency getById(Long id) throws SQLException;
    //Update
    void update(Emergency emergency) throws SQLException;
    //Delete
    void delete(Emergency emergency) throws SQLException;
}
