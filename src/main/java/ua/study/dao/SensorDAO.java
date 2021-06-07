package ua.study.dao;

import ua.study.entity.Sensor;

import java.sql.SQLException;
import java.util.List;

public interface SensorDAO {
    //Create
    void add(Sensor sensor) throws SQLException;
    //Read
    List<Sensor> getAll() throws SQLException;
    Sensor getById(Long id) throws SQLException;
    //Update
    void update(Sensor sensor) throws SQLException;
    //Delete
    void delete(Sensor sensor) throws SQLException;
}
