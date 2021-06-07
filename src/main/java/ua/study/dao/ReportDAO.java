package ua.study.dao;

import ua.study.entity.Report;

import java.sql.SQLException;
import java.util.List;

public interface ReportDAO {
    //Create
    void add(Report report) throws SQLException;
    //Read
    List<Report> getAll() throws SQLException;
    Report getById(Long id) throws SQLException;
    //Update
    void update(Report report) throws SQLException;
    //Delete
    void delete(Report report) throws SQLException;
}
