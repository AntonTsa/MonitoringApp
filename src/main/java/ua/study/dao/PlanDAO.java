package ua.study.dao;

import ua.study.entity.Plan;

import java.sql.SQLException;
import java.util.List;

public interface PlanDAO {
    //Create
    void add(Plan plan) throws SQLException;
    //Read
    List<Plan> getAll() throws SQLException;
    Plan getById(Long id) throws SQLException;
    //Update
    void update(Plan plan) throws SQLException;
    //Delete
    void delete(Plan plan) throws SQLException;
}
