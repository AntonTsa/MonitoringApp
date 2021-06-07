package ua.study.dao;

import ua.study.entity.Place;

import java.sql.SQLException;
import java.util.List;

public interface PlaceDAO {
    //Create
    void add(Place place) throws SQLException;
    //Read
    List<Place> getAll() throws SQLException;
    Place getById(Long id) throws SQLException;
    //Update
    void update(Place place) throws SQLException;
    //Delete
    void delete(Place place) throws SQLException;
}
