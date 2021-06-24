package ua.study.dao;

import ua.study.entity.Substance;

import java.sql.SQLException;
import java.util.List;

public interface SubstanceDAO {
    //Create
    void add(Substance substance) throws SQLException;
    //Read
    List<Substance> getAll() throws SQLException;
    List<Substance> getAllBySensorId(Long id) throws SQLException;
    Substance getById(Long id) throws SQLException;
    //Delete
    void delete(Substance substance) throws SQLException;
}
