package ua.study.dao;

import ua.study.entity.NormalSubstance;

import java.sql.SQLException;
import java.util.List;

public interface NormalSubstanceDAO {
    //Create
    void add(NormalSubstance normalSubstance) throws SQLException;
    //Read
    List<NormalSubstance> getAll() throws SQLException;
    NormalSubstance getById(Long id) throws SQLException;
    //Update
    void update(NormalSubstance normalSubstance) throws SQLException;
    //Delete
    void delete(NormalSubstance normalSubstance) throws SQLException;
}
