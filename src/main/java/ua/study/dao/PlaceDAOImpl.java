package ua.study.dao;

import ua.study.entity.Place;
import ua.study.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PlaceDAOImpl implements PlaceDAO {
    private final Util util;
    private Connection connection;
    private PreparedStatement preparedStatement;
    private Statement statement;
    private ResultSet resultSet;

    public PlaceDAOImpl(Util util) {
        this.util = util;
    }

    @Override
    public void add(Place place) throws SQLException {
        String sql = "INSERT INTO place(place_id, region, district, object) value(?, ?, ?, ?)";
        try{
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, place.getRegion());
            preparedStatement.setString(2, place.getDistrict());
            preparedStatement.setString(3, place.getObject());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<Place> getAll() throws SQLException {
        String sql = "SELECT place_id, region, district, object FROM place";

        List<Place> placeList = new ArrayList<>();

        try {
            connection = util.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                Place place = setPlace();

                placeList.add(place);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            statement.close();
            connection.close();
        }

        return placeList;
    }

    private Place setPlace() throws SQLException {
        Place place = new Place();

        place.setPlaceId(resultSet.getLong("place_id"));
        place.setRegion(resultSet.getString("region"));
        place.setDistrict(resultSet.getString("district"));
        place.setObject(resultSet.getString("object"));

        return place;
    }

    @Override
    public Place getById(Long id) throws SQLException {
        String sql = "SELECT place_id, region, district, object FROM place WHERE place_id = ?";

        Place place = null;

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, id);

            resultSet = preparedStatement.executeQuery();

            place = setPlace();

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            resultSet.close();
            preparedStatement.close();
            connection.close();
        }
        return place;
    }

    @Override
    public void update(Place place) throws SQLException {
        String sql = "UPDATE place SET region=?, district=?, object=? WHERE place_id=?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, place.getRegion());
            preparedStatement.setString(2, place.getDistrict());
            preparedStatement.setString(3, place.getObject());
            preparedStatement.setLong(4, place.getPlaceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public void delete(Place place) throws SQLException {
        String sql = "DELETE FROM place WHERE place_id = ?";

        try {
            connection = util.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setLong(1, place.getPlaceId());

            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }
}
